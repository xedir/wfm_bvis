package com.bvis.wip.delegates;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

import com.bvis.wip.objects.Claim;
import com.bvis.wip.objects.RequestCC;

public class InformCC implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingClaimCreation");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// get the processVariable claimID that was set in "CreateClaim"
		IntegerValue typedVal = execution.getVariableTyped("ClaimID");
		int claimID = (int) typedVal.getValue();
		// to proceed with the created Claim in order to inform CC
		Claim claim = Claim.createFromID(claimID);
		// create a request with the information of the claim
		RequestCC request = new RequestCC(claim);
		LOGGER.info("Car ID of the Request: '" + request.getCarID() + "'...");
		
		/* here, the request must be sent */
	}

}
