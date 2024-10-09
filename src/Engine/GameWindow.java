package Engine;

import javax.swing.*;
import java.awt.Graphics;
import NPCs.WeaponPickup;
//import Engine.WeaponOverlay; // Import the WeaponOverlay class

/*
 * The JFrame that holds the GamePanel
 * Just does some setup and exposes the gamePanel's screenManager to allow an external class to setup their own content and attach it to this engine.
 */
public class GameWindow {
    private JFrame gameWindow;
    private GamePanel gamePanel;
    private WeaponOverlay weaponOverlay; // Add an instance of WeaponOverlay

    public GameWindow() {
        gameWindow = new JFrame("Game");
        gamePanel = new GamePanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the weapon overlay here
                if (WeaponPickup.showOverlay) {
                    weaponOverlay.draw(g); 
            } 
            }
        };

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        gameWindow.setContentPane(gamePanel);
        gameWindow.setResizable(false);
        gameWindow.setSize(Config.GAME_WINDOW_WIDTH, Config.GAME_WINDOW_HEIGHT);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // it'd be nice if this actually worked more than 1/3rd of the time

        // Initialize the weapon overlay
        weaponOverlay = new WeaponOverlay();

        gamePanel.setupGame();
    }

    // triggers the game loop to start as defined in the GamePanel class
    public void startGame() {
        gamePanel.startGame();
    }

    public ScreenManager getScreenManager() {
        return gamePanel.getScreenManager();
    }
}
