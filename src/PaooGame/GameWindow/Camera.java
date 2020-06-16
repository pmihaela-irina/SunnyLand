package PaooGame.GameWindow;

import PaooGame.Game;
import PaooGame.Graphics.Entity;

public class Camera  {
    private float x;
    private float y;

    public Camera(float x, float y){
        this.x=x;
        this.y=y;
    }

    public void update(Entity player){
        x = -player.getX()+(float)Game.WIDTH/2;
    }

    public  float getX(){return x;}

    public  void setX(float x){this.x=x;}

    public  float getY(){return y;}

    public  void setY(float y){this.y=y;}
}
