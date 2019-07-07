package com.bvis.spring.api;

import com.bvis.wip.objects.Claim;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class InsuranceClaiming {

	public InsuranceClaiming() {}
	
	
	public InsuranceClaiming(Claim claim) {
		this.vehicleId = Integer.toString(claim.getCar().getId());
		this.claimDate = "2019-07-12T09:12:33.001Z";
		this.claimStatus = "pending";
		
	}
	
	@JsonProperty
	private String vehicleId;
	@JsonProperty
	private String claimDate;
	@JsonProperty
	private String claimStatus;
	@JsonProperty
	private Damage[] damages;
	
	public void setDamages(Damage[] damages) {
		this.damages = damages;
	}
	
	public Damage[] getDamages() {
		return this.damages;
	}
	
}

class Damage{
	
	public Damage(){}
	
	public Damage(String part, int price) {
		this.part = part;
		this.price = price;
		
	}
	
	public Damage(String part, int price, boolean covered) {
		this.part = part;
		this.price = price;
		this.covered = covered;
		
	}
	
	@JsonProperty
	public String part;
	@JsonProperty
	public int price;
	@JsonProperty
	public boolean covered;
	
}
