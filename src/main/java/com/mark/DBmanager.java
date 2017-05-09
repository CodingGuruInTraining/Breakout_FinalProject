package com.mark;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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

        try {
            Class.forName("org.sqlite.JDBC").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        String url = "jdbc:sqlite:" + dirPath + "/highscores.db";
        String url = "jdbc:sqlite:highscores.db";
        try (Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement()) {
            if (connection != null) {
                statement.executeUpdate(createTbl);
                return connection;
            }
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
        System.out.println("database created successfully!");
        return null;
    }






    protected ResultSet selectAll() {
        try (Connection connection = makeConnection()) {
            ResultSet rs = null; // ps.executeQuery(somethin)
            if (rs != null) {
                return rs;
            }
        }
        catch(SQLException err) {
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


//        String url = "jdbc:sqlite:highscores.db";
//        try (Connection conn = DriverManager.getConnection(url)) {


        try (Connection conn = makeConnection();
             PreparedStatement ps = conn.prepareStatement(insertNew)) {
//            PreparedStatement ps = conn.prepareStatement(insertNew);

//        PreparedStatement ps = conn.prepareStatement(insertNew)) {
//        try {
//            conn = makeConnection();
//            Statement statement = conn.createStatement();
//            PreparedStatement ps = conn.prepareStatement(insertNew);
            ps.setString(1, username);
            ps.setInt(2, score);
            Date currDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(currDate.getTime());
            ps.setDate(3, sqlDate);
//            System.out.println(sqlDate);
//            System.out.println(sqlDate.toString());

            ps.executeUpdate();
            conn.close();
            System.out.println("closing time");
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
    }






    protected void updateEntry(String username, int newScore) {
        try (Connection connection = makeConnection();
        PreparedStatement ps = connection.prepareStatement(updateOld)) {
            ps.setInt(1, newScore);
            ps.setString(2, username);
            ps.executeUpdate();
            connection.close();
        }
        catch (SQLException err) {
            err.printStackTrace();
        }
    }






    protected boolean isExist(String name, ArrayList<Score> scores) {
        for (Score s : scores) {
            if (s.username.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }





// TODO   for testing purposes: remove later.
    public static void main(String[] args) {
        DBmanager mgr = new DBmanager();
        mgr.addNewEntry("test", 42);
    }
}





//directory path help:
//        http://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java

// connection help:
//http://www.sqlitetutorial.net/sqlite-java/create-database/

// sql Date format help:
//http://stackoverflow.com/questions/18257648/get-the-current-date-in-java-sql-date-format

