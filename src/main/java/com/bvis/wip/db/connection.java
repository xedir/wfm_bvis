package com.bvis.wip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class connection {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");
	
	// Only call askQuery(queryText) or putQuery(queryText) define methods and queries in ConnectionManager
	
	public static ResultSet askQuery(String queryText) {

		ResultSet rs = null;
		Connection conn;
		
		try {
			conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = queryText;
		rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static int putQuery(String queryText)  {
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = queryText;
		int rs = stmt.executeUpdate(query);
		
		 try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int generatedKey = generatedKeys.getInt(1);
	                return generatedKey;
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		

		//return rs;
	}
	
}
