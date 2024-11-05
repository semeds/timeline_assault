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
import Players.ArmedJoe;
import Level.Map;
import Utils.Point;
import java.util.HashMap;

public class WeaponPickup extends NPC {
    private boolean isActive;
    private Map mapReference;
    public static boolean showOverlay = false; // Persistent overlay flag
    public static boolean weaponPickedUp = false; // Flag to indicate weapon is picked up
    private static Frame weaponFrame; // Frame for weapon image

    public WeaponPickup(Point location, Map map) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("apocalypse_pistol.png"), 40, 40), "DEFAULT");
        isActive = true;
        this.mapReference = map;

    }

    public void update(Player player) {
        super.update();

        // Check for interaction and set the overlay to true
        if (isActive && intersects(player)) {
            if (Keyboard.isKeyDown(Key.SHIFT)) {
                isActive = false; // Remove the weapon from the map
                removeFromMap();
                showOverlay = true; // Enable the overlay to be drawn
                weaponPickedUp = true; // fireball shooting
               // ArmedJoe armed = new ArmedJoe(x,y);
            }
        }
    }

    private void removeFromMap() {
        mapReference.getNPCs().remove(this);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // Draw the NPC only if its active
        if (isActive) {
            super.draw(graphicsHandler);
        }

        // Always draw the overlay if the weapon has been picked up
        if (showOverlay) {
            int overlayX = 24; // X coordinate for the overlay (bottom-left corner)
            int overlayY = 550; // Y coordinate for the overlay (bottom-left corner)
            graphicsHandler.drawImage(weaponFrame.getImage(), overlayX, overlayY);
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {
            {
                put("DEFAULT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0))
                                .withScale(1.3f)
                                .withBounds(1, 1, 38, 38)
                                .build()
                });
            }
        };
    }
}
