package com.mark;

import java.awt.*;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class Paddle {

    protected int PADDLE_WIDTH = 50;
    protected int PADDLE_HEIGHT = 10;
    protected int START_X;
    protected int START_Y;

    protected int x_loc;
    protected int y_loc;


    // Getters for current location.
    public int getX_loc() { return x_loc; }
    public int getY_loc() { return y_loc; }

    // Constructor.
    Paddle(int boardWidth, int boardHeight) {
        this.x_loc = ((boardWidth/2) - (PADDLE_WIDTH/2));
        this.y_loc = (boardHeight - (2*PADDLE_HEIGHT));
        System.out.println("Starting at " + this.x_loc + ", " + this.y_loc);




        // Might use to reset position after losing a life or something.
        this.START_X = this.x_loc;
        this.START_Y = this.y_loc;
    }

    // Probably need a draw/redraw method.
    protected void draw() {

    }




}
