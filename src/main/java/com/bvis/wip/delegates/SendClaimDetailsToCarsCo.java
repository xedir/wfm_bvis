package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendClaimDetailsToCarsCo implements JavaDelegate {

	private final static Logger LOG = LoggerFactory.getLogger(SendClaimDetailsToCarsCo.class);
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LOG.info("inside SendClaimDetailsToCarsCo with execution={}", execution);
		
		String selectedContract = (String) execution.getVariable("selectedContract");
		LOG.info("selectedContract={}", selectedContract);
		
		String carLocation = (String) execution.getVariable("carLocation");
		LOG.info("carLocation={}", carLocation);
		
		String complaintDetails = (String) execution.getVariable("complaintDetails");
		LOG.info("complaintDetails={}", complaintDetails);
		
		
		// execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("AskCapitol").setVariable("request", request).correlate();
	}

}
