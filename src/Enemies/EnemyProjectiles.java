package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Players.Joe;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

public class EnemyProjectiles extends Enemy {
    // EnemyProjectiles variables
    private float movementSpeed;
    private int existenceFrames;
    private float xSpeed;
    private float ySpeed;

    public EnemyProjectiles(Point location, float movementSpeed, int existenceFrames, Player target) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("LaserBullet.png"), 20, 20), "DEFAULT");
        this.movementSpeed = movementSpeed;
        this.existenceFrames = existenceFrames;

        // Calculate the initial direction vector toward the player
        float xDifference = target.getX() - location.x;
        float yDifference = target.getY() - location.y;

        // Normalize the direction vector and scale it by the movementSpeed
        float distance = (float) Math.sqrt(xDifference * xDifference + yDifference * yDifference);
        this.xSpeed = (xDifference / distance) * movementSpeed;
        this.ySpeed = (yDifference / distance) * movementSpeed;

        initialize();
    }

    @Override
    public void update(Player player) {
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // Move the EnemyProjectiles in the fixed direction
            moveXHandleCollision(xSpeed);
            moveYHandleCollision(ySpeed);
            super.update(player);
        }
        existenceFrames--;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
        if (hasCollided) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void touchedPlayer(Player player) {
        if (!(player instanceof Joe)) {
            super.touchedPlayer(player);
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    public void touchedEntity(MapEntity entity) {
        if (entity instanceof Enemy) {
            ((Enemy) entity).takeDamage();
        }

        // Remove the EnemyProjectiles after it touches any entity
        this.mapEntityStatus = MapEntityStatus.REMOVED;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("DEFAULT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(3)
                                .withBounds(1, 1, 5, 5)
                                .build()
                });
            }
        };
    }
}
