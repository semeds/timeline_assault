package Screens;

import Engine.*;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the level lose screen
public class LevelLoseScreen extends Screen {
    protected SpriteFont loseMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected WorldOneScreen playLevelScreen;
    protected WorldTwoScreen playLevelScreen2;
    protected WorldThreeScreen playLevelScreen3;

   private boolean showFPistolOverlay = false;
   private boolean showFAssaultRifleOverlay = false;
   private boolean showFShotgunOverlay = false;
   private boolean showAPistolOverlay = false;
   private boolean showAAssaultRifleOverlay = false;
   private boolean showAShotgunOverlay = false;
   private boolean showMPistolOverlay = false;
   private boolean showMAssaultRifleOverlay = false;
   private boolean showMShotgunOverlay = false;

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

    public LevelLoseScreen(WorldOneScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
        resetOverlays();
    }

    public LevelLoseScreen(WorldTwoScreen playLevelScreen2) {
        this.playLevelScreen2 = playLevelScreen2;
        initialize();
        resetOverlays();
    }

    public LevelLoseScreen(WorldThreeScreen playLevelScreen3) {
        this.playLevelScreen3 = playLevelScreen3;
        initialize();
        resetOverlays();
    }

    @Override
    public void initialize() {
        loseMessage = new SpriteFont("You lose!", 350, 239, "Arial", 30, Color.white);
        instructions = new SpriteFont("Press Space to try again or Escape to go back to the main menu", 120, 279,"Arial", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            playLevelScreen.resetLevel();
        } else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            playLevelScreen.goBackToMenu();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        loseMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
