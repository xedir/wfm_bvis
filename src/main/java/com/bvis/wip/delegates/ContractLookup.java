package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.BusinessCustomer;
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
		// Very important - Make sure the contract you are returning is in status 'ongoing'
		Contract contract = Contract.createFromID(contractID);
		int companyID = contract.getCompanyid();
		
		
		// Generate Variables for preview form
		execution.setVariable("contractID", contract.getId());
		execution.setVariable("customer_name", contract.getCustomer().getName());
		execution.setVariable("car", contract.getCar().getName());
		execution.setVariable("carID", contract.getCar().getId());
		execution.setVariable("contractPrice", contract.getPrice());
		System.out.println("The company id of the contract: "+companyID);
		if (companyID == 0) {
			execution.setVariable("CompanyName", "--");
		} else {
			BusinessCustomer company = BusinessCustomer.createFromID(companyID);
			execution.setVariable("CompanyName", company.getCompany_name());
		}
			
	}
}
