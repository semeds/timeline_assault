package Engine;

import GameObject.Rectangle;
import SpriteFont.SpriteFont;
import Utils.Colors;

//new imports
import GameObject.Sprite;
import java.awt.image.BufferedImage;

import javax.swing.*;

import Game.ScreenCoordinator;

import java.awt.*;

/*
 * This is where the game loop process and render back buffer is setup
 */
public class GamePanel extends JPanel {
	// loads Screens on to the JPanel
	// each screen has its own update and draw methods defined to handle a "section" of the game.
	private ScreenManager screenManager;

	// used to draw graphics to the panel
	private GraphicsHandler graphicsHandler;

	// private boolean isGamePaused = false;
	// private SpriteFont pauseLabel;
	private KeyLocker keyLocker = new KeyLocker();
	// private final Key pauseKey = Key.P;
	private Thread gameLoopProcess;

	private Key showFPSKey = Key.G;
	private SpriteFont fpsDisplayLabel;
	private boolean showFPS = false;
	private int currentFPS;
	private boolean doPaint;

	//variables to load shop into game
	protected ScreenCoordinator screenCoordinator;
	private SpriteFont shopLabel;
	private SpriteFont arLabel;
	private SpriteFont shottyLabel;
	private SpriteFont pistolLabel;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
	protected int menuItemSelected = -1;
   	protected SpriteFont credits;
   	protected BufferedImage backgroundImage;
   	protected int keyPressTimer;
   	protected int pointerLocationX, pointerLocationY;


	// The JPanel and various important class instances are setup here
	public GamePanel() {
		super();
		this.setDoubleBuffered(true);

		// attaches Keyboard class's keyListener to this JPanel
		this.addKeyListener(Keyboard.getKeyListener());

		graphicsHandler = new GraphicsHandler();

		screenManager = new ScreenManager();

		// pauseLabel = new SpriteFont("PAUSE", 365, 100, "Arial", 24, Color.white);
		// pauseLabel.setOutlineColor(Color.black);
		// pauseLabel.setOutlineThickness(2.0f);

		shopLabel = new SpriteFont("SHOP", 360, 100, "Impact", 36, Color.white);
		shopLabel.setOutlineColor(Color.black);
		shopLabel.setOutlineThickness(2.0f);

		arLabel = new SpriteFont("Assault Rifle", 360, 200, "Impact", 24, Color.white);
		arLabel.setOutlineColor(Color.black);
		arLabel.setOutlineThickness(2.0f);

		shottyLabel = new SpriteFont("Shotgun", 360, 300, "Impact", 24, Color.white);
		shottyLabel.setOutlineColor(Color.black);
		shottyLabel.setOutlineThickness(2.0f);

		// pistolLabel = new SpriteFont("Pistol", 360, 400, "Impact", 24, Color.white);
		// pistolLabel.setOutlineColor(Color.black);
		// pistolLabel.setOutlineThickness(2.0f);

		keyPressTimer = 0;
		menuItemSelected = -1;

		fpsDisplayLabel = new SpriteFont("FPS", 4, 3, "Arial", 12, Color.black);

		currentFPS = Config.TARGET_FPS;

		// this game loop code will run in a separate thread from the rest of the program
		// will continually update the game's logic and repaint the game's graphics
		GameLoop gameLoop = new GameLoop(this);
		gameLoopProcess = new Thread(gameLoop.getGameLoopProcess());
	}

	// this is called later after instantiation, and will initialize screenManager
	// this had to be done outside of the constructor because it needed to know the JPanel's width and height, which aren't available in the constructor
	public void setupGame() {
		setBackground(Colors.CORNFLOWER_BLUE);
		screenManager.initialize(new Rectangle(getX(), getY(), getWidth(), getHeight()));
	}

	// this starts the timer (the game loop is started here)
	public void startGame() {
		gameLoopProcess.start();
	}

	public ScreenManager getScreenManager() {
		return screenManager;
	}

	public void setCurrentFPS(int currentFPS) {
		this.currentFPS = currentFPS;
	}

	public void setDoPaint(boolean doPaint) {
		this.doPaint = doPaint;
	}

	public void update() {
		// updatePauseState();
		updateShowFPSState();

		//  if (!isGamePaused) {
		 	screenManager.update();
		// }
		// else {
		// 	//menuNav();
		// 		if (Keyboard.isKeyDown(Key.DOWN) && keyPressTimer == 0) {
		// 			keyPressTimer = 14;
		// 			currentMenuItemHovered++;
		// 		} else if (Keyboard.isKeyDown(Key.UP) && keyPressTimer == 0) {
		// 			keyPressTimer = 14;
		// 			currentMenuItemHovered--;
		// 		} else {
		// 			if (keyPressTimer > 0) {
		// 				keyPressTimer--;
		// 			}
		// 		}
		
		// 		// if down is pressed on last menu item or up is pressed on first menu item,
		// 		// "loop" the selection back around to the beginning/end
		// 		if (currentMenuItemHovered > 1) {
		// 			currentMenuItemHovered = 0;
		// 		} else if (currentMenuItemHovered < 0) {
		// 			currentMenuItemHovered = 1;
		// 		}
		
		// 		// sets location for blue square in front of text (pointerLocation) and also
		// 		// sets color of spritefont text based on which menu item is being hovered
		// 		if (currentMenuItemHovered == 0) {
		// 			arLabel.setColor(new Color(255, 215, 0));
		// 			shottyLabel.setColor(new Color(49, 207, 240));
		// 			pointerLocationX = 300;
		// 			pointerLocationY = 200;
		// 		} else if (currentMenuItemHovered == 1) {
		// 			arLabel.setColor(new Color(49, 207, 240));
		// 			shottyLabel.setColor(new Color(255, 215, 0));
		// 			pointerLocationX = 300;
		// 			pointerLocationY = 300;
		// 		}
		// }
	}

	// private void updatePauseState() {
	// 	if (Keyboard.isKeyDown(pauseKey) && !keyLocker.isKeyLocked(pauseKey)) {
	// 		isGamePaused = !isGamePaused;
	// 		keyLocker.lockKey(pauseKey);
	// 	}

	// 	if (Keyboard.isKeyUp(pauseKey)) {
	// 		keyLocker.unlockKey(pauseKey);
	// 	}
	// }

	private void updateShowFPSState() {
		if (Keyboard.isKeyDown(showFPSKey) && !keyLocker.isKeyLocked(showFPSKey)) {
			showFPS = !showFPS;
			keyLocker.lockKey(showFPSKey);
		}

		if (Keyboard.isKeyUp(showFPSKey)) {
			keyLocker.unlockKey(showFPSKey);
		}

		fpsDisplayLabel.setText("FPS: " + currentFPS);
	}

	public void draw() {
		screenManager.draw(graphicsHandler);

		// //if game is paused, draw pause gfx over Screen gfx
		// if (isGamePaused) {
		// 	//pauseLabel.draw(graphicsHandler);
		// 	graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), new Color(165,42,42, 255));
		// 	shopLabel.draw(graphicsHandler);
		// 	arLabel.draw(graphicsHandler);
		// 	shottyLabel.draw(graphicsHandler);
		// 	//pistolLabel.draw(graphicsHandler);
		// 	graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20,
        //         new Color(49, 207, 240), Color.black, 2);
		// }

		if (showFPS) {
			fpsDisplayLabel.draw(graphicsHandler);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (doPaint) {
			// every repaint call will schedule this method to be called
			// when called, it will setup the graphics handler and then call this class's draw method
			graphicsHandler.setGraphics((Graphics2D) g);
			draw();
		}
	}
}
