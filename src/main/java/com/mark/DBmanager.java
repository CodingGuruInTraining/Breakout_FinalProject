package com.mark;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
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
    //protected Connection conn;

    final String DB_URL = "jdbc:sqlite:highscores.db";

    public DBmanager() {

        try {
            Class.forName("org.sqlite.JDBC").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        dirPath = Paths.get(".").toAbsolutePath().normalize().toString();
        System.out.println(dirPath);
        createTbl = "CREATE TABLE IF NOT EXISTS scores (" +
                "username text PRIMARY KEY, " +
                "high_score integer NOT NULL," +
                "score_date date NOT NULL)";
        insertNew = "INSERT INTO scores VALUES(?, ?, ?)";       // not sure if needs column names
        updateOld = "UPDATE scores SET high_score = ? WHERE username = ?";

        createTable();

    }

    private void createTable() {

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createTbl);

        }
        catch (SQLException err) {
            err.printStackTrace();
        }

        System.out.println("database created successfully!");

    }


    protected ResultSet selectAll() {

        try (Connection conn = DriverManager.getConnection(DB_URL)) {

            ResultSet rs = null; // ps.executeQuery(somethin)
            if (rs != null) {
                return rs;
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        return null;
    }


    protected ArrayList<Score> getRSscores(ResultSet rs) {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            while (rs.next()) {
// TODO move statics to interface.
                String username = rs.getString("username");
                int score = rs.getInt("high_score");
                java.sql.Date scoreDate = rs.getDate("score_date");
                scores.add(new Score(username, score, scoreDate));
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        return scores;
    }




    protected void addNewEntry(String username, int score) {


        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(insertNew)) {

            ps.setString(1, username);
            ps.setInt(2, score);
            Date currDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
            ps.setDate(3, sqlDate);

            ps.executeUpdate();
          //  conn.close();   // The try-with-resources will close the DB for you
            System.out.println("closing time");
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
    }


    protected void updateEntry(String username, int newScore) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = connection.prepareStatement(updateOld)) {

            ps.setInt(1, newScore);
            ps.setString(2, username);
            ps.executeUpdate();
           // connection.close();

        }

        catch (SQLException err) {
            err.printStackTrace();
        }
    }



    // TODO   for testing purposes: remove later.
    public static void main(String[] args) {
        DBmanager mgr = new DBmanager();
        mgr.addNewEntry("test777", 44);
        mgr.updateEntry("test777", 43);
    }
}





//directory path help:
//        http://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java

// connection help:
//http://www.sqlitetutorial.net/sqlite-java/create-database/

// sql Date format help:
//http://stackoverflow.com/questions/18257648/get-the-current-date-in-java-sql-date-format

