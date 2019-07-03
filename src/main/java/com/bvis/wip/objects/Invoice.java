package com.bvis.wip.objects;

import java.sql.SQLException;

import com.bvis.wip.db.ConnectionManager;

public class Invoice {
	
	int id;
	int claimID;
	String jobType;
	String Services;
	double partCosts;
	double totalCosts;
	
	public Invoice(String jobType, String Services, double partCosts, double totalCosts) {
		this.jobType = jobType;
		this.Services = Services;
		this.partCosts = partCosts;
		this.totalCosts = totalCosts;
	}
	
	public void save() throws SQLException {
		ConnectionManager.putInvoice(this.claimID, this.jobType, this.Services, this.partCosts, this.totalCosts);
	}
	
	public int getClaimID() {
		return this.claimID;
	}
	
	public void setClaimID(int newClaimID) {
		this.claimID = newClaimID;
	}
	
	public double getPartCosts() {
		return this.partCosts;
	}
	
	public void setPartCosts(double partCosts) {
		this.partCosts = partCosts;
	}
	
	public double getTotalCosts() {
		return this.totalCosts;
	}
	
	public void setTotalCosts(double newTotalCosts) {
		this.totalCosts = newTotalCosts;
	}
}
