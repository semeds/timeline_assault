package Players;




import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;
import Enemies.Fireball;
import Enemies.ModernBullets;
import Enemies.FutureBullets;
import NPCs.APistolPickup;
import NPCs.AAsaultRiflePickup;
import NPCs.AShotgunPickup;
import NPCs.MAssaultRiflePickup;
import NPCs.MPistolPickup;
import NPCs.MShotgunPickup;
import NPCs.FAssaultRiflePickup;
import NPCs.FPistolPickup;
import NPCs.FShotgunPickup;
import Screens.WorldOneScreen;
import Screens.WorldThreeScreen;
import Screens.WorldTwoScreen;
import Utils.Point;




import java.util.ArrayList;
import java.util.HashMap;




public class Joe extends Player {
  private ArrayList<Fireball> fireballs = new ArrayList<>(); // Store fired fireballs
  private ArrayList<ModernBullets> modernBullet = new ArrayList<>(); 
  private ArrayList<FutureBullets> futureBullet = new ArrayList<>(); 
  private boolean facingRight = true; // Track direction Joe is facing

  private int apistolAmmo = 12; // Tracks bullets for the pistol
  private int mpistolAmmo = 12;
  private int fpistolAmmo = 12;
  private static final int APISTOL_MAX_AMMO = 12;
  private static final int MPISTOL_MAX_AMMO = 12;
  private static final int FPISTOL_MAX_AMMO = 12;

  private int aassaultRifleAmmo = 30; // Tracks bullets for the assault rifle
  private int massaultRifleAmmo = 30;
  private int fassaultRifleAmmo = 30;
  private static final int AASSAULT_RIFLE_MAX_AMMO = 30; // Max ammo for the assault rifle
  private static final int MASSAULT_RIFLE_MAX_AMMO = 30;
  private static final int FASSAULT_RIFLE_MAX_AMMO = 30;

  private int ashotgunAmmo = 8; // Tracks bullets for the shotgun
  private int mshotgunAmmo = 8;
  private int fshotgunAmmo = 8;
  private static final int ASHOTGUN_MAX_AMMO = 8; // Max ammo for the shotgun
  private static final int MSHOTGUN_MAX_AMMO = 8;
  private static final int FSHOTGUN_MAX_AMMO = 8;

  private boolean isAPistolEquipped = false; // Tracks if pistol is equipped
  private boolean isMPistolEquipped = false;
  private boolean isFPistolEquipped = false;

  private boolean isAAssaultRifleEquipped = false; // Tracks if assault rifle is equipped
  private boolean isMAssaultRifleEquipped = false;
  private boolean isFAssaultRifleEquipped = false;

  private boolean isAShotgunEquipped = false; // Tracks if shotgun is equipped
  private boolean isMShotgunEquipped = false;
  private boolean isFShotgunEquipped = false;

  private boolean canShoot = true; // Prevents continuous shooting with pistol
  private boolean reloading = false; // Reload is in progress
  private int reloadTimer = 0; // Reload delay
  private static final int RELOAD_DELAY = 60; // Reload delay in frames


public int getAPistolCurrentAmmo() {
   return apistolAmmo;
}

public int getMPistolCurrentAmmo() {
        return mpistolAmmo;
     }

public int getFPistolCurrentAmmo() {
        return fpistolAmmo;
     }


public int getAAssaultRifleAmmo() {
   return aassaultRifleAmmo;
}

public int getMAssaultRifleAmmo() {
        return massaultRifleAmmo;
     }

public int getFAssaultRifleAmmo() {
        return fassaultRifleAmmo;
     }


public int getAShotgunAmmo() {
   return ashotgunAmmo;
}

public int getMShotgunAmmo() {
        return mshotgunAmmo;
     }

     public int getFShotgunAmmo() {
        return fshotgunAmmo;
     }




  // NEW variables for assault rifle cooldown
  private int fireCooldownTimer = 0; // Timer to control firing rate for assault rifle
  private static final int FIRE_COOLDOWN_DELAY = 10; // Cooldown delay for assault rifle firing rate
   // NEW variables for shotgun cooldown
  private int shotgunCooldownTimer = 0;
  private static final int SHOTGUN_COOLDOWN_DELAY = 60; // 1-second delay for shotgun
 


  private void resetWeaponStatus() {
   isAPistolEquipped = false;
   isMPistolEquipped = false;
   isFPistolEquipped = false;
   
   isAAssaultRifleEquipped = false;
   isMAssaultRifleEquipped = false;
   isFAssaultRifleEquipped = false;

   isAShotgunEquipped = false;
   isMShotgunEquipped = false;
   isFShotgunEquipped = false;

   apistolAmmo = APISTOL_MAX_AMMO; // Reset ammo for pistol
   mpistolAmmo = MPISTOL_MAX_AMMO;
   fpistolAmmo = FPISTOL_MAX_AMMO;

   aassaultRifleAmmo = AASSAULT_RIFLE_MAX_AMMO; // Reset ammo for assault rifle
   massaultRifleAmmo = MASSAULT_RIFLE_MAX_AMMO;
   fassaultRifleAmmo = FASSAULT_RIFLE_MAX_AMMO;

   ashotgunAmmo = ASHOTGUN_MAX_AMMO; // Reset ammo for shotgun
   mshotgunAmmo = MSHOTGUN_MAX_AMMO;
   fshotgunAmmo = FSHOTGUN_MAX_AMMO;

   APistolPickup.weaponPickedUp = false;
   AAsaultRiflePickup.weaponPickedUp = false;
   AShotgunPickup.weaponPickedUp = false;
   MPistolPickup.weaponPickedUp = false;
   MAssaultRiflePickup.weaponPickedUp = false;
   MShotgunPickup.weaponPickedUp = false;
   FPistolPickup.weaponPickedUp = false;
   FAssaultRiflePickup.weaponPickedUp = false;
   FShotgunPickup.weaponPickedUp = false;
}






  public Joe(float x, float y) {
      super(new SpriteSheet(ImageLoader.load("combinedJoe.png"), 24, 24), x, y, "STAND_RIGHT");
      gravity = .5f;
      terminalVelocityY = 6f;
      jumpHeight = 12.5f;
      jumpDegrade = .5f;
      walkSpeed = 3.0f;
      momentumYIncrease = .5f;
  }




  public void update() {
   super.update();


   // Update direction based on player input
   if (Keyboard.isKeyDown(Key.RIGHT)) {
       facingRight = true;
   } else if (Keyboard.isKeyDown(Key.LEFT)) {
       facingRight = false;
   }


   // Equip the correct weapon and reset others
   if (APistolPickup.weaponPickedUp) {
       resetWeaponStatus();
       isAPistolEquipped = true;
   } else if (AAsaultRiflePickup.weaponPickedUp) {
       resetWeaponStatus();
       isAAssaultRifleEquipped = true;
   } else if (AShotgunPickup.weaponPickedUp) {
       resetWeaponStatus();
       isAShotgunEquipped = true;
   } else if (MPistolPickup.weaponPickedUp) {
        resetWeaponStatus();
        isMPistolEquipped = true;
   } else if (MAssaultRiflePickup.weaponPickedUp) {
        resetWeaponStatus();
        isMAssaultRifleEquipped = true;
   } else if (MShotgunPickup.weaponPickedUp) {
        resetWeaponStatus();
        isMShotgunEquipped = true;
   } else if (FPistolPickup.weaponPickedUp) {
        resetWeaponStatus();
        isFPistolEquipped = true;
   } else if (FAssaultRiflePickup.weaponPickedUp) {
        resetWeaponStatus();
        isFAssaultRifleEquipped = true;
   } else if (FShotgunPickup.weaponPickedUp) {
        resetWeaponStatus();
        isFShotgunEquipped = true;
   }


   // Reloading logic
   if (reloading) {
       reloadTimer++;
       if (reloadTimer >= RELOAD_DELAY) {
           finishReload();
       }
   } else {
       // Shooting logic based on the equipped weapon
       fireCooldownTimer++;
       shotgunCooldownTimer++;


       if (isAPistolEquipped && Keyboard.isKeyDown(Key.SPACE) && canShoot && apistolAmmo > 0) {
        shootFireball(240);
        apistolAmmo--;
        canShoot = false;
    } else if (isAAssaultRifleEquipped && Keyboard.isKeyDown(Key.SPACE) 
            && aassaultRifleAmmo > 0 && fireCooldownTimer >= FIRE_COOLDOWN_DELAY) {
        shootFireball(240); // Fire a projectile with a 240-frame lifetime
        aassaultRifleAmmo--; // Reduce ammo count by 1
        WorldOneScreen.aassaultRifleAmmo = aassaultRifleAmmo; // Sync display ammo count for assault rifle
        fireCooldownTimer = 0; // Reset cooldown timer
    } else if (isAShotgunEquipped && Keyboard.isKeyDown(Key.SPACE) 
            && ashotgunAmmo > 0 && shotgunCooldownTimer >= SHOTGUN_COOLDOWN_DELAY) {
        shootFireball(40);
        ashotgunAmmo--;
        shotgunCooldownTimer = 0;
    } else if (isMPistolEquipped && Keyboard.isKeyDown(Key.SPACE) && canShoot && mpistolAmmo > 0) {
        shootModernBullets(240);
        mpistolAmmo--;
        canShoot = false;
    } else if (isMAssaultRifleEquipped && Keyboard.isKeyDown(Key.SPACE) 
            && massaultRifleAmmo > 0 && fireCooldownTimer >= FIRE_COOLDOWN_DELAY) {
        shootModernBullets(240); // Fire a projectile with a 240-frame lifetime
        massaultRifleAmmo--; // Reduce ammo count by 1
       WorldTwoScreen.aassaultRifleAmmo = aassaultRifleAmmo; // Sync display ammo count for assault rifle
        fireCooldownTimer = 0; // Reset cooldown timer
    } else if (isMShotgunEquipped && Keyboard.isKeyDown(Key.SPACE) 
            && mshotgunAmmo > 0 && shotgunCooldownTimer >= SHOTGUN_COOLDOWN_DELAY) {
        shootModernBullets(240);
        mshotgunAmmo--;
        shotgunCooldownTimer = 0;
    } else if (isFPistolEquipped && Keyboard.isKeyDown(Key.SPACE) && canShoot && fpistolAmmo > 0) {
        shootFutureBullets(240);
        fpistolAmmo--;
        canShoot = false;
    } else if (isFAssaultRifleEquipped && Keyboard.isKeyDown(Key.SPACE) 
            && fassaultRifleAmmo > 0 && fireCooldownTimer >= FIRE_COOLDOWN_DELAY) {
        shootFutureBullets(240); // Fire a projectile with a 240-frame lifetime
        fassaultRifleAmmo--; // Reduce ammo count by 1
        WorldThreeScreen.fassaultRifleAmmo = fassaultRifleAmmo; // Sync display ammo count for assault rifle
        fireCooldownTimer = 0; // Reset cooldown timer
    } else if (isFShotgunEquipped && Keyboard.isKeyDown(Key.SPACE) 
            && fshotgunAmmo > 0 && shotgunCooldownTimer >= SHOTGUN_COOLDOWN_DELAY) {
        shootFutureBullets(40);
        fshotgunAmmo--;
        shotgunCooldownTimer = 0;
    }
    
    








       if (!Keyboard.isKeyDown(Key.SPACE)) {
           canShoot = true;
       }


       // Start reload when R is pressed
       if (Keyboard.isKeyDown(Key.R)) {
           startReload();
       }
   }


   // Update fireballs
   for (Fireball fireball : fireballs) {
       fireball.update(this);
   }
   fireballs.removeIf(fireball -> fireball.getMapEntityStatus() == Level.MapEntityStatus.REMOVED);

   for (ModernBullets modernBullets : modernBullet) {
        modernBullets.update(this);
    }
    modernBullet.removeIf(modernBullets -> modernBullets.getMapEntityStatus() == Level.MapEntityStatus.REMOVED);

    for (FutureBullets futureBullets : futureBullet) {
        futureBullets.update(this);
    }
    futureBullet.removeIf(futureBullets -> futureBullets.getMapEntityStatus() == Level.MapEntityStatus.REMOVED);
}










  private void startReload() {
      reloading = true;
      reloadTimer = 0; // Start the reload timer
      canShoot = false; // Disable shooting during reload
  }




  public void finishReload() {
   reloading = false;  // Reset reloading state
   if (isAPistolEquipped) {
        apistolAmmo = APISTOL_MAX_AMMO;  // Reset pistol ammo
   } else if (isAAssaultRifleEquipped) {
        aassaultRifleAmmo = AASSAULT_RIFLE_MAX_AMMO;  // Reset assault rifle ammo
   } else if (isAShotgunEquipped) {
        ashotgunAmmo = ASHOTGUN_MAX_AMMO;  // Reset shotgun ammo
   } else if (isMPistolEquipped) {
        mpistolAmmo = MPISTOL_MAX_AMMO;  // Reset pistol ammo
   } else if (isMAssaultRifleEquipped) {
        massaultRifleAmmo = MASSAULT_RIFLE_MAX_AMMO;  // Reset assault rifle ammo
   } else if (isMShotgunEquipped) {
        mshotgunAmmo = MSHOTGUN_MAX_AMMO;  // Reset shotgun ammo
   } else if (isFPistolEquipped) {
        fpistolAmmo = FPISTOL_MAX_AMMO;  // Reset pistol ammo
   } else if (isFAssaultRifleEquipped) {
        fassaultRifleAmmo = FASSAULT_RIFLE_MAX_AMMO;  // Reset assault rifle ammo
   } else if (isFShotgunEquipped) {
        fshotgunAmmo = FSHOTGUN_MAX_AMMO;  // Reset shotgun ammo
   }
   // Ensure that the ammo count for display is updated here if it's a static variable
   WorldOneScreen.apistolAmmo = apistolAmmo;  // Update display for pistol
   WorldOneScreen.aassaultRifleAmmo = aassaultRifleAmmo;  // Update display for assault rifle
   WorldOneScreen.ashotgunAmmo = ashotgunAmmo;  // Update display for shotgun

   WorldTwoScreen.apistolAmmo = mpistolAmmo;
   WorldTwoScreen.aassaultRifleAmmo = massaultRifleAmmo;
   WorldTwoScreen.ashotgunAmmo = mshotgunAmmo;

   WorldThreeScreen.apistolAmmo = fpistolAmmo;
   WorldThreeScreen.aassaultRifleAmmo = fassaultRifleAmmo;
   WorldThreeScreen.ashotgunAmmo = fshotgunAmmo;

}








private void shootFireball(int lifetime) {
   Point fireballStart = new Point(getX() + getWidth() / 2, getY() + getHeight() / 2);
   float fireballSpeed = 5.0f; // Speed for the fireball
   float movementSpeed = facingRight ? fireballSpeed : -fireballSpeed;


   Fireball fireball = new Fireball(fireballStart, movementSpeed, lifetime);


   if (map != null) {
       fireball.setMap(map);
       map.addProjectile(fireball);
   }


   fireballs.add(fireball);
}

private void shootModernBullets(int lifetime) {
        Point ModernBulletsStart = new Point(getX() + getWidth() / 2, getY() + getHeight() / 2);
        float ModernBulletSpeed = 5.0f; 
        float movementSpeed = facingRight ? ModernBulletSpeed : -ModernBulletSpeed;
     
     
        ModernBullets modernBullets = new ModernBullets(ModernBulletsStart, movementSpeed, lifetime);
     
        if (map != null) {
        modernBullets.setMap(map);
            map.addProjectile(modernBullets);
        }
     
     
        modernBullet.add(modernBullets);
     }

     private void shootFutureBullets(int lifetime) {
        Point FutureBulletsStart = new Point(getX() + getWidth() / 2, getY() + getHeight() / 2);
        float FutureBulletSpeed = 5.0f; 
        float movementSpeed = facingRight ? FutureBulletSpeed : -FutureBulletSpeed;
     
     
        FutureBullets futureBullets = new FutureBullets(FutureBulletsStart, movementSpeed, lifetime);
     
        if (map != null) {
                futureBullets.setMap(map);
            map.addProjectile(futureBullets);
        }
     
     
        futureBullet.add(futureBullets);
     }
















public void draw(GraphicsHandler graphicsHandler) {
   super.draw(graphicsHandler);


   // Draw each fireball
   for (Fireball fireball : fireballs) {
       fireball.draw(graphicsHandler);
   }

   for( ModernBullets modernBullets : modernBullet) {
        modernBullets.draw(graphicsHandler);
   }

   for(FutureBullets futureBullets : futureBullet) {
        futureBullets.draw(graphicsHandler);
   }
}






  @Override
  public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        if (isAPistolEquipped == false) {
                return new HashMap<String, Frame[]>() {
                        {
                            put("STAND_RIGHT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                                            .withScale(3)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("STAND_LEFT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("WALK_RIGHT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                            .withScale(3)
                                            .withBounds(8, 9, 8, 9)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                            .withScale(3)
                                            .withBounds(8, 9, 8, 9)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                            .withScale(3)
                                            .withBounds(8, 9, 8, 9)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                                            .withScale(3)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("WALK_LEFT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 9, 8, 9)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 9, 8, 9)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 9, 8, 9)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("JUMP_RIGHT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                                            .withScale(3)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("JUMP_LEFT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(2, 0))
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("FALL_RIGHT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(3, 0))
                                            .withScale(3)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("FALL_LEFT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(3, 0))
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("CROUCH_RIGHT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(4, 0))
                                            .withScale(3)
                                            .withBounds(8, 12, 8, 6)
                                            .build()
                            });
              
              
              
              
                            put("CROUCH_LEFT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(4, 0))
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 12, 8, 6)
                                            .build()
                            });
              
              
              
              
                            put("DEATH_RIGHT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
                                            .withScale(3)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
                                            .withScale(3)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                                            .withScale(3)
                                            .build()
                            });
              
              
              
              
                            put("DEATH_LEFT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(5, 0), 8)
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(5, 1), 8)
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .build(),
                                    new FrameBuilder(spriteSheet.getSprite(5, 2), -1)
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .build()
                            });
              
              
              
              
                            put("SWIM_STAND_RIGHT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(6, 0))
                                            .withScale(3)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
              
              
              
              
                            put("SWIM_STAND_LEFT", new Frame[] {
                                    new FrameBuilder(spriteSheet.getSprite(6, 0))
                                            .withScale(3)
                                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                            .withBounds(8, 9, 8, 9)
                                            .build()
                            });
                        }
                    };
                }
                else if(isAPistolEquipped == true) {
                        return new HashMap<String, Frame[]>() {
                                {
                                    put("STAND_RIGHT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(7, 0))
                                                    .withScale(3)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("STAND_LEFT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(7, 0))
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("WALK_RIGHT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(8, 0), 14)
                                                    .withScale(3)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(8, 1), 14)
                                                    .withScale(3)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(8, 2), 14)
                                                    .withScale(3)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(8, 3), 14)
                                                    .withScale(3)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("WALK_LEFT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(8, 0), 14)
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(8, 1), 14)
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(8, 2), 14)
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(8, 3), 14)
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("JUMP_RIGHT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(9, 0))
                                                    .withScale(3)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("JUMP_LEFT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(9, 0))
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("FALL_RIGHT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(10, 0))
                                                    .withScale(3)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("FALL_LEFT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(10, 0))
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("CROUCH_RIGHT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(11, 0))
                                                    .withScale(3)
                                                    .withBounds(8, 12, 8, 6)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("CROUCH_LEFT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(11, 0))
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 12, 8, 6)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("DEATH_RIGHT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(12, 0), 8)
                                                    .withScale(3)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(12, 1), 8)
                                                    .withScale(3)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(12, 2), -1)
                                                    .withScale(3)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("DEATH_LEFT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(12, 0), 8)
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(12, 1), 8)
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .build(),
                                            new FrameBuilder(spriteSheet.getSprite(12, 2), -1)
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("SWIM_STAND_RIGHT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(6, 0))
                                                    .withScale(3)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                      
                      
                      
                      
                                    put("SWIM_STAND_LEFT_2", new Frame[] {
                                            new FrameBuilder(spriteSheet.getSprite(6, 0))
                                                    .withScale(3)
                                                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                                    .withBounds(8, 9, 8, 9)
                                                    .build()
                                    });
                                }
                            };
                }
                        return animations;
        }
}
