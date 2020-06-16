package PaooGame.GraphicsGame;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage loadImage(int col, int row, int width, int height){
        return image.getSubimage((col*width) -  width,(row*height)-height, width, height);
    }
}
