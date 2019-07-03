package com.bvis.wip.delegates;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

import com.bvis.wip.objects.Claim;
import com.bvis.wip.objects.RequestCC;

public class InformCC extends AbstractClient {
	
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
		request.save();
		/* here, the request must be sent with the following data*/
		int sendClaimID = claimID;
		int carID = request.getCarID();
		String jobType = request.getJobType();
		String damageDesc = request.getDescription();
		String location = request.getLocation();
	}

	@Override
	String restEndpoint() {
		// TODO Auto-generated method stub
		return null;
	}


}
