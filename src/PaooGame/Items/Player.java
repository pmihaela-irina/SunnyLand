package PaooGame.Items;

import PaooGame.Game;
import PaooGame.GameWindow.Camera;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.Animation;
import PaooGame.GraphicsGame.Texture;
import PaooGame.RefLinks;

import java.awt.*;
import java.util.LinkedList;

public class Player extends Entity {
    protected float gravity = 0.5f;
    protected float MAX_SPEED = 10;

    private float width = 50;
    private float height = 60;

    private RefLinks refLinks;
    private Camera camera;

    private Animation player_run_right;
    private Animation player_run_left;
    private Animation player_idle_right;
    private Animation player_idle_left;
    private Animation player_jump_right;
    private Animation player_jump_left;
    private Animation player_down_right;
    private Animation player_down_left;
    private Animation player_dead;

    public static int playerLife = 50;
    public static int DEAD = 0;

    Texture texture = Game.getInstance();

    public Player(float x, float y, RefLinks refLinks, Camera camera, EntityID id) {
        super(x, y, id);
        this.refLinks = refLinks;
        this.camera = camera;

        player_run_right = new Animation(4, texture.player[0], texture.player[1], texture.player[2], texture.player[3], texture.player[4], texture.player[5]);
        player_run_left = new Animation(4, texture.player[6], texture.player[7], texture.player[8], texture.player[9], texture.player[10], texture.player[11]);
        player_idle_right = new Animation(4, texture.player_idle[0], texture.player_idle[1], texture.player_idle[2], texture.player_idle[3]);
        player_idle_left = new Animation(4, texture.player_idle[4], texture.player_idle[5], texture.player_idle[6], texture.player_idle[7]);
        player_jump_right = new Animation(20, texture.player_jump[0], texture.player_jump[1]);
        player_jump_left = new Animation(20, texture.player_jump[2], texture.player_jump[3]);
        player_down_right = new Animation(5, texture.player_down[0], texture.player_down[1]);
        player_down_left = new Animation(5, texture.player_down[2], texture.player_down[3]);
        player_dead = new Animation(4, texture.player_dead[0], texture.player_dead[1]);
    }

    public void update(LinkedList<Entity> entity) {
        x += velX;
        y += velY;

        if (velX < 0) facing = -1;
        else if (velX > 0) facing = 1;

        if (falling || jumping) {
            velY += gravity;

            if (velY > MAX_SPEED)
                velY = MAX_SPEED;
        }

        collision(entity);
        player_run_right.runAnimation();
        player_run_left.runAnimation();
        player_idle_right.runAnimation();
        player_idle_left.runAnimation();
        player_down_right.runAnimation();
        player_down_left.runAnimation();
        player_jump_right.runAnimation();
        player_jump_left.runAnimation();
        player_dead.runAnimation();

        if (playerLife == 0) {
            velY = 0;
            velX = 0;
            DEAD = 1;

        }
        if (y >= 600) playerLife = 0;
    }

    public void draw(Graphics g) {
        if (playerLife <= 0) {
            player_dead.drawAnimation(g, (int) x, (int) y, 50, 60);
        } else {

            // player-ul ataca

            if (attacking) {
                if (facing == 1) player_idle_right.drawAnimation(g, (int) x, (int) y, 50, 60);
                else player_idle_left.drawAnimation(g, (int) x, (int) y, 50, 60);
            }

            //player-ul sare

            else if (jumping) {
                if (facing == 1) player_jump_right.drawAnimation(g, (int) x, (int) y, 50, 60);
                else player_jump_left.drawAnimation(g, (int) x, (int) y, 50, 60);
            } else {

                //player-ul alearga stanga - dreapta

                if (velX != 0) {
                    if (facing == 1) player_run_right.drawAnimation(g, (int) x, (int) y, 50, 60);
                    else
                        player_run_left.drawAnimation(g, (int) x, (int) y, 50, 60);
                } else {

                    //player-ul sta pe loc

                    if (facing == 1) player_idle_right.drawAnimation(g, (int) x, (int) y, 50, 60);
                    else player_idle_left.drawAnimation(g, (int) x, (int) y, 50, 60);
                }
            }
        }


    }

    public Rectangle getBounds() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + (height / 2)), (int) width / 2, (int) height / 2 + 5);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2, (int) height / 2 - 10);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
    }

    private void collision(LinkedList<Entity> entity) {

        for (int i = 0; i < refLinks.entity.size(); i++) {
            Entity temporary = refLinks.entity.get(i);
            if ((temporary.getID() == EntityID.block || temporary.getID() == EntityID.diamond || temporary.getID() == EntityID.gravityblock || temporary.getID() == EntityID.oposum || temporary.getID() == EntityID.cherry || temporary.getID() == EntityID.heart || temporary.getID() == EntityID.movingblock) && DEAD == 0) {

                //verificare coliziunea limite sus
                if (getBoundsTop().intersects(temporary.getBounds())) {
                    //dispare in caz ca e cireasa
                    if (temporary.getID() == EntityID.cherry) {
                        refLinks.removeEntity(temporary);
                        Game.CHERRIES++;
                    //dispare in caz ca e diamant
                    } else if (temporary.getID() == EntityID.diamond) {
                        if (Game.DIAMONDS < 19) {
                            refLinks.removeEntity(temporary);
                            Game.DIAMONDS++;
                        }
                    }
                    //dispare daca e inima
                    else if(temporary.getID() == EntityID.heart)
                    {   if (playerLife<=40)
                    {
                        refLinks.removeEntity(temporary);
                        playerLife+=10;
                    }
                    }
                    //se realizeaza coliziune si se muta player-ul cu 16
                    else {
                        y = temporary.getY() + 32;
                        velY = 0;
                    }


                    if (temporary.getID() == EntityID.oposum) playerLife--;
                }

                //verificare coliziunea limite joc
                if (getBounds().intersects(temporary.getBounds())) {
                    //disapare daca e cireasa
                    if (temporary.getID() == EntityID.cherry) {
                        refLinks.removeEntity(temporary);
                        Game.CHERRIES++;
                     //dipare daca e diamant
                    } else if (temporary.getID() == EntityID.diamond) {
                        if (Game.DIAMONDS < 19) {
                            refLinks.removeEntity(temporary);
                            Game.DIAMONDS++;
                        }
                    }

                    else if(temporary.getID() == EntityID.heart)
                    {   if (playerLife<=40)
                    {
                        refLinks.removeEntity(temporary);
                        playerLife+=10;
                    }
                    }
                    //se realizeaza coliziunea
                    else {
                        velY = 0;
                        falling = false;
                        jumping = false;
                    }


                    if (temporary.getID() == EntityID.oposum) playerLife--;
                } else falling = true;

                //verificare coliziunea limite dreapta
                if (getBoundsRight().intersects(temporary.getBounds())) {
                    //disapare daca e cireasa
                    if (temporary.getID() == EntityID.cherry) {
                        refLinks.removeEntity(temporary);
                        Game.CHERRIES++;
                        //dipare daca e diamant
                    } else if (temporary.getID() == EntityID.diamond) {
                        if (Game.DIAMONDS < 19) {
                            refLinks.removeEntity(temporary);
                            Game.DIAMONDS++;
                        }
                    }

                    else if(temporary.getID() == EntityID.heart)
                    {   if (playerLife<=40)
                    {
                        refLinks.removeEntity(temporary);
                        playerLife+=10;
                    }
                    }

                    //se realizeaza coliziunea fizica
                    else x = temporary.getX() - width;


                    if (temporary.getID() == EntityID.oposum) {
                        playerLife--;
                        y -= 96;

                    }

                }

                //verificare coliziunea limite stanga
                if (getBoundsLeft().intersects(temporary.getBounds())) {
                    //disapare daca e cireasa
                    if (temporary.getID() == EntityID.cherry) {
                        refLinks.removeEntity(temporary);
                        Game.CHERRIES++;
                        //dipare daca e diamant
                    } else if (temporary.getID() == EntityID.diamond) {
                        if (Game.DIAMONDS < 19) {
                            refLinks.removeEntity(temporary);
                            Game.DIAMONDS++;
                        }
                    }

                    else if(temporary.getID() == EntityID.heart)
                    {   if (playerLife<=40)
                    {
                        refLinks.removeEntity(temporary);
                        playerLife+=10;
                    }
                    }
                    // se realizeaza coliziune
                    else x = temporary.getX() + 35;

                    if (temporary.getID() == EntityID.oposum) {
                        playerLife--;
                        y -= 96;
                    }
                }

            } else if (temporary.getID() == EntityID.door) {
                //switch level
                if (getBounds().intersects(temporary.getBounds())) {
                    if (Game.CHERRIES >= 19)
                        refLinks.switchLevel();
                }
            }

            if (temporary.getID() == EntityID.gravityblock) {
                if (getBounds().intersects(temporary.getBounds())) {
                    gravity = 0.2f;
                }
            }
            if (temporary.getID() == EntityID.block || temporary.getID() == EntityID.oposum || temporary.getID() == EntityID.movingblock)

                if (getBounds().intersects(temporary.getBounds()))
                    gravity = 0.5f;

        }

    }
}