package com.bvis.wip.delegates;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendClaimDetailsToCarsCo implements JavaDelegate {

	// private final static Logger LOG = LoggerFactory.getLogger(SendClaimDetailsToCarsCo.class);

	private final static Logger LOGGER = Logger.getLogger("SendClaimDetailsToCarsCo");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String selectedContract = (String) execution.getVariable("selectedContract");
		LOGGER.info("selectedContract=" + selectedContract);
		
		String carLocation = (String) execution.getVariable("carLocation");
		LOGGER.info("carLocation=" + carLocation);
		
		String complaintDetails = (String) execution.getVariable("complaintDetails");
		LOGGER.info("complaintDetails=" + complaintDetails);
		
		int claimId = (int) execution.getVariable("claimId");
		LOGGER.info("claimId=" + claimId);
		
		
		// execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("AskCapitol").setVariable("request", request).correlate();
	}

}
