package com.bvis.wip.objects;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bvis.wip.db.ConnectionManager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({ "carId", "licensePlate", "location" })
public class Car implements Serializable {
	
	String name;
	int dayPrice;
	int carId;
	String status;
	String location; 
	String licensePlate;
	
	
	
	public Car() {
		
	}
	
	@JsonProperty("carId")
	public int getId() {
		return this.carId;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Car(String name) {
		this.name = name;
		this.status = "free";
	}
	
	public Car(String name, int dayPrice, int id, String status) {
		this.name = name;
		this.dayPrice = dayPrice;
		this.carId = id;
		this.status = status;
		
	}
	
	@JsonIgnore
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
		this.carId = id;
	}
	
	public void setPickedUp() {
		this.status = "picked up";
	}
	
	@JsonIgnore
	public String getStatus() {
		return this.status;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getValueByID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForCarByID(id);
		rs.next();
		return rs.getInt("CAR_VALUE");
	}
	
	public int getPriceByID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForCarByID(id);
		rs.next();
		return rs.getInt("PRICE_PER_DAY");
	}
	
	public static Car createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForCarByID(id);
		rs.next();
		
		return new Car(rs.getString("CAR_NAME"), rs.getInt("PRICE_PER_DAY"), rs.getInt("ID"), rs.getString("STATUS"));
	}

	public void setLicensePlate(int id) {
		// TODO Auto-generated method stub
		int x = id + 26745;
		String Lic = "DE-"+String.valueOf(x);
		this.licensePlate = Lic;
	}
	
}
