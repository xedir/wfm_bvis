package com.bvis.wip.objects;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bvis.wip.db.ConnectionManager;

public class Car implements Serializable {
	
	String name;
	int dayPrice;
	int id;
	String status;
	String location; 
	String licensePlate;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Car() {}
	
	public String getLicensePlate() {
		return licensePlate;
	}

	public Car(String name) {
		this.name = name;
		this.status = "free";
	}
	
	public Car(String name, int dayPrice, int id, String status) {
		this.name = name;
		this.dayPrice = dayPrice;
		this.id = id;
		this.status = status;
		
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public void setFree() {
		ConnectionManager.putCarAsFree(this.getId());
		this.status = "free";
	}
	
	public void setRented() {
		this.status = "rented";
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPickedUp() {
		this.status = "picked up";
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static Car createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForCarByID(id);
		rs.next();
		
		return new Car(rs.getString("CAR_NAME"), rs.getInt("PRICE_PER_DAY"), rs.getInt("ID"), rs.getString("STATUS"));
	}

	public void setLicensePlate(int id) {
		// TODO Auto-generated method stub
		int x = id + 12345;
		String Lic = "DE-"+String.valueOf(x);
		this.licensePlate = Lic;
	}
	
}
