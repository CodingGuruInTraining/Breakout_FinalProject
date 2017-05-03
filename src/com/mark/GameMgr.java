package com.mark;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class GameMgr implements Runnable{

    protected Game_GUI gui;
    protected Game_GUI gameFrame;
    protected Thread gameInstance;
    protected BufferStrategy bufferStrategy;
    protected Graphics graphics;

    protected ArrayList<Brick> bricks;
    protected Paddle paddle;
    protected Ball ball;

    protected int BOARD_WIDTH;
    protected int BOARD_HEIGHT;
    protected boolean gameON = false;

    // Constructor.
    public GameMgr() {
        gameFrame = new Game_GUI();
        BOARD_WIDTH = gameFrame.getBOARD_WIDTH();
        BOARD_HEIGHT = gameFrame.getBOARD_HEIGHT();
    }

    // Run method must be public for some reason.
    public void run() {
        setupGame();
        while (gameON) {
            draw();
        }
        endGame();
    }

    private void draw() {
        bufferStrategy = gameFrame.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            gameFrame.getCanvas().createBufferStrategy(1);
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);


        bufferStrategy.show();
        graphics.dispose();
    }

    // Init method.
    protected void setupGame() {

    }

    public synchronized void startGame() {
        if (gameON) {
            return;
        }
        else {
            gameON = true;
        }
        gameInstance = new Thread(this);
        gameInstance.start();
    }

    public synchronized void endGame() {
        if (!gameON) {
            return;
        }
        else {
            gameON = false;
        }

        try {
            gameInstance.join();
        }
        catch (InterruptedException err) {
            err.printStackTrace();
        }
    }




//        this.frame = frame;
//        this.gui = gui;
//
//        BOARD_WIDTH = gui.getBOARD_WIDTH();
//        BOARD_HEIGHT = gui.getBOARD_HEIGHT();
//
//        bricks = new ArrayList<Brick>();
//        paddle = new Paddle(BOARD_WIDTH, BOARD_HEIGHT);
//
//        setupGame();
//    }



    // Collision detection method that can be used by either class.
    protected boolean detectCollision() {
// TODO fill in
        return true;
    }
}




// Learned a lot about game structures from the tutorial linked below. This app's structure was adapted
// from the lessons learned.
//https://www.youtube.com/watch?v=lf9awz6j88Q&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ&index=2