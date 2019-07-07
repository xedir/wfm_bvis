package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.ConfirmationSending;

public class SendConfirmationToCapitol implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		String insuranceNumber = (String) execution.getVariable("insuranceNumber");
		
		
		
		ConfirmationSending confirmationSending = new ConfirmationSending(insuranceNumber);
		
		restTemplate.postForLocation("http://127.0.0.1:5555/", confirmationSending);
		
	}

}
