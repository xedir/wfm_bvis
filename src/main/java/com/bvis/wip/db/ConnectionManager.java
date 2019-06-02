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
	
	public static ResultSet getAllCustomerData() throws SQLException {
		
		String queryText = "SELECT * FROM CUSTOMER;";
		
		return connection.askQuery(queryText);
		
	}
	
	public static ResultSet getAllFreeCars() throws SQLException {
		
		String queryText = "SELECT DISTINCT CAR_NAME FROM CARS WHERE STATUS='free';";
		
		return connection.askQuery(queryText);
		
	}	
	
	
	public static ResultSet askForBusinessCustomer(String company_name) throws SQLException {
		String queryText = "SELECT * FROM BUSINESS_CUSTOMER WHERE COMPANY_NAME= '" 
								+ company_name + "' ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForPrice(String name) throws SQLException {
		String queryText = "SELECT PRICE_PER_DAY FROM CARS WHERE CAR_NAME= '" 
								+ name + "' ";
		return connection.askQuery(queryText);
	}

	public static void putCustomer(String first_name, String last_name, String address) throws SQLException {
		
		String queryText = "INSERT INTO CUSTOMER VALUES(default, '" + first_name + "', '"+ last_name + "', '"+ address + " ')";
		connection.putQuery(queryText);
		
		//return rs;
	}
	
	public static void putNewCar(String car_name, int price, String status) throws SQLException {
		String queryText = "INSERT INTO CARS VALUES(default, '" + car_name + "', " + price + " ,'" + status +"' )";
		connection.putQuery(queryText);
	}
	
	public static void putBusinessCustomer(String company_name, String address) throws SQLException {
		
		String queryText = "INSERT INTO BUSINESS_CUSTOMER VALUES(default, '" + company_name + "', '"+ address + " ')";
		connection.putQuery(queryText);
		
		//return rs;
	}
	
	
	public static void createDefaults() throws SQLException {
		
		String createQuery = "CREATE TABLE IF NOT EXISTS CUSTOMER(id bigint auto_increment, first_name varchar(255), last_name varchar(255), address varchar(255))";
		connection.putQuery(createQuery);
		String createBusinessQuery = "CREATE TABLE IF NOT EXISTS BUSINESS_CUSTOMER(id bigint auto_increment, company_name varchar(255), address varchar(255))";
		connection.putQuery(createBusinessQuery);
		String createCarsQuery = "CREATE TABLE IF NOT EXISTS CARS(id bigint auto_increment, car_name varchar(255), price_per_day int , status varchar(255))";
		connection.putQuery(createCarsQuery);
	}
	
	
	
}
