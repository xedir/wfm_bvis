package com.bvis.wip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Logger;

public class ConnectionManager {
	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");
	
	
	// Querie generation and call for Customers and BusinessCustomers, generate queries for askQuery(queryText) here.
	
	public static ResultSet askForCustomer(String first_name, String last_name) throws SQLException {
		String queryText = "SELECT * FROM CUSTOMER WHERE FIRST_NAME= '" 
								+ first_name + "' AND LAST_NAME='" + last_name +"'; ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForCustomerByID(int id) throws SQLException {
		String queryText = "SELECT * FROM CUSTOMER WHERE ID= '" 
								+ id + "'; ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForCarByID(int id) throws SQLException {	
		String queryText = "SELECT * FROM CARS WHERE ID= '" 
								+ id + "'; ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet getAllCustomerData() throws SQLException {
		
		String queryText = "SELECT * FROM CUSTOMER;";
		
		return connection.askQuery(queryText);
		
	}
	
	public static ResultSet askForId(String type, String first_name, String last_name) throws SQLException {
		
		String queryText = "SELECT ID FROM " + type + " WHERE FIRST_NAME = '"+ first_name + "' AND LAST_NAME='" + last_name + "'" ; 
		
		return connection.askQuery(queryText);
	}
	
	
	public static ResultSet getAllFreeCars() throws SQLException {
		
		String queryText = "SELECT DISTINCT CAR_NAME FROM CARS WHERE STATUS='free';";
		
		return connection.askQuery(queryText);
	}
	
	public static ResultSet getFreeCar(String name) throws SQLException {
		
		String queryText = "SELECT * FROM CARS WHERE CAR_NAME='" + name + "' AND STATUS = 'free';";
		
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
	
	public static ResultSet askForPrivateContractByID(int id) throws SQLException {
		String queryText = "SELECT * FROM PRIVATE_CONTRACTS WHERE ID= '" 
								+ id + "' ";
		return connection.askQuery(queryText);
	}

	public static void putCustomer(String first_name, String last_name, String address) throws SQLException {
		String queryText = "INSERT INTO CUSTOMER VALUES(default, '" + first_name + "', '"+ last_name + "', '"+ address + " ')";
		connection.putQuery(queryText);
	}
	
	public static void putNewCar(String car_name, int price, String status) throws SQLException {
		String queryText = "INSERT INTO CARS VALUES(default, '" + car_name + "', " + price + " ,'" + status +"' )";
		connection.putQuery(queryText);
	}
	
	public static void putCarAsRented(int id) throws SQLException {
		String queryText = "UPDATE CARS SET STATUS = 'rented' WHERE ID = '" + id + "' ";
		connection.putQuery(queryText);
	}

	
	public static void putBusinessCustomer(String company_name, String address) throws SQLException {
		
		String queryText = "INSERT INTO BUSINESS_CUSTOMER VALUES(default, '" + company_name + "', '"+ address + " ')";
		connection.putQuery(queryText);
		
		//return rs;
	}
	
	public static void putContract(String first_name, String last_name, int customerId, String address, String car, int carId, String insurance, String start, String end, long duration, double price, String status) throws SQLException {
		String queryText = "INSERT INTO PRIVATE_CONTRACTS VALUES(default, '" + first_name 
				+ "', '" + last_name 
				+ "', '" + customerId 
				+ "', '" + address 
				+ "', '" + car
				+ "', '" + carId 
				+ "', '" + insurance 
				+ "', '" + start 
				+ "', '" + end 
				+ "', '" + duration 
				+ "', '" + price 
				+ "', '" + status
				+ "')";
		connection.putQuery(queryText);
	}
	
	public static void putFinalizeContract(int id) {
		String queryText = "UPDATE PRIVATE_CONTRACTS SET STATUS='finalized' WHERE id=" + id + ";";
		try {
			connection.putQuery(queryText);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void putCarAsFree(int id) {
		String queryText = "UPDATE CARS SET STATUS='free' WHERE id=" + id + ";";
		try {
			connection.putQuery(queryText);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void createDefaults(){
		
		String createQuery = "CREATE TABLE IF NOT EXISTS CUSTOMER(id bigint auto_increment, first_name varchar(255), last_name varchar(255), address varchar(255))";
		try {
			connection.putQuery(createQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createBusinessQuery = "CREATE TABLE IF NOT EXISTS BUSINESS_CUSTOMER(id bigint auto_increment, company_name varchar(255), address varchar(255))";
		try {
			connection.putQuery(createBusinessQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String createCarsQuery = "CREATE TABLE IF NOT EXISTS CARS(id bigint auto_increment, car_name varchar(255), price_per_day int , status varchar(255))";
		try {
			connection.putQuery(createCarsQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String createContractsQuery = "CREATE TABLE IF NOT EXISTS PRIVATE_CONTRACTS(id bigint auto_increment, "
																		+ "first_name varchar(255), last_name varchar(255), customerId int, "
																		+ "address varchar(255), car varchar(255), carId int,insurance varchar(255), "
																		+ "start SMALLDATETIME, end SMALLDATETIME, duration bigint, price double, status varchar(255))";
		try {
			connection.putQuery(createContractsQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
