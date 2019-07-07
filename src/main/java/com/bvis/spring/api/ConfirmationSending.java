package com.bvis.spring.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfirmationSending {

	public ConfirmationSending(String insuranceNumber){
		this.insuranceNumber = insuranceNumber;
		this.accepted = true;
		
	}
	
	
	
	@JsonProperty
	private String insuranceNumber;
	@JsonProperty
	private Boolean accepted;
	
}
