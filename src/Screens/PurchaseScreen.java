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
import GameObject.Sprite;
import Level.Map;
import SpriteFont.SpriteFont;

import java.awt.*;

public class PurchaseScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    private SpriteFont purchaseLabel;

    public PurchaseScreen() {
        initialize();
    }

    @Override
    public void initialize() {
        purchaseLabel = new SpriteFont("Purchase succesful", 360, 200, "Impact", 24, Color.yellow);
        purchaseLabel.setOutlineColor(Color.black);
        purchaseLabel.setOutlineThickness(2.0f);
    }

    @Override
    public void update(){

    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        purchaseLabel.draw(graphicsHandler);
    }

    // public void purchaseWeapon() {
	// 	if (Keyboard.isKeyDown(Key.SPACE) && keyPressTimer == 0) {
	// 		if (currentMenuItemHovered == 0) {
	// 			purchaseLabel.setColor(Color.white);
	// 		}

	// 	}
	// }
}
