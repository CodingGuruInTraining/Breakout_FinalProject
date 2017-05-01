package com.mark;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class Brick {

// TODO maybe make dynamic somehow later on
    protected static int BRICK_WIDTH = 40;
    protected static int BRICK_HEIGHT = 10;

    protected int x_loc;
    protected int y_loc;

    public static int getBRICK_WIDTH() { return BRICK_WIDTH; }
    public static int getBRICK_HEIGHT() { return BRICK_HEIGHT; }

    // Constructor.
    Brick() {

    }

    // Draw/redraw method.
    protected void draw() {

    }
}
