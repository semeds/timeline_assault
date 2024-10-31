package Engine;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class WeaponOverlay {
    private Image shotgunImage;
    private int x, y;
    private int width, height; 

    public WeaponOverlay() {
        shotgunImage = new ImageIcon("Resources/TransparentShottyOverlay.png").getImage();
        
        // Position set to the bottom-left corner 
        x = -20;  
        y = 525; 

        // 4x scale of image
        width = 38 * 4;  
        height = 14 * 4; 
    }

    public void draw(Graphics g) {
        g.drawImage(shotgunImage, x, y, width, height, null);
    }

    // Getter methods for position and dimensions
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
