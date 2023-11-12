package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.Data;

import java.sql.*;

public class DB {

    private static DB instance;
    private Connection connection;

    /**
     * Constructs a DatabaseAccess instance.
     *
     */
    private DB() {
        try {
            connection = DriverManager.getConnection(Data.DATABASE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            synchronized (DB.class) {
                if (instance == null) {
                    instance = new DB();
                }
            }
        }
        return instance;
    }

//    public static void resetInstance() {
//        instance = null;
//        instance = new DB();
//    }

    public Connection getConnection() {
        resetConnection();
        try {
            connection = DriverManager.getConnection(Data.DATABASE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void resetConnection() {
        connection = null;
    }

}