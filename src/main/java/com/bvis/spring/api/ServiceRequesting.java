package com.bvis.spring.api;

import com.bvis.wip.objects.RealClaim;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceRequesting {

	
	public ServiceRequesting() {}
	
	public ServiceRequesting(RealClaim claim) {
		this.processId = claim.getPid();
		this.car = new Car2(claim.getCar().getId(), claim.getCar().getLocation());
		this.jobType = "REPAIR";
		this.problemDescription = claim.getProblemDescription();
	}

	
	@JsonProperty
	public String processId;
	@JsonProperty
	public Car2 car;
	@JsonProperty
	public String jobType;
	@JsonProperty
	public String problemDescription;
	
}


class Car2{
	
	Car2(){}
	Car2(int carId, String location){
		this.carId = carId;
		int x = carId + 26745;
		String Lic = "DE-"+String.valueOf(x);
		this.licensePlate = Lic;; 	
		this.location = location;
	}
	
	
	@JsonProperty
	public int carId;
	@JsonProperty
	public String licensePlate;
	@JsonProperty
	public String location;
	
}