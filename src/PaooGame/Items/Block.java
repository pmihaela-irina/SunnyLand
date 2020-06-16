package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Block extends Entity {
    public enum Type{
        grass,dirt
    }

    Texture texture = Game.getInstance();
    private Type type;

    public Block(float x, float y,Type type, EntityID id){
        super(x,y,id);
        this.type = type;
    }

    public void update(LinkedList<Entity> entity) {

    }

    public void draw (Graphics g) {
        if( type == Type.dirt)
        {
            g.drawImage(texture.block[0],(int) x,(int)  y,null);
        }
        if( type == Type.grass)
        {
            g.drawImage(texture.block[1],(int) x,(int)  y,null);
        }
    }

   public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y,32, 32);
   }
}
