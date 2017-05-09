package com.mark;

import java.awt.*;

/**
 * This Class outlines the design of a Ball object.
 */
public class Ball implements Globals {
    // Defines Ball's location and velocity/speed variables.
    protected int x_loc;
    protected int y_loc;
    protected int x_spd;
    protected int y_spd;

    // Defines starting location and speed for ball reset.
    protected int START_X;
    protected int START_Y;
    protected int START_X_SPD;
    protected int START_Y_SPD;

    // Flag to show ball has gone off the deep end.
    protected boolean floorHit = false;


    // Getters.
    public int getX() { return this.x_loc; }
    public int getY() { return this.y_loc; }
    public boolean getFloorHit() { return this.floorHit; }
    public void setFloorHit(boolean changeIt) { this.floorHit = changeIt; }




    // Constructor.
    public Ball() {
        // Sets the Ball's starting location to 1/3 of window's width
        // and 1/2 of window's height.
        this.START_X = ((BOARD_WIDTH / 3) - (BALL_DIAMETER / 2));
        this.START_Y = (((BOARD_HEIGHT + STATS_HEIGHT) / 2) - (BALL_DIAMETER / 2));
        this.START_X_SPD = 2;
        this.START_Y_SPD = 5; // 3;
        resetBall();
    }




    // Draw/redraw method.
    protected void draw(int paddleX, int paddleY, Graphics g) {
        // Adds the current speeds to the Ball's current location.
        this.x_loc += this.x_spd;
        this.y_loc += this.y_spd;
        // Draws circle.
        g.fillOval(this.x_loc, this.y_loc, BALL_DIAMETER, BALL_DIAMETER);
        // Runs wall collision detecting method.
        // May be replaced with a global method later on.
        changeDirectionHitWall(paddleX, paddleY);
    }





    protected void changeDirectionHitWall(int paddleX, int paddleY) {
        // Checks if the Ball is at the left wall.
        if (this.x_loc <= 0 && this.y_loc > STATS_HEIGHT) {
            this.x_spd *= -1;
        }
        // Checks if the Ball is at the right wall.
        else if (this.x_loc >= (BOARD_WIDTH - BALL_DIAMETER) && this.y_loc > STATS_HEIGHT) {
            this.x_spd *= -1;
        }
        // Checks if the Ball is at the top wall.
        else if (this.y_loc <= STATS_HEIGHT && this.x_loc > 0) {
            this.y_spd *= -1;
        }
// TODO this will be game over at some point
        else if (this.y_loc >= ((BOARD_HEIGHT + STATS_HEIGHT) - BALL_DIAMETER) && this.x_loc > 0) {
            this.floorHit = true;
        }
        int radius = BALL_DIAMETER / 2;
        if ((this.y_loc + BALL_DIAMETER) >= paddleY) {
            if (((this.x_loc + radius) >= paddleX) &&
                    ((this.x_loc + BALL_DIAMETER) <= (paddleX + PADDLE_WIDTH) + radius)) {
                this.y_spd *= -1;
            }
        }
    }





    protected void resetBall() {
// TODO add starting variables for x, y, and their speeds
        this.x_loc = this.START_X;
        this.y_loc = this.START_Y;
        this.x_spd = this.START_X_SPD;
        this.y_spd = this.START_Y_SPD;
    }





    protected void changeDirectionHitBrick(int xoverlap, int yoverlap) { //Brick b) {
        if (xoverlap == yoverlap) {
            this.x_spd *= -1;
            this.y_spd *= -1;
        }
        else if (xoverlap < yoverlap) {
            this.x_spd *= -1;
        }
        else {
            this.y_spd *= -1;
        }
    }
}
