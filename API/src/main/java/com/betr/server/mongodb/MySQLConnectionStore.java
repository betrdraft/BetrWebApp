package com.betr.server.mongodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionStore {

	  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	  private static final String DB_URL = "jdbc:mysql://localhost/EMP";
	  
	  private static final String USER = "username";
	  private static final String PASS = "password";
	  
	  public static Connection getConnection() throws SQLException {
		  try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		  
		return DriverManager.getConnection(DB_URL,USER,PASS);
	  }
	
}
