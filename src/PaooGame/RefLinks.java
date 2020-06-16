package PaooGame;

import PaooGame.GameWindow.Camera;
import PaooGame.Graphics.Entity;
import PaooGame.Graphics.EntityFactory;
import PaooGame.GraphicsGame.BufferedImageLoader;
import PaooGame.Items.*;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static PaooGame.Levels.*;
import static PaooGame.Levels.Level.*;

public class RefLinks {

    private Entity temporary;
    private Camera camera;
    private EntityFactory entityFactory;
    
    public LinkedList<Entity> entity = new LinkedList<Entity>();
    public static boolean win = false;
    
    private BufferedImage level2;
    private BufferedImage level3;
    private BufferedImage level4;
    private BufferedImage level5;

    
    public RefLinks(Camera camera){
        
        this.camera = camera;
        BufferedImageLoader loader = new BufferedImageLoader();
        level2 = loader.loadImage(GetLevelWorld(level_2));
        level3 = loader.loadImage(GetLevelWorld(Game.LEVEL.level_3));
        level4 = loader.loadImage(GetLevelWorld(Game.LEVEL.level_4));
        level5 = loader.loadImage(GetLevelWorld(Game.LEVEL.level_5));
        entityFactory = new EntityFactory(this,camera);
    }

    public void LoadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        //urmeaza decodificarea matricii nivelului
        for (int xx = 0; xx < h; xx++) {
            for (int yy = 0; yy < w; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 255 && green == 120 && blue == 0)
                    addEntity(entityFactory.createEntity(9,xx * 32, yy * 32));

                if (red == 255 && green == 255 && blue == 255)
                    addEntity(entityFactory.createEntity(8,xx * 32, yy * 32));

                if (red == 128 && green == 128 && blue == 128)
                    addEntity(entityFactory.createEntity(10,xx * 32, yy * 32));

                if (red == 0 && green == 0 && blue == 255)
                    addEntity(entityFactory.createEntity(1,xx * 32, yy * 32));

                if (red == 149 && green == 101 && blue == 194)
                    addEntity(entityFactory.createEntity(5,xx * 32, yy * 32));

                if (red ==255 && green == 255 && blue == 0)
                    addEntity(entityFactory.createEntity(3,xx * 32, yy * 32));

                if (red ==255 && green == 0 && blue == 0)
                    addEntity(entityFactory.createEntity(7,xx * 32, yy * 32));

                if (red == 240 && green == 123 && blue == 111)
                    addEntity(entityFactory.createEntity(6,xx * 32, yy * 32));

                if (red == 255 && green == 0 && blue == 246)
                    addEntity(entityFactory.createEntity(4,xx * 32, yy * 32));

                if (red == 48 && green == 255 && blue == 0)
                    addEntity(entityFactory.createEntity(2,xx * 32, yy * 32));

                if (red == 158 && green == 0 && blue == 0)
                    addEntity(entityFactory.createEntity(7,xx * 32, yy * 32));

            }
        }
    }


    public void switchLevel() {
        clearLevel();
        camera.setX(0);
        switch (Game.LEVEL) {
            case level_1:
                System.out.println("Case 1");
                Player.playerLife = 50;
                Game.CHERRIES = 0;
                Game.DIAMONDS = 0;
                LoadLevel(level2);
                Game.LEVEL= level_2;
                 System.out.println("S-a incarcat level 2");
                System.out.println("Oposumi morti" + Game.ENEMYDEAD);
                break;
            case level_2:
                System.out.println("Case 2");
                Player.playerLife = 50;
                Game.CHERRIES = 0;
                Game.DIAMONDS = 0;
                LoadLevel(level3);
                Game.LEVEL=level_3;
                System.out.println("S-a incarcat level 3");
                System.out.println("Oposumi morti" + Game.ENEMYDEAD);
                if(Game.ENEMYDEAD == 2)
                {
                    win = true;
                }
                break;

            case level_3:
                System.out.println("Case 3");
                Player.playerLife = 50;
                Game.CHERRIES = 0;
                Game.DIAMONDS = 0;
                LoadLevel(level4);
                Game.LEVEL=level_4;
                //  System.out.println("S-a incarcat level 4");
                System.out.println("Oposumi morti" + Game.ENEMYDEAD);
                break;
            case level_4:
                System.out.println("Case 4");
                Player.playerLife = 50;
                Game.CHERRIES = 0;
                Game.DIAMONDS = 0;
                LoadLevel(level5);
                Game.LEVEL=level_5;
                System.out.println("Oposumi morti" + Game.ENEMYDEAD);
                break;
            case level_5:
                if(Game.ENEMYDEAD == 20)
                {
                    win = true;
                }
       }
        Game.wg++;
    }


    public void update(){

        for(int i = 0; i < entity.size(); i++){
            temporary = entity.get(i);

            temporary.update(entity);
        }
    }

    public void draw(Graphics g){

        for(int i = 0; i < entity.size(); i++){
            temporary = entity.get(i);

            temporary.draw(g);
        }
    }

    public void addEntity(Entity entity){
        this.entity.add(entity);
    }

    public void removeEntity(Entity entity){
        this.entity.remove(entity);
    }

    public void clearLevel() {
        entity.clear();
    }
}
