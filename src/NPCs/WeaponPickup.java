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

public class WeaponPickup extends NPC {
    private boolean isActive;
    private Map mapReference;
    private static boolean showOverlay;  // Persistent overlay flag
    private static Frame weaponFrame;    // Frame for weapon image

    public WeaponPickup(Point location, Map map) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("NewShotty.png"), 40, 40), "DEFAULT");
        isActive = true;
        this.mapReference = map;

        // Initialize the overlay and frame only once
        if (weaponFrame == null) {
            weaponFrame = new FrameBuilder(new SpriteSheet(ImageLoader.load("NewShotty.png"), 40, 40).getSprite(0, 0))
                    .withScale(5)
                    .withBounds(1, 1, 38, 38)
                    .build();
        }

        // Initially set showOverlay to false
        showOverlay = false;
    }

    public void update(Player player) {
        super.update();

        // Check for interaction and set the overlay to true
        if (isActive && intersects(player)) {
            if (Keyboard.isKeyDown(Key.SPACE)) {
                isActive = false;  // Remove the weapon from the map
                removeFromMap();
                showOverlay = true;  // Enable the overlay to be drawn
            }
        }
    }

    private void removeFromMap() {
        mapReference.getNPCs().remove(this);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // Always draw the overlay if the flag is true, regardless of isActive
        if (showOverlay) {
            int overlayX = 24;  // X coordinate for the overlay (x2 grid position)
            int overlayY = 530; // Y coordinate for the overlay (y12 grid position)
            graphicsHandler.drawImage(weaponFrame.getImage(), overlayX, overlayY);
        }

        // Draw the NPC only if it's active
        if (isActive) {
            super.draw(graphicsHandler);  // Draw the NPC while it's active
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
