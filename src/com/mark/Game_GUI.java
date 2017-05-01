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


    Game_GUI() {
        super();
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
//        setContentPane(rootPanel);
//        pack();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);

// TODO will need parameters at some point maybe.
// TODO might need to move to manager.
        Paddle paddle = new Paddle();
        Ball ball = new Ball();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);


    }

    protected void drawPaddle(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(BOARD_WIDTH/2 - 20, BOARD_HEIGHT - 10, 40, 10);
    }


}
