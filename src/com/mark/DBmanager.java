package com.mark;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This Class outlines the design for managing a SQLite database connection.
 */
public class DBmanager {
    protected String dirPath;
    protected String createTbl;
    protected String insertNew;
    protected String updateOld;
    protected String updateOld2;

    public DBmanager() {
        dirPath = Paths.get(".").toAbsolutePath().normalize().toString();
        System.out.println(dirPath);
        createTbl = "CREATE TABLE IF NOT EXISTS scores (" +
                "username text PRIMARY KEY, " +
                "high_score integer NOT NULL," +
                "score_date date NOT NULL)";
        insertNew = "INSERT INTO scores VALUES(";       // not sure if needs column names
        updateOld = "UPDATE scores SET high_score = ";
        updateOld2 = " WHERE username = ";
    }

    protected void makeConnection() {
// TODO move statics to interface class later when working
//        String url = "jdbc:sqlite:" + dirPath + "/highscores.db";
        String url = "jdbc:sqlite:highscores.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {

            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        System.out.println("database created successfully!");
    }
}





//directory path help:
//        http://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java

// connection help:
//http://www.sqlitetutorial.net/sqlite-java/create-database/
