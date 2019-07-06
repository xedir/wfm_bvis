package com.bvis.wip.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import com.bvis.wip.db.ConnectionManager;

public class Quotation {
	int id;
	int claimID;
	int serviceID;
	String damagedParts;
	double partCosts;
	double totalCosts;
	
	public Quotation(int claimID, int serviceID, String damagedParts, double partCosts){
		this.claimID = claimID;
		this.serviceID = serviceID;
		this.damagedParts = damagedParts;
		this.partCosts = partCosts;
		this.totalCosts = sum(this.partCosts);
	}
	
	public Quotation(int id, int claimID, int serviceID, String damagedParts, double partCosts){
		this.id = id;
		this.claimID = claimID;
		this.serviceID = serviceID;
		this.damagedParts = damagedParts;
		this.partCosts = partCosts;
		this.totalCosts = sum(this.partCosts);
	}
	
	// save in data base
	public void save() throws SQLException {
		ConnectionManager.putQuot(this.claimID, this.serviceID, this.damagedParts, this.partCosts, this.totalCosts);
	}
	
	public static Quotation createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForQuotById(id);
		rs.next();
		return new Quotation(id, rs.getInt("claimID"), rs.getInt("serviceID"), rs.getString("damaged_parts"), rs.getDouble("part_costs"));
	}
	
	public double sum(double partCosts) {
		return 1;
	}
	
	// setter and getter
	public int getClaimID() {
		return this.claimID;
	}
	
	public int getServiceID() {
		return this.serviceID;
	}
	
	public void setDamagedParts(String damagedParts) {
		this.damagedParts = damagedParts;
	}
	
	public String getDamagedParts() {
		return this.damagedParts;
	}
	
	public void setPartCosts(double partCosts) {
		this.partCosts = partCosts;
	}
	
	public double getPartCosts() {
		 return this.partCosts;
	 }
	
	public void setTotalCosts(double totalCost) {
		this.totalCosts = totalCosts;
	}
	
	public double getTotalCosts() {
		return this.totalCosts;
	}
	
}
