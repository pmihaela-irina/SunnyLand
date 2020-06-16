package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Animation;
import PaooGame.GraphicsGame.Texture;

import java.awt.*;
import java.util.LinkedList;

public class MovingBlock extends Entity {


    Texture texture = Game.getInstance();

    public MovingBlock(float x, float y, EntityID id) {

        super(x, y, id);

    }

    boolean movingLeft = true;
    float pozstart = getX();
    float pozmax = pozstart - 300;//DISTANTA

    public void update(LinkedList<Entity> entity) {

        //MISCARE STANGA DREAPTA
        if (movingLeft)
            x -= 5;
        else
            x += 5;
        if (x == pozmax)
            movingLeft = false;
        else if (x == pozstart)
            movingLeft = true;

    }

    public void draw(Graphics g) {

        g.drawImage(texture.block[0], (int) x, (int) y, 32, 32,null);

    }



    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }


}

