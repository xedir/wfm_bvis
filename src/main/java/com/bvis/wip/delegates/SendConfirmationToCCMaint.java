package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.ServiceResponseCC;
import com.bvis.spring.api.ServiceResponseCCMaint;
import com.bvis.spring.api.InsuranceClaiming;
import com.bvis.spring.api.Quotation;

public class SendConfirmationToCCMaint implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String pid = execution.getProcessInstanceId();
		Quotation quotation = (Quotation) execution.getVariable("quotationSave");
		ServiceResponseCCMaint confirmation = new ServiceResponseCCMaint(pid, quotation);
		
		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForLocation("http://localhost:5555", confirmation);
		restTemplate.postForLocation("http://10.67.20.255:8080/serviceResponse", confirmation);

	}

}
