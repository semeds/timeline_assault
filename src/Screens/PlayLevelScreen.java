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
import NPCs.APistolPickup;
import NPCs.AAsaultRiflePickup;
import NPCs.AShotgunPickup;
import Engine.APistolOverlay;
import Engine.AAsaultrifleOverlay;
import Engine.AShotgunOverlay;
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
    private Image coinImage;

    private boolean isWeaponPickedUp = false;
    private boolean isAssaultRiflePickedUp = false; // Assault rifle flag
    private boolean isShotgunPickedUp = false; // Shotgun flag
    private APistolOverlay apistolOverlay;
    private AAsaultrifleOverlay aassaultRifleOverlay; // Assault rifle overlay
    private AShotgunOverlay ashotgunOverlay; // Shotgun overlay
    private int coinCount = 0;
    public static int currentAmmo = 12; // Current bullets for pistol
    public static final int MAX_AMMO = 12; // Ammo in a pistol magazine
    public static int assaultRifleAmmo = 30; // Assault rifle current bullets
    public static final int ASSAULT_RIFLE_MAX_AMMO = 30; // Assault rifle max ammo
    public static int shotgunAmmo = 8; // Shotgun current bullets
    public static final int SHOTGUN_MAX_AMMO = 8; // Shotgun max ammo
    private boolean canShoot = true; // Flag to prevent multiple shots per SPACE press
    public static boolean reloading = false; // Flag to indicate if reload is in progress
    private int reloadTimer = 0; // Reload delay
    private static final int RELOAD_DELAY = 60; // Reload delay in frames

    // NEW variables for assault rifle and shotgun cooldowns
    private int fireCooldownTimer = 0; // Timer to control firing rate for assault rifle
    private static final int FIRE_COOLDOWN_DELAY = 10; // Cooldown delay for assault rifle firing rate
    private int shotgunCooldownTimer = 0; // Timer to control firing rate for shotgun
    private static final int SHOTGUN_COOLDOWN_DELAY = 60; // 1-second delay for shotgun firing rate

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        apistolOverlay = new APistolOverlay();
        aassaultRifleOverlay = new AAsaultrifleOverlay(); // Initialize assault rifle overlay
        ashotgunOverlay = new AShotgunOverlay(); // Initialize shotgun overlay
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
                if (!isWeaponPickedUp && APistolPickup.weaponPickedUp) {
                    switchToArmedJoe();
                    isWeaponPickedUp = true;
                }

                if (!isAssaultRiflePickedUp && AAsaultRiflePickup.weaponPickedUp) { // Check assault rifle pickup
                    switchToArmedJoe();
                    isAssaultRiflePickedUp = true;
                }

                if (!isShotgunPickedUp && AShotgunPickup.weaponPickedUp) { // Check shotgun pickup
                    switchToArmedJoe();
                    isShotgunPickedUp = true;
                }

                player.update();
                map.update(player);

                if (reloading) {
                    reloadTimer++;
                    if (reloadTimer >= RELOAD_DELAY) {
                        finishReload();
                    }
                } else {
                    // Increment cooldown timers
                    fireCooldownTimer++;
                    shotgunCooldownTimer++;

                    // Shoot when SPACE is pressed / if ammo is available
                    if (isWeaponPickedUp && Keyboard.isKeyDown(Key.SPACE) && canShoot && currentAmmo > 0) {
                        currentAmmo--; // Reduce ammo by 1
                        canShoot = false; // Prevents holding SPACE for shots
                    } else if (isAssaultRiflePickedUp && Keyboard.isKeyDown(Key.SPACE) && assaultRifleAmmo > 0 
                            && fireCooldownTimer >= FIRE_COOLDOWN_DELAY) { // Automatic fire for assault rifle with cooldown
                        assaultRifleAmmo--; // Decrement assault rifle ammo
                        fireCooldownTimer = 0; // Reset cooldown timer
                    } else if (isShotgunPickedUp && Keyboard.isKeyDown(Key.SPACE) && shotgunAmmo > 0 
                            && shotgunCooldownTimer >= SHOTGUN_COOLDOWN_DELAY) { // Shotgun fire with cooldown
                        shotgunAmmo--; // Decrement shotgun ammo
                        shotgunCooldownTimer = 0; // Reset shotgun cooldown timer
                    }

                    // Reset canShoot when SPACE is released
                    if (!Keyboard.isKeyDown(Key.SPACE)) {
                        canShoot = true;
                    }

                    // Initiate reload if "R" is pressed for any weapon
                    if ((isWeaponPickedUp && Keyboard.isKeyDown(Key.R)) || 
                        (isAssaultRiflePickedUp && Keyboard.isKeyDown(Key.R)) || 
                        (isShotgunPickedUp && Keyboard.isKeyDown(Key.R))) {
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

                if (APistolPickup.showOverlay) {
                    isWeaponPickedUp = true;
                }

                if (AAsaultRiflePickup.showOverlay) { // Show assault rifle overlay if picked up
                    isAssaultRiflePickedUp = true;
                }

                if (AShotgunPickup.showOverlay) { // Show shotgun overlay if picked up
                    isShotgunPickedUp = true;
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

    public static void finishReload() {
        reloading = false;
        if (AAsaultRiflePickup.weaponPickedUp) { // Check for assault rifle reload
            assaultRifleAmmo = ASSAULT_RIFLE_MAX_AMMO;
        } else if (APistolPickup.weaponPickedUp) { // Pistol reload
            currentAmmo = MAX_AMMO;
        } else if (AShotgunPickup.weaponPickedUp) { // Shotgun reload
            shotgunAmmo = SHOTGUN_MAX_AMMO;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);

                if (isWeaponPickedUp) {
                    apistolOverlay.draw(graphicsHandler.getGraphics());
                    drawAmmoCount(graphicsHandler, currentAmmo, MAX_AMMO); // Draw pistol ammo
                }

                if (isAssaultRiflePickedUp) { // Draw assault rifle overlay and ammo count
                    aassaultRifleOverlay.draw(graphicsHandler.getGraphics());
                    drawAmmoCount(graphicsHandler, assaultRifleAmmo, ASSAULT_RIFLE_MAX_AMMO);
                }

                if (isShotgunPickedUp) { // Draw shotgun overlay and ammo count
                    ashotgunOverlay.draw(graphicsHandler.getGraphics());
                    drawAmmoCount(graphicsHandler, shotgunAmmo, SHOTGUN_MAX_AMMO);
                }

                drawHitpoints(graphicsHandler);
                drawCoinForCount(graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                levelClearedScreen.draw(graphicsHandler);
                break;
            case LEVEL_LOSE:
                levelLoseScreen.draw(graphicsHandler);
                break;
        }
    }

    private void drawAmmoCount(GraphicsHandler graphicsHandler, int currentAmmo, int maxAmmo) {
        Graphics2D g2d = (Graphics2D) graphicsHandler.getGraphics();
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));

        int ammoX = 0;
        int ammoY = 0;
        if (isAssaultRiflePickedUp) {
            ammoX = aassaultRifleOverlay.getX() + 72;
            ammoY = aassaultRifleOverlay.getY() + 25;
        } else if (isShotgunPickedUp) {
            ammoX = ashotgunOverlay.getX() + 72;
            ammoY = ashotgunOverlay.getY() + 25;
        } else if (isWeaponPickedUp) {
            ammoX = apistolOverlay.getX() + 72;
            ammoY = apistolOverlay.getY() + 25;
        }

        g2d.drawString(currentAmmo + "/" + maxAmmo, ammoX, ammoY);
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

    private void drawCoinForCount(GraphicsHandler graphicsHandler) {
        Image coinpicture = coinImage;
      
        if (coinpicture != null) {
            int originalWidth = coinpicture.getWidth(null);
            int originalHeight = coinpicture.getHeight(null);

            if (originalWidth > 0 && originalHeight > 0) {
                BufferedImage bufferedImage = new BufferedImage(originalWidth, originalHeight,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D bGr = bufferedImage.createGraphics();

                if (bGr != null) {
                    bGr.drawImage(coinImage, 0, 0, null);
                    bGr.dispose();

                    int whiteRGB = 0xFFFFFFFF;
                    for (int y = 0; y < originalHeight; y++) {
                        for (int x = 0; x < originalWidth; x++) {
                            if (bufferedImage.getRGB(x, y) == whiteRGB) {
                                bufferedImage.setRGB(x, y, 0x00FFFFFF);
                            }
                        }
                    }
                    int scaledWidth = (int) (originalWidth * 0.075);
                    int scaledHeight = (int) (originalHeight * 0.075);

                    graphicsHandler.drawImage(bufferedImage, 710, 47, scaledWidth, scaledHeight);
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
        APistolPickup.showOverlay = false;
        AAsaultRiflePickup.showOverlay = false;
        AShotgunPickup.showOverlay = false;
        APistolPickup.weaponPickedUp = false;
        AAsaultRiflePickup.weaponPickedUp = false;
        AShotgunPickup.weaponPickedUp = false;
        isWeaponPickedUp = false;
        isAssaultRiflePickedUp = false;
        isShotgunPickedUp = false;
        currentAmmo = MAX_AMMO;
        assaultRifleAmmo = ASSAULT_RIFLE_MAX_AMMO;
        shotgunAmmo = SHOTGUN_MAX_AMMO;
        reloading = false;
        reloadTimer = 0;
    }
}
