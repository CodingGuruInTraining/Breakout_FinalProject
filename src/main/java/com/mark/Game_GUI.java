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
public class Game_GUI extends JFrame implements KeyListener, Globals {
    private JTable table;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel livesLabel;

    // Variable represents what direction to move paddle.
    protected int moveDirection = 0;
    protected String goodSubmit;

    // Getters.
    public int getMoveDirection() { return moveDirection; }
    public String getGoodSubmit() { return goodSubmit; }



    // Constructor.
    public Game_GUI() {
//        frame = new JFrame("Breakout Attempt");
        this.setSize(BOARD_WIDTH, BOARD_HEIGHT + STATS_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        this.addKeyListener(this);
        this.pack();

        scoreLabel = new JLabel();
        timeLabel = new JLabel();
        livesLabel = new JLabel();
        this.add(scoreLabel);
        this.add(timeLabel);
        this.add(livesLabel);
        // Breaks up the window's width into intervals.
        int interval = STATS_WIDTH / 7;

        scoreLabel.setLocation(5, 20);
        livesLabel.setLocation(interval * 6, 20);
        timeLabel.setLocation(STATS_WIDTH/2, 20);

        this.setSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT + STATS_HEIGHT));
        this.setLocationRelativeTo(null);
    }



    // Draw method.
    protected void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT + STATS_HEIGHT);
    }

    // Draw method for the scoreboard at top.
    protected void drawScoreboard(int score, int time, int lives, Graphics g) {

        // Sets color for text.
        g.setColor(Color.white);

        // Updates label information with passed variables.
        scoreLabel.setText("Score: " + score);


        livesLabel.setText("Lives: " + lives);


//        g.drawString("Score: " + score, 5, 20);
//        g.drawString("Lives: " + lives, interval * 6, 20);

        // Transforms time value into a more appealing style.
        String timeTxt = "";

        if (time >= 60) {
            int minutes = time / 60;
            int seconds = time % 60;
            if (seconds < 10) {
                timeTxt = minutes + ":0" + seconds;
            }
            else {
                timeTxt = minutes + ":" + seconds;
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

        timeLabel.setText(timeTxt);

//        g.drawString(timeTxt, STATS_WIDTH/2, 20);
    }




    // Method to display an input box for user input.
    protected void promptUsername() {
        String username = JOptionPane.showInputDialog("Please enter a username:");
        // Validates that user enters a value.
        while (username == "" || username == null) {
            username = JOptionPane.showInputDialog("Please enter a username:");
        }
        // Sets variable to input.
        goodSubmit = username;
    }





    protected void showHighScores(ArrayList<Score> scores) {
// TODO add timeout so still shows gameover screen briefly
        // Makes string arrays for the JTable.
        String[] columns =  {"Username", "High Score", "Date Achieved"};
        String[][] data = new String[scores.size()][scores.size()];
        // Loops through passed array.
        for (int i = 0; i < scores.size(); i++) {
            // Gets each Score object's values and adds them to a new string array.
            String[] temp = {scores.get(i).username, scores.get(i).score + "", scores.get(i).scoreDate + ""};
            data[i] = temp;
        }

        // Creates table, sets size, sets location, and adds to frame.
        table = new JTable(data, columns);
        table.setSize(BOARD_WIDTH, BOARD_HEIGHT/2);
        table.setLocation(0, (BOARD_HEIGHT + STATS_HEIGHT) / 5);
        this.add(new JScrollPane(table));
        // Needed method when overwriting frame from what I understand.
        this.validate();
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
// http://stackoverflow.com/questions/13935934/java-jtable-column-headers-not-showing