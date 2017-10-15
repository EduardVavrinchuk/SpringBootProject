package com.spring.boot.project.mysqlConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionManager {
	private static final Logger logger = Logger.getLogger(ConnectionManager.class);
	private static final String URL = "jdbc:mysql://localhost:3306/TestProject"; 
	private static final String USERNAME = "root"; 
	private static final String PASSWORD = "0905"; 
    private static final String driverName = "com.mysql.jdbc.Driver";  
    private static Connection connection; 
    
    public static Connection getConnection() {
        try {
        	Class.forName(driverName);
        	connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new RuntimeException(e); 
            }
        return connection;
    }
    
}
