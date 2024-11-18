package Screens;

import Engine.ImageLoader;
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
    }
}
