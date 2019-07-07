package com.bvis.wip.objects;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.bvis.wip.db.ConnectionManager;

public class RealClaim implements Serializable{

	public RealClaim() {}
	
	public RealClaim(Contract contract, String problemDescription, String processId) throws SQLException {
		this.processId = processId;
		this.contract = contract;
		this.carId = contract.carId;
		this.vehicleId = Integer.toString(contract.carId);
		this.claimDate = createMyDate();
		this.car = Car.createFromID(contract.carId);
		this.location = car.getLocation();
		this.claimStatus = "pending";
		this.claimType = "Repair";
		this.problemDescription = problemDescription;
		
	}


	
	public String processId;
	public Contract contract;
	public int carId;
	public Car car;
	public String claimDate;
	public String location;
	public String vehicleId;
	public String claimStatus;
	public String claimType;
	public String problemDescription;
	//Damage[] Array to be filled later
	public Damage[] damages;
	

	
	/**
	 * @return the pid
	 */
	public String getPid() {
		return processId;
	}
	
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String processId) {
		this.processId = processId;
	}
	
	/**
	 * @return the claimType
	 */
	public String getClaimType() {
		return claimType;
	}

	/**
	 * @param claimType the claimType to set
	 */
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	/**
	 * @return the problemDescription
	 */
	public String getProblemDescription() {
		return problemDescription;
	}

	/**
	 * @param problemDescription the problemDescription to set
	 */
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	private String createMyDate() {
		Date today = Calendar.getInstance().getTime();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern);
		return  simpleFormatter.format(today);
	}
	
	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	/**
	 * @return the carId
	 */
	public int getCarId() {
		return carId;
	}

	/**
	 * @param carId the carId to set
	 */
	public void setCarId(int carId) {
		this.carId = carId;
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
	 * @return the claimDate
	 */
	public String getClaimDate() {
		return claimDate;
	}

	/**
	 * @param claimDate the claimDate to set
	 */
	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the vehicleId
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @return the claimStatus
	 */
	public String getClaimStatus() {
		return claimStatus;
	}

	/**
	 * @param claimStatus the claimStatus to set
	 */
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	/**
	 * @return the damages
	 */
	public Damage[] getDamages() {
		return damages;
	}

	/**
	 * @param damages the damages to set
	 */
	public void setDamages(Damage[] damages) {
		this.damages = damages;
	}


	
	
}
