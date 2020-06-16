package PaooGame.Graphics;

import java.awt.*;
import java.util.LinkedList;

public abstract class Entity {
    protected float x;
    protected float y;
    protected EntityID id;
    protected float velX=0;
    protected float velY=0;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected boolean attacking = false;
    protected int facing = 1; // 1 indreptata spre dreapat ... -1 indreptata spre stanga

    public Entity(float x, float y, EntityID id){
        this.x=x;
        this.y=y;
        this.id=id;
    }

    public abstract void update(LinkedList<Entity> entity);
    public abstract void draw(Graphics g);
    public abstract Rectangle getBounds();


    public  float getX(){return x;}

    public  void setX(float x){this.x=x;}

    public  float getY(){return y;}

    public  void setY(float y){this.y=y;}

    public  float getVelX(){return velX;}
    public  float getVelY(){return velY;}

    public  void setVelX(float velX){this.velX=velX;}
    public  void setVelY(float velY){this.velY=velY;}

    public boolean isJumping() {
        return jumping;
    }
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setAttacking(boolean atking){this.attacking=atking;}
    public boolean isAttacking(){return attacking;}

    public int getFacing(){
        return facing;
    }
    public  EntityID getID(){return id;}
}
