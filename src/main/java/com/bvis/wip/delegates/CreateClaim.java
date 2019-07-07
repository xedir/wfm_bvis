package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.StringValue;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;
import com.bvis.wip.objects.RealClaim;

public class CreateClaim implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingClaimCreation");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		int contractId = (int) execution.getVariable("contractID");
		System.out.println(1);
		String problemDescription = (String) execution.getVariable("damageDesc");
		System.out.println(2);
		Contract contract = Contract.createFromIDAll(contractId);
		System.out.println(3);
		String processId = execution.getProcessInstanceId();
		execution.setVariable("car", contract.getCar());
		System.out.println(4);
		RealClaim claim = new RealClaim(contract, problemDescription, processId);
		System.out.println(5);
		execution.setVariable("claim", claim);
		System.out.println(6);
		ConnectionManager.putCarAsInRepair(contract.getCar().getId());

		
		
		
//		// get the name and address from text fields
//		String first_name = (String) execution.getVariable("first_name");
//		String last_name = (String) execution.getVariable("last_name");
//		String address = (String) execution.getVariable("address");
//		String damageDesc= (String) execution.getVariable("damageDesc");
//		String carLocation = (String) execution.getVariable("carLocation");
//		// get claimType from selectfield
//		StringValue typedValue = execution.getVariableTyped("selected_claimType");
//		String claimType = (String) typedValue.getValue();
//		// get the customer based on previous values
//		ResultSet rsCustomer = ConnectionManager.getCertainCustomer(first_name, last_name , address);
//		rsCustomer.next();
//		Customer customer = Customer.createFromID(rsCustomer.getInt("ID"));
//		
//		// get the contract for that customer
//		ResultSet rsContract = ConnectionManager.askForPrivateContractByCustomerID(customer.getId());
//		rsContract.next();
//		Contract contract = Contract.createFromID(rsContract.getInt("ID"));
//		// get the car that is considered in the claim
//		Car car = contract.getCar();
//		int customerID = customer.getId();
//		// create and set Claim to ongoing based on the previous sourced values
//		Claim claim = new Claim(contract, customer, car, claimType, "to be clarified", "ongoing", damageDesc, carLocation);
//		claim.save();
//		// set claimID for later tasks
//		ResultSet rsClaim = ConnectionManager.getClaimIDdatabase(customerID, claimType);
//		rsClaim.next();
//		int claimID = rsClaim.getInt("ID");
//		execution.setVariable("ClaimID", claimID);
//		LOGGER.info("ClaimID: " + claimID);
//		
//		// Contract ID noch einfügen, weil sonst nur eine miete möglich ist
	}

}
