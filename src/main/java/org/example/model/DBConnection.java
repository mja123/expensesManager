package org.example.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.utils.PropertiesUtils.getProperty;

public class DBConnection {
    private static Connection INSTANCE;

    public static Connection getConnection() {
        // Singleton method to use only one connection
        if (INSTANCE == null) {
            INSTANCE = setConnection();
        }
        return INSTANCE;
    }

    private static Connection setConnection() {
        // Create new database connection based on environment variables
        try {
            return DriverManager.getConnection(getProperty("CONNECTOR") + "://" + getProperty("HOST") +
                    "/" + getProperty("DB") + "?user=" + getProperty("USERNAME") +
                    "&password=" + getProperty("PASSWORD"));

        } catch (SQLException ex) {
            throw new RuntimeException("Set connection error");
        }
    }
}
