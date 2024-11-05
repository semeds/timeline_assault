package Collectibles;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Map;
import Level.NPC;
import Level.Player;
import Utils.Point;
import java.util.HashMap;

//class that handles coincollection and the likes
public class Coin extends NPC {

    protected Map mapPosition;

    public Coin(Point location, Map map) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("coin1.png"), 24, 24), "DEFAULT");
        isInteractable = true;
        this.mapPosition = map;
    }

    // for player intersection
    public void update(Player player) {
        super.update();

        if (intersects(player)) {
             collectCoin(player);
        }
    }

    private void collectCoin(Player player) {
        player.incrementCoinCount();
        mapPosition.getNPCs().remove(this);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("DEFAULT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(2)
                                .withBounds(1, 1, 38, 38)
                                .build()
                });
            }
        };
    }
}
