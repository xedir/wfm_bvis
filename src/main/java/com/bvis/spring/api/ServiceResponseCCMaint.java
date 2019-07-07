package com.bvis.spring.api;

import java.io.Serializable;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceResponseCCMaint implements Serializable {

	ServiceResponseCCMaint(){}
	
	
	public ServiceResponseCCMaint(InsuranceClaiming returnedClaim, Quotation quotation){
		super();
		this.processId = returnedClaim.getProcessId();
		this.serviceResponsibility = ServiceResponsibility.convertFromDamagesToServiceResponsibility(returnedClaim.getDamages(), quotation.getJobDetails());
	}
	
	@JsonProperty
	public String processId;
	@JsonProperty
	public ServiceResponsibility[] serviceResponsibility;
	
	
	
}


class ServiceResponsibilityMaint implements Serializable{
	
	@JsonProperty
	public String serviceIDMaint;
	@JsonProperty
	public String serviceResponsibilityMaint;
	
	public static ServiceResponsibilityMaint[] convertFromDamagesToServiceResponsibilityMaint(Damage[] damages, JobDetails[] jobs) {
		int length = damages.length;
		ServiceResponsibilityMaint[] services = new ServiceResponsibilityMaint[length];
		
		for(int i = 0; i < length; i++ ) {			
			ServiceResponsibilityMaint serviceObject = new ServiceResponsibilityMaint();
			serviceObject.setServiceIDMaint(Integer.toString(jobs[i].getServiceId()));
			if (damages[i].isCovered()) {
				serviceObject.setServiceResponsibilityMaint("CAPITOL_PAYMENT");
			} else {
				serviceObject.setServiceResponsibilityMaint("BVIS_PAYMENT");
			}
			
			services[i] = serviceObject;
		}
		return services;
	}

	/**
	 * @return the serviceIDMaint
	 */
	public String getServiceIDMaint() {
		return serviceIDMaint;
	}

	/**
	 * @param serviceIDMaint the serviceIDMaint to set
	 */
	public void setServiceIDMaint(String serviceIDMaint) {
		this.serviceIDMaint = serviceIDMaint;
	}

	/**
	 * @return the serviceResponsibilityMaint
	 */
	public String getServiceResponsibilityMaint() {
		return serviceResponsibilityMaint;
	}

	/**
	 * @param serviceResponsibilityMaint the serviceResponsibilityMaint to set
	 */
	public void setServiceResponsibilityMaint(String serviceResponsibilityMaint) {
		this.serviceResponsibilityMaint = serviceResponsibilityMaint;
	}


	
	
}