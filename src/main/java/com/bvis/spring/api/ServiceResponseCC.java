package com.bvis.spring.api;

import java.io.Serializable;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceResponseCC implements Serializable {

	ServiceResponseCC(){}
	
	
	public ServiceResponseCC(InsuranceClaiming returnedClaim, Quotation quotation){
		super();
		this.processId = returnedClaim.getProcessId();
		this.serviceResponsibility = ServiceResponsibility.convertFromDamagesToServiceResponsibility(returnedClaim.getDamages(), quotation.getJobDetails());
	}
	
	@JsonProperty
	public String processId;
	@JsonProperty
	public ServiceResponsibility[] serviceResponsibility;
	
	
	
}


class ServiceResponsibility implements Serializable{
	
	@JsonProperty
	public String serviceID;
	@JsonProperty
	public String serviceResponsibility;
	
	public static ServiceResponsibility[] convertFromDamagesToServiceResponsibility(Damage[] damages, JobDetails[] jobs) {
		int length = damages.length;
		ServiceResponsibility[] services = new ServiceResponsibility[length];
		
		for(int i = 0; i < length; i++ ) {			
			ServiceResponsibility serviceObject = new ServiceResponsibility();
			serviceObject.setServiceID(Integer.toString(jobs[i].getServiceId()));
			if (damages[i].isCovered()) {
				serviceObject.setServiceResponsibility("CAPITOL_PAYMENT");
			} else {
				serviceObject.setServiceResponsibility("BVIS_PAYMENT");
			}
			
			services[i] = serviceObject;
		}
		return services;
	}

	/**
	 * @return the serviceID
	 */
	public String getServiceID() {
		return serviceID;
	}

	/**
	 * @param serviceID the serviceID to set
	 */
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	/**
	 * @return the serviceResponsibility
	 */
	public String getServiceResponsibility() {
		return serviceResponsibility;
	}

	/**
	 * @param serviceResponsibility the serviceResponsibility to set
	 */
	public void setServiceResponsibility(String serviceResponsibility) {
		this.serviceResponsibility = serviceResponsibility;
	}
	
	
}