package com.bvis.wip.delegates;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.PolicySending;
import com.bvis.spring.api.ServiceRequesting;
import com.bvis.wip.objects.RealClaim;
import com.bvis.wip.objects.RequestCC;

public class InformCC implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingClaimCreation");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println(7);
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(8);
		RealClaim claim = (RealClaim) execution.getVariable("claim");
		System.out.println(9);
		ServiceRequesting serviceRequesting = new ServiceRequesting(claim);
		System.out.println(10);
		restTemplate.postForLocation("http://10.67.20.255:8080/requestService", serviceRequesting);
		System.out.println(11);
		
		
//		// TODO Auto-generated method stub
//		// get the processVariable claimID that was set in "CreateClaim"
//		IntegerValue typedVal = execution.getVariableTyped("ClaimID");
//		int claimID = (int) typedVal.getValue();
//		// to proceed with the created Claim in order to inform CC
//		Claim claim = Claim.createFromID(claimID);
//		// create a request with the information of the claim
//		RequestCC request = new RequestCC(claim);
//		request.save();
//		/* here, the request must be sent with the following data*/
//		int sendClaimID = claimID;
//		int carID = request.getCarID();
//		String jobType = request.getJobType();
//		String damageDesc = request.getDescription();
//		String location = request.getLocation();
	}

}
