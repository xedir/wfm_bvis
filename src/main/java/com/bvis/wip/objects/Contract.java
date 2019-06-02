package com.bvis.wip.objects;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.bvis.wip.db.ConnectionManager;

public class Contract {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingContract");
	

	Customer customer;
	Car car;
	String start;
	String end;
	String insurance;
	long duration;
	double price;
	int customerId;
	
	
	public Contract(Customer customer, Car car, String start, String end, long duration,String insurance, double price) throws SQLException {
		this.customer = customer;
		this.car = car;
		this.start = start;
		this.end = end;
		this.duration = duration;
		this.insurance = insurance;
		this.price = price;
		this.customerId = customer.getId();
		
		// LOGGER.info(this.getDetails());
	}
	
	public String getDetails() {
		
		String details = 
				"Customer: " + this.customer.getName() + 
				". Rented car: " + this.car.getName() + 
				". Rental period from " + this.start.toString() + " till " + this.end.toString();
	
		return this.customer.getName();
	}
	
	public Contract getContract() {
		return this;
	}
	
	public void save() throws SQLException {
		
			ConnectionManager.putContract(this.customer.first_name, this.customer.last_name, this.customer.getId(), this.customer.address, this.car.getName(), this.insurance, this.start, this.end, this.duration, this.price);
		
		
		
	}
	
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	
	public void saveContract() {
		LOGGER.info("Contract created: " + this.customer.getName() + " Car: " + this.car.getName() + " Duration: " + this.duration + " days, for a price of " + this.price);
	}
	

}
