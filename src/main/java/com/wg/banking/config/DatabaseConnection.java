package com.wg.banking.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
//		if (connection == null) {
//            try {
//            	Class.forName("com.mysql.cj.jdbc.Driver");		
//            	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","mysql");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
		Class.forName("com.mysql.cj.jdbc.Driver");		
    	String URL = "jdbc:mysql://localhost:3306/bank";
		String USERNAME = "root";
		String PASSWORD = "mysql";
		connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return connection;
	}
}