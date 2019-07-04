package com.bvis.wip.objects;

import java.io.Serializable;
import java.util.List;

public class BusinessCustomer implements Serializable {
	String company_name;
	String address;
	List<Rent> rents;
		
	
	public BusinessCustomer(String company_name){
		this.company_name = company_name;
	}
	

	public String getName() {
		String name = this.company_name;		
		return name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
