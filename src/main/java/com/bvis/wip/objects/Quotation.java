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
	String damageDesc;
	double damageCost; // delete part costs and just make it total costs
	String damagedParts;
	double partCosts;
	
	public Quotation(int claimID, String damageDesc, double damageCost, String damagedParts, double partCosts){
		this.claimID = claimID;
		this.damageDesc = damageDesc;
		this.damageCost = damageCost;
		this.damagedParts = damagedParts;
		this.partCosts = partCosts;
	}
	
	public Quotation(int id, int claimID, String damageDesc, double damageCost, String damagedPart, double partCosts){
		this.id = id;
		this.claimID = claimID;
		this.damageDesc = damageDesc;
		this.damageCost = damageCost;
		this.damagedParts = damagedPart;
		this.partCosts = partCosts;
	}
	
	// save in data base
	public void save() throws SQLException {
		ConnectionManager.putQuot(this.claimID, this.damageDesc, this.damageCost, this.damagedParts, this.partCosts);
	}
	
	public static Quotation createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForQuotById(id);
		rs.next();
		return new Quotation(id, rs.getInt("claimID"), rs.getString("damage_desc"), rs.getDouble("damage_cost"), rs.getString("damaged_parts"), rs.getDouble("part_costs"));
	}
	
	// setter and getter
	public int getClaimID() {
		return this.claimID;
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
	
	public void setDamageCost(double damageCost) {
		this.damageCost = damageCost;
	}
	
	public double getDamageCost() {
		return this.damageCost;
	}
	
}
