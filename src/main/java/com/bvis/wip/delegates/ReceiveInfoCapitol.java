package com.bvis.wip.delegates;

import java.sql.ResultSet;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Claim;

public class ReceiveInfoCapitol implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// here we get the info about insurance coverage of the damages
		// following data just examplary until we have the Rest API
		IntegerValue typedVal = execution.getVariableTyped("ClaimID");
		int claimID = (int) typedVal.getValue();
		String isCovered = "Partially"; // other 2 options: "Covered" or "not Covered"
		String partsNotCovered = "window, engine";
		double costsNotCovered = 300;
		double totalCosts = 800;
		// extend claim data table with costs that are covered by capitol
		// total costs in quotation already 
		Claim claim = Claim.createFromID(claimID);
		String name = claim.getCustomer().getName();
		
		if(isCovered == "Covered") {
			execution.setVariable("isCovered", true);
			} 
		else {
			execution.setVariable("isCovered", false);
			}
		
		execution.setVariable("name", name);
		execution.setVariable("isCovered", isCovered);
		execution.setVariable("partsNotCovered", partsNotCovered);
		execution.setVariable("costsNotCovered", costsNotCovered);
		execution.setVariable("totalCosts", totalCosts);
		
		// just show total costs and then show costs to be paid by BVIS and by Capitol
	}
}
