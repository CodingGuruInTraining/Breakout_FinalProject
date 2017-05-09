package com.mark;

import java.sql.Date;

/**
 * Created by hl4350hb on 5/9/2017.
 */
public class Score {
    protected String username;
    protected int score;
    protected Date scoreDate;

    public Score() {
    }

    public Score(String user, int aScore, Date theDate) {
        this.username = user;
        this.score = aScore;
        this.scoreDate = theDate;
    }

    @Override
    public String toString() {
        return "Score{}";
    }
}
