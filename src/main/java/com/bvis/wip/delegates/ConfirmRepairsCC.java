package com.bvis.wip.delegates;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

import com.bvis.wip.objects.Claim;

public class ConfirmRepairsCC implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingRepairs");
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Double paidByBVIS = (Double) execution.getVariable("paidByBVIS");
		Double paidByCapitol = (Double) execution.getVariable("paidByCapitol");
		boolean repairAll = (boolean) execution.getVariable("decideRepair");
		boolean paid = false;
		IntegerValue typedVal = execution.getVariableTyped("ClaimID");
		int claimID = (int) typedVal.getValue();
		Claim claim = Claim.createFromID(claimID);
		// claim.getDamagedParts();
		
		if(repairAll) {
			paid = true;
		}
		
		int carID = claim.getCar().getId();
		execution.setVariable("carID", carID);
		execution.setVariable("isCovered", true);
		LOGGER.info("Paid by BVIS: '" + paidByBVIS + "'...");
		LOGGER.info("Paid by Capitol: '" + paidByCapitol + "'...");
	}

}
