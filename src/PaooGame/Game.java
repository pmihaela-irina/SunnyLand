package PaooGame;

import PaooGame.GameWindow.*;
import PaooGame.Graphics.EntityID;
import PaooGame.GraphicsGame.*;
import PaooGame.Input.*;
import PaooGame.Items.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import static PaooGame.Levels.*;
import static PaooGame.Levels.Level.*;

public class Game extends Canvas implements Runnable {

    protected AudioPlayer audioPlayer;
    protected Thread thread;

    private boolean running = false;
    private Menu menu;


    public static int WIDTH;
    public static int HEIGHT;

    public BufferedImage level = null;
    public BufferedImage health1=null;
    public BufferedImage health2=null;
    public BufferedImage health3=null;
    public BufferedImage health4=null;
    public BufferedImage health5=null;

    public BufferedImage meniu=null;
    public BufferedImage help=null;
    public BufferedImage game_over=null;
    public BufferedImage win_game=null;
    public BufferedImage dead = null;
    public BufferedImage fundal=null;
    public BufferedImage sound_on = null;
    public BufferedImage sound_off = null;


    RefLinks refLinks;
    Camera camera;
    static Texture texture;

    public static Level LEVEL = level_1;
    public static int DIAMONDS=0;
    public static int CHERRIES=0;
    public static int ENEMYDEAD=0;
    public static int RESTART=0;
    public static int off = 0;
    public static int wg = 0;
    public static GameState State = GameState.MENU;


    public void init(){

        WIDTH=getWidth();
        HEIGHT=getHeight();

        texture = new Texture();
        menu = new Menu();

        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage(GetLevelWorld(level_2));
        game_over = loader.loadImage("/background/game_over.png");
        win_game = loader.loadImage("/background/you_win.png");
        dead = loader.loadImage("/background/dead_message.png");

        health1=loader.loadImage("/player/health1.png");
        health2=loader.loadImage("/player/health2.png");
        health3=loader.loadImage("/player/health3.png");
        health4=loader.loadImage("/player/health4.png");
        health5=loader.loadImage("/player/health5.png");

        meniu=loader.loadImage("/background/meniu.png");
        fundal=loader.loadImage("/background/back.png");
        help=loader.loadImage("/background/help.png");
        sound_on =loader.loadImage("/background/volume_on.png");
        sound_off =loader.loadImage("/background/volume_off.png");


        camera = new Camera(0,0);
        refLinks = new RefLinks(camera);

        refLinks.LoadLevel(level);

        this.addKeyListener(new KeyManager(refLinks));
        this.addMouseListener(new MouseInput());
        this.audioPlayer = new AudioPlayer(GetLevelMusic(level_1));
    }
    private void update(){
        if(State == GameState.GAME)
            refLinks.update();
            for (int i = 0; i < refLinks.entity.size(); i++) {
                if (refLinks.entity.get(i).getID() == EntityID.player) {
                    camera.update(refLinks.entity.get(i));

                    if(RESTART==1) //DOAR LA APASARE PLAY RESTART=1 & SE INCARCA LVL 1
                    {   Player.DEAD=0;
                        camera.setX(0);
                        refLinks.clearLevel();
                        Player.playerLife=50;
                        Game.CHERRIES=0;
                        Game.DIAMONDS=0;
                        refLinks.LoadLevel(level);
                        System.out.println("S-a incarcat level 1");
                        Game.LEVEL = level_1;
                        off = 0;
                        audioPlayer.setClip(GetLevelMusic(LEVEL));
                        audioPlayer.loop_play();
                        RESTART = 0;
                    }

                    if(off == 1)
                        audioPlayer.close();

                }
            }

    }

    private void draw(){
        BufferStrategy  bs = this.getBufferStrategy();
        if(bs==null)
        {
            this.createBufferStrategy(3); //am sa zic ca se incarca 3 stari ale jocului
            return;
        }

        Graphics g= bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        // aici urmeaza sa se deseneze tot ce tine de joc
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawImage(fundal,0,0,getWidth(),getHeight(),this);

        if(State == GameState.GAME) {

            g2d.translate(camera.getX(), camera.getY()); //begin of camera
            refLinks.draw(g);
            g2d.translate(-camera.getX(),-camera.getY()); //end of camera

            //randare mesaj de castig sau infrangere
            if (Game.wg == 5 && refLinks.win)
                g.drawImage(win_game, 0, 0, getWidth(),getHeight(),this);
            if (Game.wg == 5 && !refLinks.win )
                g.drawImage(game_over, 0, 0, getWidth(),getHeight(),this);

            //desenare si verificare a vietii playerului
            if (Player.playerLife <= 50 && Player.playerLife >= 41)
                g.drawImage(health1, 0, 0,142,32, this);
            if (Player.playerLife <= 40 && Player.playerLife >= 31)
                g.drawImage(health2, 0, 0,142,32, this);
            if (Player.playerLife <= 30 && Player.playerLife >= 21)
                g.drawImage(health3, 0, 0,142,32, this);
            if (Player.playerLife <= 20 && Player.playerLife >= 11)
                g.drawImage(health4, 0, 0,142,32, this);
            if (Player.playerLife <= 10 && Player.playerLife >= 1 || Player.playerLife <= 0)
                g.drawImage(health5, 0, 0,142,32, this);

            //desenare contor munitie si banuti
            for (int k = 0; k < 20; k++) {
                if (DIAMONDS == k) {
                    g.drawImage(texture.munition[k], 32, 32, this);
                }
            }
            for (int k = 0; k < 20; k++) {
                if (CHERRIES == k) {
                    g.drawImage(texture.coins[k], 32, 64, this);
                }
            }

            //desenare imagine meniu ce va fi vizibila in joc
            g.drawImage(meniu, 70, 32, this);

            //desenare buton de volum
            g.drawImage(sound_on,32,100,32,32,this);
            if(off ==1)
                g.drawImage(sound_off,32,100,32,32,this);

            if (Player.DEAD==1)
            { g.drawImage(dead,100,250, 600,140,this);}

        } else if(State == GameState.MENU){

            menu.draw(g);
        }
        if (State==GameState.HELP){
            g.drawImage(help,0,0,getWidth(),getHeight(),this);
            g.drawImage(meniu,40,80,this);
        }


        //
        g.dispose();
        bs.show();
    }
    public synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public void run()
    {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfUpdates = 60.0;
        double ns = 1000000000 / amountOfUpdates;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                update();
                updates++;
                delta--;
            }
            draw();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }



    public static  Texture getInstance(){
        return texture;
    }

    public static void main(String args[]){
        GameWindow.getInstance();
    }
}
