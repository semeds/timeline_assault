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
import NPCs.WeaponPickup;
import Utils.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class Joe extends Player {
    private ArrayList<Fireball> fireballs = new ArrayList<>(); // Store fired fireballs
    private boolean facingRight = true; // Track direction Joe is facing
    private int currentAmmo = 15; // Tracks current bullets
    private static final int MAX_AMMO = 15; // Maximum ammo in a magazine
    private boolean canShoot = true; // Prevents holding space to shoot continuously
    private boolean reloading = false; // Indicates if reload is in progress
    private int reloadTimer = 0; // Timer for reload delay
    private static final int RELOAD_DELAY = 60; // Reload delay in frames (approx. 1 second)

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

        // Handle reloading process
        if (reloading) {
            reloadTimer++;
            if (reloadTimer >= RELOAD_DELAY) {
                finishReload(); // Complete reload after delay
            }
        } else {
            // Shoot if SPACE is pressed, ammo is available, and Joe is not reloading
            if (Keyboard.isKeyDown(Key.SPACE) && canShoot && currentAmmo > 0) {
                shootFireball();
                currentAmmo--; // Decrease ammo by 1 for each shot
                canShoot = false; // Prevent continuous shooting
            }

            // Reset canShoot when SPACE is released
            if (!Keyboard.isKeyDown(Key.SPACE)) {
                canShoot = true;
            }

            // Initiate reload when R is pressed
            if (Keyboard.isKeyDown(Key.R)) {
                startReload();
            }
        }

        // Update fireballs
        for (Fireball fireball : fireballs) {
            fireball.update(this); // Pass the player for collision checking
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
        currentAmmo = MAX_AMMO; // Refill ammo
        canShoot = true; // Enable shooting after reload
    }

    private void shootFireball() {
        // Position fireball to come directly from Joe
        Point fireballStart = new Point(getX() + getWidth() / 2, getY() + getHeight() / 2);
        float fireballSpeed = 5.0f; // Fireball speed
        int fireballLifetime = 120;

        // Fireball shoots in direction player is facing
        float movementSpeed = facingRight ? fireballSpeed : -fireballSpeed;
        Fireball fireball = new Fireball(fireballStart, movementSpeed, fireballLifetime);
        fireball.setMap(map);

        fireballs.add(fireball);
        map.addProjectile(fireball);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // Draws fireballs
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
