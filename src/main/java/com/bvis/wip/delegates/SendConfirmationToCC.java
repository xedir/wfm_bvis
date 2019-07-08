package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.ServiceResponseCC;
import com.bvis.spring.api.InsuranceClaiming;
import com.bvis.spring.api.Quotation;

public class SendConfirmationToCC implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		InsuranceClaiming returnedClaim = (InsuranceClaiming) execution.getVariable("InsuranceCoverageSave");
		Quotation quotation = (Quotation) execution.getVariable("quotationSave");
		
		ServiceResponseCC confirmation = new ServiceResponseCC(returnedClaim, quotation);
		
		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		// Cars and Co IP:
		restTemplate.postForLocation("http://10.67.20.255:8080/serviceResponse", confirmation);
		
		System.out.println("Claim settled, insurance payment responsibilities send to Cars and Co.");
		
	}
}