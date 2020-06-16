package PaooGame.GraphicsGame;

import java.awt.image.BufferedImage;


public class Texture {

    SpriteSheet bs; // block sheet
    SpriteSheet ps; // player sheet
    SpriteSheet psb; //player sheet back
    SpriteSheet os; //oposum
    SpriteSheet buls; //bullet
    SpriteSheet muns; //munition
    SpriteSheet cs; //coins
    SpriteSheet chs; //cherry(munition)
    SpriteSheet ds; //diamond(coins)
    SpriteSheet hs; //heart

    protected BufferedImage block_sheet = null;
    protected BufferedImage player_sheet = null;
    protected BufferedImage player_sheet_back = null;
    protected BufferedImage oposum_sheet = null;
    protected BufferedImage bullet_sheet = null;
    protected BufferedImage munition_sheet = null;
    protected BufferedImage coin_sheet = null;
    protected BufferedImage cherry_sheet = null;
    protected BufferedImage diamond_sheet = null;
    protected BufferedImage heart_sheet = null;

    public BufferedImage[] block = new BufferedImage[6];
    public BufferedImage[] player = new BufferedImage[15];
    public BufferedImage[] player_jump = new BufferedImage[4];
    public BufferedImage[] player_idle = new BufferedImage[8];
    public BufferedImage[] player_down = new BufferedImage[4];
    public BufferedImage[] player_dead = new BufferedImage[4];
    public BufferedImage[] oposum_attack = new BufferedImage[12];
    public BufferedImage[] bullet = new BufferedImage[5];
    public BufferedImage[] munition = new BufferedImage[20];
    public BufferedImage[] coins = new BufferedImage[20];
    public BufferedImage[] cherry = new BufferedImage[5];
    public BufferedImage[] diamond = new BufferedImage[5];
    public BufferedImage[] heart = new BufferedImage[8];

    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            block_sheet = loader.loadImage("/texture/block.png");
            player_sheet = loader.loadImage("/player/player.png");
            player_sheet_back = loader.loadImage("/player/player_back.png");
            oposum_sheet = loader.loadImage("/enemy/oposum.png");
            bullet_sheet = loader.loadImage("/texture/bullet.png");
            munition_sheet = loader.loadImage("/texture/munition.png");
            coin_sheet = loader.loadImage("/texture/coin.png");
            cherry_sheet = loader.loadImage("/texture/cherry.png");
            diamond_sheet = loader.loadImage("/texture/gem.png");
            heart_sheet = loader.loadImage("/texture/heart.png");

        } catch (Exception e) {
            e.printStackTrace();
        }

        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        psb = new SpriteSheet(player_sheet_back);
        os = new SpriteSheet(oposum_sheet);
        buls = new SpriteSheet(bullet_sheet);
        muns = new SpriteSheet(munition_sheet);
        cs = new SpriteSheet(coin_sheet);
        chs = new SpriteSheet(cherry_sheet);
        ds = new SpriteSheet(diamond_sheet);
        hs = new SpriteSheet(heart_sheet);

        getTexture();
    }

    private void getTexture() {

        block[0] = bs.loadImage(1, 1, 32, 32); //dirt
        block[1] = bs.loadImage(2, 1, 32, 32); //grass

        //player run right
        player[0] = ps.loadImage(1, 1, 33, 32);
        player[1] = ps.loadImage(2, 1, 33, 32);
        player[2] = ps.loadImage(3, 1, 33, 32);
        player[3] = ps.loadImage(4, 1, 33, 32);
        player[4] = ps.loadImage(5, 1, 33, 32);
        player[5] = ps.loadImage(6, 1, 33, 32);

        //player run left
        player[6] = psb.loadImage(1, 1, 33, 32);
        player[7] = psb.loadImage(2, 1, 33, 32);
        player[8] = psb.loadImage(3, 1, 33, 32);
        player[9] = psb.loadImage(4, 1, 33, 32);
        player[10] = psb.loadImage(5, 1, 33, 32);
        player[11] = psb.loadImage(6, 1, 33, 32);

        //player idle right
        player_idle[0] = ps.loadImage(1, 3, 33, 32);
        player_idle[1] = ps.loadImage(2, 3, 33, 32);
        player_idle[2] = ps.loadImage(3, 3, 33, 32);
        player_idle[3] = ps.loadImage(4, 3, 33, 32);

        //player idle left
        player_idle[4] = psb.loadImage(1, 3, 33, 32);
        player_idle[5] = psb.loadImage(2, 3, 33, 32);
        player_idle[6] = psb.loadImage(3, 3, 33, 32);
        player_idle[7] = psb.loadImage(4, 3, 33, 32);

        //player jump right
        player_jump[0] = ps.loadImage(5, 2, 33, 32);
        player_jump[1] = ps.loadImage(6, 2, 33, 32);

        //player jump left
        player_jump[2] = psb.loadImage(5, 2, 33, 32);
        player_jump[3] = psb.loadImage(6, 2, 33, 32);

        //player crouch right
        player_down[0] = ps.loadImage(1, 2, 33, 32);
        player_down[1] = ps.loadImage(2, 2, 33, 32);

        //player crouch left
        player_down[2] = psb.loadImage(1, 2, 33, 32);
        player_down[3] = psb.loadImage(2, 2, 33, 32);

        //player hurt/dead
        player_dead[0] = ps.loadImage(3, 2, 33, 32);
        player_dead[1] = ps.loadImage(4, 2, 33, 32);

        //oposum_left(enemy)

        oposum_attack[0] = os.loadImage(1, 1, 36, 28);
        oposum_attack[1] = os.loadImage(2, 1, 36, 28);
        oposum_attack[2] = os.loadImage(3, 1, 36, 28);
        oposum_attack[3] = os.loadImage(4, 1, 36, 28);
        oposum_attack[4] = os.loadImage(5, 1, 36, 28);
        oposum_attack[5] = os.loadImage(6, 1, 36, 28);

        //oposum_right
        oposum_attack[6] = os.loadImage(1, 2, 36, 28);
        oposum_attack[7] = os.loadImage(2, 2, 36, 28);
        oposum_attack[8] = os.loadImage(3, 2, 36, 28);
        oposum_attack[9] = os.loadImage(4, 2, 36, 28);
        oposum_attack[10] = os.loadImage(5, 2, 36, 28);
        oposum_attack[11] = os.loadImage(6, 2, 36, 28);

        //bullet
        bullet[0] = buls.loadImage(1, 1, 32, 32);
        bullet[1] = buls.loadImage(2, 1, 32, 32);
        bullet[2] = buls.loadImage(1, 2, 32, 32);
        bullet[3] = buls.loadImage(2, 2, 32, 32);

        //munition(cherry)
        cherry[0] = chs.loadImage(1, 1, 21, 21);
        cherry[1] = chs.loadImage(2, 1, 21, 21);
        cherry[2] = chs.loadImage(3, 1, 21, 21);
        cherry[3] = chs.loadImage(4, 1, 21, 21);
        cherry[4] = chs.loadImage(5, 1, 21, 21);

        //coins(diamond)
        diamond[0] = ds.loadImage(1, 1, 15, 13);
        diamond[1] = ds.loadImage(2, 1, 15, 13);
        diamond[2] = ds.loadImage(3, 1, 15, 13);
        diamond[3] = ds.loadImage(4, 1, 15, 13);
        diamond[4] = ds.loadImage(5, 1, 15, 13);

        //block ce modifica gravitatia
        block[3] = bs.loadImage(1, 2, 32, 32);

        //usita portal
        block[4] = bs.loadImage(2, 2, 32, 32);

        //heart
        heart[0]=hs.loadImage(1,1,18,14);
        heart[1]=hs.loadImage(2,1,18,14);
        heart[2]=hs.loadImage(3,1,18,14);
        heart[3]=hs.loadImage(4,1,18,14);
        heart[4]=hs.loadImage(5,1,18,14);
        heart[5]=hs.loadImage(6,1,18,14);
        heart[6]=hs.loadImage(7,1,18,14);
        heart[7]=hs.loadImage(8,1,18,14);

        //preiau numarul de cirese
        for (int col = 0; col <= 9; col++)
            munition[col] = muns.loadImage(col + 1, 1, 32, 32);
        for (int col = 10; col <= 19; col++)
            munition[col] = muns.loadImage(col - 9, 2, 32, 32);

        //preiau numarul de diamante
        for (int col = 0; col <= 9; col++)
            coins[col] = cs.loadImage(col + 1, 1, 32, 32);
        for (int col = 10; col <= 19; col++)
            coins[col] = cs.loadImage(col - 9, 2, 32, 32);
    }
}
