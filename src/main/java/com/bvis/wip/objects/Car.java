package com.bvis.wip.objects;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bvis.wip.db.ConnectionManager;

public class Car {
	
	String name;
	int dayPrice;
	int id;
	String status;
	
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
	
	public int getId() {
		return this.id;
	}
	
	public static Car createFromID(int id) throws SQLException {
		ResultSet rs = ConnectionManager.askForCarByID(id);
		rs.next();
		
		return new Car(rs.getString("CAR_NAME"), rs.getInt("PRICE_PER_DAY"), rs.getInt("ID"), rs.getString("STATUS"));
	}
	
}
