package com.bvis.spring.api;

import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Contract;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Implements REST model to send maintenance request to c&c
 * @JsonIgnoreProperties(ignoreUnknown = true)
 */
public class MaintenanceObject {

	@JsonProperty
	private String processId;
	
	@JsonProperty
	private String maintenanceId;
	
	@JsonProperty
	private Car car;
	
	@JsonProperty
	private String jobType;
	
	@JsonProperty
	private String problemDescription;

	public MaintenanceObject(Car maintcar, String jt, String pd, int maintid, String pid) {
		super();
		this.processId = pid;
		this.maintenanceId = String.valueOf(maintid);
		this.car = maintcar;
		this.jobType = jt;
		this.problemDescription = pd;
	}
		


}
