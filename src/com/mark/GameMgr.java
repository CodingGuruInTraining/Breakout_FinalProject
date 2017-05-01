package com.mark;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class GameMgr {

    protected Game_GUI gui;
    protected JFrame frame;

    protected ArrayList<Brick> bricks;
    protected Paddle paddle;
    protected Ball ball;

    int BOARD_WIDTH;
    int BOARD_HEIGHT;

    // Constructor.
    GameMgr(JFrame frame, Game_GUI gui) {
        this.frame = frame;
        this.gui = gui;

        BOARD_WIDTH = gui.getBOARD_WIDTH();
        BOARD_HEIGHT = gui.getBOARD_HEIGHT();

        bricks = new ArrayList<Brick>();
        paddle = new Paddle(BOARD_WIDTH, BOARD_HEIGHT);

        setupGame();
    }

    // Init method.
    protected void setupGame() {

    }

    // Collision detection method that can be used by either class.
    protected boolean detectCollision() {
// TODO fill in
        return true;
    }
}
