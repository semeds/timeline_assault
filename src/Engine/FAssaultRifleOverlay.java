package Engine;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class FAssaultRifleOverlay {
    private Image pistolImage;
    private int x, y;
    private int width, height; 

    public FAssaultRifleOverlay() {
        pistolImage = new ImageIcon("Resources/Transparentfuture_ar.png").getImage();
        
        // Position set to the bottom-left corner 
        x = 16;  
        y = 533; 

        // 4x scale of image
        width = 62;  
        height = 50; 
    }

    public void draw(Graphics g) {
        g.drawImage(pistolImage, x, y, width, height, null);
    }

    // Getter methods for position/dimensions
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
