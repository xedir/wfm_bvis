package com.bvis.spring.api;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;

import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implements REST model to send the contract in the form Capitol requires.
 * @JsonIgnoreProperties(ignoreUnknown = true)
 */
public class PolicySending {
		
	//(customer, car, start, end, duration, insurance , price)
	
	/**
	 * @return the processID
	 */
	public String getProcessID() {
		return processID;
	}


	/**
	 * @return the insuranceNumber
	 */
	public String getInsuranceNumber() {
		return insuranceNumber;
	}


	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	public PolicySending(Contract contract, String pid, boolean outOfCountry, Customer adDriver) throws SQLException {
		super();
		this.processID = pid;
		this.insuranceNumber = "";
		this.vehicleID = Integer.toString(contract.getCar().getId());
		this.carValue = contract.getCar().getValueByID(contract.getCar().getId());
		this.price = contract.getCar().getPriceByID(contract.getCar().getId());
		this.startDate = contract.getStart() + "T00:00:00.001Z";
		this.endDate = contract.getEnd() + "T00:00:00.001Z";
		this.status = "requested";
		this.coverage = new Coverage(contract.getInsurance(), outOfCountry);

		//Additional Driver Information	
		this.driver = new Driver[2];
		int mainCustomerLicenseID = contract.getCustomer().getId() + 81604;
		int addCustomerLicenseID = 2 * contract.getCustomer().getId() + 81604;
		driver[0] = new Driver(contract.getCustomer().getFirst_name(), contract.getCustomer().getLast_name(), mainCustomerLicenseID, contract.getCustomer().getDateOfBirth());
		driver[1] = new Driver(adDriver.getFirst_name(), adDriver.getLast_name(), addCustomerLicenseID, adDriver.getDateOfBirth());
		}
	
	
	/**
	 * @param insuranceNumber the insuranceNumber to set
	 */
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}


	public PolicySending(Contract contract, String pid, boolean outOfCountry) throws SQLException {
		super();
		this.processID = pid;
		this.insuranceNumber = "";
		this.vehicleID = Integer.toString(contract.getCar().getId());
		this.carValue = contract.getCar().getValueByID(contract.getCar().getId());
		this.price = contract.getCar().getPriceByID(contract.getCar().getId());
		this.startDate = contract.getStart() + "T00:00:00.001Z";
		this.endDate = contract.getEnd() + "T00:00:00.001Z";
		this.status = "requested";
		this.coverage = new Coverage(contract.getInsurance(), outOfCountry);

		this.driver = new Driver[1];
		int mainCustomerLicenseID = contract.getCustomer().getId() + 81604;
		driver[0] = new Driver(contract.getCustomer().getFirst_name(), contract.getCustomer().getLast_name(), mainCustomerLicenseID, contract.getCustomer().getDateOfBirth());
		}
	
	//Attributes
	@JsonProperty
	private String processID;
	@JsonProperty
	private String insuranceNumber;
	@JsonProperty
	private String vehicleID;
	@JsonProperty
	private int carValue;
	@JsonProperty
	private int price;
	@JsonProperty
	private String startDate;
	@JsonProperty
	private String endDate;
	@JsonProperty
	private String status;
	@JsonProperty
	private Coverage coverage;
	@JsonProperty
	private Driver[] driver;

	
	
	public PolicySending() {
	}
		

}

// Coverage Object to send within the policy, gets created by default with dynamic value for outOfCountry as only this value is individual for each rent
class Coverage {
	
	public Coverage(){}
	
	@JsonProperty
	int coPay;
	@JsonProperty
	boolean outOfCountry;
	@JsonProperty
	int maxCoverage;
	
	public void setcoPay(int value) {
		this.coPay = value;
	}
	
	public void setmaxCoverage(int value) {
		this.maxCoverage = value;
	}
	
	public void setoutOfCountry(boolean value) {
		this.outOfCountry = value;
	}
	
	Coverage(String insurance ,boolean outOfCountry){
		System.out.println(insurance);
		if(insurance.equalsIgnoreCase("A") ) {
			this.coPay=500;
		} else if (insurance.equalsIgnoreCase("B")) {
			this.coPay=1000;
		} else {
			this.coPay = 1500;
			
		}
		
		this.outOfCountry = outOfCountry;
		this.maxCoverage = 1000000;
	}
}


class Driver{
	
	public Driver(){}
		
	@JsonProperty
	String firstName;
	@JsonProperty
	String lastName;
	@JsonProperty
	int driverLicenseNumber;
	@JsonProperty
	String dob;
	
	Driver(String firstName, String lastName, int driverLicenseNumber, String dob){
		this.firstName = firstName;
		this.lastName = lastName;
		this.driverLicenseNumber = driverLicenseNumber;
		this.dob = dob;
	}
	
	
}
