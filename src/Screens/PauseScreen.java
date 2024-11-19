package Screens;

import Engine.ImageLoader;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Key;
import Engine.GamePanel;
import Engine.GraphicsHandler;
import Engine.Screen;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import SpriteFont.SpriteFont;

import java.awt.*;

public class PauseScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    private SpriteFont shopLabel;
	private SpriteFont arLabel;
	private SpriteFont shottyLabel;
	private SpriteFont pistolLabel;

    //menu nav variables
    protected int currentMenuItemHovered = 0;
    protected int menuItemSelected = -1;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    private KeyLocker keyLocker = new KeyLocker();
    


    
    public PauseScreen() {
        initialize();
    }

    @Override
    public void initialize() {
        shopLabel = new SpriteFont("SHOP", 360, 100, "Impact", 36, Color.white);
		shopLabel.setOutlineColor(Color.black);
		shopLabel.setOutlineThickness(2.0f);

		arLabel = new SpriteFont("Assault Rifle", 360, 200, "Impact", 24, Color.white);
		arLabel.setOutlineColor(Color.black);
		arLabel.setOutlineThickness(2.0f);

		shottyLabel = new SpriteFont("Shotgun", 360, 300, "Impact", 24, Color.white);
		shottyLabel.setOutlineColor(Color.black);
		shottyLabel.setOutlineThickness(2.0f);

		pistolLabel = new SpriteFont("Pistol", 360, 400, "Impact", 24, Color.white);
		pistolLabel.setOutlineColor(Color.black);
		pistolLabel.setOutlineThickness(2.0f);
    }

    @Override
    public void update() {
     
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // paint entire screen black and dislpay level cleared text
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        shopLabel.draw(graphicsHandler);
		arLabel.draw(graphicsHandler);
		shottyLabel.draw(graphicsHandler);
		pistolLabel.draw(graphicsHandler);
		menuNav();
    }

    public void menuNav() {
        if (Keyboard.isKeyDown(Key.DOWN) && keyPressTimer == 0) {
					keyPressTimer = 14;
					currentMenuItemHovered++;
				} else if (Keyboard.isKeyDown(Key.UP) && keyPressTimer == 0) {
					keyPressTimer = 14;
					currentMenuItemHovered--;
				} else {
					if (keyPressTimer > 0) {
						keyPressTimer--;
					}
				}
		
				// if down is pressed on last menu item or up is pressed on first menu item,
				// "loop" the selection back around to the beginning/end
				if (currentMenuItemHovered > 1) {
					currentMenuItemHovered = 0;
				} else if (currentMenuItemHovered < 0) {
					currentMenuItemHovered = 1;
				}
		
				// sets location for blue square in front of text (pointerLocation) and also
				// sets color of spritefont text based on which menu item is being hovered
				if (currentMenuItemHovered == 0) {
					arLabel.setColor(new Color(255, 215, 0));
					shottyLabel.setColor(new Color(49, 207, 240));
					pointerLocationX = 300;
					pointerLocationY = 200;
				} else if (currentMenuItemHovered == 1) {
					arLabel.setColor(new Color(49, 207, 240));
					shottyLabel.setColor(new Color(255, 215, 0));
					pointerLocationX = 300;
					pointerLocationY = 300;
				}
    }
}
