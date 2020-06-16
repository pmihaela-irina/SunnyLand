package PaooGame;

import PaooGame.GraphicsGame.BufferedImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu {

    public Rectangle playButton=new Rectangle(Game.WIDTH/2-50,200,100,50);
    public Rectangle helpButton=new Rectangle(Game.WIDTH/2-50,300,100,50);
    public Rectangle quitButton=new Rectangle(Game.WIDTH/2-50,400,100,50);

    BufferedImageLoader loader = new BufferedImageLoader();
    public BufferedImage title = loader.loadImage("/background/title-screen.png");


    public void draw(Graphics g){
        Graphics2D g2d=(Graphics2D) g; // cast
        Font fnt0=new Font("arial",Font.BOLD,50);
        g.setFont(fnt0);
        g.setColor(new Color(250,95,38));
        g.drawImage(title,Game.WIDTH/2-150,20,294,138,null);
        //g.drawString("MY Game",Game.WIDTH/2-150,100);

        Font fnt1=new Font("arial",Font.BOLD,30);
        g.setFont(fnt1);
        g.drawString("Play",playButton.x+19,playButton.y+30);
        g2d.draw(playButton);


        g.drawString("Help",helpButton.x+19,helpButton.y+30);
        g2d.draw(helpButton);


        g.drawString("Quit",quitButton.x+19,quitButton.y+30);
        g2d.draw(quitButton);
    }
}
