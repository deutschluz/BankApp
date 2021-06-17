package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * 
 * JDBC = Java Database Connectivity = a java library with classes to connect to a database
 * - with JDBC we can set up a connection that will use our RDS credentials
 * - once we have that connection - we can make queries and execute statements
 * 
 * Steps:
 * 1. Add the JDBC dependency to our project (pom.xml)
 * 2. Set up a connection file using our credentials
 * 3. Refactor our DAOs (or create them) that will make calls to our database when needed
 * 		- DAO = Data Access Object - Objects meant for accessing data
 * 
 * Notable Interfaces of JDBC
 * 
 * Connection Interface - represents established connections to our RDS -> the main gateway
 * 
 * Statement Interface - we won't use this (because it's not protected against SQL Injection)
 * 
 * PreparedStatement Interface - Used to create SQL Statements and Queries (sanitizes input and protects them against SQL injection)
 * 
 * CallableStatement Interface - Used to call Stored Procedures
 * 
 * ResultSet Interface - Objects that represent the data returned from our SQL Statements or Queries
 * 
 * */

/*
 * We are going to maintain and observe a single Connection Object
 * within this class. If no Connection exists, we will create and
 * return one. If a connection does exists, we will return that. 
 * 
 * */
public class JDBCConnection {
	private static Connection conn = null;
	// Define a method to get the connection
	public static Connection getConnection() {
		try {
			// If connection doesn't exist - make one
			if (conn == null) {				
				Properties props = new Properties();			
				InputStream input =
						JDBCConnection.class.getClassLoader().getResourceAsStream("connection.properties");			
				props.load(input);			
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				conn = DriverManager.getConnection(url, username, password);
				return conn;
				
			} else {
				return conn;
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
