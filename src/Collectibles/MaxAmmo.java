package Collectibles;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Map;
import Level.NPC;
import Level.Player;
import Screens.WorldOneScreen;
import Utils.Point;
import java.util.HashMap;

//class that handles coincollection and the likes
public class MaxAmmo extends NPC {

    protected Map mapPosition;

    public MaxAmmo(Point location, Map map) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("invincibility.png"), 24, 24), "DEFAULT");
        isInteractable = true;
        this.mapPosition = map;
    }

    // for player intersection
    public void update(Player player) {
        super.update();

        if (intersects(player)) {
             collectpowerup(player);
        }
    }

    private void collectpowerup(Player player) {
        //set ammo to full
        WorldOneScreen.finishReload();
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
