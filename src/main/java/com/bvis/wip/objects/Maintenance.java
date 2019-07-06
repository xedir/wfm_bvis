package com.bvis.wip.objects;

public class Maintenance {
	
	int maint_id;  	
	int car_id;
	String created_on;
	String pickup_date;
	String car_return_date;
	String status;
	String invoice_number;
	double invoice_amount;
	
	public Maintenance(int maint_id, int car_id, String created_on, String pickup_date, String car_return_date, String status, String invoice_number, double invoice_amount){

		this.maint_id = maint_id;  	
		this.car_id = car_id;
		this.created_on = created_on;
		this.pickup_date = pickup_date;
		this.car_return_date = car_return_date;
		this.status = status;
		this.invoice_number = invoice_number;
		this.invoice_amount = invoice_amount;
		
	}

	public Maintenance(int maintid, int carid) {
		this.maint_id = maintid;
		this.car_id = carid;
	}

	public int getMaint_id() {
		return maint_id;
	}

	public void setMaint_id(int maint_id) {
		this.maint_id = maint_id;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getPickup_date() {
		return pickup_date;
	}

	public void setPickup_date(String pickup_date) {
		this.pickup_date = pickup_date;
	}

	public String getCar_return_date() {
		return car_return_date;
	}

	public void setCar_return_date(String car_return_date) {
		this.car_return_date = car_return_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public double getInvoice_amount() {
		return invoice_amount;
	}

	public void setInvoice_amount(double invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	
	
}
