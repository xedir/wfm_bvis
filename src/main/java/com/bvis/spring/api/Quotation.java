package com.bvis.spring.api;

import java.io.Serializable;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class Quotation implements Serializable{

	Quotation(){}
	
	
	@JsonProperty
	public String processId;
	@JsonProperty
	public Damage[] serviceEvaluation;

	
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
	 * @return the serviceEvaluation
	 */
	public Damage[] getServiceEvaluation() {
		return serviceEvaluation;
	}
	/**
	 * @param serviceEvaluation the serviceEvaluation to set
	 */
	public void setServiceEvaluation(Damage[] serviceEvaluation) {
		this.serviceEvaluation = serviceEvaluation;
	}



	
}


