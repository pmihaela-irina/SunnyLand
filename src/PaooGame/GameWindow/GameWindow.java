package PaooGame.GameWindow;

import PaooGame.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private static GameWindow instance = null;

    public GameWindow(int w, int h, String title, Game game){

        game.setPreferredSize(new Dimension(w,h));
        game.setMaximumSize(new Dimension(w,h));
        game.setMinimumSize(new Dimension(w,h));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }
    public static GameWindow getInstance(){

        if(instance == null)
            instance = new GameWindow(800,600,"SunnyLand", new Game());
        return instance;
    }
}
