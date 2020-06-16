package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Animation;
import PaooGame.GraphicsGame.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Diamond extends Entity {

    Texture texture = Game.getInstance();
    private Animation diamond;

    public Diamond(float x, float y, EntityID id){

        super(x,y,id);
        diamond=new Animation(10,texture.diamond[0],texture.diamond[1],texture.diamond[2],texture.diamond[3],texture.diamond[4]);

    }

    public void update(LinkedList<Entity> entity) {
        diamond.runAnimation();
    }

    public void draw(Graphics g) { diamond.drawAnimation(g,(int)x,(int)y,32,32);}

    public Rectangle getBounds() {return new Rectangle((int)x,(int)y,16,16);}


}

