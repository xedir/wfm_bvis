package com.bvis.wip.objects;
// class with information for requests, required by CC
public class RequestCC {
	int carID;
	String claimType;
	String damageDesc;
	String location;
	
	public RequestCC (Claim claim) {
		this.carID = claim.getCar().getId();
		this.claimType = claim.getClaimType();
		this.damageDesc = claim.getDesc();
		this.location = claim.getLocation();
	}
	
	public int getCarID() {
		return this.carID;
	}
}
