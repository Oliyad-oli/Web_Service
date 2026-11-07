package com.lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ============================================================================
 * WEB SERVICES LAB PRACTICE 2 - EXPERIMENT 2
 * Program Title: Database Connection Manager (DBConnection.java)
 * Description: Manages JDBC connections to MySQL database 'student_db'.
 * ============================================================================
 */
public class DBConnection {

    // Database connection parameters
    private static final String DB_HOST = "127.0.0.1";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "student_db";
    private static final String DB_USER = "labuser";
    private static final String DB_PASS = "LabPassword123!";

    // JDBC Connection URL with timezone and SSL options
    private static final String JDBC_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=UTF-8";

    // Driver class name for MySQL Connector/J 8.x
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("CRITICAL: MySQL JDBC Driver not found in classpath: " + e.getMessage());
        }
    }

    /**
     * Establishes and returns a new database connection.
     *
     * @return An active java.sql.Connection
     * @throws SQLException If connection to database fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }

    /**
     * Standalone main method for quick diagnostic testing from CLI.
     */
    public static void main(String[] args) {
        System.out.println("Testing JDBC Connection to " + JDBC_URL + " ...");
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("SUCCESS: Successfully connected to MySQL via JDBC! Database: " 
                        + conn.getCatalog() + " (Catalog version: " 
                        + conn.getMetaData().getDatabaseProductVersion() + ")");
            }
        } catch (SQLException e) {
            System.err.println("FAILURE: JDBC Connection failed!");
            e.printStackTrace();
        }
    }
}
