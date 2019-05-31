package com.bvis.wip.delegates;

import java.sql.ResultSet;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.db.connection;
import com.bvis.wip.objects.BusinessCustomer;
import com.bvis.wip.objects.Customer;

public class SaveNewBusinessCustomer implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingSaveCustomer");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String company_name = (String) execution.getVariable("company_name");
		String address = (String) execution.getVariable("address");
		
		
		BusinessCustomer customer = new BusinessCustomer("company_name");
		customer.setAddress(address);
		
		
		LOGGER.info("Customer Credentials: '" + customer.getName() + "'...");
		ConnectionManager.putBusinessCustomer(company_name, address);
		
	}
}
