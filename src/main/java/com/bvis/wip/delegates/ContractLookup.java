package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class ContractLookup implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		// Get contractID from form
		int contractID = (Integer) execution.getVariable("contractID");
		
		// Create contract object from db pull
		Contract contract = Contract.createFromID(contractID);
		
		// Generate Variables for preview form
		execution.setVariable("contractID", contract.getId());
		execution.setVariable("customer_name", contract.getCustomer().getName());
		execution.setVariable("car", contract.getCar().getName());
		execution.setVariable("carID", contract.getCar().getId());
			
	}
}
