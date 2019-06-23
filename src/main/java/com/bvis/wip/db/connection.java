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
	
	public static ResultSet askQuery(String queryText) throws SQLException {

		//create db statement create table CUSTOMER(id bigint auto_increment, first_name varchar(255), last_name varchar(255), address_name varchar(255));

		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = queryText;
		ResultSet rs = stmt.executeQuery(query);
		
		return rs;
	}
	
	public static void putQuery(String queryText) throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = queryText;
		int rs = stmt.executeUpdate(query);
		
		//return rs;
	}
	
}
