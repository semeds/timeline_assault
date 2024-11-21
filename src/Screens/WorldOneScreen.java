package Screens;


import Enemies.Fireball;
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
import NPCs.APistolPickup;
import NPCs.AAsaultRiflePickup;
import NPCs.AShotgunPickup;
import Engine.APistolOverlay;
import Engine.AAsaultrifleOverlay;
import Engine.AShotgunOverlay;
import java.awt.Color;
import java.awt.Font;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Maps.*;


public class WorldOneScreen extends Screen implements PlayerListener {
   protected ScreenCoordinator screenCoordinator;
   protected Map map;
   protected Player player;
   protected PlayLevelScreenState playLevelScreenState;
   protected int screenTimer;
   protected LevelClearedScreen levelClearedScreen;
   protected PauseScreen pauseScreen;
   protected PurchaseScreen purchaseScreen;
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


   private boolean isAPistolickedUp = false;
   private boolean isAAssaultRiflePickedUp = false; 
   private boolean isAShotgunPickedUp = false;

   private boolean showAPistolOverlay = false;
   private boolean showAAssaultRifleOverlay = false;
   private boolean showAShotgunOverlay = false;


   private APistolOverlay apistolOverlay;
   private AAsaultrifleOverlay aassaultRifleOverlay;
   private AShotgunOverlay ashotgunOverlay;

   private int coinCount = 0;
   public static int apistolAmmo = 12; // Current bullets for pistol
   public static final int APISTOL_MAX_AMMO = 12; // Ammo in a pistol magazine
   public static int aassaultRifleAmmo = 30; // Assault rifle current bullets
   public static final int AASSAULT_RIFLE_MAX_AMMO = 30; // Assault rifle max ammo
   public static int ashotgunAmmo = 8; // Shotgun current bullets
   public static final int AASHOTGUN_MAX_AMMO = 8; // Shotgun max ammo
   private boolean canShoot = true; // Flag to prevent multiple shots per SPACE press
   public static boolean reloading = false; // Flag to indicate if reload is in progress
   private int reloadTimer = 0; // Reload delay
   private static final int RELOAD_DELAY = 60; // Reload delay in frames


   // NEW variables for assault rifle and shotgun cooldowns
   private int fireCooldownTimer = 0; // Timer to control firing rate for assault rifle
   private static final int FIRE_COOLDOWN_DELAY = 10; // Cooldown delay for assault rifle firing rate
   private int shotgunCooldownTimer = 0; // Timer to control firing rate for shotgun
   private static final int SHOTGUN_COOLDOWN_DELAY = 60; // 1-second delay for shotgun firing rate

   private boolean isMapLoaded = false;

   public WorldOneScreen(ScreenCoordinator screenCoordinator) {
       this.screenCoordinator = screenCoordinator;
       apistolOverlay = new APistolOverlay();
       aassaultRifleOverlay = new AAsaultrifleOverlay(); 
       ashotgunOverlay = new AShotgunOverlay(); 
   }


   public void initialize() {
        resetWeaponStatus();
        if (!isMapLoaded) {
            this.map = new Map1(); // Start with Map 1
        }


       // Start the player as normal Joe
       this.player = new Joe(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
       this.player.setMap(map);
       this.player.addListener(this);


       levelClearedScreen = new LevelClearedScreen();
       levelLoseScreen = new LevelLoseScreen(this);
       pauseScreen = new PauseScreen();
       purchaseScreen = new PurchaseScreen();


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
    completePurchase();

       switch (playLevelScreenState) {
           case RUNNING:
               if (APistolPickup.weaponPickedUp && !isAPistolickedUp) {
                   isAPistolickedUp = true;
                   resetOverlays();
                   showAPistolOverlay = true;
                   showAAssaultRifleOverlay = false;
                   showAShotgunOverlay = false;
               } else if (AAsaultRiflePickup.weaponPickedUp && !isAAssaultRiflePickedUp) {
                isAAssaultRiflePickedUp = true;
                resetOverlays();
                showAPistolOverlay = false;
                showAAssaultRifleOverlay = true;
                showAShotgunOverlay = false;
               } else if (AShotgunPickup.weaponPickedUp && !isAShotgunPickedUp) {
                isAShotgunPickedUp = true;
                resetOverlays();
                showAPistolOverlay = false;
                showAAssaultRifleOverlay = false;
                showAShotgunOverlay = true;
               }


               if (Keyboard.isKeyDown(Key.R) && !reloading) { // Replace with the desired key
                startReload();
            }
              
  
               player.update();
               map.update(player);


                if (map.isLastWaveComplete()) {
                 screenCoordinator.setGameState(GameState.WORLDTWO);// Manually trigger level completion
            }
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
                       if (isAPistolickedUp && apistolAmmo > 0) {
                           apistolAmmo--;
                           canShoot = false;
                           spawnFireball();
                       } else if (isAAssaultRiflePickedUp && aassaultRifleAmmo > 0 && fireCooldownTimer >= FIRE_COOLDOWN_DELAY) {
                           aassaultRifleAmmo--;
                           fireCooldownTimer = 0;
                           spawnFireball();
                       } else if (isAShotgunPickedUp && ashotgunAmmo > 0 && shotgunCooldownTimer >= SHOTGUN_COOLDOWN_DELAY) {
                           ashotgunAmmo--;
                           shotgunCooldownTimer = 0;
                           spawnFireball();
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
                       if (projectile instanceof Fireball && projectile.getBounds().intersects(enemy.getBounds())) {
                           ((Fireball) projectile).touchedEntity(enemy);
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
               resetOverlays();
               break;
            
            case PAUSED:
            //   updatePauseState();
              pauseScreen.update();
              
               break;
            
           case PURCHASE:
               purchaseScreen.update();

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


    private void completePurchase() {
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            //isGamePaused = !isGamePaused;
            keyLocker.lockKey(Key.SPACE);
            if (isGamePaused == true) {
                playLevelScreenState = PlayLevelScreenState.PURCHASE;
            }
            else {
                playLevelScreenState = PlayLevelScreenState.RUNNING;
            }
            
        }

        if (Keyboard.isKeyUp(pauseKey)) {
            keyLocker.unlockKey(pauseKey);
        }
    }
    
  
  
   // Helper method to spawn a fireball for the player
   private void spawnFireball() {
       // Logic to spawn a fireball projectile from the player's location
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
       if (AAsaultRiflePickup.weaponPickedUp) { // Assault rifle reload
           aassaultRifleAmmo = AASSAULT_RIFLE_MAX_AMMO;
       } else if (APistolPickup.weaponPickedUp) { // Pistol reload
           apistolAmmo = APISTOL_MAX_AMMO;
       } else if (AShotgunPickup.weaponPickedUp) { // Shotgun reload
           ashotgunAmmo = AASHOTGUN_MAX_AMMO;
       } 
}
  
  


   public void draw(GraphicsHandler graphicsHandler) {
       switch (playLevelScreenState) {
           case RUNNING:
               map.draw(graphicsHandler);
               player.draw(graphicsHandler);


  
               if (showAPistolOverlay) {
                   apistolOverlay.draw(graphicsHandler.getGraphics());
                   drawAmmoCount(graphicsHandler, apistolAmmo, APISTOL_MAX_AMMO); 
               }
              
               if (showAAssaultRifleOverlay) {
                   aassaultRifleOverlay.draw(graphicsHandler.getGraphics());
                   drawAmmoCount(graphicsHandler, aassaultRifleAmmo, AASSAULT_RIFLE_MAX_AMMO); 
               }
              
               if (showAShotgunOverlay) {
                   ashotgunOverlay.draw(graphicsHandler.getGraphics());
                   drawAmmoCount(graphicsHandler, ashotgunAmmo, AASHOTGUN_MAX_AMMO); 
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
            
            case PURCHASE:
               purchaseScreen.draw(graphicsHandler);
               break;
       }
   }
  


   private void drawAmmoCount(GraphicsHandler graphicsHandler, int apistolAmmo, int maxAmmo) {
       Graphics2D g2d = (Graphics2D) graphicsHandler.getGraphics();
       g2d.setColor(Color.WHITE);
       g2d.setFont(new Font("Arial", Font.BOLD, 14));


       int ammoX = 0;
       int ammoY = 0;
       if (isAAssaultRiflePickedUp) {
           ammoX = aassaultRifleOverlay.getX() + 72;
           ammoY = aassaultRifleOverlay.getY() + 25;
       } else if (isAShotgunPickedUp) {
           ammoX = ashotgunOverlay.getX() + 72;
           ammoY = ashotgunOverlay.getY() + 25;
       } else if (isAPistolickedUp) {
           ammoX = apistolOverlay.getX() + 72;
           ammoY = apistolOverlay.getY() + 25;
       }


       g2d.drawString(apistolAmmo + "/" + maxAmmo, ammoX, ammoY);
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
       RUNNING, LEVEL_COMPLETED, LEVEL_LOSE, PAUSED, PURCHASE
};


   private void resetOverlays() {
    showAPistolOverlay = false;
    showAAssaultRifleOverlay = false;
    showAShotgunOverlay = false;
}


   private void resetWeaponStatus() {
       APistolPickup.showOverlay = false;
       AAsaultRiflePickup.showOverlay = false;
       AShotgunPickup.showOverlay = false;
       APistolPickup.weaponPickedUp = false;
       AAsaultRiflePickup.weaponPickedUp = false;
       AShotgunPickup.weaponPickedUp = false;
       isAPistolickedUp = false;
       isAAssaultRiflePickedUp = false;
       isAShotgunPickedUp = false;
       apistolAmmo = APISTOL_MAX_AMMO;
       aassaultRifleAmmo = AASSAULT_RIFLE_MAX_AMMO;
       ashotgunAmmo = AASHOTGUN_MAX_AMMO;
       reloading = false;
       reloadTimer = 0;
   }
}