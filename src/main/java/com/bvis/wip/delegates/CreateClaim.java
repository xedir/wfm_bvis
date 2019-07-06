package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.StringValue;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Claim;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class CreateClaim implements JavaDelegate {
	

	private final static Logger LOGGER = Logger.getLogger("CreateClaim");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String contractIdString = (String) execution.getVariable("selectedContract");
		int contractId = Integer.parseInt(contractIdString);
		Contract claimContract = Contract.createFromID(contractId);
		LOGGER.info("claimContract.id=" + claimContract.getId());
		
		String complaintDetails = (String) execution.getVariable("complaintDetails");
		LOGGER.info("complaintDetails=" + complaintDetails);
		
		String carLocation = (String) execution.getVariable("carLocation");
		LOGGER.info("carLocation=" + carLocation);
		
		Car car = claimContract.getCar();
		LOGGER.info("car.name=" + car.getName());
		
		Customer customer = claimContract.getCustomer();
		LOGGER.info("customer.name=" + customer.getName());
		
		int customerID = customer.getId();
		// create and set Claim to ongoing based on the previous sourced values
		Claim claim = new Claim(claimContract, customer, car, "some claim type", "to be clarified", "ongoing", complaintDetails, carLocation);
		int claimId = claim.save();
		LOGGER.info("claimId=" + claimId);
		
		
		execution.setVariable("claimId", claimId);
	}

}
