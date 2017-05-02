package com.mark;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class Game_GUI {
    private JFrame frame;
    private JPanel rootPanel;
    ///  etc...
    private Canvas canvas;

    protected int BOARD_WIDTH = 300;
    protected int BOARD_HEIGHT = 400;

    public int getBOARD_WIDTH() { return BOARD_WIDTH; }
    public int getBOARD_HEIGHT() { return BOARD_HEIGHT; }

    public Game_GUI() {
        frame = new JFrame("Breakout Attempt");
        canvas = new Canvas();

//
//        super();
//        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
//
//
//// TODO will need parameters at some point maybe.
//// TODO might need to move to manager.
//        Paddle paddle = new Paddle(BOARD_WIDTH, BOARD_HEIGHT);
//        Ball ball = new Ball();
//
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        // Draws game board background.
//        g.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
//        g.setColor(Color.black);
//        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
//
//        // Draws paddle at starting point.
//        g.setColor(Color.white);
//        g.fillRect(BOARD_WIDTH / 2 - 20, BOARD_HEIGHT - 20, 40, 10);
//
//        // Static additions to figure out placement.
//// TODO find this code a home
//        Color[] colorArray = {Color.green, Color.blue, Color.cyan, Color.yellow};
////        int x = 0;
//        int y = 0;
//        for (int k = 0; k < colorArray.length; k++) {
////            g.setColor(Color.GREEN);
//            int brickWidth = 40;
//            int brickHeight = 10;
//            int x = 0;
////            int y = 0;
//            for (int i = 0; i < 10; i++) {
//// TODO might want buffer room later on
//                g.setColor(colorArray[k]);
//                g.fillRect(x, y, brickWidth, brickHeight);
//                g.setColor(Color.white);
//                g.drawRect(x, y, brickWidth, brickHeight);
//// TODO could do (x + (x * i), (y +....
//                x += brickWidth;
////            y += brickHeight;
//                if (i == 9) { y += brickHeight; }
//            }
////            y += brickHeight;
////            g.setColor(Color.blue);
////            g.fillRect(0, 11, 40, 10);
//        }
//    }
////    protected void drawPaddle(Graphics g) {
////
////    }
//
    }
}
