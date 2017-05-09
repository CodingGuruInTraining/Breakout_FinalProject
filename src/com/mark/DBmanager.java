package com.mark;

import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
//import java.sql.Date;

/**
 * This Class outlines the design for managing a SQLite database connection.
 */
public class DBmanager {
    protected String dirPath;
    protected String createTbl;
    protected String insertNew;
    protected String updateOld;
    protected Connection conn;


    public DBmanager() {
        dirPath = Paths.get(".").toAbsolutePath().normalize().toString();
        System.out.println(dirPath);
        createTbl = "CREATE TABLE IF NOT EXISTS scores (" +
                "username text PRIMARY KEY, " +
                "high_score integer NOT NULL," +
                "score_date date NOT NULL)";
        insertNew = "INSERT INTO scores VALUES(?, ?, ?)";       // not sure if needs column names
        updateOld = "UPDATE scores SET high_score = ? WHERE username = ?";
    }

    protected Connection makeConnection() {
// TODO move statics to interface class later when working
//        String url = "jdbc:sqlite:" + dirPath + "/highscores.db";
        String url = "jdbc:sqlite:highscores.db";
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                return connection;
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        System.out.println("database created successfully!");
        return null;
    }

    protected void addNewEntry(String username, int score) {
        try {
            conn = makeConnection();
            PreparedStatement ps = conn.prepareStatement(insertNew);
            ps.setString(1, username);
            ps.setInt(2, score);
            Date currDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
            ps.setDate(3, sqlDate);
            System.out.println(sqlDate);
            System.out.println(sqlDate.toString());

            ps.executeUpdate();
            conn.close();

        }
        catch (SQLException err) {
            err.printStackTrace();
        }
    }
// TODO   for testing purposes: remove later.
    public static void main(String[] args) {
        DBmanager mgr = new DBmanager();
        mgr.addNewEntry("test1", 42);
    }
}





//directory path help:
//        http://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java

// connection help:
//http://www.sqlitetutorial.net/sqlite-java/create-database/

// sql Date format help:
//http://stackoverflow.com/questions/18257648/get-the-current-date-in-java-sql-date-format

