package com.bvis.spring.api;

import java.io.Serializable;
import spinjar.com.fasterxml.jackson.annotation.JsonProperty;


public class Quotation implements Serializable{

	Quotation(){}
	
	Quotation(String processId, JobDetails[] jobDetails){
		super();
		this.processId = processId;
		this.jobDetails = jobDetails;
	}
	
	@JsonProperty
	public String processId;
	@JsonProperty
	public JobDetails[] jobDetails;

	
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
	public JobDetails[] getJobDetails() {
		return jobDetails;
	}
	/**
	 * @param serviceEvaluation the serviceEvaluation to set
	 */
	public void setJobDetails(JobDetails[] jobDetails) {
		this.jobDetails = jobDetails;
	}



	
}

class JobDetails implements Serializable{
	
	JobDetails(){}
	
	
	
	@JsonProperty
	public int serviceId;
	@JsonProperty
	public String serviceName;
	@JsonProperty
	public double particularCost;
	
	/**
	 * @return the serviceId
	 */
	public int getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the particularCost
	 */
	public double getParticularCost() {
		return particularCost;
	}
	/**
	 * @param particularCost the particularCost to set
	 */
	public void setParticularCost(double particularCost) {
		this.particularCost = particularCost;
	}
	
	
	
	
	
	
}


