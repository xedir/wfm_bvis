package com.bvis.spring.api;

import java.io.Serializable;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceResponseCCMaint implements Serializable {

	ServiceResponseCCMaint(){}
	
	public ServiceResponseCCMaint(String processId, Quotation quotation){
		super();
		this.processId = processId;
		this.serviceResponsibility = ServiceResponsibilityMaint.convertToServiceResponsibility(quotation.getJobDetails());
	}
	
	@JsonProperty
	public String processId;
	@JsonProperty
	public ServiceResponsibilityMaint[] serviceResponsibility;
	
}


class ServiceResponsibilityMaint implements Serializable{

	@JsonProperty
	public String serviceID;
	
	@JsonProperty
	public String serviceResponsibility;
	
	public static ServiceResponsibilityMaint[] convertToServiceResponsibility(JobDetails[] jobs) {
		int length = jobs.length;
		ServiceResponsibilityMaint[] services = new ServiceResponsibilityMaint[length];
		
		for(int i = 0; i < length; i++ ) {			
			ServiceResponsibilityMaint serviceObject = new ServiceResponsibilityMaint();
			serviceObject.setServiceID(Integer.toString(jobs[i].getServiceId()));
			serviceObject.setServiceResponsibility("BVIS_PAYMENT");
			services[i] = serviceObject;
		}
		return services;
	}

	/**
	 * @return the serviceIDMaint
	 */
	public String getServiceID() {
		return serviceID;
	}

	/**
	 * @param serviceIDMaint the serviceIDMaint to set
	 */
	public void setServiceID(String serviceIDMaint) {
		this.serviceID = serviceIDMaint;
	}

	/**
	 * @return the serviceResponsibilityMaint
	 */
	public String getServiceResponsibility() {
		return serviceResponsibility;
	}

	/**
	 * @param serviceResponsibilityMaint the serviceResponsibilityMaint to set
	 */
	public void setServiceResponsibility(String serviceResponsibilityMaint) {
		this.serviceResponsibility = serviceResponsibilityMaint;
	}
	
}