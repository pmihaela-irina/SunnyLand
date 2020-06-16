package PaooGame.Items;


import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Animation;
import PaooGame.GraphicsGame.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Heart extends Entity {

    Texture texture= Game.getInstance();
    private Animation heart;

    public Heart(float x, float y, EntityID id){

        super(x,y,id);
        heart=new Animation(10,texture.heart[0],texture.heart[1],texture.heart[2],texture.heart[3],texture.heart[4],texture.heart[5],texture.heart[6],texture.heart[7]);

    }

    public void update(LinkedList<Entity> entity) {
        heart.runAnimation();
    }

    public void draw(Graphics g) {
        heart.drawAnimation(g,(int)x,(int)y,32,32);
    }

    public Rectangle getBounds() {return new Rectangle((int)x,(int)y,32,32);}


}


