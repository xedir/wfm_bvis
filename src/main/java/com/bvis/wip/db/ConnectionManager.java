package com.bvis.wip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import org.camunda.bpm.engine.variable.value.StringValue;

public class ConnectionManager {
	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");

	// Querie generation and call for Customers and BusinessCustomers, generate
	// queries for askQuery(queryText) here.

	public static ResultSet askForCustomer(String first_name, String last_name) throws SQLException {
		String queryText = "SELECT * FROM CUSTOMER WHERE FIRST_NAME= '" + first_name + "' AND LAST_NAME='" + last_name
				+ "'; ";
		return connection.askQuery(queryText);
	}

	public static ResultSet askForCustomerByID(int id) throws SQLException {
		String queryText = "SELECT * FROM CUSTOMER WHERE ID= '" + id + "'; ";
		return connection.askQuery(queryText);
	}

	public static ResultSet askForCarByID(int id) throws SQLException {
		String queryText = "SELECT * FROM CARS WHERE ID= '" + id + "'; ";
		return connection.askQuery(queryText);
	}

	public static ResultSet getAllCustomerData() throws SQLException {

		String queryText = "SELECT * FROM CUSTOMER;";

		return connection.askQuery(queryText);

	}

	public static ResultSet askForId(String type, String first_name, String last_name) throws SQLException {

		String queryText = "SELECT ID FROM " + type + " WHERE FIRST_NAME = '" + first_name + "' AND LAST_NAME='"
				+ last_name + "'";

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
	
	
	public static ResultSet getCertainCustomer (String first_name, String last_name , String address) {
		String queryText = "SELECT * FROM CUSTOMER WHERE FIRST_NAME= '" + first_name + "' AND LAST_NAME= '" + last_name + "' AND ADDRESS= '" + address + "';";
		return connection.askQuery(queryText);
	}
	

	public static ResultSet askForBusinessCustomer(String company_name) {
		String queryText = "SELECT * FROM BUSINESS_CUSTOMER WHERE COMPANY_NAME= '" 
								+ company_name + "' ";
		return connection.askQuery(queryText);
	}

	public static ResultSet askForBusinessCustomerByID(int compid) {
		String queryText = "SELECT * FROM BUSINESS_CUSTOMER WHERE ID= " + compid + "; ";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForBusinessAgreement(int companyid) {
		String queryText = "SELECT * FROM BUSINESS_AGREEMENT WHERE BUSINESS_CUSTOMER_ID = "+companyid+";";
		return connection.askQuery(queryText);
	}
	
	public static ResultSet askForPrice(String name) {
		String queryText = "SELECT PRICE_PER_DAY FROM CARS WHERE CAR_NAME= '" 
								+ name + "'; ";
		return connection.askQuery(queryText);
	}

	public static ResultSet askForPrivateContractByID(int id) {
		String queryText = "SELECT * FROM PRIVATE_CONTRACTS WHERE ID="+ id + " AND STATUS = 'ongoing'; ";
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
	
	
	public static ResultSet askForPrivateContractByCustomerID(int id) {
		String queryText = "SELECT * FROM PRIVATE_CONTRACTS WHERE CUSTOMERID= " 
				+ id;
		return connection.askQuery(queryText);
	}
	
	// new Andre
	public static ResultSet getClaimIDdatabase(int customerID, String claimType) {
		String queryText = "SELECT ID FROM CLAIMS WHERE customerId= '" 
								+ customerID + "' AND claimType= '"+ claimType + "';";
		return connection.askQuery(queryText);
	}

	public static ResultSet getNeedMaintenanceCars() throws SQLException {

		String queryText = "SELECT * FROM CARS WHERE STATUS = 'free' AND NEXT_MAINTENANCE <=  DATEADD(DAY, 7, CURRENT_DATE);"; // For final
																											// verion,
																											// this
																											// should be
																											// == not <=
																											// so that
																											// we are 7
																											// days
																											// ahead of
																											// maintance
																											// without
																											// double
																											// count.
		return connection.askQuery(queryText);
	}



	public static void putCustomer(String first_name, String last_name, String address, Integer phone, String email, String date_of_birth)
			throws SQLException {
		String queryText = "INSERT INTO CUSTOMER VALUES(default, '" + first_name + "', '" + last_name + "', '" + address
				+ "', " + phone + ", '" + email + "', '"+date_of_birth+"')";
		connection.putQuery(queryText);
	}
	
	public static void putCarAsRented(int id) throws SQLException {
		String queryText = "UPDATE CARS SET STATUS = 'rented' WHERE ID = '" + id + "' ";
		connection.putQuery(queryText);
	}
	
	public static void putCarAsPickedUp(int id) throws SQLException {
		String queryText = "UPDATE CARS SET STATUS = 'picked up' WHERE ID = '" + id + "' ";
		connection.putQuery(queryText);
	}

	public static void putBusinessCustomer(String company_name, String address, Integer bphone, String bemail) throws SQLException {

		String queryText = "INSERT INTO BUSINESS_CUSTOMER VALUES(default, '" + company_name + "', '" + address +"', " + bphone +", '" + bemail + "')";
		connection.putQuery(queryText);
	}

	public static Integer putContract(String first_name, String last_name, int customerId, String address, String car,
			int carId, String insurance, String start, String end, long duration, double price, String status, double extra_charge, int extra_days, String return_date, int companyid, boolean outOfCountry, boolean addDriver, String fullNameAD, String birthDateAddDr)
			throws SQLException {
		String queryText = "INSERT INTO PRIVATE_CONTRACTS VALUES(default, '" + first_name + "', '" + last_name + "', '"
				+ customerId + "', '" + address + "', '" + car + "', '" + carId + "', '" + insurance + "', '" + start
				+ "', '" + end + "', '" + duration + "', '" + price + "', '" + status +"', "+extra_charge+ ", "+extra_days+ ", "+return_date+  ", "+companyid+", "+outOfCountry+", "+addDriver+", '"+fullNameAD+"', '"+birthDateAddDr+"')";
		
		Integer contractid = null;
		try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
				PreparedStatement statement = connection.prepareStatement(queryText,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) 
			{
			   contractid = rs.getInt(1);
			}
		}
		return contractid;
	}

	public static void putContractAsOngoing(int contractid) throws SQLException {
		String queryText = "UPDATE PRIVATE_CONTRACTS  SET STATUS = 'ongoing' WHERE ID = " + contractid + ";";
		connection.putQuery(queryText);
	}
	
	public static void putContractAsRejected(int contractid) throws SQLException {
		String queryText = "UPDATE PRIVATE_CONTRACTS  SET STATUS = 'rejected' WHERE ID = " + contractid + ";";
		connection.putQuery(queryText);
	}
	
	public static void putNewCar(String car_name, int price, String status, String carLoc, int carValue ) throws SQLException {
		String queryText = "INSERT INTO CARS VALUES(default, '" + car_name + "', " + price + " ,'" + status	+"', '"+carLoc+"', DATEADD(MONTH, 3, CURRENT_DATE), "+carValue+")";
		connection.putQuery(queryText);
	}
		
	// new Andre
	public static void putClaim(int contractID, String first_name, String last_name, int customerID, String car, int carID, String insurance, String claimType, String isCovered, String status, String problemDesc, String carLocation) {
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
		connection.putQuery(queryText);
	}
	
	// new Andre
	public static void putQuot(int claimID, int serviceID, String damagedParts, double partCosts, double totalCosts) {
		String queryText = "INSERT INTO Quotations VALUES(default, '" + claimID 
				+ "', '" + serviceID
				+ "', '" + damagedParts
				+ "', '" + partCosts 
				+ "', '" + totalCosts
				+ "')";
		connection.putQuery(queryText);
	}

	public static void putInvoice (int claimID, String jobType, String services, double partCosts, double totalCosts) {
		String queryText = "INSERT INTO INVOICES VALUES(default, '" + claimID 
				+ "', '" + jobType
				+ "', '" + services
				+ "', '" + partCosts
				+ "', '" + totalCosts
				+ "')";
		connection.putQuery(queryText);
	}
	
	public static void putRequest(int claimID, int carID, String jobType, String damageDesc, String carLocation) {
		String queryText = "INSERT INTO REQUESTS VALUES(default, '" + claimID 
				+ "', '" + carID
				+ "', '" + jobType
				+ "', '" + damageDesc
				+ "', '" + carLocation 
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

	public static void putNewBusAgreement(String companyName, double discount10, double discount1030,
			double discount30) {
		String queryText = "INSERT INTO BUSINESS_AGREEMENT VALUES(DEFAULT, SELECT ID FROM BUSINESS_CUSTOMER WHERE COMPANY_NAME = '"
				+ companyName + "', " + discount10 + ", " + discount1030 + " , " + discount30 + ", CURRENT_TIMESTAMP);";
		connection.putQuery(queryText);
	}
	
	public static void putMaintPickeUp(int maintid, String pickupdae) throws SQLException {
		String queryText = "UPDATE MAINTENANCE SET PICK_UP_DATE  = '" + pickupdae
				+ "', STATUS = 'Car Picked Up' WHERE MAINT_ID = " + maintid + ";";
		connection.putQuery(queryText);
		String queryText1 = "UPDATE CARS SET STATUS = 'In Maintenance' WHERE ID = (SELECT CAR_ID FROM MAINTENANCE WHERE MAINT_ID = " + maintid + ");";
		connection.putQuery(queryText1);
	}
	
	public static void putMaintReturn(int maintid, String location) throws SQLException {
		String queryText = "UPDATE MAINTENANCE SET STATUS = 'Done' WHERE MAINT_ID = " + maintid + ";";
		connection.putQuery(queryText);
		String queryText1 = "UPDATE CARS SET STATUS = 'free', NEXT_MAINTENANCE = DATEADD(MONTH, 5, CURRENT_DATE), location = '"+location+"' WHERE ID = (SELECT CAR_ID FROM MAINTENANCE WHERE MAINT_ID = " + maintid + ");";
		connection.putQuery(queryText1);
	}

	public static Integer putNewMaintenanceReturnID(int car_id, String status) throws SQLException {
		String queryText1 = "UPDATE CARS SET STATUS = 'Waiting Maintenance' WHERE ID = " + car_id + ";";
		connection.putQuery(queryText1);
		
		String queryText = "INSERT INTO MAINTENANCE (CAR_ID, STATUS) VALUES (" + car_id + ", '" + status + "')";
		Integer maintid = null;
		try (Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");
				PreparedStatement statement = connection.prepareStatement(queryText,
						Statement.RETURN_GENERATED_KEYS);) {

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) 
			{
				maintid = rs.getInt(1);
			   System.out.println("ID of generated maint: "+ rs.getString(1));
			}
		}
		return maintid;
	}
		
	public static Integer askForActiveContracts(int companyid) {
		String queryText = "SELECT * FROM PRIVATE_CONTRACTS WHERE COMPANYID = "+companyid+" AND STATUS = 'ongoing'";
		ResultSet contracts = connection.askQuery(queryText);
		Integer count = 0;
		try {
			contracts.last();
			count = contracts.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}	
	
	public static ResultSet getCarPirceByClaimId(int claimId) {
		String queryText = "SELECT * FROM CARS WHERE ID = (SELECT CARID FROM PRIVATE_CONTRACTS WHERE ID = "+ claimId + ");"; 
		return connection.askQuery(queryText);
	}
		
	public static ResultSet getCarInMaint(int maintid) throws SQLException {
		String queryText = "SELECT * FROM CARS WHERE ID = (SELECT CAR_ID FROM MAINTENANCE WHERE MAINT_ID = " + maintid + ");";
		return connection.askQuery(queryText);
	}
	
	public static void putNoDamageReturn(int carid, String selectedloc) throws SQLException {
		String queryText = "UPDATE CARS SET STATUS = 'free', location = '"+selectedloc+"' WHERE ID = " + carid + ";";
		connection.putQuery(queryText);
	}
	
	public static void putDamageReturn(int carid, String location) throws SQLException {
		String queryText = "UPDATE CARS SET STATUS = 'Needs Repair', location = '"+location+"' WHERE ID = " + carid + ";";
		connection.putQuery(queryText);
	}
	
	public static void putExtraCharge(int contractid, double extra, int extradays, String returnedOn) throws SQLException {
		String queryText = "UPDATE PRIVATE_CONTRACTS  SET EXTRA_CHARGE = "+extra+", EXTRA_DAYS = "+extradays+",   RETURN_DATE = '"+returnedOn+"' WHERE ID = " + contractid + ";";
		connection.putQuery(queryText);
	}


	public static void createDefaults() {

		String createQuery = "CREATE TABLE IF NOT EXISTS CUSTOMER(id bigint auto_increment, first_name varchar(255), last_name varchar(255), address varchar(255), phone bigint, email varchar(255), date_of_birth SMALLDATETIME)";
		connection.putQuery(createQuery);
		String createBusinessQuery = "CREATE TABLE IF NOT EXISTS BUSINESS_CUSTOMER(id bigint auto_increment, company_name varchar(255), address varchar(255), phone bigint, email varchar(255))";
		connection.putQuery(createBusinessQuery);

		String createCarsQuery = "CREATE TABLE IF NOT EXISTS CARS(id bigint auto_increment, car_name varchar(255), price_per_day int , status varchar(255), location varchar(255), next_maintenance SMALLDATETIME, car_value int)";
		connection.putQuery(createCarsQuery);
		
		String createContractsQuery = "CREATE TABLE IF NOT EXISTS PRIVATE_CONTRACTS(id bigint auto_increment, "
				+ "first_name varchar(255), last_name varchar(255), customerId int, "
				+ "address varchar(255), car varchar(255), carId int,insurance varchar(255), "
				+ "start SMALLDATETIME, end SMALLDATETIME, duration bigint, price double,status varchar(255), extra_charge double, extra_days int, return_date SMALLDATETIME, "
				+ "companyid int, outOfCountry boolean, addDriver boolean, fullNameAD varchar(255), birthDateAddDr SMALLDATETIME)";
		connection.putQuery(createContractsQuery);

		String createMaintenanceQuery = "CREATE TABLE IF NOT EXISTS MAINTENANCE(maint_id bigint auto_increment, "
				+ "car_id bigint, created_on SMALLDATETIME DEFAULT CURRENT_TIMESTAMP, pick_up_date SMALLDATETIME,"
				+ "status varchar(100), invoice_number varchar(50), invoice_amount double)";
		connection.putQuery(createMaintenanceQuery);

		String createAgreementQuery = "CREATE TABLE IF NOT EXISTS BUSINESS_AGREEMENT(AGREEMENT_ID bigint auto_increment, BUSINESS_CUSTOMER_ID bigint, DISCOUNT_10 DOUBLE, DISCOUNT_10TO30 DOUBLE, DISCOUNT_30UP DOUBLE, created_on SMALLDATETIME DEFAULT CURRENT_TIMESTAMP);";
		connection.putQuery(createAgreementQuery);
		
		String createClaimsQuery = "CREATE TABLE IF NOT EXISTS CLAIMS(ID bigint auto_increment, " 
									+ "contractID int, first_name varchar(255), last_name varchar(255), customerId int, " 
									+ " car varchar(255), carId int,insurance varchar(255), " 
									+ " claimType varchar(255), isCovered varchar(255), status varchar(255), " 
									+ "damageDesc varchar(255), carLocation varchar(255))"; 
		connection.putQuery(createClaimsQuery); 
		
		String createRequestQuery = "CREATE TABLE IF NOT EXISTS REQUESTS(ID bigint auto_increment, " 
				+ "claimID int, carID int, jobType varchar(255), damageDesc varchar(255), carLocation varchar(255))"; 
		connection.putQuery(createRequestQuery);
		
		String createQuotationQuery = "CREATE TABLE IF NOT EXISTS Quotations(ID bigint auto_increment, " 
				+ "claimID int, serviceID int, damagedParts varchar(255), partCosts double, " 
				+ " totalCosts double)"; 
		connection.putQuery(createQuotationQuery);
		
		String createInvoiceQuery = "CREATE TABLE IF NOT EXISTS Invoices(ID bigint auto_increment, " 
				+ "claimID int, jobType varchar(255), services varchar(255), partCosts double, totalCosts double)"; 
		connection.putQuery(createInvoiceQuery);
	}
}
