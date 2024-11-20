package Screens;


import Enemies.ModernBullets;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Enemy;
import Level.Map;
import Level.MapEntity;
import Level.Player;
import Level.PlayerListener;
import Players.Joe;
import Players.ArmedJoe;
import Utils.Direction;
import Utils.Point;
import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import NPCs.MAssaultRiflePickup;
import NPCs.MPistolPickup;
import NPCs.MShotgunPickup;
import Engine.MPistolOverlay;
import Engine.MShotgunOverlay;
import Engine.MAssaultRifleOverlay;
import java.awt.Color;
import java.awt.Font;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Maps.*;


public class WorldTwoScreen extends Screen implements PlayerListener {
   protected ScreenCoordinator screenCoordinator;
   protected Map map;
   protected Player player;
   protected PlayLevelScreenState playLevelScreenState;
   protected int screenTimer;
   protected LevelClearedScreen levelClearedScreen;
   protected PauseScreen pauseScreen;
   protected LevelLoseScreen levelLoseScreen;
   protected boolean levelCompletedStateChangeStart;


   private Image hp3Image;
   private Image hp2Image;
   private Image hp1Image;
   private Image coinImage;

   //pause screen logic
   private KeyLocker keyLocker = new KeyLocker();
   private final Key pauseKey = Key.P;
   private boolean isGamePaused = false;
   private ScreenManager screenManager;
   protected int currentMenuItemHovered = 0;
   protected int menuItemSelected = -1;
   protected int keyPressTimer;
   protected int pointerLocationX, pointerLocationY;
   protected boolean screenPausedStateChangeStart;


   private boolean isMPistolickedUp = false;
   private boolean isMAssaultRiflePickedUp = false; 
   private boolean isMShotgunPickedUp = false; 

   private boolean showMPistolOverlay = false;
   private boolean showMAssaultRifleOverlay = false;
   private boolean showMShotgunOverlay = false;

   private MPistolOverlay mPistolOverlay;
   private MShotgunOverlay mShotgunOverlay;
   private MAssaultRifleOverlay mAssaultRifleOverlay;

   private int coinCount = 0;
   public static int mpistolAmmo = 12;
   private static final int MPISTOL_MAX_AMMO = 12;
   private static int massaultRifleAmmo = 30;
   private static final int MASSAULT_RIFLE_MAX_AMMO = 30;
   private static int mshotgunAmmo = 8;
    private static final int MSHOTGUN_MAX_AMMO = 8;
    private boolean canShoot = true; // Flag to prevent multiple shots per SPACE press
    public static boolean reloading = false; // Flag to indicate if reload is in progress
    public static int aassaultRifleAmmo;
    public static int apistolAmmo;
    public static int ashotgunAmmo;
    private int reloadTimer = 0; // Reload delay
    private static final int RELOAD_DELAY = 60; // Reload delay in frames
                     
                     
    // NEW variables for assault rifle and shotgun cooldowns
    private int fireCooldownTimer = 0; // Timer to control firing rate for assault rifle
    private static final int FIRE_COOLDOWN_DELAY = 10; // Cooldown delay for assault rifle firing rate
    private int shotgunCooldownTimer = 0; // Timer to control firing rate for shotgun
    private static final int SHOTGUN_COOLDOWN_DELAY = 60; // 1-second delay for shotgun firing rate
                     
                        private boolean isMapLoaded = false;
                        private WorldOneScreen worldOneScreen;
                        
                     
                        public WorldTwoScreen(ScreenCoordinator screenCoordinator) {
                            this.screenCoordinator = screenCoordinator;
                            mPistolOverlay = new MPistolOverlay();
                            mAssaultRifleOverlay = new MAssaultRifleOverlay();
                            mShotgunOverlay = new MShotgunOverlay();
                        }
                     
                     
                        public void initialize() {
                             resetWeaponStatus();
                             if (!isMapLoaded) {
                                 this.map = new Map2(); // Start with Map 2
                             }
                     
                     
                            // Start the player as normal Joe
                            this.player = new Joe(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
                            this.player.setMap(map);
                            this.player.addListener(this);
                     
                     
                            levelClearedScreen = new LevelClearedScreen();
                            levelLoseScreen = new LevelLoseScreen(worldOneScreen);
                            pauseScreen = new PauseScreen();
                     
                     
                            this.playLevelScreenState = PlayLevelScreenState.RUNNING;
                     
                     
                            hp3Image = ImageLoader.load("ThreeHearts.png");
                            hp2Image = ImageLoader.load("TwoHearts.png");
                            hp1Image = ImageLoader.load("OneHeart.png");
                     
                            coinImage = ImageLoader.load("coinForCount.png");
                     
                            map.setupMap();
                        }
                     
                     
                        private boolean playerCollidesWith(Enemy enemy) {
                            return player.getBounds().intersects(enemy.getBounds());
                        }
                     
                     
                        public void update() {
                         updatePauseState();
                     
                            switch (playLevelScreenState) {
                                case RUNNING:
                                    if (MPistolPickup.weaponPickedUp && !isMPistolickedUp) {
                                     isMPistolickedUp = true;
                                     resetOverlays();
                                     showMPistolOverlay = true;
                                     showMAssaultRifleOverlay = false;
                                     showMShotgunOverlay = false;
                                 } else if (MAssaultRiflePickup.weaponPickedUp && !isMAssaultRiflePickedUp) {
                                     isMAssaultRiflePickedUp = true;
                                     resetOverlays();
                                     showMPistolOverlay = false;
                                     showMAssaultRifleOverlay = true;
                                     showMShotgunOverlay = false;
                                 } else if (MShotgunPickup.weaponPickedUp && !isMShotgunPickedUp) {
                                     isMShotgunPickedUp = true;
                                     resetOverlays();
                                     showMAssaultRifleOverlay = false;
                                     showMPistolOverlay = false;
                                     showMShotgunOverlay = true;
                                 }
                     
                     
                                    if (Keyboard.isKeyDown(Key.R) && !reloading) { // Replace with the desired key
                                     startReload();
                                 }
                                   
                       
                                    player.update();
                                    map.update(player);
                     
                     
                                     if (map.isWaveComplete()) {
                                         System.out.println("Level 2 done switching to 3...");
                                         screenCoordinator.setGameState(GameState.WORLDTHREE);// Manually trigger level completion
                                 }
                     
                                 //     if (map.isWaveComplete() && map instanceof Map2 && !isMap3Loaded) {
                                 //     System.out.println("All waves in Map2 are complete. Switching to Map3...");
                                 //     onLevelCompleted(); // Manually trigger level completion
                                 // }
                       
                                    // Handle reloading and shooting
                                    if (reloading) {
                                        reloadTimer++;
                                        
                                        if (reloadTimer >= RELOAD_DELAY) {
                                            finishReload();
                                            reloadTimer = 0; // Reset the timer after reloading
                                        }
                                    }
                                   
                                    else {
                                        fireCooldownTimer++;
                                        shotgunCooldownTimer++;
                       
                                        if (Keyboard.isKeyDown(Key.SPACE) && canShoot) {
                                            if (isMPistolickedUp && mpistolAmmo >0) {
                                             mpistolAmmo--;
                                                canShoot = false;
                                                spawnModernBullets();
                                            } else if (isMAssaultRiflePickedUp && massaultRifleAmmo > 0 && fireCooldownTimer >= FIRE_COOLDOWN_DELAY) {
                                             massaultRifleAmmo--;
                                                fireCooldownTimer = 0;
                                                spawnModernBullets();
                                            } else if (isMShotgunPickedUp && mshotgunAmmo > 0 && shotgunCooldownTimer >= SHOTGUN_COOLDOWN_DELAY) {
                                             mshotgunAmmo--;
                                             shotgunCooldownTimer = 0;
                                             spawnModernBullets();
                                            }
                                        }
                                        if (!Keyboard.isKeyDown(Key.SPACE)) {
                                            canShoot = true;
                                        }
                                    }
                       
                                    // Check for any collisions and other actions
                                    for (Enemy enemy : map.getActiveEnemies()) {
                                        enemy.update(player);
                                        if (playerCollidesWith(enemy)) {
                                            player.hurtPlayer(enemy);
                                            enemy.touchedPlayer(player);
                                        }
                                        for (int i = map.getProjectiles().size() - 1; i >= 0; i--) {
                                            MapEntity projectile = map.getProjectiles().get(i);
                                            if (projectile instanceof ModernBullets && projectile.getBounds().intersects(enemy.getBounds())) {
                                                ((ModernBullets) projectile).touchedEntity(enemy);
                                                map.removeProjectile(projectile);
                                            }
                                        }
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
                                 
                                 case PAUSED:
                                 //   updatePauseState();
                                   pauseScreen.update();
                                   
                                    break;
                     
                            }
                        }
                       
                     //update pause state for game; copied from Gamepanel
                         private void updatePauseState() {
                             if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey)) {
                                 isGamePaused = !isGamePaused;
                                 keyLocker.lockKey(pauseKey);
                                 if (isGamePaused == true) {
                                     playLevelScreenState = PlayLevelScreenState.PAUSED;
                                 }
                                 else {
                                     playLevelScreenState = PlayLevelScreenState.RUNNING;
                                 }
                                 
                             }
                     
                             if (Keyboard.isKeyUp(pauseKey)) {
                                 keyLocker.unlockKey(pauseKey);
                             }
                         }
                     
                         
                       
                       
                        private void spawnModernBullets() {
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
                            if (MPistolPickup.weaponPickedUp) { // Pistol reload
                             mpistolAmmo = MPISTOL_MAX_AMMO;
                      } else if (MAssaultRiflePickup.weaponPickedUp) { // Assault Rifle reload
                          massaultRifleAmmo = MASSAULT_RIFLE_MAX_AMMO;
                } else if (MShotgunPickup.weaponPickedUp) { // Shotgun reload
                    mshotgunAmmo = MSHOTGUN_MAX_AMMO;
   }
}
  
  


   public void draw(GraphicsHandler graphicsHandler) {
       switch (playLevelScreenState) {
           case RUNNING:
               map.draw(graphicsHandler);
               player.draw(graphicsHandler);


               if (showMPistolOverlay) {
                mPistolOverlay.draw(graphicsHandler.getGraphics());
                drawAmmoCount(graphicsHandler, mpistolAmmo, MPISTOL_MAX_AMMO); 
            }
            
            if (showMAssaultRifleOverlay) {
                mAssaultRifleOverlay.draw(graphicsHandler.getGraphics());
                drawAmmoCount(graphicsHandler, massaultRifleAmmo, MASSAULT_RIFLE_MAX_AMMO); 
            }
            
            if (showMShotgunOverlay) {
                mShotgunOverlay.draw(graphicsHandler.getGraphics());
                drawAmmoCount(graphicsHandler, mshotgunAmmo, MSHOTGUN_MAX_AMMO); 
            }
            
  
               drawHitpoints(graphicsHandler);
               drawCoinForCount(graphicsHandler);
               drawCoinCount(graphicsHandler);
               break;
  
           case LEVEL_COMPLETED:
               levelClearedScreen.draw(graphicsHandler);
               break;
  
           case LEVEL_LOSE:
               levelLoseScreen.draw(graphicsHandler);
               break;

            case PAUSED:
               pauseScreen.draw(graphicsHandler);
               break;
       }
   }
  


   private void drawAmmoCount(GraphicsHandler graphicsHandler, int currentAmmo, int maxAmmo) {
       Graphics2D g2d = (Graphics2D) graphicsHandler.getGraphics();
       g2d.setColor(Color.WHITE);
       g2d.setFont(new Font("Arial", Font.BOLD, 14));


       int ammoX = 0;
       int ammoY = 0;
       if (isMPistolickedUp) {
        ammoX = mPistolOverlay.getX() + 72;
        ammoY = mPistolOverlay.getY() + 25;
    } else if (isMAssaultRiflePickedUp) {
        ammoX = mAssaultRifleOverlay.getX() + 72;
        ammoY = mAssaultRifleOverlay.getY() + 25;
    } else if (isMShotgunPickedUp) {
        ammoX = mShotgunOverlay.getX() +72;
        ammoY = mShotgunOverlay.getY() + 25;
    }

       g2d.drawString(currentAmmo + "/" + maxAmmo, ammoX, ammoY);
   }

   private void drawCoinCount(GraphicsHandler graphicsHandler) {
    Graphics2D g2d = (Graphics2D) graphicsHandler.getGraphics();
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Arial", Font.BOLD, 20));
    String coinCount = " :" + player.getCoinCount();
    g2d.drawString(coinCount, 730, 65);
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
        // if (!isMap1Loaded) {
        //     System.out.println("Level Complete! Switching to Map1...");
        //     isMap1Loaded = true;
        //     initialize(); // Reinitialize to load Map1
        // } else if (!isMap2Loaded) {
        //     System.out.println("Level Complete! Switching to Map2...");
        //     isMap2Loaded = true;
        //     initialize();
        // } else {
        //     System.out.println("Level Complete! Switching to Map3...");
        //     isMap3Loaded = true;
        //     initialize();
        //}
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
       RUNNING, LEVEL_COMPLETED, LEVEL_LOSE, PAUSED
};


   private void resetOverlays() {
    showMPistolOverlay = false;
    showMAssaultRifleOverlay = false;
    showMShotgunOverlay = false;
}


   private void resetWeaponStatus() {
       MPistolPickup.showOverlay = false;
       MAssaultRiflePickup.showOverlay = false;
       MShotgunPickup.showOverlay = false;
       MPistolPickup.weaponPickedUp = false;
       MAssaultRiflePickup.weaponPickedUp = false;
       MShotgunPickup.weaponPickedUp = false;
       isMPistolickedUp = false;
       isMAssaultRiflePickedUp = false;
       isMShotgunPickedUp = false;
       mpistolAmmo = MPISTOL_MAX_AMMO;
       massaultRifleAmmo = MASSAULT_RIFLE_MAX_AMMO;
       mshotgunAmmo = MSHOTGUN_MAX_AMMO;
       reloading = false;
       reloadTimer = 0;
   }
}