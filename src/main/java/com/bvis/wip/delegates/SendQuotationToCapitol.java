package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.InsuranceClaiming;
import com.bvis.spring.api.Quotation;
import com.bvis.wip.objects.RealClaim;

public class SendQuotationToCapitol implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		Quotation quotation = (Quotation) execution.getVariable("quotationSave");
		RealClaim claim = (RealClaim) execution.getVariable("claim");
		
		InsuranceClaiming insuranceClaiming = new InsuranceClaiming(claim, quotation);
		
		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(quotation.getProcessId());	
		
		restTemplate.postForLocation("http://127.0.0.1:5555/", insuranceClaiming);
		
		
	}

}
