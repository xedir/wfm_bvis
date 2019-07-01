package com.bvis.wip.delegates;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

public class ConfirmRepairsCC implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingRepairs");
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String paidByBVIS = (String) execution.getVariable("paidByBVIS");
		String paidByCapitol = (String) execution.getVariable("paidByCapitol");
		
		LOGGER.info("Paid by BVIS: '" + paidByBVIS + "'...");
		LOGGER.info("Paid by Capitol: '" + paidByCapitol + "'...");
	}

}
