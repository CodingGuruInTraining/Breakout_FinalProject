package com.mark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * This Class outlines the design for the GUI object.
 */
public class Game_GUI implements KeyListener, Globals {
    private JFrame frame;
    private Canvas canvas;
    // Variable represents what direction to move paddle.
    protected int moveDirection = 0;

    // Getters.
    public Canvas getCanvas() {
        return canvas;
    }
    public int getMoveDirection() { return moveDirection; }




    // Constructor.
    public Game_GUI() {
        frame = new JFrame("Breakout Attempt");
        frame.setSize(BOARD_WIDTH, BOARD_HEIGHT + STATS_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT + STATS_HEIGHT));
        frame.add(canvas);
        frame.addKeyListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }




    protected void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT + STATS_HEIGHT);
    }

    protected void drawScoreboard(int score, int time, int lives, Graphics g) {

        int interval = STATS_WIDTH / 7;
        g.setColor(Color.white);

        g.drawString("Score: " + score, 5, 20);
        g.drawString("Lives: " + lives, interval * 6, 20);


        String timeTxt = "";
// TODO is only showing the digits, no zero spacers
        if (time >= 60) {
            int hours = time / 60;
            int minutes = time % 60;
            timeTxt = hours + ":" + minutes;
        }
        else {
            timeTxt = time + "";
        }
        g.drawString(timeTxt, STATS_WIDTH/2, 20);
    }





    protected String promptUsername() {
        String username = JOptionPane.showInputDialog("Please enter a username:");
        while (username == "" || username == null) {
            username = JOptionPane.showInputDialog("Please enter a username:");
        }
        return username;
    }





/******
 Keypress Listeners
 ********/
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveDirection = -1;
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveDirection = 1;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        moveDirection = 0;
    }
}



//helpers:
//        http://stackoverflow.com/questions/21997130/how-to-use-keylistener-with-jframe