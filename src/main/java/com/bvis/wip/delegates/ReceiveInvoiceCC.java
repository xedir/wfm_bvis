package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

import com.bvis.wip.objects.Invoice;

public class ReceiveInvoiceCC implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// here we receive the invoice together with the claim ID
		// following data just examplary - should be provided with the invoice
		IntegerValue typedVal = execution.getVariableTyped("ClaimID");  // we get that
		int claimID = (int) typedVal.getValue();						// from the invoice
		String jobType = "Claim";
		String Services = "engine";
		double partCosts = 100;
		double totalCosts = 300;
		
		
		Invoice invoice = new Invoice(jobType, Services, partCosts, totalCosts);		
		invoice.setClaimID(claimID);			
		
		invoice.save();
	}

}
