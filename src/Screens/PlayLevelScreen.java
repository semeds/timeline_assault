package Screens;

import Enemies.DinosaurEnemy;
import Enemies.Fireball;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Enemy;
import Level.Map;
import Level.MapEntity;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Players.Joe;
import Players.ArmedJoe;
import Utils.Direction;
import Utils.Point;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import NPCs.WeaponPickup;
import Engine.WeaponOverlay;
import java.awt.Color;
import java.awt.Font;
import Engine.Key;
import Engine.Keyboard;

public class PlayLevelScreen extends Screen implements PlayerListener {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected int screenTimer;
    protected LevelClearedScreen levelClearedScreen;
    protected LevelLoseScreen levelLoseScreen;
    protected boolean levelCompletedStateChangeStart;

    private Image hp3Image;
    private Image hp2Image;
    private Image hp1Image;
    private boolean isWeaponPickedUp = false;
    private WeaponOverlay weaponOverlay;
    private int currentAmmo = 15; // Tracks current bullets
    private static final int MAX_AMMO = 15; // Maximum ammo in a magazine
    private boolean canShoot = true; // Flag to prevent multiple shots per SPACE press
    private boolean reloading = false; // Flag to indicate if reload is in progress
    private int reloadTimer = 0; // Timer for reload delay
    private static final int RELOAD_DELAY = 60; // Reload delay in frames (approx. 1 second)

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        weaponOverlay = new WeaponOverlay();
    }

    public void initialize() {
        resetWeaponStatus();
        this.map = new TestMap();

        // Start the player as normal Joe
        this.player = new Joe(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;

        Point[] spawnLocations = {
                // new Point(450, 530),
                // new Point(600, 530),
        };

        for (Point location : spawnLocations) {
            DinosaurEnemy dinosaur = new DinosaurEnemy(location, new Point(location.x +
                    150, location.y),
                    Direction.RIGHT);
            map.addEnemy(dinosaur);
        }

        hp3Image = ImageLoader.load("ThreeHearts.png");
        hp2Image = ImageLoader.load("TwoHearts.png");
        hp1Image = ImageLoader.load("OneHeart.png");
    }

    private boolean playerCollidesWith(Enemy enemy) {
        return player.getBounds().intersects(enemy.getBounds());
    }

    public void update() {
        switch (playLevelScreenState) {
            case RUNNING:
                if (!isWeaponPickedUp && WeaponPickup.weaponPickedUp) {
                    switchToArmedJoe();
                    isWeaponPickedUp = true;
                }

                player.update();
                map.update(player);

                if (reloading) {
                    // Increment the reload timer while reloading
                    reloadTimer++;
                    if (reloadTimer >= RELOAD_DELAY) {
                        finishReload(); // Complete the reload after delay
                    }
                } else {
                    // Shoot when SPACE is pressed and ammo is available
                    if (isWeaponPickedUp && Keyboard.isKeyDown(Key.SPACE) && canShoot && currentAmmo > 0) {
                        currentAmmo--; // Reduce ammo by 1
                        canShoot = false; // Prevents holding SPACE for multiple shots
                    }

                    // Reset canShoot when SPACE is released
                    if (!Keyboard.isKeyDown(Key.SPACE)) {
                        canShoot = true;
                    }

                    // Initiate reload if "R" is pressed
                    if (isWeaponPickedUp && Keyboard.isKeyDown(Key.R)) {
                        startReload();
                    }
                }

                for (Enemy enemy : map.getActiveEnemies()) {
                    enemy.update(player);
                    if (playerCollidesWith(enemy)) {
                        player.hurtPlayer(enemy);
                        enemy.touchedPlayer(player);
                    }
                    for (int i = map.getProjectiles().size() - 1; i >= 0; i--) {
                        MapEntity projectile = map.getProjectiles().get(i);
                        if (projectile instanceof Fireball && projectile.getBounds().intersects(enemy.getBounds())) {
                            ((Fireball) projectile).touchedEntity(enemy);
                            map.removeProjectile(projectile);
                        }
                    }
                }

                if (WeaponPickup.showOverlay) {
                    isWeaponPickedUp = true;
                }

                break;

            case LEVEL_COMPLETED:
                if (levelCompletedStateChangeStart) {
                    screenTimer = 130;
                    levelCompletedStateChangeStart = false;
                } else {
                    levelClearedScreen.update();
                    screenTimer--;
                    if (screenTimer == 0) {
                        goBackToMenu();
                    }
                }
                break;

            case LEVEL_LOSE:
                levelLoseScreen.update();
                resetWeaponStatus();
                break;
        }
    }

    private void switchToArmedJoe() {
        float currentX = player.getX();
        float currentY = player.getY();

        ArmedJoe armedJoe = new ArmedJoe(currentX, currentY);
        armedJoe.setMap(map);
        armedJoe.addListener(this);

        player = armedJoe;
        armedJoe.update();
    }

    private void startReload() {
        reloading = true;
        reloadTimer = 0; // Start the reload timer
    }

    private void finishReload() {
        reloading = false;
        currentAmmo = MAX_AMMO; // Reset ammo to maximum
    }

    public void draw(GraphicsHandler graphicsHandler) {
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);

                if (isWeaponPickedUp) {
                    weaponOverlay.draw(graphicsHandler.getGraphics());
                    drawAmmoCount(graphicsHandler); // Draw ammo count next to overlay
                }

                drawHitpoints(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

    private void drawAmmoCount(GraphicsHandler graphicsHandler) {
        Graphics2D g2d = (Graphics2D) graphicsHandler.getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        // Position the text slightly to the right of the weapon overlay
        int ammoX = weaponOverlay.getX() + weaponOverlay.getWidth() + 10;
        int ammoY = weaponOverlay.getY() + weaponOverlay.getHeight() / 2;

        // Draw the ammo count as "currentAmmo/MAX_AMMO"
        g2d.drawString(currentAmmo + "/" + MAX_AMMO, ammoX, ammoY);
    }

    private void drawHitpoints(GraphicsHandler graphicsHandler) {
        Image hitpointImage;
        switch (player.getHitPoints()) {
            case 3:
                hitpointImage = hp3Image;
                break;
            case 2:
                hitpointImage = hp2Image;
                break;
            case 1:
                hitpointImage = hp1Image;
                break;
            default:
                hitpointImage = null;
                break;
        }

        if (hitpointImage != null) {
            int originalWidth = hitpointImage.getWidth(null);
            int originalHeight = hitpointImage.getHeight(null);

            if (originalWidth > 0 && originalHeight > 0) {
                BufferedImage bufferedImage = new BufferedImage(originalWidth, originalHeight,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D bGr = bufferedImage.createGraphics();

                if (bGr != null) {
                    bGr.drawImage(hitpointImage, 0, 0, null);
                    bGr.dispose();

                    int blackRGB = 0xFF000000;
                    for (int y = 0; y < originalHeight; y++) {
                        for (int x = 0; x < originalWidth; x++) {
                            if (bufferedImage.getRGB(x, y) == blackRGB) {
                                bufferedImage.setRGB(x, y, 0x00FFFFFF);
                            }
                        }
                    }

                    int scaledWidth = (int) (originalWidth * 0.25);
                    int scaledHeight = (int) (originalHeight * 0.25);

                    graphicsHandler.drawImage(bufferedImage, 700, 12, scaledWidth, scaledHeight);
                }
            }
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    @Override
    public void onLevelCompleted() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_COMPLETED) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
            levelCompletedStateChangeStart = true;
        }
    }

    @Override
    public void onDeath() {
        if (playLevelScreenState != PlayLevelScreenState.LEVEL_LOSE) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE;
        }
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }

    private void resetWeaponStatus() {
        WeaponPickup.showOverlay = false;
        WeaponPickup.weaponPickedUp = false;
        isWeaponPickedUp = false;
        currentAmmo = MAX_AMMO; // Reset ammo when level resets
        reloading = false; // Reset reload status
        reloadTimer = 0; // Reset timer
    }
}
