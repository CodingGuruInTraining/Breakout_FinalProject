package com.mark;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
	    GameMgr mgr = new GameMgr();
        JFrame frame = new JFrame("My Game Name");

        Game_GUI gui = new Game_GUI();
        frame.add(gui);
        frame.pack();
        frame.setVisible(true);
    }
}
