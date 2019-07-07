package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.ServiceResponseCC;
import com.bvis.spring.api.InsuranceClaiming;
import com.bvis.spring.api.Quotation;

public class SendConfirmationToCCMaint implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		InsuranceClaiming returnedClaim = (InsuranceClaiming) execution.getVariable("ReturnClaimCapitol");
		Quotation quotation = (Quotation) execution.getVariable("quotationSave");
		
		ServiceResponseCC confirmation = new ServiceResponseCC(returnedClaim, quotation);
		
		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForLocation("http://127.0.0.1:5555/", confirmation);
		
		System.out.println("blabla");
		
	}

}
