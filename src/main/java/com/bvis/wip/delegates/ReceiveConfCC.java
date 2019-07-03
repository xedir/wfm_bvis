package com.bvis.wip.delegates;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Claim;

// contains the "Mark car as picked-up, as it can be done immediately with receiving the confirmation
public class ReceiveConfCC implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingClaimCreation");
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// following values will be filled with the sent data via RestAPI. The shown data are just examplary
		// here we should receive the data 
		IntegerValue typedVal = execution.getVariableTyped("ClaimID");
		int claimID = (int) typedVal.getValue();
		String pickUpDate = "2019-05-08";
		
		
		// when the car is picked up we create the claim with the sent claimID
			Claim claim = Claim.createFromID(claimID);
			// change status ot the picked up car of this Claim
			int carID = claim.getCar().getId();
			ConnectionManager.putCarAsPickedUp(carID);
			// and set the date of pickUp in the Claim
			// ConnectionManager.putPickUpDateClaims(claimID, pickUpDate);
	}
}
