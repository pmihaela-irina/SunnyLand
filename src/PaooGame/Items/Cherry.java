package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Animation;
import PaooGame.GraphicsGame.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Cherry extends Entity {

    Texture texture = Game.getInstance();
    private Animation cherry;

    public Cherry(float x, float y, EntityID id){

        super(x,y,id);
        cherry = new Animation(10, texture.cherry[0],texture.cherry[1],texture.cherry[2],texture.cherry[3],texture.cherry[4]);

    }

    public void update(LinkedList<Entity> entity) {
        cherry.runAnimation();
    }

    public void draw(Graphics g) {
        cherry.drawAnimation(g,(int)x,(int)y,32,32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }
}
