package com.sparta.g1.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static volatile DBConnection instance;
    private Connection connection;

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(DBProperties.getConnectionProperty("url"), DBProperties.getConnectionProperty("username"), DBProperties.getConnectionProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
