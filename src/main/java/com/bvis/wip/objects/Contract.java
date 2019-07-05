package com.bvis.wip.objects;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.bvis.wip.db.ConnectionManager;

public class Contract implements Serializable{
	
	private final static Logger LOGGER = Logger.getLogger("LoggingContract");
	

	Customer customer;
	Car car;
	int carId;
	String start;
	String end;
	String insurance;
	long duration;
	double price;
	int customerId;
	String status;
	int contractID;
	double extra_charge;
	int extra_days;
	String return_date;
	int companyid;
	boolean outOfCountry;
	boolean addDriver;
	String fullNameAD;
	String birthDate;
	// 
	
	public Contract(){}
	
	
	public Contract(Customer customer, int companyid, Car car, String start, String end, long duration,String insurance, double price, boolean outOfCountry, boolean addDriver, String fullNameAD, String birthDateAddDr) throws SQLException {
		this.customer = customer;
		this.companyid = companyid;
		this.car = car;
		this.carId = car.getId();
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.insurance = insurance;
		this.price = price;
		this.customerId = customer.getId();
		this.status = "pending";
		this.outOfCountry = outOfCountry;
		this.addDriver = addDriver;
		this.fullNameAD = fullNameAD;
		this.birthDate = birthDateAddDr;
		/*
		this.lastNameAddDriver = lastNameAddDriver;
		*/
		// LOGGER.info(this.getDetails());
	}
	
	
	
	public Contract(Customer customer, int companyid, Car car, String start, String end, long duration,String insurance, double price, int contractID) throws SQLException {
		this.customer = customer;
		this.car = car;
		this.carId = car.getId();
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.insurance = insurance;
		this.price = price;
		this.customerId = customer.getId();
		this.status = "ongoing";
		this.contractID = contractID;
		this.companyid = companyid;
		// LOGGER.info(this.getDetails());
	}
	
	
	public static Contract createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForPrivateContractByID(id);
		rs.next();
		return new Contract(Customer.createFromID(rs.getInt("CUSTOMERID")),rs.getInt("COMPANYID"), Car.createFromID(rs.getInt("CARID")), rs.getString("START"), rs.getString("END"), rs.getLong("DURATION"), rs.getString("INSURANCE"), rs.getDouble("PRICE"), rs.getInt("ID"));
	}
	
	
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getId() {
		return this.contractID;
	}
	
	/**
	 * @return the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * @param car the car to set
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the insurance
	 */
	public String getInsurance() {
		return insurance;
	}

	public String getDetails() {
		
		String details = 
				"Customer: " + this.customer.getName() + 
				". Rented car: " + this.car.getName() + 
				". Rental period from " + this.start.toString() + " till " + this.end.toString();
	
		return this.customer.getName();
	}

	
	public boolean getOutOfC() {
		return this.outOfCountry;
	}
	
	public void setOutOfC(boolean newOutOfCountry) {
		this.outOfCountry = newOutOfCountry;
	}
	
	public boolean getAddDriver() {
		return this.addDriver;
	}
	
	public void setAddDriver(boolean newAddDriver) {
		this.addDriver = newAddDriver;
	}
	
	public String getLastNameAddDriver() {
		return this.fullNameAD;
	}
	
	public void setLastNameAddDriver(String newFullNameAD) {
		this.fullNameAD = newFullNameAD;
	}
	
	public String getBirthDateAD() {
		return this.birthDate;
	}
	
	public void setBirthDateAD(String newBirthDate) {
		this.birthDate = newBirthDate;
	}
	
	public Contract getContract() {
		return this;
	}
	
	public static void finalizeContract(Contract contract) {
		ConnectionManager.putFinalizeContract(contract.getId());
		contract.getCar().setFree();
		
	}
	
	public Integer save() throws SQLException {
			ConnectionManager.putContract(this.customer.first_name, this.customer.last_name, this.customer.getId(), this.customer.address, this.car.getName(), this.car.getId(), this.insurance, this.start, this.end, this.duration, this.price, this.status, this.extra_charge, this.extra_days, this.return_date, this.companyid, this.outOfCountry, this.addDriver, this.fullNameAD, this.birthDate);
			LOGGER.info("Contract created: " + this.customer.getName() + " Car: " + this.car.getName() + " Duration: " + this.duration + " days, for a price of " + this.price);	
			return contID;
	}
	
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	
	public double getExtra_charge() {
		return extra_charge;
	}

	public void setExtra_charge(double extra_charge) {
		this.extra_charge = extra_charge;
	}

	public int getExtra_days() {
		return extra_days;
	}

	public void setExtra_days(int extra_days) {
		this.extra_days = extra_days;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	public int getCompanyid() {
		return companyid;
	}

	public void setCompanyid(int companyid) {
		this.companyid = companyid;
	}

	
	
	

}
