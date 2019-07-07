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
	private String requestId;
	
	@JsonProperty
	private Car2 car;
	
	@JsonProperty
	private String jobType;
	
	@JsonProperty
	private String problemDescription;

	public MaintenanceObject(Car maintcar, String jt, String pd, int maintid, String pid) {
		super();
		this.processId = pid;
		this.requestId = String.valueOf(maintid);
		this.car = new Car2(maintcar.getId(), maintcar.getLocation());
		this.jobType = jt;
		this.problemDescription = pd;
	}
		


}

