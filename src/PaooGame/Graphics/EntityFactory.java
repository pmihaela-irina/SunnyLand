package PaooGame.Graphics;

import PaooGame.GameWindow.Camera;
import PaooGame.Items.*;
import PaooGame.RefLinks;


import static  PaooGame.Items.Block.Type.*;

public class EntityFactory{
    private RefLinks refLinks;
    private Camera camera;

    public EntityFactory(RefLinks refLinks, Camera camera){
        this.refLinks = refLinks;
        this.camera = camera;
    }

    public Entity createEntity(int code, float x, float y){
        switch(code){
            case 1:
                return new Player(x,y,refLinks,camera,EntityID.player);
            case 2:
                return new Heart(x,y,EntityID.heart);
            case 3:
                return new Cherry(x,y,EntityID.cherry);
            case 4:
                return new Diamond(x,y,EntityID.diamond);
            case 5:
                return new Door(x,y,EntityID.door);
            case 6:
                return new GravityChangeBlock(x,y,EntityID.gravityblock);
            case 7:
                return new Oposum(x,y,refLinks,EntityID.oposum);
            case 8:
                return new Block(x,y,dirt,EntityID.block);
            case 9:
                return new MovingBlock(x,y,EntityID.movingblock);
            default:
                return new Block(x,y,grass,EntityID.block);
        }

    }
}
