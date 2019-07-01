package com.bvis.wip.objects;

import java.sql.SQLException;

import com.bvis.wip.db.ConnectionManager;

public class Invoice {
	
	int id;
	int claimID;
	double costs;
	
	public Invoice(double costs) {
		this.costs = costs;
	}
	
	public void save() throws SQLException {
		ConnectionManager.putInvoice(this.claimID, this.costs);
	}
	
	public int getClaimID() {
		return this.claimID;
	}
	
	public void setClaimID(int newClaimID) {
		this.claimID = newClaimID;
	}
	
	public double getCosts() {
		return this.costs;
	}
	
	public void setClaimID(double newCosts) {
		this.costs = newCosts;
	}
}
