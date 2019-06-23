package com.bvis.wip.delegates;

import static org.asynchttpclient.Dsl.asyncHttpClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
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
		
		Runnable runnable = () -> {
			LOGGER.info("inside thread");
			try {
				Thread.sleep(1000);
				AsyncHttpClient asyncHttpClient = asyncHttpClient();
				Future<Response> whenResponse = asyncHttpClient.preparePost("http://localhost:8080/engine-rest/message").setBody("	 {\n" + 
					"       \"messageName\" : \"receive_pickup_message\",\n" + 
					"       \"correlationKeys\" : {\n" + 
					"         \"claimId\" : {\"value\" : \" " + claimId + "\", \"type\": \"Integer\"}\n" + 
					"       }\n" + 
					"     }").addHeader("Content-Type", "application/json").
					execute();
				Response response;
			
				response = whenResponse.get();
				String responseBody = response.getResponseBody();
				LOGGER.info(responseBody);
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		};
		
		Thread thread = new Thread(runnable);
		thread.start();
		LOGGER.info("thread started");
		// execution.getProcessEngineServices().getRuntimeService().createMessageCorrelation("AskCapitol").setVariable("request", request).correlate();
	}

}
