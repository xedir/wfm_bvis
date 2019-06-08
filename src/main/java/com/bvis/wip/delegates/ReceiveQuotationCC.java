package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.objects.Claim;
import com.bvis.wip.objects.Quotation;

public class ReceiveQuotationCC implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// wait for the quotation
		execution.wait();
		// here we receive the quotation from CC - the arrays are just examplary and actually should be sent from CC
		Quotation quotation = new Quotation(1, "example damage", 11, "broken window", 100);
		// take its ClaimID to create the claim accordingly
		int claimID = quotation.getClaimID();
		Claim claim = Claim.createFromID(claimID);
		// and finally set the quotation of this claim with the transmitted quotation
		claim.setQuotation(quotation);
		// set the process variable ClaimID to continue with the current claim in the next task automatically
		execution.setVariable("ClaimID", claimID);
	}

}
