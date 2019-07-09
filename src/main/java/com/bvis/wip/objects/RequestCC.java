package com.bvis.wip.objects;

import java.sql.SQLException;

import com.bvis.wip.db.ConnectionManager;


// class with information for requests, required by CC
public class RequestCC {
	int claimID;
	int carID;
	String jobType;
	String damageDesc;
	String location;
	
	public RequestCC (Claim claim) {
		this.claimID = claim.getClaimID(); 
		this.carID = claim.getCar().getId();
		this.jobType = claim.getClaimType(); //maintenance or repair
		this.damageDesc = claim.getDesc();
		this.location = claim.getLocation();
	}
	
	public void save() throws SQLException {
		ConnectionManager.putRequest(this.claimID, this.carID, this.jobType, this.damageDesc, this.location);
	}
	
	public int getClaimID() {
		return this.claimID;
	}
	
	public int getCarID() {
		return this.carID;
	}
	
	public String getJobType() {
		return this.jobType;
	}
	
	public String getDescription() {
		return this.damageDesc;
	}
	
	public String getLocation() {
		return this.location;
	}
}
