package com.bvis.spring.api;

import java.io.Serializable;
import java.sql.SQLException;

import com.bvis.wip.db.ConnectionManager;
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
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}


	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}


	/**
	 * @return the claimDate
	 */
	public String getClaimDate() {
		return claimDate;
	}


	/**
	 * @return the insuranceNumber
	 */
	public int getInsuranceNumber() {
		return insuranceNumber;
	}


	/**
	 * @param insuranceNumber the insuranceNumber to set
	 */
	public void setInsuranceNumber(int insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
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


	public InsuranceClaiming(RealClaim claim, Quotation quotation, String processId) throws SQLException {
		super();
		this.processId = processId;
		this.insuranceNumber = ConnectionManager.askForContractInsuranceNumber(claim.getContract().getId());
		this.vehicleId = Integer.toString(claim.getCarId());
		System.out.println(vehicleId);
		this.claimDate = claim.getClaimDate();
		this.claimStatus = "pending";
		
		this.damages = Damage.convertFromJob(quotation.getJobDetails());
	}
	
	
	@JsonProperty
	private String processId;
	@JsonProperty
	private int insuranceNumber;
	@JsonProperty
	private String vehicleId;
	@JsonProperty
	private String claimDate;
	@JsonProperty
	private String claimStatus;
	@JsonProperty
	private Damage[] damages;
	
	public void setDamages(Damage[] jobDetails) {
		this.damages = jobDetails;
	}
	
	public Damage[] getDamages() {
		return this.damages;
	}
	
}

class Damage implements Serializable{
	
	public Damage(){}
	
	public static Damage[] convertFromJob(JobDetails[] jobDetails) {
		int length = jobDetails.length;
		Damage[] damage = new Damage[length];
		for(int i = 0; i < length; i++ ) {
			
			Damage damageObject = new Damage();
			damageObject.setPart(jobDetails[i].getServiceName());
			damageObject.setPrice(jobDetails[i].getParticularCost());
			damageObject.setCovered(false);
			damage[i] = damageObject;
		}
		
		return damage;
	}
	
	public Damage(String part, int price) {
		this.part = part;
		this.price = price;
		
	}
	
	public Damage(String part, int price, boolean covered) {
		this.part = part;
		this.price = price;
		this.covered = covered;
		
	}
	
	/**
	 * @return the part
	 */
	public String getPart() {
		return part;
	}

	/**
	 * @param part the part to set
	 */
	public void setPart(String part) {
		this.part = part;
	}



	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the covered
	 */
	public boolean isCovered() {
		return covered;
	}

	/**
	 * @param covered the covered to set
	 */
	public void setCovered(boolean covered) {
		this.covered = covered;
	}

	@JsonProperty
	public String part;
	@JsonProperty
	public double price;
	@JsonProperty
	public boolean covered;
	
}
