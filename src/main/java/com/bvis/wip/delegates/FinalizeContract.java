package com.bvis.wip.delegates;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.objects.Contract;

public class FinalizeContract implements JavaDelegate {

	private final static Logger LOGGER = Logger.getLogger("LoggingQueries");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		
		int contractID = (Integer) execution.getVariable("contractID");
		Contract contract = Contract.createFromID(contractID);
		LOGGER.info("Contract number " + contract.getId() + " is found, attempting to finalize");
		Contract.finalizeContract(contract);
		
	}

}
