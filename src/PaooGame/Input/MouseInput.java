package PaooGame.Input;

import PaooGame.Game;
import PaooGame.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

        int mx=e.getX();
        int my=e.getY();


        if(Game.State!= GameState.GAME) {
            //PlayState
            if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2+50)
                if (my >= 200 && my <= 300)
                {
                    Game.State = GameState.GAME;
                    Game.RESTART=1;
                    Game.off = 1;

                }


            //HelpState
            if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2+50)
                if (my >= 300 && my <= 350)
                    Game.State = GameState.HELP;

            //QuitState
            if (mx >= Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2+50)
                if (my >= 400 && my <= 450)
                    System.exit(1);
        }else {
            if (mx >= 70 && mx <=200 )
                if (my >= 32 && my <= 72)
                    Game.State = GameState.MENU;

            if(mx >= 32 && mx <= 64)
                if(my >= 100 && my <=132)
                    Game.off = 1;
        }
        if (Game.State ==GameState.HELP){
            if (mx >= 40 && mx <= 170)
                if (my >= 80 && my <= 120)
                    Game.State = GameState.MENU;


        }



    }

    public void mouseReleased(MouseEvent e) {
        Game.RESTART=0;
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
