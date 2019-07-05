package com.bvis.wip.objects;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bvis.wip.db.ConnectionManager;

public class Customer implements Serializable {

	String first_name;
	String last_name;
	String type;
	String address;
	int id;
	String date_of_birth;
	List<Rent> rents;
		
	
	public Customer() {}
	
	
	public Customer(String first_name, String last_name){
		this.first_name = first_name;
		this.last_name = last_name;
		this.type = "privateCustomer";
	}
	
	public Customer(String first_name, String last_name, String address, int id, String date_of_birth){
		this.first_name = first_name;
		this.last_name = last_name;
		this.type = "privateCustomer";
		this.id = id;
		this.address = address;
		this.date_of_birth = date_of_birth;
	}
	
	public static Customer createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForCustomerByID(id);
		rs.next();
		return new Customer(rs.getString("FIRST_NAME"),rs.getString("LAST_NAME"),rs.getString("ADDRESS"),rs.getInt("ID"), rs.getString("DATE_OF_BIRTH")); 
	}
	

	public String getName() {
		
		String name = this.first_name + " " + this.last_name;
		
		return name;
		
	}
	
	public int getId() throws SQLException{
		ResultSet id;
		
		id = ConnectionManager.askForId("customer", this.first_name, this.last_name);
		id.next();
		int customerId = id.getInt("ID"); 
		
		
		return customerId;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setBirth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
}
