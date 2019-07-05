package com.bvis.spring.api;

import com.bvis.wip.objects.Contract;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implements REST model to send the contract in the form Capitol requires.
 * @JsonIgnoreProperties(ignoreUnknown = true)
 */
public class PolicySending {

	
	//(customer, car, start, end, duration, insurance , price)
	
	public PolicySending(Contract contract, String pid) {
		super();
		this.customer = contract.getCustomer().getName();
		this.car = contract.getCar().getName();
		this.startDate = contract.getStart() + "T00:00:00.001Z";
		this.endDate = contract.getEnd() + "T00:00:00.001Z";
		this.dateOfBirth = contract.getCustomer().getDateOfBirth();
		
	}
	
	//Attributes
	@JsonProperty
	private String customer;
	@JsonProperty
	private String car;
	@JsonProperty
	private String startDate;
	@JsonProperty
	private String endDate;
	@JsonProperty
	private String dateOfBirth;
	
	
	public PolicySending() {
	}
	
	
	@Override
	public String toString() {
		return "ContractSending [customer=" + customer + ", car=" + car + "]";
	}
	

}
