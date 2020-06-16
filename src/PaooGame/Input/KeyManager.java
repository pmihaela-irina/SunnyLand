package PaooGame.Input;

import PaooGame.Game;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.Items.Bullet;
import PaooGame.RefLinks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

import static PaooGame.Items.Player.DEAD;

public class KeyManager extends KeyAdapter {

    RefLinks refLinks;

    public KeyManager(RefLinks refLinks) {this.refLinks = refLinks; }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < refLinks.entity.size(); i++) {
            Entity temporary = refLinks.entity.get(i);
            if (temporary.getID() == EntityID.player) {
                if (key == KeyEvent.VK_RIGHT && DEAD == 0) {
                    temporary.setVelX(5);
                }
                if (key == KeyEvent.VK_LEFT && DEAD == 0) {
                    temporary.setVelX(-5);
                }
                if (((key == KeyEvent.VK_UP) && DEAD == 0)&& !temporary.isJumping()){

                    temporary.setJumping(true);
                    temporary.setVelY(-10);
                }
                if(key==KeyEvent.VK_SPACE && DEAD==0)
                {   if(Game.DIAMONDS!=0) {
                    Game.DIAMONDS--;
                    temporary.setAttacking(true);
                    int facing = temporary.getFacing();
                    if (facing == 1)
                        refLinks.addEntity(new Bullet(temporary.getX() + 40, temporary.getY() +20 ,refLinks, EntityID.bullet, temporary.getFacing() * 10));
                    else
                        refLinks.addEntity(new Bullet(temporary.getX() -10, temporary.getY() + 20 ,refLinks, EntityID.bullet, temporary.getFacing() * 10));
                }
                else System.out.println("Munitie insuficienta");
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < refLinks.entity.size(); i++) {
            Entity temporary = refLinks.entity.get(i);
            if (temporary.getID() == EntityID.player) {
                if (key == KeyEvent.VK_RIGHT) {
                    temporary.setVelX(0);
                }
                if (key == KeyEvent.VK_LEFT) {
                    temporary.setVelX(0);
                }
                if(key == KeyEvent.VK_UP){
                    temporary.setAttacking(false);
                }
            }

        }
    }
}

