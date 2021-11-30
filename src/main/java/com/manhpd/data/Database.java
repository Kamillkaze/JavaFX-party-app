package com.manhpd.data;

import java.sql.*;

public class Database {
   public static final String DB_NAME = "datasource.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static final String TABLE_GUESTS = "guests";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FOOD = "food";
    public static final String COLUMN_DRINK = "drink";
    public static final String COLUMN_PHONE = "phone";

    private static Connection conn;

    private static String command;

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
    }

    public static void initTableGuests() {
        command = "CREATE TABLE IF NOT EXISTS " + TABLE_GUESTS +
                    " (" + COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_FOOD + " TEXT NOT NULL, " +
                    COLUMN_DRINK + " TEXT NOT NULL, " +
                    COLUMN_PHONE + " TEXT NOT NULL)";

        proceedActionOnDB();
    }


    private static boolean addToJavaListFromDB(Guest guest) {
        if (guest != null) {
            RuntimeGuestsData.getInstance().saveFromDB(guest);
            return true;
        }
        return false;
    }

    static void addGuestToDB(Guest guest) {

        String name = guest.getName();
        String food = guest.getFood();
        String drink = guest.getDrink();
        String phone = guest.getPhone();

        command = "INSERT INTO " + TABLE_GUESTS +
                    " VALUES('" + name + "', '"+ food + "', '" + drink + "', '" + phone + "')";

        proceedActionOnDB();
    }

    static void removeGuestFromDB(String phone) {
        command = "DELETE FROM " + TABLE_GUESTS + " WHERE phone = '" + phone + "'";

        proceedActionOnDB();
    }

    static void updateGuestInDB(String searchedPhone, Guest updated) {

        String name = updated.getName();
        String food = updated.getFood();
        String drink = updated.getDrink();
        String phone = updated.getPhone();

        command = "UPDATE " + TABLE_GUESTS + "SET name = '" + name +
                                                "', food = '" + food +
                                                "', drink = '" + drink +
                                                "', phone = '" + phone +
                                                "' WHERE phone = '" + searchedPhone + "'";

        proceedActionOnDB();
    }

    private static void proceedActionOnDB() {
        Statement statement = null;

        try {
            statement = getConnection().createStatement();
            statement.execute(command);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    public static void readData() {
        Statement statement = null;

        try {
            statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_GUESTS);

            while (rs.next()) {
                String name = rs.getString(1);
                String food = rs.getString(2);
                String drink = rs.getString(3);
                String phone = rs.getString(4);

                Guest guest = new Guest(name, food, drink, phone);

                addToJavaListFromDB(guest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }
}
