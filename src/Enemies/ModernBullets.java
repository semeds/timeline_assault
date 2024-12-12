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

public class ModernBullets extends Enemy {

    private float movementSpeed;
    private int existenceFrames;
    private Point position;
    private float speed;
    private float range;

    public ModernBullets(Point location, float movementSpeed, int existenceFrames) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("ModernBullets.png"), 5, 5), "DEFAULT");
        this.movementSpeed = movementSpeed;

        this.existenceFrames = existenceFrames;
        this.hitPoints = 1;

        this.position = position;
        this.speed = speed;
        this.range = range;

        initialize();
    }

    @Override
    public void update(Player player) {
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            moveXHandleCollision(movementSpeed);
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

    // @Override
    public void touchedPlayer(Player player) {
        if (!(player instanceof Joe)) {
            super.touchedPlayer(player);
            this.mapEntityStatus = MapEntityStatus.REMOVED;
            map.removeEnemy(this);
        }
    }

    public void touchedEntity(MapEntity entity) {
        if (entity instanceof Enemy) {
            ((Enemy) entity).takeDamage();
        }

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