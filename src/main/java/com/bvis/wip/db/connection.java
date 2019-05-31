package com.bvis.wip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class connection {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");
	
	
	
	public static ResultSet askQuery(String db, String first_name, String last_name, String company_name) throws SQLException {
		String where;
		
		if(company_name.length()>2) {
			where = " WHERE COMPANY_NAME= '"+ company_name + "' ;";
		} else {
			where = " WHERE FIRST_NAME= '"+ first_name +"' AND LAST_NAME= '"+ last_name + "' ;";
		}
			

		//create db statement create table CUSTOMER(id bigint auto_increment, first_name varchar(255), last_name varchar(255), address_name varchar(255));

		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = "SELECT * " +
						"FROM " + db + where;
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
	
	public static void putBusinessQuery(String company_name, String address) throws SQLException {
		
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
		Statement stmt = conn.createStatement();
		String query = "INSERT INTO BUSINESS_CUSTOMER VALUES(default, '" + company_name + "', '" + address + " ')";
		int rs = stmt.executeUpdate(query);
		
		//return rs;
	}
	
}

