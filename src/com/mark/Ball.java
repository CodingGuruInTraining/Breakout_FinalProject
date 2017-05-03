package com.mark;

import java.awt.*;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class Ball implements Globals {

//    protected int BALL_DIAMETER = 20;       // 10
//    protected int BOARD_WIDTH;
//    protected int BOARD_HEIGHT;

    protected int x_loc;
    protected int y_loc;
    protected int x_spd;
    protected int y_spd;

    public int getX() { return this.x_loc; }
    public int getY() { return this.y_loc; }

    Ball() {
//        this.BOARD_WIDTH = boardWidth;
//        this.BOARD_HEIGHT = boardHeight;
        this.x_loc = ((BOARD_WIDTH / 3) - (BALL_DIAMETER / 2));
        this.y_loc = ((BOARD_HEIGHT / 2) - (BALL_DIAMETER / 2));
        this.x_spd = 1;
        this.y_spd = 2;
    }

    // Draw/redraw method.
    protected void draw(Graphics g) {
        this.x_loc += this.x_spd;
        this.y_loc += this.y_spd;

        g.fillOval(this.x_loc, this.y_loc, BALL_DIAMETER, BALL_DIAMETER);
        changeTrajectory();
    }

    protected void changeTrajectory() {
        if (this.x_loc <= 0 && this.y_loc > 0) {        // left wall
            this.x_spd *= -1;
        }
        else if (this.x_loc >= (BOARD_WIDTH - BALL_DIAMETER) && this.y_loc > 0) {   // right wall
            this.x_spd *= -1;
        }

        if (this.y_loc <= 0 && this.x_loc > 0) {        // top wall
            this.y_spd *= -1;
        }

// just for testing purposes
        if (this.y_loc >= (BOARD_HEIGHT - BALL_DIAMETER) && this.x_loc > 0) {
            this.y_spd *= -1;
        }
    }


}
