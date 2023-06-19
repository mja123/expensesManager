package org.example;


import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.utils.Utils.getProperty;

public class DBConnection {
    private static Connection INSTANCE;

    public static Connection getConnection() {
        if (INSTANCE == null) {
            return setConnection();
        }
        return INSTANCE;
    }

    private static Connection setConnection() {
        try {
            return DriverManager.getConnection(getProperty("CONNECTOR") + "://" + getProperty("HOST") +
                    "/" + getProperty("DB") + "?user=" + getProperty("USERNAME") +
                    "&password=" + getProperty("PASSWORD"));

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            throw new RuntimeException("Set connection error");
        }
    }
}
