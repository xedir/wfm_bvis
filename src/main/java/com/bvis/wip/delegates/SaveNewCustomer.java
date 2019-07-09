package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Date;
import java.util.logging.Logger;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.db.connection;
import com.bvis.wip.objects.Customer;

public class SaveNewCustomer implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingSaveCustomer");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String first_name = (String) execution.getVariable("first_name");
		String last_name = (String) execution.getVariable("last_name");
		String address = (String) execution.getVariable("address");
		Integer phone = (Integer) execution.getVariable("phone");
		String email = (String) execution.getVariable("email");
		Date birth_date = (Date) execution.getVariable("birth_date");
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern);
		String date_of_birth = simpleFormatter.format(birth_date);
		
		execution.setVariable("date_of_birth", date_of_birth);
		
		Customer customer = new Customer(first_name, last_name);
		customer.setAddress(address);
		customer.setBirth(date_of_birth);
		
		
		LOGGER.info("Customer Credentials: '" + customer.getName() + "'...");
		
		ConnectionManager.putCustomer(first_name, last_name, address, phone, email, date_of_birth);
		
	}
}
