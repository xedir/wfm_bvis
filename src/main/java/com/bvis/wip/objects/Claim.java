package com.bvis.wip.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.*;

public class Claim {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingClaim");
	
	Customer customer;
	Contract contract;
	Car car;
	int ID;
	int contractID;
	String claimType;
	String isCovered;
	String status;
	String damageDesc;
	String carLocation;
	Quotation quotation;
	String damagedParts;
	double partCosts;
	double totalCosts;
	
	// for "CreateClaim"
	public Claim(Contract contract, Customer customer, Car car, String claimType, String isCovered, String status, String damageDesc, String carLocation) {
		this.contract = contract;
		this.contractID = contract.getId();
		this.customer = customer;
		this.car = car;
		this.claimType = claimType;
		this.isCovered = isCovered;
		this.status = status;
		this.damageDesc = damageDesc;
		this.carLocation = carLocation;
	}
	
	public Claim(int id, Contract contract, Customer customer,Car car, String claimType, String isCovered, String status, String damageDesc, String carLocation) {
		this.ID = id;
		this.contract = contract;
		this.contractID = contract.getId();
		this.customer = customer;
		this.car = car;
		this.claimType = claimType;
		this.isCovered = isCovered;
		this.status = status;
		this.damageDesc = damageDesc;
		this.carLocation = carLocation;
	}
	
	// save in data base
	public void save() throws SQLException {
		ConnectionManager.putClaim(this.contractID, this.customer.first_name, this.customer.last_name, this.customer.getId(), this.car.getName(), this.car.getId(), this.contract.getInsurance(), this.claimType, this.isCovered, this.status, this.damageDesc, this.carLocation);
		LOGGER.info("Claim created: " + this.customer.getName() + " Car: " + this.car.getName() + " Claim type" + this.claimType);
	}
	
	public static Claim createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForClaimByID(id);
		rs.next();
		return new Claim(id, Contract.createFromID(rs.getInt("contractID")), Customer.createFromID(rs.getInt("customerId")), Car.createFromID(rs.getInt("carId")), rs.getString("claimType"), rs.getString("isCovered"), rs.getString("status"), rs.getString("damageDesc"), rs.getString("carLocation"));
	}

	// getter and setter
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Contract getContract() {
		return this.contract;
	}
	
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public Car getCar() {
		return this.car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public Quotation getQuotation() {
		return this.quotation;
	}
	
	public void setQuotation(Quotation givenQuotation) {
		this.quotation = givenQuotation;
		this.damagedParts = givenQuotation.getDamagedParts();
		this.partCosts = givenQuotation.getPartCosts();
		this.totalCosts = givenQuotation.getTotalCosts();
	}
	
	public int getClaimID() {
		return this.ID;
	}
	
	public void setClaimID(int claimID) {
		this.ID = claimID;
	}
	
	public int getContractID() {
		return this.contractID;
	}
	
	public void setContractID(int contractID) {
		this.contractID = contractID;
	}
	
	public String getClaimType() {
		return this.claimType;
	}
	
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	
	public String getIsCovered() {
		return this.isCovered;
	}
	
	public void setIsCovered(String isCovered) {
		this.isCovered = isCovered;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDesc() {
		return this.damageDesc;
	}
	
	public void setDesc(String damageDesc) {
		this.damageDesc = damageDesc;
	}
	
	public String getLocation() {
		return this.carLocation;
	}
	
	public void setLocation(String carLocation) {
		this.carLocation = carLocation;
	}
	
	public String getDamagedParts() {
		return this.damagedParts;
	}
	
	public Claim getClaim() {
		return this;
	}
}
