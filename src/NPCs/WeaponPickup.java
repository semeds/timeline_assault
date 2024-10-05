package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Level.Map;
import Utils.Point;

import java.util.HashMap;

// This class is for the WeaponPickup NPC (Shotgun)
public class WeaponPickup extends NPC {
    private boolean isActive; 
    private Map mapReference; 

    public WeaponPickup(Point location, Map map) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("shotgun.png"), 40, 40), "DEFAULT");
        isActive = true; 
        this.mapReference = map; 
    }

    public void update(Player player) {
        super.update();

        if (isActive && intersects(player)) {
            if (Keyboard.isKeyDown(Key.SPACE)) {
                isActive = false; 
                removeFromMap();  
            }
        }
    }

    private void removeFromMap() {
        mapReference.getNPCs().remove(this);
        System.out.println("WeaponPickup removed from the map.");
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (isActive) {
            super.draw(graphicsHandler);
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(2)
                            .withBounds(1, 1, 38, 38) 
                            .build()
            });
        }};
    }
}
