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
import NPCs.APistolPickup;
import NPCs.AAsaultRiflePickup;
import NPCs.AShotgunPickup;
import Utils.Point;


import java.util.ArrayList;
import java.util.HashMap;


public class Joe extends Player {
   private ArrayList<Fireball> fireballs = new ArrayList<>(); // Store fired fireballs
   private boolean facingRight = true; // Track direction Joe is facing
   private int currentAmmo = 12; // Tracks bullets for the pistol
   private static final int MAX_AMMO = 12; // Max ammo for the pistol
   private int assaultRifleAmmo = 30; // Tracks bullets for the assault rifle
   private static final int ASSAULT_RIFLE_MAX_AMMO = 30; // Max ammo for the assault rifle
   private int shotgunAmmo = 8; // Tracks bullets for the shotgun
   private static final int SHOTGUN_MAX_AMMO = 8; // Max ammo for the shotgun
   private boolean isPistolEquipped = false; // Tracks if pistol is equipped
   private boolean isAssaultRifleEquipped = false; // Tracks if assault rifle is equipped
   private boolean isShotgunEquipped = false; // Tracks if shotgun is equipped
   private boolean canShoot = true; // Prevents continuous shooting with pistol
   private boolean reloading = false; // Reload is in progress
   private int reloadTimer = 0; // Reload delay
   private static final int RELOAD_DELAY = 60; // Reload delay in frames


   // NEW variables for assault rifle cooldown
   private int fireCooldownTimer = 0; // Timer to control firing rate for assault rifle
   private static final int FIRE_COOLDOWN_DELAY = 10; // Cooldown delay for assault rifle firing rate
  
   // NEW variables for shotgun cooldown
   private int shotgunCooldownTimer = 0;
   private static final int SHOTGUN_COOLDOWN_DELAY = 60; // 1-second delay for shotgun
   

   private void resetWeaponStatus() {
    isPistolEquipped = false;
    isAssaultRifleEquipped = false;
    isShotgunEquipped = false;
    currentAmmo = MAX_AMMO; // Reset ammo for pistol
    assaultRifleAmmo = ASSAULT_RIFLE_MAX_AMMO; // Reset ammo for assault rifle
    shotgunAmmo = SHOTGUN_MAX_AMMO; // Reset ammo for shotgun
    APistolPickup.weaponPickedUp = false;
    AAsaultRiflePickup.weaponPickedUp = false;
    AShotgunPickup.weaponPickedUp = false;

    APistolPickup.weaponPickedUp = false; // Ensure pistol overlay is disabled
    AAsaultRiflePickup.weaponPickedUp = false; // Ensure assault rifle overlay is disabled
    AShotgunPickup.weaponPickedUp = false; // Ensure shotgun overlay is disabled
}



   public Joe(float x, float y) {
       super(new SpriteSheet(ImageLoader.load("test.png"), 24, 24), x, y, "STAND_RIGHT");
       gravity = .5f;
       terminalVelocityY = 6f;
       jumpHeight = 12.5f;
       jumpDegrade = .5f;
       walkSpeed = 2.3f;
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
        isPistolEquipped = true;
    } else if (AAsaultRiflePickup.weaponPickedUp) {
        resetWeaponStatus();
        isAssaultRifleEquipped = true;
    } else if (AShotgunPickup.weaponPickedUp) {
        resetWeaponStatus();
        isShotgunEquipped = true;
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

         if (isPistolEquipped && Keyboard.isKeyDown(Key.SPACE) && canShoot && currentAmmo > 0) {
            shootFireball(240); 
            currentAmmo--;
            canShoot = false;
        } else if (isAssaultRifleEquipped && Keyboard.isKeyDown(Key.SPACE) && assaultRifleAmmo > 0 
                && fireCooldownTimer >= FIRE_COOLDOWN_DELAY) {
            shootFireball(240); 
            assaultRifleAmmo--;
            fireCooldownTimer = 0;
        }
        
        else if (isShotgunEquipped && Keyboard.isKeyDown(Key.SPACE) && shotgunAmmo > 0 
        && shotgunCooldownTimer >= SHOTGUN_COOLDOWN_DELAY) {
    shootFireball(40); 
    shotgunAmmo--;
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
}





   private void startReload() {
       reloading = true;
       reloadTimer = 0; // Start the reload timer
       canShoot = false; // Disable shooting during reload
   }


   private void finishReload() {
    reloading = false;
    if (isPistolEquipped) {
        currentAmmo = MAX_AMMO; // Reset pistol ammo
    } else if (isAssaultRifleEquipped) {
        assaultRifleAmmo = ASSAULT_RIFLE_MAX_AMMO; // Reset assault rifle ammo
    } else if (isShotgunEquipped) {
        shotgunAmmo = SHOTGUN_MAX_AMMO; // Reset shotgun ammo
    }
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








public void draw(GraphicsHandler graphicsHandler) {
    super.draw(graphicsHandler);

    // Draw each fireball
    for (Fireball fireball : fireballs) {
        fireball.draw(graphicsHandler);
    }
}



   @Override
   public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
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
}
