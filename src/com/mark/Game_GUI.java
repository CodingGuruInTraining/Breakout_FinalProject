package com.mark;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class Game_GUI extends JPanel {
    private JPanel rootPanel;
    ///  etc...


    protected int BOARD_WIDTH = 400;
    protected int BOARD_HEIGHT = 600;

    public int getBOARD_WIDTH() { return BOARD_WIDTH; }
    public int getBOARD_HEIGHT() { return BOARD_HEIGHT; }

    Game_GUI() {
        super();
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));


// TODO will need parameters at some point maybe.
// TODO might need to move to manager.
        Paddle paddle = new Paddle(BOARD_WIDTH, BOARD_HEIGHT);
        Ball ball = new Ball();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws game board background.
        g.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        // Draws paddle at starting point.
        g.setColor(Color.white);
        g.fillRect(BOARD_WIDTH/2 - 20, BOARD_HEIGHT - 20, 40, 10);

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 40, 10);
        g.setColor(Color.blue);
        g.fillRect(0, 11, 40, 10);
    }

//    protected void drawPaddle(Graphics g) {
//
//    }


}
