package com.bvis.wip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class connection {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");
	
	
	
	public static ResultSet askQuery(String name) throws SQLException {
		

		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = "SELECT ID " +
						"FROM CUSTOMER " +
						"WHERE NAME= '"+ name +"';";
		ResultSet rs = stmt.executeQuery(query);
		
		return rs;
	}
	
	public static void putQuery(String first_name, String last_name, String address) throws SQLException {
		
		String name = first_name + " " + last_name;
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO CUSTOMER VALUES(default, '" + name + "', '"+ address + " ')";
		int rs = stmt.executeUpdate(query);
		
		//return rs;
	}
}

