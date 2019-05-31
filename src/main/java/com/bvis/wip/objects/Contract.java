package com.bvis.wip.objects;

import java.util.Date;
import java.util.logging.Logger;

public class Contract {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingContract");
	

	Customer customer;
	Car car;
	Date start;
	Date end;
	String insurance;
	
	
	public Contract(Customer customer, Car car, Date start, Date end, String insurance) {
		this.customer = customer;
		this.car = car;
		this.start = start;
		this.end = end;
		this.insurance = insurance;
		
		LOGGER.info(this.getDetails());
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
	
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	

}
