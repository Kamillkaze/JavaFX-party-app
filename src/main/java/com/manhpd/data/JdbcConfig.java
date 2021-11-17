package com.manhpd.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:guests.db");

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
