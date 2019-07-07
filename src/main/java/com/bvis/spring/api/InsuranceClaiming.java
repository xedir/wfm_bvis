package com.bvis.spring.api;

import java.io.Serializable;

import com.bvis.wip.objects.RealClaim;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class InsuranceClaiming implements Serializable{

	public InsuranceClaiming() {}
	
	
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


	public InsuranceClaiming(RealClaim claim, Quotation quotation) {
		super();
		this.vehicleId = Integer.toString(claim.getCarId());
		System.out.println(vehicleId);
		this.claimDate = "2019-07-12T09:12:33.001Z";
		this.claimStatus = "pending";
		this.damages = quotation.getServiceEvaluation();
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

class Damage implements Serializable{
	
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
