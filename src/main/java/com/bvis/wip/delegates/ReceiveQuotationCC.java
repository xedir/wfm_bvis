package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

import com.bvis.wip.objects.Claim;
import com.bvis.wip.objects.Quotation;

public class ReceiveQuotationCC implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// wait for the quotation
		// execution.wait(); how to implement that?
		// here we receive the quotation from CC - the claimID and other values are just examplary and actually should be sent from CC
		// we will get a Quotation that contains the claimID 
		IntegerValue typedVal = execution.getVariableTyped("ClaimID");
		int claimID = (int) typedVal.getValue();
		// claimID, damageDesc, totalCosts, damagedParts, partCosts - will damagedParts and partCosts be arrays?
		Quotation quotation = new Quotation(claimID, "example damage", 100, "broken window", 11);
		quotation.save();
		// take its ClaimID to create the claim accordingly
		claimID = quotation.getClaimID();
		Claim claim = Claim.createFromID(claimID);
		// and finally set the quotation of this claim with the transmitted quotation
		claim.setQuotation(quotation);
		// set the process variable ClaimID to continue with the current claim in the next task automatically
		execution.setVariable("ClaimID", claimID);
		
		// ! Add a User task after that so we can show the information of the Quotation
	}

}
