package com.hospital.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    public static Connection getDatabaseConnection(){
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");
        if(dbUrl == null || dbUser == null || dbPassword == null){
            throw new IllegalStateException("ENV variables are null");
        }
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl,dbUser,dbPassword);
        } catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException("Fatal error: Could not connect to db");
        }
    }
}
