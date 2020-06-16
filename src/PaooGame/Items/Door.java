package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Texture;

import java.awt.*;
import java.util.LinkedList;

public class Door extends Entity {

    Texture texture = Game.getInstance();

    public Door(float x, float y, EntityID id) {
        super(x, y, id);
    }

    public void update(LinkedList<Entity> entity) {}

    public void draw(Graphics g) {
        g.drawImage(texture.block[4],(int)x,(int)y,32,32,null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
}

