package com.springBoot.project.mysqlConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class ConnectionManager {
	private static final Logger logger = Logger.getLogger(ConnectionManager.class);
	@Value("${jdbc.url}") private static String URL;
	@Value("${jdbc.username}") private static String USERNAME;
	@Value("${jdbc.password}") private static String PASSWORD;
    private static String driverName = "com.mysql.jdbc.Driver";  
	
	private static Connection con;

	/**
	 * This method create and return a connection
	 * @return Connection
	 */
    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException | RuntimeException e) {
            	logger.error(e);
            	throw new RuntimeException(e); 
            }
        } catch (ClassNotFoundException | RuntimeException e) {
        	logger.error(e);
        	throw new RuntimeException(e);
        }
        return con;
    }
    
}
