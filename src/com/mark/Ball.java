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


    protected int brickX;
    protected int brickY;
    protected boolean brickHit = false;



    // Getters.
    public int getX() { return this.x_loc; }
    public int getY() { return this.y_loc; }

    // Temporary Brick Setters.
    public void setBrickX(int brickX) { this.brickX = brickX; }
    public void setBrickY(int brickY) { this.brickY = brickY; }
    public void setBrickHit(boolean brickHit) { this.brickHit = brickHit; }

    // Constructor.
    Ball() {
        // Sets the Ball's starting location to 1/3 of window's width
        // and 1/2 of window's height.
        this.x_loc = ((BOARD_WIDTH / 3) - (BALL_DIAMETER / 2));
        this.y_loc = ((BOARD_HEIGHT / 2) - (BALL_DIAMETER / 2));
        this.x_spd = 1;
        this.y_spd = 2;
    }

    // Draw/redraw method.
    protected void draw(Graphics g) {
        // Adds the current speeds to the Ball's current location.
        this.x_loc += this.x_spd;
        this.y_loc += this.y_spd;

        // Draws circle.
        g.fillOval(this.x_loc, this.y_loc, BALL_DIAMETER, BALL_DIAMETER);
        // Runs wall collision detecting method.
        // May be replaced with a global method later on.
        changeTrajectory();
    }

    protected void changeTrajectory() {
        // Checks if the Ball is at the left wall.
        if (this.x_loc <= 0 && this.y_loc > 0) {
            this.x_spd *= -1;
        }
        // Checks if the Ball is at the right wall.
        else if (this.x_loc >= (BOARD_WIDTH - BALL_DIAMETER) && this.y_loc > 0) {
            this.x_spd *= -1;
        }
        // Checks if the Ball is at the top wall.
        else if (this.y_loc <= 0 && this.x_loc > 0) {
            this.y_spd *= -1;
        }

// just for testing purposes
        else if (this.y_loc >= (BOARD_HEIGHT - BALL_DIAMETER) && this.x_loc > 0) {
            this.y_spd *= -1;
        }


//        else if (brickHit) {
//            // Ball hit side of Brick.
//            if ((this.x_loc == (brickX + BRICK_WIDTH)) ||
//                    (this.x_loc == brickX)) {
//                if (this.y_loc == (brickY + BRICK_HEIGHT)) {
//                    // hit corner
//                } else {
//                    // only hit side
//                    this.x_spd *= -1;
//                }
//
//            else if (this.x_loc == (brickX)) {
//                    this.x_loc
//                }
//            }
//        }


    }
}
