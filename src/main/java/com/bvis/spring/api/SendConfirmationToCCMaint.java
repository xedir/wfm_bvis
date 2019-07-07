package com.bvis.spring.api;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.spring.api.Quotation;

public class SendConfirmationToCCMaint implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String processId = execution.getProcessInstanceId();
		Quotation maintQuote = (Quotation) execution.getVariable("quotationSave");
		JobDetails[] qDetails = maintQuote.getJobDetails();
		System.out.println("Process Id: "+processId);
		int l = qDetails.length;
		for (int i= 0; i<l; i++) {
			System.out.println(qDetails[i].getServiceId()+" - "+qDetails[i].getParticularCost());
		}
		
		System.out.println("blabla");
		
	}
	
}
