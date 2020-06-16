package PaooGame.Items;

import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Animation;
import PaooGame.GraphicsGame.Texture;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.LinkedList;

public class Oposum extends Entity {

    boolean movingRight = true;
    float pozstart = getX();
    //    float pozmax = pozstart + 270;//DISTANTA
    private int oposumLife = 2;
    private Animation oposum_right;
    private Animation oposum_left;

    private RefLinks refLinks;
    Texture texture = Game.getInstance();


    public Oposum(float x, float y, RefLinks refLinks, EntityID id) {

        super(x, y, id);
        this.refLinks = refLinks;
        oposum_left = new Animation(10, texture.oposum_attack[0], texture.oposum_attack[1], texture.oposum_attack[2], texture.oposum_attack[3], texture.oposum_attack[4], texture.oposum_attack[5]);
        oposum_right = new Animation(10, texture.oposum_attack[6], texture.oposum_attack[7], texture.oposum_attack[8], texture.oposum_attack[9], texture.oposum_attack[10], texture.oposum_attack[11]);

    }

    public void update(LinkedList<Entity> entity) {

        oposum_right.runAnimation();
        oposum_left.runAnimation();
        if (movingRight) {
            facing = 1;
            x += 2;
        } else {
            facing = -1;
            x -= 2;
        }

        coliziuneOposum(entity);
    }

    public void draw(Graphics g) {
        if(facing == 1)
            oposum_right.drawAnimation(g, (int) x, (int) y+ 25, 46, 38);
        else
            oposum_left.drawAnimation(g, (int) x, (int) y + 25, 46, 38);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    private void coliziuneOposum(LinkedList<Entity> entity) {
        for (int i = 0; i < refLinks.entity.size(); i++) {
            Entity temporary = refLinks.entity.get(i);
            if (temporary.getID() == EntityID.bullet) {
                if (getBounds().intersects(temporary.getBounds())) {
                    oposumLife--;
                    refLinks.removeEntity(temporary);
                    if (oposumLife == 0) {
                        refLinks.removeEntity(this);
                        Game.ENEMYDEAD++;
                    }
                }
            }
            if (temporary.getID() == EntityID.block || temporary.getID() == EntityID.gravityblock){
                if (getBounds().intersects(temporary.getBounds()))
                    movingRight = !movingRight;
            }
        }
    }
}