package com.bvis.wip.objects;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bvis.wip.db.ConnectionManager;

public class BusinessCustomer {
	
	int id;
	String company_name;
	String address;
	int phone; 
	String email;
	
	
	public BusinessCustomer(){

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCompany_name() {
		return company_name;
	}


	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public static BusinessCustomer createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForBusinessCustomerByID(id);
		rs.next();
		BusinessCustomer comp = new BusinessCustomer();
		comp.setId(rs.getInt("ID"));
		comp.setCompany_name(rs.getString("COMPANY_NAME"));
		comp.setAddress(rs.getString("ADDRESS"));
		comp.setPhone(rs.getInt("PHONE"));
		comp.setEmail(rs.getString("EMAIL"));
				
		return comp;
	}
	
	
	
}
