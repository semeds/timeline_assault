package Enemies;

import Builders.FrameBuilder;
import Enemies.DinosaurEnemy.DinosaurState;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.Player;
import Utils.AirGroundState;
import Utils.Point;
import Utils.Direction;
import java.util.HashMap;
import java.util.Random;

// This class is for the black bug enemy
// enemy behaves like a Mario goomba -- walks forward until it hits a solid map tile, and then turns around
// if it ends up in the air from walking off a cliff, it will fall down until it hits the ground again, and then will continue walking
public class BaseAlien extends Enemy {

    private float gravity = .5f;
    private float movementSpeed = 1.25f;
    private Direction startFacingDirection;
    private Direction facingDirection;
    private AirGroundState airGroundState;

    protected Point startLocation;
    protected Point endLocation;

    protected int shootWaitTimer;

    protected int shootTimer;

    protected AlienState alienState;
    protected AlienState previousAlienState;



    public BaseAlien(Point location, Direction facingDirection) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("ZombieTrial.png"), 63, 58), "WALK_LEFT");
        this.startFacingDirection = facingDirection;
        this.hitPoints = 4;
        this.initialize();
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
    }

    // Method to hurt the enemy - Basically copied hurtPlayrt()
    public void hurtEnemy(Player player) {
        if (hitPoints > 0) {
            hitPoints--;

            // Enemy Death
            if (hitPoints <= 0) {
                die();
            }
        }
    }

    public void die() {
        Random random = new Random();
        int chance = random.nextInt(10);

        // Write the loot dropping logic here
        if (map != null) { // Ensure the map is assigned to this enemy
            if (chance < 9) {
                // 90% chance enemy drops a coin
                map.spawnCoin(this.getX(), this.getY());
            } else {
                // 10% chance it drops a powerup
                map.spawnpowerup(this.getX(), this.getY());
            }
            map.removeEnemy(this);
        }
    }

    @Override
    public void initialize() {
        super.initialize();
        facingDirection = startFacingDirection;
        previousAlienState = alienState;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "WALK_LEFT";
        }
        airGroundState = AirGroundState.GROUND;

        shootWaitTimer = 65;
    }
    

    @Override
    public void update(Player player) {
        float moveAmountX = 0;
        float moveAmountY = 0;

         if (shootWaitTimer == 0 && alienState != AlienState.SHOOT_WAIT) {
            alienState = AlienState.SHOOT_WAIT;
        } else {
            shootWaitTimer--;
        }

        if (alienState == AlienState.SHOOT_WAIT) {
            if (previousAlienState == AlienState.WALK) {
                shootTimer = 65;
                currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer == 0) {
                alienState = AlienState.SHOOT;
            } else {
                shootTimer--;
            }
        }

        if (alienState == AlienState.SHOOT) {
    int enemyProjectilesX;
    float movementSpeed;
    if (facingDirection == Direction.RIGHT) {
        enemyProjectilesX = Math.round(getX()) + getWidth();
        movementSpeed = 3;
    } else {
        enemyProjectilesX = Math.round(getX() - 21);
        movementSpeed = 3;
    }

    int enemyProjectilesY = Math.round(getY()) + 4;

    // Create enemy projectile with updated constructor
    EnemyProjectiles enemyProjectiles = new EnemyProjectiles(
        new Point(enemyProjectilesX, enemyProjectilesY), 
        movementSpeed, 
        300, 
        player // Pass the player as the target
    );

    map.addEnemy(enemyProjectiles);
    alienState = AlienState.WALK;
    shootWaitTimer = 400;
}


        // add gravity (if in air, this will cause bug to fall)
        moveAmountY += gravity;

        // if on ground, walk forward based on facing direction
        if (airGroundState == AirGroundState.GROUND) {
            if (facingDirection == Direction.RIGHT) {
                moveAmountX += movementSpeed;
            } else {
                moveAmountX -= movementSpeed;
            }
        }

        // move bug
        moveYHandleCollision(moveAmountY);
        moveXHandleCollision(moveAmountX);

        super.update(player);
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if enemy has collided into something while walking forward,
        // it turns around (changes facing direction)
        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "WALK_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "WALK_RIGHT";
            }
        }
    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        // if bug is colliding with the ground, change its air ground state to GROUND
        // if it is not colliding with the ground, it means that it's currently in the
        // air, so its air ground state is changed to AIR
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                airGroundState = AirGroundState.GROUND;
            } else {
                airGroundState = AirGroundState.AIR;
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0,0))
                        .withScale(1)
                        .withBounds(20,20,20,20)
                        .build()
                });

            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,0))
                        .withScale(1)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .withBounds(20,20,20,20)
                        .build()
                });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,1),25)
                            .withScale(1)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,2),25)
                            .withScale(1)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,3),25)
                            .withScale(1)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,4),25)
                            .withScale(1)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,5),25)
                            .withScale(1)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,6),25)
                            .withScale(1)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,7),25)
                            .withScale(1)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,8),25)
                            .withScale(1)
                            .withBounds(20,20,20,20)
                            .build()
                 });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0,1),25)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,2),25)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,3),25)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,4),25)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,5),25)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,6),25)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,7),25)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20,20,20,20)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0,8),25)
                            .withScale(1)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(20,20,20,20)
                            .build()
                });
            }
        };
    }

    public enum AlienState {
        WALK, SHOOT_WAIT, SHOOT
    }
}