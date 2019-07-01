package com.bvis.wip.delegates;

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
		// execution.wait();
		// here we should receive the data 
		IntegerValue typedVal = execution.getVariableTyped("ClaimID");
		int claimID = (int) typedVal.getValue();
		String sentStatus = "will be picked up";
		// when the car is picked up we create the claim with the claimID and set the status of the car of this claim to "pickedup"
		if (sentStatus == "will be picked up") {
			Claim claim = Claim.createFromID(claimID);
			// change status ot the picked up car
			int carID = claim.getCar().getId();
			// to be discussed - maybe do it the other way like in maintenance
			ConnectionManager.putCarAsPickedUp(carID);
		}
		else {
			execution.isCanceled();
		}
	}

}
