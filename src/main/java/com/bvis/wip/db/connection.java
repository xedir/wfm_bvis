package com.bvis.wip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class connection {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");
	
	
	
	public static ResultSet askQuery(String first_name, String last_name) throws SQLException {
		
		
		//create db statement create table CUSTOMER(id bigint auto_increment, first_name varchar(255), last_name varchar(255), address_name varchar(255));

		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = "SELECT * " +
						"FROM CUSTOMER " +
						"WHERE FIRST_NAME= '"+ first_name +"' AND LAST_NAME= '"+ last_name + "' ;";
		ResultSet rs = stmt.executeQuery(query);
		
		return rs;
	}
	
	public static void putQuery(String first_name, String last_name, String address) throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO CUSTOMER VALUES(default, '" + first_name + "', '"+ last_name + "', '"+ address + " ')";
		int rs = stmt.executeUpdate(query);
		
		//return rs;
	}
}

