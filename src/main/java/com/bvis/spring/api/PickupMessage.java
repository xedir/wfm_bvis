package com.bvis.spring.api;

import java.util.Date;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class PickupMessage {

	PickupMessage(){}
	
	
	@JsonProperty
	public String processId;
	
	

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
//	/**
//	 * @return the pickupDate
//	 */
//	public String getPickupDate() {
//		return pickupDate;
//	}
//	/**
//	 * @param pickupDate the pickupDate to set
//	 */
//	public void setPickupDate(String pickupDate) {
//		this.pickupDate = pickupDate;
//	}
	
	
	
	
}
