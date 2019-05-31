package com.bvis.wip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class ConnectionManager {
	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");
	
	
	// Querie generation and call for Customers and BusinessCustomers, generate queries for askQuery(queryText) here.
	
	public static ResultSet askForCustomer(String first_name, String last_name) throws SQLException {
		String queryText = "SELECT * FROM CUSTOMER WHERE FIRST_NAME= '" 
								+ first_name + "' AND LAST_NAME='" + last_name +"'; ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForBusinessCustomer(String company_name) throws SQLException {
		String queryText = "SELECT * FROM BUSINESS_CUSTOMER WHERE COMPANY_NAME= '" 
								+ company_name + "' ";
		return connection.askQuery(queryText);
	}

	
	public static void putCustomer(String first_name, String last_name, String address) throws SQLException {
		
		String queryText = "INSERT INTO CUSTOMER VALUES(default, '" + first_name + "', '"+ last_name + "', '"+ address + " ')";
		connection.putQuery(queryText);
		
		//return rs;
	}
	
	public static void putBusinessCustomer(String company_name, String address) throws SQLException {
		
		String queryText = "INSERT INTO BUSINESS_CUSTOMER VALUES(default, '" + company_name + "', '"+ address + " ')";
		connection.putQuery(queryText);
		
		//return rs;
	}
}
