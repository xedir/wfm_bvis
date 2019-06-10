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
	
	
	//  Querie generation and call for Customers and BusinessCustomers, generate queries for askQuery(queryText) here.
	
	public static ResultSet askForCustomer(String first_name, String last_name) {
		String queryText = "SELECT * FROM CUSTOMER WHERE FIRST_NAME= '" 
								+ first_name + "' AND LAST_NAME='" + last_name +"'; ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForCustomerByID(int id) {
		String queryText = "SELECT * FROM CUSTOMER WHERE ID= '" 
								+ id + "'; ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForCarByID(int id) {	
		String queryText = "SELECT * FROM CARS WHERE ID= '" 
								+ id + "'; ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet getAllCustomerData() {
		
		String queryText = "SELECT * FROM CUSTOMER;";
		
		return connection.askQuery(queryText);
		
	}
	
	public static ResultSet askForId(String type, String first_name, String last_name) {
		
		String queryText = "SELECT ID FROM " + type + " WHERE FIRST_NAME = '"+ first_name + "' AND LAST_NAME='" + last_name + "'" ; 
		
		return connection.askQuery(queryText);
	}
	
	
	public static ResultSet getAllFreeCars() {
		
		String queryText = "SELECT DISTINCT CAR_NAME FROM CARS WHERE STATUS='free';";
		
		return connection.askQuery(queryText);
	}
	
	public static ResultSet getFreeCar(String name) {
		
		String queryText = "SELECT * FROM CARS WHERE CAR_NAME='" + name + "' AND STATUS = 'free';";
		
		return connection.askQuery(queryText);
	}
	
	
	public static ResultSet askForBusinessCustomer(String company_name) {
		String queryText = "SELECT * FROM BUSINESS_CUSTOMER WHERE COMPANY_NAME= '" 
								+ company_name + "' ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForPrice(String name) {
		String queryText = "SELECT PRICE_PER_DAY FROM CARS WHERE CAR_NAME= '" 
								+ name + "' ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForPrivateContractByID(int id) {
		String queryText = "SELECT * FROM PRIVATE_CONTRACTS WHERE ID= '" 
								+ id + "' ";
		return connection.askQuery(queryText);
	}

	public static ResultSet findAllContracts() {
		String queryText = "SELECT * FROM PRIVATE_CONTRACTS";
		return connection.askQuery(queryText);
	}
	
	public static void putCustomer(String first_name, String last_name, String address) {
		String queryText = "INSERT INTO CUSTOMER VALUES(default, '" + first_name + "', '"+ last_name + "', '"+ address + " ')";
		connection.putQuery(queryText);
	}
	
	public static void putNewCar(String car_name, int price, String status) {
		String queryText = "INSERT INTO CARS VALUES(default, '" + car_name + "', " + price + " ,'" + status +"' )";
		connection.putQuery(queryText);
	}
	
	public static void putCarAsRented(int id) {
		String queryText = "UPDATE CARS SET STATUS = 'rented' WHERE ID = '" + id + "' ";
		connection.putQuery(queryText);
	}

	
	public static void putBusinessCustomer(String company_name, String address) {
		
		String queryText = "INSERT INTO BUSINESS_CUSTOMER VALUES(default, '" + company_name + "', '"+ address + " ')";
		connection.putQuery(queryText);
		
		//return rs;
	}
	
	public static void putContract(String first_name, String last_name, int customerId, String address, String car, int carId, String insurance, String start, String end, long duration, double price, String status){
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
		connection.putQuery(queryText);
	}
	
	public static void putCarAsFree(int id) {
		String queryText = "UPDATE CARS SET STATUS='free' WHERE id=" + id + ";";
		connection.putQuery(queryText);
	}
	
	// new Andre
	public static ResultSet askForPrivateContractByCustomerID(int id) {
		String queryText = "SELECT * FROM PRIVATE_CONTRACTS WHERE CUSTOMERID= " 
				+ id;
		return connection.askQuery(queryText);
	}
	// new Andre
	public static ResultSet askForClaimByID(int id) {
		String queryText = "SELECT * FROM CLAIMS WHERE ID= " 
								+ id + "; ";
		return connection.askQuery(queryText);
	}
	// new Andre
	public static ResultSet askForQuotById(int id) {
		String queryText = "SELECT * FROM QUOTATIONS WHERE ID= "
				+ id + "; ";
		return connection.askQuery(queryText);
	}
	// new Andre
	public static ResultSet getClaimIDdatabase(int customerID, String claimType) {
		String queryText = "SELECT ID FROM CLAIMS WHERE customerId= '" 
								+ customerID + "' AND claimType= '"+ claimType + "';";
		return connection.askQuery(queryText);
	}
	
	// new Andre
	public static int putClaim(int contractID, String first_name, String last_name, int customerID, String car, int carID, String insurance, String claimType, String isCovered, String status, String problemDesc, String carLocation) {
		String queryText = "INSERT INTO CLAIMS VALUES(default, '" + contractID 
				+ "', '" + first_name 
				+ "', '" + last_name 
				+ "', '" + customerID 
				+ "', '" + car
				+ "', '" + carID 
				+ "', '" + insurance 
				+ "', '" + claimType
				+ "', '" + isCovered
				+ "', '" + status
				+ "', '" + problemDesc
				+ "', '" + carLocation
				+ "')";
		int generatedKey = connection.putQuery(queryText);
		return generatedKey;
	}
	// new Andre
	public static void putQuot(int claimID, String damageDesc, double damageCost, String damagedParts, double partCosts) {
		String queryText = "INSERT INTO Quotations VALUES(default, '" + claimID 
				+ "', '" + damageDesc
				+ "', '" + damageCost
				+ "', '" + damagedParts 
				+ "', '" + partCosts
				+ "')";
		connection.putQuery(queryText);
	}
	
	public static void createDefaults(){
		
		String createQuery = "CREATE TABLE IF NOT EXISTS CUSTOMER(id bigint auto_increment, first_name varchar(255), last_name varchar(255), address varchar(255))";
		connection.putQuery(createQuery);
		String createBusinessQuery = "CREATE TABLE IF NOT EXISTS BUSINESS_CUSTOMER(id bigint auto_increment, company_name varchar(255), address varchar(255))";
		connection.putQuery(createBusinessQuery);
		
		String createCarsQuery = "CREATE TABLE IF NOT EXISTS CARS(id bigint auto_increment, car_name varchar(255), price_per_day int , status varchar(255))";
		connection.putQuery(createCarsQuery);
		String createContractsQuery = "CREATE TABLE IF NOT EXISTS PRIVATE_CONTRACTS(id bigint auto_increment, "															+ "start SMALLDATETIME, end SMALLDATETIME, duration bigint, price double, status varchar(255))";
		connection.putQuery(createContractsQuery);
		
		// new Andre
		String createClaimsQuery = "CREATE TABLE IF NOT EXISTS CLAIMS(ID bigint auto_increment, "
									+ "contractID int, first_name varchar(255), last_name varchar(255), customerId int, "
									+ " car varchar(255), carId int,insurance varchar(255), "
									+ " claimType varchar(255), isCovered varchar(255), status varchar(255), "
									+ "damageDesc varchar(255), carLocation varchar(255))";
		connection.putQuery(createClaimsQuery);
		String createQuotationsQuery = "CREATE TABLE IF NOT EXISTS Quotations(ID bigint auto_increment, "
				+ "claimID int, damage_desc varchar(255), damage_cost double, "
				+ " damaged_parts varchar(255), part_costs double)";
	}
	
	
	
}
