package com.mark;

import java.awt.*;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class Ball {

    protected int BALL_DIAMETER = 20;       // 10
    protected int BOARD_WIDTH;
    protected int BOARD_HEIGHT;

    protected int x_loc;
    protected int y_loc;

    Ball(int boardWidth, int boardHeight) {
        this.BOARD_WIDTH = boardWidth;
        this.BOARD_HEIGHT = boardHeight;
        this.x_loc = ((BOARD_WIDTH / 2) - (BALL_DIAMETER / 2));
        this.y_loc = ((BOARD_HEIGHT / 2) - (BALL_DIAMETER / 2));
    }

    // Draw/redraw method.
    protected void draw(Graphics g) {
        g.fillOval(this.x_loc, this.y_loc, BALL_DIAMETER, BALL_DIAMETER);
    }



}
