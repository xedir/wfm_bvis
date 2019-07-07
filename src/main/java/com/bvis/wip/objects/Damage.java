package com.bvis.wip.objects;

import spinjar.com.fasterxml.jackson.annotation.JsonProperty;

public class Damage {

	
	public Damage(){}
	
	public Damage(String part, int price) {
		this.part = part;
		this.price = price;
		
	}
	
	public Damage(String part, int price, boolean covered) {
		this.part = part;
		this.price = price;
		this.covered = covered;
		
	}
	
	@JsonProperty
	public String part;
	@JsonProperty
	public int price;
	@JsonProperty
	public boolean covered;
	
	
	
}
