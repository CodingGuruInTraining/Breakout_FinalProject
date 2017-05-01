package com.mark;

import java.util.ArrayList;

/**
 * Created by hl4350hb on 5/1/2017.
 */
public class GameMgr {

    protected ArrayList<Brick> bricks;



    // Constructor.
    GameMgr() {
        bricks = new ArrayList<Brick>();
    }

    // Init method.
    protected void setupGame() {

    }

    // Collision detection method that can be used by either class.
    protected boolean detectCollision() {
        return true;
    }
}
