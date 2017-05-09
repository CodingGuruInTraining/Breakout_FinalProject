package com.mark;

import java.nio.file.Paths;

/**
 * This Class outlines the design for managing a SQLite database connection.
 */
public class DBmanager {
    protected String dirPath;

    public DBmanager() {
        dirPath = Paths.get(".").toAbsolutePath().normalize().toString();
        System.out.println(dirPath);
    }
}





//directory path help:
//        http://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java