package com.mark;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This Class outlines the design for a Game Manager object,
 * which controls a lot of the game processing.
 */
public class GameMgr implements Runnable, Globals{
    // Defines single object variables.
    protected Game_GUI gameFrame;
    protected Thread gameInstance;
    protected BufferStrategy bufferStrategy;
    protected Graphics graphics;
    protected ArrayList<Brick> bricks;
    protected Paddle paddle;
    protected Ball ball;

    // Flag variable to indicate "game state."
    protected boolean gameON = false;
    // Determines speed of redraw.
    protected int fps = 40;
    // Creates array of color objects.
    protected Color[] brickColors = { Color.red, Color.orange, Color.yellow,
            Color.green, Color.blue, Color.magenta };

    // Defines non-mutable variables.
    protected int bricksPerRow;
    // Can change later on to make more "difficult."
    protected int rowsOfBricks = 6;

// TODO consider making a score class.
// TODO add static values to interface class.
    protected int score = 0;
    protected int pointsPerHit = 20;
    protected int lives = 3;
    protected int timeInGame = 0;
    Timer gameTime;



    // Constructor.
    public GameMgr() {
        // Instantiates single objects.
        gameFrame = new Game_GUI();
        paddle = new Paddle();
        ball = new Ball();

        // Calculates how many bricks can fit on the board and
        // sets variable value.
        bricksPerRow = BOARD_WIDTH / BRICK_WIDTH;
        // Creates array to hold all the Brick objects.
        bricks = new ArrayList<Brick>();
        makeBricks();


        gameTime = new Timer();
        gameTime.schedule(new TimerTask() {
            @Override
            public void run() {
                timeInGame += 1;
                System.out.println(timeInGame);
            }
        },0,1000);



    }




    // Standard run method that runs the app.
    public void run() {
        // Calculates how many nanoseconds are in one cycle.
        double microTicks = 1000000000/fps;
        double diff = 0;
        // Makes variable to hold time values down to the nanosecond.
        long currentTime;
        long prevCurrTime = System.nanoTime();


//        makeBricks();


        // Loops until variable value is changed.
        while (gameON) {
// TODO make a gameover part
            // Gets current time in nanoseconds.
            currentTime = System.nanoTime();
            // Calculates how much time has passed since the last cycle.
            diff += (currentTime - prevCurrTime) / microTicks;
            prevCurrTime = currentTime;
            // Checks if enough time has passed to redraw game.
            if (diff >= 1) {
                draw();
                diff = 0;
            }
        }
        // Runs Thread ending method as a redundant safety action.
        endGame();
    }

    private void draw() {
        // Generates a BufferedStrategy object to utilize buffer frames
        // between renders.
        bufferStrategy = gameFrame.getCanvas().getBufferStrategy();
        // Checks if an instance already exists and creates one if not.
        if (bufferStrategy == null) {
            // Note: One buffer is not enough.
            gameFrame.getCanvas().createBufferStrategy(2);
            return;
        }
        // Makes graphics object for drawing.
        graphics = bufferStrategy.getDrawGraphics();
        // Clears board.
        graphics.clearRect(0, STATS_HEIGHT, BOARD_WIDTH, BOARD_HEIGHT);

        // Runs background draw method.
        gameFrame.draw(graphics);
        gameFrame.drawScoreboard(score, timeInGame, lives);

        // Runs the Paddle's draw method and passes an integer. The
        // integer comes from the game frame's keypressed event.
        paddle.draw(gameFrame.getMoveDirection(), graphics);
        // Runs the Ball's draw method.
        ball.draw(paddle.getX_loc(), paddle.getY_loc(), graphics);
        // Runs method to draw all active Bricks.
//        makeBricks();           // just using starting point method for now


        drawBricks();


        detectCollisions();


        // Finalize process.
        bufferStrategy.show();
        graphics.dispose();
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
        // Makes Brick objects and store in array.
// TODO change rowsofbricks to array length or something
        for (int i = 0; i < rowsOfBricks; i++) {       // for num of rows...
            for (int k = 0; k < bricksPerRow; k++) {    // for bricks in each row...
                Brick b = new Brick(
                        (k * BRICK_WIDTH),       // x coord
                        ((i * BRICK_HEIGHT) + STATS_HEIGHT),      // y coord
                        brickColors[i]);         // color of brick
//                        graphics);
                bricks.add(b);
            }
        }
    }

    protected void drawBricks() {
//        int ballX = ball.getX();
//        int ballY = ball.getY();
        for (Brick b : bricks) {
            b.draw(graphics);
        }
    }


    protected void detectCollisions() {
        int ballx = ball.getX();
        int bally = ball.getY();
        for (Brick b : bricks) {
// TODO maybe move checks to either Brick or Ball class


            Rectangle ballRect = new Rectangle(ballx, bally, BALL_DIAMETER, BALL_DIAMETER);
            Rectangle brickRect = new Rectangle(b.x_loc, b.y_loc, BRICK_WIDTH, BRICK_HEIGHT);

            if (ballRect.intersects(brickRect)) {
                int xoverlap;
                int yoverlap;

                if (ballRect.getCenterX() < brickRect.getCenterX()) {
                    xoverlap = (ballx + BALL_DIAMETER) - b.x_loc;
                }
                else {
                    xoverlap = (b.x_loc + BRICK_WIDTH) - ballx;
                }

                if (ballRect.getCenterY() < brickRect.getCenterY()) {
                    yoverlap = (bally + BALL_DIAMETER) - b.y_loc;
                }
                else {
                    yoverlap = (b.y_loc + BRICK_HEIGHT) - bally;
                }

                ball.changeDirectionHitBrick(xoverlap, yoverlap);
                bricks.remove(b);
                score += pointsPerHit;
                System.out.println(score);
                return;
            }
//            http://stackoverflow.com/questions/19408458/brickbreaker-clone-ball-brick-collision-and-ball-behavior-on-brick-collision




//            if (ballx < b.x_loc + BRICK_WIDTH &&                // ball is on LEFT half of brick
//                    ballx + BALL_DIAMETER > b.x_loc &&          // ball passed LEFT side >>>
//                    bally < b.y_loc + BRICK_HEIGHT &&
//                    bally + BRICK_HEIGHT > b.y_loc ) {
//
//
//                ball.changeDirectionHitBrick(b);
//
//
//                bricks.remove(b);
//
//
//                System.out.println("collision!");
//                return;
//            }
        }
    }
}




// Learned a lot about game structures from the tutorial linked below. This app's structure was adapted
// from the lessons learned.
//https://www.youtube.com/watch?v=lf9awz6j88Q&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ&index=2

// Timer help:
//http://stackoverflow.com/questions/14454063/how-to-make-a-timer

