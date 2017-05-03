package com.mark;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class GameMgr implements Runnable, Globals{

//    protected Game_GUI gui;
    protected Game_GUI gameFrame;
    protected Thread gameInstance;
    protected BufferStrategy bufferStrategy;
    protected Graphics graphics;

    // Objects.
    protected ArrayList<Brick> bricks;
    protected Paddle paddle;
    protected Ball ball;
    protected Timer timer;

    // Variables.
//    protected int BOARD_WIDTH;
//    protected int BOARD_HEIGHT;
    protected boolean gameON = false;
    // Determines speed of redraw.
    protected int fps = 40;

    protected Color[] brickColors = { Color.red, Color.orange, Color.yellow,
            Color.green, Color.blue, Color.magenta };
    protected int bricksPerRow;
    // Can change later on to make more "difficult."
    protected int rowsOfBricks = 6;
//    protected int brickWidth;
//    protected int brickHeight;

    // Constructor.
    public GameMgr() {
        gameFrame = new Game_GUI();
//        BOARD_WIDTH = gameFrame.getBOARD_WIDTH();
//        BOARD_HEIGHT = gameFrame.getBOARD_HEIGHT();
//        timer = new Timer();
//        timer.scheduleAtFixedRate(something, 0, 100);
        paddle = new Paddle();
        ball = new Ball();
        // total guess and it worked!:
//        Brick.BOARD_WIDTH = BOARD_WIDTH;
//        Brick.BOARD_HEIGHT = BOARD_HEIGHT;

//        brickWidth = Brick.getBRICK_WIDTH();
//        brickHeight = Brick.getBRICK_HEIGHT();
        bricksPerRow = BOARD_WIDTH / BRICK_WIDTH;
        bricks = new ArrayList<Brick>();
    }

    // Run method must be public for some reason.
    public void run() {
        setupGame();

        double microTicks = 1000000000/fps;
        double diff = 0;
        long currentTime;
        long prevCurrTime = System.nanoTime();

        while (gameON) {
            currentTime = System.nanoTime();
            diff += (currentTime - prevCurrTime) / microTicks;
            prevCurrTime = currentTime;
            if (diff >= 1) {
                draw();
                diff = 0;
            }
        }
        endGame();
    }

    private void draw() {
        bufferStrategy = gameFrame.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            // Note: One buffer is not enough.
            gameFrame.getCanvas().createBufferStrategy(2);
            return;
        }
        // Makes graphics object for drawing.
        graphics = bufferStrategy.getDrawGraphics();
        // Clears board.
        graphics.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);


        gameFrame.draw(graphics);
        // draw paddle (-1 is left, 0 no move, 1 is right)
        paddle.draw(gameFrame.getMoveDirection(), graphics);
        // draw ball
        ball.draw(graphics);
        // draw bricks
        makeBricks();           // just using starting point method for now


        bufferStrategy.show();
        graphics.dispose();
    }

    // Init method.
    protected void setupGame() {
        // May not need anymore.


//        makeBricks();
    }

    // Creates new game instance/thread.
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

    // Ends game instance.
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

    protected void makeBricks() {
// TODO change rowsofbricks to array length or something
        for (int i = 0; i < rowsOfBricks; i++) {       // for num of rows...
            for (int k = 0; k < bricksPerRow; k++) {    // for bricks in each row...
                Brick b = new Brick(
                        (k * BRICK_WIDTH),       // x coord
                        (i * BRICK_HEIGHT),      // y coord
                        brickColors[i],         // color of brick
                        graphics);
                bricks.add(b);
            }
        }
    }

    protected void detectCollisions() {
        int ballx = ball.getX();
        int bally = ball.getY();
        for (Brick b : bricks) {

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
//    protected boolean detectCollision() {
//// TODO fill in
//        return true;
//    }
}




// Learned a lot about game structures from the tutorial linked below. This app's structure was adapted
// from the lessons learned.
//https://www.youtube.com/watch?v=lf9awz6j88Q&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ&index=2