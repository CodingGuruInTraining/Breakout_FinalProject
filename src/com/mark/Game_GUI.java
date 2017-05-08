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
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel livesLabel;

    protected int moveDirection = 0;


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

// TODO add statics to interface class.
        scoreLabel = new JLabel();
        timeLabel = new JLabel();
        livesLabel = new JLabel();


        int interval = STATS_WIDTH / 7;
        scoreLabel.setLocation(0,0);
        timeLabel.setLocation(interval * 2, 0);
        livesLabel.setLocation(interval * 4, 0);

        scoreLabel.setSize(40, STATS_HEIGHT);
        timeLabel.setSize(40, STATS_HEIGHT);
        livesLabel.setSize(40, STATS_HEIGHT);

        frame.add(scoreLabel);
        frame.add(timeLabel);
        frame.add(livesLabel);

frame.setLocationRelativeTo(null);
    }

    protected void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT + STATS_HEIGHT);
        drawScoreboard();
    }

    protected void drawScoreboard() {

        scoreLabel = new JLabel();
        timeLabel = new JLabel();
        livesLabel = new JLabel();
        scoreLabel.setForeground(Color.white);
        timeLabel.setForeground(Color.white);
        livesLabel.setForeground(Color.white);
        int interval = STATS_WIDTH / 7;
        scoreLabel.setLocation(0,0);
        timeLabel.setLocation(interval * 2, 0);
        livesLabel.setLocation(interval * 4, 0);

        scoreLabel.setSize(new Dimension(25, STATS_HEIGHT));
        timeLabel.setSize(40, STATS_HEIGHT);
        livesLabel.setSize(40, STATS_HEIGHT);

        scoreLabel.setText("Score: ");
        timeLabel.setText("no label");
        livesLabel.setText("Lives: ");
        scoreLabel.setVisible(true);
//        System.out.println(scoreLabel.getText());
        frame.add(scoreLabel);
        frame.add(timeLabel);
        frame.add(livesLabel);


    }

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