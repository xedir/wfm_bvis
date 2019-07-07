package com.bvis.spring.api;

import com.bvis.wip.objects.RealClaim;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceRequesting {

	
	public ServiceRequesting() {}
	
	public ServiceRequesting(RealClaim claim) {
		this.pid = claim.getPid();
		this.car = new Car(claim.getVehicleId(), claim.getVehicleId(), claim.getLocation());
		this.jobType = "REPAIR";
		this.problemDescription = claim.getProblemDescription();
		
	}
	
	@JsonProperty
	public String pid;
	@JsonProperty
	public Car car;
	@JsonProperty
	public String jobType;
	@JsonProperty
	public String problemDescription;
	
	
	
	
}


class Car{
	
	Car(){}
	Car(String carId, String licensePlate, String location){
		this.carId = carId;
		int x = Integer.getInteger(carId) + 26745;
		String Lic = "DE-"+String.valueOf(x);
		this.licensePlate = Lic;; 	
		this.location = location;
	}
	
	@JsonProperty
	public String carId;
	@JsonProperty
	public String licensePlate;
	@JsonProperty
	public String location;
	
}