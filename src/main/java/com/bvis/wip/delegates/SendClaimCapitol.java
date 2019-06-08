package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;
import org.camunda.bpm.engine.variable.value.StringValue;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Claim;

public class SendClaimCapitol implements JavaDelegate {

	private final static Logger LOGGER = Logger.getLogger("LoggingClaimCreation");
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// get the processVariable claimID that was set in "CreateClaim"
		IntegerValue typedVal = execution.getVariableTyped("ClaimID");
		int claimID = (int) typedVal.getValue();
		
		// to proceed with the created Claim in order to send it to Capitol
		Claim claim = Claim.createFromID(claimID);
		LOGGER.info("Claim ID: '" + claim.getClaimID() + "'...");

		// here the Rest API is required to send it to CC
	}

}
