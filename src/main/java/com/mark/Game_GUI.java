package com.mark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * This Class outlines the design for the GUI object.
 */
public class Game_GUI implements KeyListener, Globals {
    private JFrame frame;
    private Canvas canvas;
    private JTextField userTextField;
    private JButton submitButton;
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
//            else if (time % 10 == 0) {
//                timeTxt = "0:" + time + "0";
//            }
            else {
                timeTxt = "0:" + time;
            }
        }
//        int hours;
//        int minutes;
//        if (time >=60) {
//            hours = time / 60;
//            minutes = time % 60;
//        }
//        else {
//            hours = 0;
//            minutes = time;
//        }
//        timeTxt = String.format("{0:##}:{1:##}", hours, minutes);

        g.drawString(timeTxt, STATS_WIDTH/2, 20);
    }





    protected void promptUsername() {

//        userTextField = new JTextField();
//        submitButton = new JButton("Submit");
//        frame.add(userTextField);
//        frame.add(submitButton);
//        userTextField.setLocation(20, 260);
//        submitButton.setLocation(150, 260);
//        submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (userTextField.getText() != "" && userTextField.getText() != null) {
//                    goodSubmit = userTextField.getText();
//                    userTextField.setText("");
//                }
//            }
//        });




                String username = JOptionPane.showInputDialog("Please enter a username:");
        while (username == "" || username == null) {
            username = JOptionPane.showInputDialog("Please enter a username:");
        }
//        return username;
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