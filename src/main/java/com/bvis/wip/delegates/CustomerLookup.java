package com.bvis.wip.delegates;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bvis.wip.objects.Customer;
import com.bvis.wip.db.ConnectionManager;

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
		
		ConnectionManager.createDefaults();
		
		String val1 = (String) execution.getVariable("first_name");
		String val2 = (String) execution.getVariable("last_name");
		boolean business = (boolean) execution.getVariable("business");
		String val3 = (String) execution.getVariable("company_name");
		Customer customer = new Customer(val1, val2);
		String name = customer.getName();
		
		String pid = execution.getProcessInstanceId();
		
		//RuntimeService runtimeService = execution.getProcessEngineServices().getRuntimeService();
		//runtimeService.setVariable(pid, "testValue", "test");
		execution.setVariable("testValue", "test");
		
		
		LOGGER.info("Customer Credentials: '" + customer.getName() + "'...");
		
		String customer_id = execution.getProcessInstanceId();
		LOGGER.info("Prozeess ID ist: " + customer_id);
		
		if(!business) {
			ResultSet rs = ConnectionManager.askForCustomer(val1, val2);
			execution.setVariable("Business", false);
			if (rs.next() == false) {
				execution.setVariable("CustExists", false);
				LOGGER.info("No Customer found ...");	
			} else {
				execution.setVariable("date_of_birth", rs.getString(7));
				execution.setVariable("customer_id", rs.getString(1));
				
				execution.setVariable("CustExists", true);
				LOGGER.info("Customer found ...");
			}	
		} else {
			ResultSet rs = ConnectionManager.askForBusinessCustomer(val3);
			execution.setVariable("Business", true);
			if (rs.next() == false) {
				execution.setVariable("CustExists", false);
				LOGGER.info("No BusinessCustomer found ...");	
			} else {
				execution.setVariable("CustExists", true);
				LOGGER.info("BusinessCustomer found ...");
				ResultSet rs1 = ConnectionManager.askForCustomer(val1, val2);
				if(rs1.next() == false) {

					String date_of_birth = "1960-10-05";
					customer.setBirth("1960-10-05");
					execution.setVariable("date_of_birth", "1960-10-05");
					String email = val3 + "@gmail.com";
					String address = "musterstraße 17";
					int phone = 149372;
					
					
					LOGGER.info("Customer Credentials: '" + customer.getName() + "'...");
					
					ConnectionManager.putCustomer(val1, val2, address, phone, email, date_of_birth);	
				} else {
					LOGGER.info("Error");
					
				}
			}
		}		
	}
}
