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
import Enemies.Fireball; // Import the Fireball class
import NPCs.WeaponPickup; // Import the WeaponPickup to check if weapon was picked up
import Utils.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class ArmedJoe extends Player {
     // fireball storing, rate, and direction
     private ArrayList<Fireball> fireballs = new ArrayList<>(); // store fired fireballs
     private int fireballCooldown = 0; // controls fire rate
     private boolean facingRight = true; // Made to track direction Joe is facing
 
     public ArmedJoe(float x, float y) {
         super(new SpriteSheet(ImageLoader.load("weaponJoe.png"), 24, 24), x, y, "STAND_RIGHT");
         gravity = .5f;
         terminalVelocityY = 6f;
         jumpHeight = 14.5f;
         jumpDegrade = .5f;
         walkSpeed = 2.3f;
         momentumYIncrease = .5f;
     }
 
     public void update() {
         super.update();
 
         // Updates the direction based on player input
         if (Keyboard.isKeyDown(Key.RIGHT)) {
             facingRight = true;
         } else if (Keyboard.isKeyDown(Key.LEFT)) {
             facingRight = false;
         }
 
         // This only allows the gun to shoot if the weapon was picked up
         if (WeaponPickup.weaponPickedUp) {
             if (Keyboard.isKeyDown(Key.SPACE) && fireballCooldown == 0) {
                 shootFireball();
                 fireballCooldown = 30;
             }
 
             // cooldown
             if (fireballCooldown > 0) {
                 fireballCooldown--;
             }
 
             for (Fireball fireball : fireballs) {
                 fireball.update(this); // Pass the player for collision checking
             }
 
             fireballs.removeIf(fireball -> fireball.getMapEntityStatus() == Level.MapEntityStatus.REMOVED);
         }
     }
 
     public void draw(GraphicsHandler graphicsHandler) {
         super.draw(graphicsHandler);
 
         // Draws fireballs
         if (WeaponPickup.weaponPickedUp) {
             for (Fireball fireball : fireballs) {
                 fireball.draw(graphicsHandler);
                 
             }
         }
     }
 
     // Method thats shoots fireballs
     private void shootFireball() {
 
         // Trying to position fireball to come directly from joe
         Point fireballStart = new Point(getX() + getWidth() / 2, getY() + getHeight() / 2);
         float fireballSpeed = 5.0f; //  fireball speed
         int fireballLifetime = 120; 
 
         // Fireballs shoot in direction player is facing
         float movementSpeed = facingRight ? fireballSpeed : -fireballSpeed;
         Fireball fireball = new Fireball(fireballStart, movementSpeed, fireballLifetime);
         fireball.setMap(map);
 
         fireballs.add(fireball);
         map.addProjectile(fireball);
     }
 
     @Override
     public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
         return new HashMap<String, Frame[]>() {{
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
         }};
     }    
}
