package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.connection;
import com.bvis.wip.objects.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


public class CustomerLookup implements JavaDelegate {
	
	
	private final static Logger LOGGER = Logger.getLogger("LoggingLookup");
	

	@Override
	public void execute(DelegateExecution execution) throws SQLException {
		// TODO Auto-generated method stub
		
		String val1 = (String) execution.getVariable("first_name");
		String val2 = (String) execution.getVariable("last_name");
		
		Customer customer = new Customer(val1, val2);
		
		String name = customer.getName();
		
		LOGGER.info("Customer Credentials: '" + customer.getName() + "'...");
		
		
		ResultSet rs = connection.askQuery(val1, val2);
		
		if (rs.next() == false) {
			execution.setVariable("CustExists", false);
			LOGGER.info("No Customer found ...");	
		} else {
			execution.setVariable("CustExists", true);
			LOGGER.info("Customer found ...");
		}
	}
}
