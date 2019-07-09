package com.bvis.spring.api;

import java.io.Serializable;
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
public class PolicySending implements Serializable{
	
		
	//(customer, car, start, end, duration, insurance , price)
	
	/**
	 * @return the processID
	 */
	public String getProcessId() {
		return processId;
	}


	/**
	 * @return the insuranceNumber
	 */
	public String getInsuranceNumber() {
		return id;
	}


	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}


	public int getCarValue() {
		return carValue;
	}


	public void setCarValue(int carValue) {
		this.carValue = carValue;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public Coverage getCoverage() {
		return coverage;
	}


	public void setCoverage(Coverage coverage) {
		this.coverage = coverage;
	}


	public Driver[] getDrivers() {
		return drivers;
	}


	public void setDrivers(Driver[] drivers) {
		this.drivers = drivers;
	}


	public void setProcessId(String processId) {
		this.processId = processId;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	public PolicySending(Contract contract, String pid, boolean outOfCountry, Customer adDriver) throws SQLException {
		super();
		this.processId = pid;
		this.id = null;
		this.vehicleId = Integer.toString(contract.getCar().getId());
		this.carValue = contract.getCar().getValueByID(contract.getCar().getId());
		this.price = contract.getCar().getPriceByID(contract.getCar().getId());
		this.startDate = contract.getStart() + "T00:00:00.001Z";
		this.endDate = contract.getEnd() + "T00:00:00.001Z";
		this.status = "Requested";
		this.coverage = new Coverage(contract.getInsurance(), outOfCountry);

		//Additional Driver Information	
		this.drivers = new Driver[2];
		int mainCustomerLicenseID = contract.getCustomer().getId() + 81604;
		int addCustomerLicenseID = 2 * contract.getCustomer().getId() + 81604;
		
		String bdateDriver = (contract.getCustomer().getDateOfBirth()).substring(0, 10);
		
		drivers[0] = new Driver(contract.getCustomer().getFirst_name(), contract.getCustomer().getLast_name(), mainCustomerLicenseID, bdateDriver);
		drivers[1] = new Driver(adDriver.getFirst_name(), adDriver.getLast_name(), addCustomerLicenseID, adDriver.getDateOfBirth());
		}
	
	
	/**
	 * @param insuranceNumber the insuranceNumber to set
	 */
	public void setInsuranceNumber(String insuranceNumber) {
		this.id = insuranceNumber;
	}


	public PolicySending(Contract contract, String pid, boolean outOfCountry) throws SQLException {
		super();
		this.processId = pid;
		this.id = null;
		this.vehicleId = Integer.toString(contract.getCar().getId());
		this.carValue = contract.getCar().getValueByID(contract.getCar().getId());
		this.price = contract.getCar().getPriceByID(contract.getCar().getId());
		this.startDate = contract.getStart() + "T00:00:00.001Z";
		this.endDate = contract.getEnd() + "T00:00:00.001Z";
		this.status = "Requested";
		this.coverage = new Coverage(contract.getInsurance(), outOfCountry);


		String bdateDriver = (contract.getCustomer().getDateOfBirth()).substring(0, 10);
		System.out.println(bdateDriver);
		
		this.drivers = new Driver[1];
		int mainCustomerLicenseID = contract.getCustomer().getId() + 81604;
		drivers[0] = new Driver(contract.getCustomer().getFirst_name(), contract.getCustomer().getLast_name(), mainCustomerLicenseID, bdateDriver);
		}
	
	//Attributes
	@JsonProperty
	private String processId;
	@JsonProperty
	private String id;
	@JsonProperty
	private String vehicleId;
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
	private Driver[] drivers;

	
	
	public PolicySending() {
	}
		

}

// Coverage Object to send within the policy, gets created by default with dynamic value for outOfCountry as only this value is individual for each rent
class Coverage implements Serializable{
	


	public int getCoPay() {
		return coPay;
	}


	public void setCoPay(int coPay) {
		this.coPay = coPay;
	}


	public boolean isOutOfCountry() {
		return outOfCountry;
	}


	public void setOutOfCountry(boolean outOfCountry) {
		this.outOfCountry = outOfCountry;
	}


	public int getMaxCoverage() {
		return maxCoverage;
	}


	public void setMaxCoverage(int maxCoverage) {
		this.maxCoverage = maxCoverage;
	}


	public Coverage(){}
	
	@JsonProperty
	int coPay;
	@JsonProperty
	boolean outOfCountry;
	@JsonProperty
	int maxCoverage;
	
	
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


class Driver implements Serializable{
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	public void setDriverLicenseNumber(int driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

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
