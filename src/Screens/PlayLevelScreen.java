package Screens;

import Enemies.DinosaurEnemy;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Players.Joe;
import Utils.Direction;
import Utils.Point;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
//import Players.Weapons;

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

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    public void initialize() {
        // define/setup map
        this.map = new TestMap();

        // setup player
        this.player = new Joe(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        this.player.setMap(map);
        this.player.addListener(this);

        levelClearedScreen = new LevelClearedScreen();
        levelLoseScreen = new LevelLoseScreen(this);

        this.playLevelScreenState = PlayLevelScreenState.RUNNING;

        Point[] spawnLocations = {
                new Point(450, 530),
                new Point(600, 530),
                // new Point(800, 435),

        };

        // Loop that creates and adds multiple enemies to the map
        for (Point location : spawnLocations) {
            DinosaurEnemy dinosaur = new DinosaurEnemy(location, new Point(location.x + 150, location.y),
                    Direction.RIGHT);
            map.addEnemy(dinosaur);
        }

        hp3Image = ImageLoader.load("ThreeHearts.png");
        hp2Image = ImageLoader.load("TwoHearts.png");
        hp1Image = ImageLoader.load("OneHeart.png");
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the
            // platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
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
            // wait on level lose screen to make a decision (either resets level or sends
            // player back to main menu)
            case LEVEL_LOSE:
                levelLoseScreen.update();
                break;
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(graphicsHandler);
                player.draw(graphicsHandler);

                if (isWeaponPickedUp) {
                    int overlayX = 24;
                    int overlayY = 550;
                    graphicsHandler.drawImage(ImageLoader.load("NewShotty.png"), overlayX, overlayY);
                }

                // Call drawHitpoints to render the player's health
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

            if (originalWidth > 0 && originalHeight > 0) { // Ensure dimensions are valid

                BufferedImage bufferedImage = new BufferedImage(originalWidth, originalHeight,
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D bGr = bufferedImage.createGraphics();

                if (bGr != null) { // Check if Graphics2D is successfully created
                    bGr.drawImage(hitpointImage, 0, 0, null);
                    bGr.dispose();

                    // Remove the background from the image
                    int blackRGB = 0xFF000000;
                    for (int y = 0; y < originalHeight; y++) {
                        for (int x = 0; x < originalWidth; x++) {
                            if (bufferedImage.getRGB(x, y) == blackRGB) {
                                bufferedImage.setRGB(x, y, 0x00FFFFFF); // set to transparent
                            }
                        }
                    }

                    int scaledWidth = (int) (originalWidth * 0.25);
                    int scaledHeight = (int) (originalHeight * 0.25);

                    // Draw the image
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

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, LEVEL_LOSE
    }
}