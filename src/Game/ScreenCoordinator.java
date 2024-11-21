package Game;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Screens.*;

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
	// currently shown Screen
	protected Screen currentScreen = new DefaultScreen();

	// keep track of gameState so ScreenCoordinator knows which Screen to show
	protected GameState gameState;
	protected GameState previousGameState;

   private boolean showFPistolOverlay = false;
   private boolean showFAssaultRifleOverlay = false;
   private boolean showFShotgunOverlay = false;
   private boolean showAPistolOverlay = false;
   private boolean showAAssaultRifleOverlay = false;
   private boolean showAShotgunOverlay = false;
   private boolean showMPistolOverlay = false;
   private boolean showMAssaultRifleOverlay = false;
   private boolean showMShotgunOverlay = false;

	public GameState getGameState() {
		return gameState;
	}

	// Other Screens can set the gameState of this class to force it to change the currentScreen
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public void resetOverlays() {
		showFPistolOverlay = false;
		showFAssaultRifleOverlay = false;
		showFShotgunOverlay = false;
		showAPistolOverlay = false;
   	    showAAssaultRifleOverlay = false;
        showAShotgunOverlay = false;
		showMPistolOverlay = false;
        showMAssaultRifleOverlay = false;
        showMShotgunOverlay = false;

	}

	@Override
	public void initialize() {
		// start game off with Menu Screen
		gameState = GameState.MENU;
	}

	@Override
	public void update() {
		do {
			// if previousGameState does not equal gameState, it means there was a change in gameState
			// this triggers ScreenCoordinator to bring up a new Screen based on what the gameState is
			if (previousGameState != gameState) {
				switch(gameState) {
					case MENU:
						currentScreen = new MenuScreen(this);
						resetOverlays();
						break;
					case LEVEL:
						currentScreen = new WorldOneScreen(this);
						resetOverlays();
						break;
					case WORLDTWO:
						currentScreen = new WorldTwoScreen(this);
						resetOverlays();
						break;
					case WORLDTHREE:
						currentScreen = new WorldThreeScreen(this);
						resetOverlays();
						break;
					case GAMECOMPLETE:
						currentScreen = new GameCompleteScreen(this);
						resetOverlays();
						break;
					case CREDITS:
						currentScreen = new CreditsScreen(this);
						resetOverlays();
						break;
				}
				currentScreen.initialize();
			}
			previousGameState = gameState;

			// call the update method for the currentScreen
			currentScreen.update();
		} while (previousGameState != gameState);
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		// call the draw method for the currentScreen
		currentScreen.draw(graphicsHandler);
	}
}
