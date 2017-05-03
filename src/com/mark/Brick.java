package com.mark;

import java.awt.*;

import static java.awt.Color.black;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class Brick {

// TODO maybe make dynamic somehow later on
    protected static int BRICK_WIDTH = 100;     // 40
    protected static int BRICK_HEIGHT = 20;     // 10

    protected static int BOARD_WIDTH;
    protected static int BOARD_HEIGHT;

    protected int x_loc;
    protected int y_loc;

    // Static constant getters.
    public static int getBRICK_WIDTH() { return BRICK_WIDTH; }
    public static int getBRICK_HEIGHT() { return BRICK_HEIGHT; }

    // Constructor.
    Brick(int x, int y, Color color, Graphics g) {
        this.x_loc = x;
        this.y_loc = y;
        g.setColor(color);
        g.fillRect(this.x_loc, this.y_loc, BRICK_WIDTH, BRICK_HEIGHT);
        g.setColor(black);
        g.drawRect(this.x_loc, this.y_loc, BRICK_WIDTH, BRICK_HEIGHT);
    }

    protected boolean checkCollision()

    // Draw/redraw method.
//    protected void draw(int x, int y, Color color, Graphics g) {
//        this.x_loc = x;
//        this.y_loc = y;
//
//        g.setColor(color);
//        g.fillRect();
//    }
}
