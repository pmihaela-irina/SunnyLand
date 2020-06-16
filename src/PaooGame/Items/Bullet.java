package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Texture;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends Entity {

    Texture texture = Game.getInstance();
    RefLinks refLinks;

    public Bullet(float x, float y, RefLinks refLinks, EntityID id, int velX) {
        super(x, y, id);
        this.refLinks = refLinks;
        this.velX = velX;

    }

    public void update(LinkedList<Entity> entity) {
        x += velX;
        y += 0.3f;
        if (velX > 0) facing = 1;
        else if (velX < 0) facing = -1;

        coliziuneBullet(entity);
    }

    public void draw(Graphics g) {
        if (facing == 1) {
            g.drawImage(texture.bullet[2], (int) x, (int) y, 25, 25, null);
        } else if (facing == -1)
            g.drawImage(texture.bullet[2], (int) x, (int) y, 25, 25, null);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    private void coliziuneBullet(LinkedList<Entity> entity) {
        for (int i = 0; i < refLinks.entity.size(); i++) {
            Entity temporary = refLinks.entity.get(i);
            if(temporary.getID() == EntityID.block || temporary.getID() == EntityID.gravityblock || temporary.getID() == EntityID.oposum){
                if (getBounds().intersects(temporary.getBounds())) {
                    refLinks.removeEntity(this);

                }
            }
        }
    }

}
