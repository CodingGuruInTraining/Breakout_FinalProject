package com.mark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * This Class outlines the design for the GUI object.
 */
public class Game_GUI implements KeyListener, Globals {
    private JFrame frame;
    private Canvas canvas;
    private JTable table;
    // Variable represents what direction to move paddle.
    protected int moveDirection = 0;
    protected String goodSubmit;

    // Getters.
    public Canvas getCanvas() {
        return canvas;
    }
    public int getMoveDirection() { return moveDirection; }
    public String getGoodSubmit() { return goodSubmit; }



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

        if (time >= 60) {
            int hours = time / 60;
            int minutes = time % 60;
            if (minutes < 10) {
                timeTxt = hours + ":0" + minutes;
            }
            else {
                timeTxt = hours + ":" + minutes;
            }
        }
        else {
            if (time < 10) {
                timeTxt = "0:0" + time;
            }
            else {
                timeTxt = "0:" + time;
            }
        }
        g.drawString(timeTxt, STATS_WIDTH/2, 20);
    }





    protected void promptUsername() {
        String username = JOptionPane.showInputDialog("Please enter a username:");
        while (username == "" || username == null) {
            username = JOptionPane.showInputDialog("Please enter a username:");
        }
    }





    protected void showHighScores(ArrayList<Score> scores) {
// TODO add timeout so still shows gameover screen briefly
        frame.getContentPane().removeAll();
//        Array columns =  {"Username", "High Score", "Date Achieved"};
//        String[] data = new String[scores.size()];
//
////        for (Score s : scores) {
//        for (int i = 0; i < scores.size(); i++) {
////            String[] temp = {s.username, s.score + "", s.scoreDate + ""};
//            String[] temp = {scores.get(i).username, scores.get(i).score + "", scores.get(i).scoreDate + ""};
//            data[i] = temp;
//        }
//
//        table = new JTable(scores, columns);


        frame.validate();
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

// http://stackoverflow.com/questions/9347076/how-to-remove-all-components-from-a-jframe-in-java
