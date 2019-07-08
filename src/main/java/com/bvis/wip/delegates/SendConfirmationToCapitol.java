package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.ConfirmationSending;
import com.bvis.spring.api.PolicySending;

public class SendConfirmationToCapitol implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		String insuranceNumber = (String) execution.getVariable("insuranceNumber");
		
		PolicySending policy = (PolicySending) execution.getVariable("policeRecieved");
		
		restTemplate.postForLocation("http://10.65.7.165:8080/contract", policy);
		
	}

}
