package com.bvis.wip.objects;

public class Customer {

	String first_name;
	String last_name;
	String address;
		
	
	public Customer(String first_name, String last_name){
		this.first_name = first_name;
		this.last_name = last_name;
	}
	

	public String getName() {
		
		String name = this.first_name + " " + this.last_name;
		
		return name;
		
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
