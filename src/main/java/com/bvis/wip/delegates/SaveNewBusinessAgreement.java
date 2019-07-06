package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.ConnectionManager;

public class SaveNewBusinessAgreement implements JavaDelegate {
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String company_name = (String) execution.getVariable("company_name");
		Double discount10 = (Double) execution.getVariable("discount10");
		Double discount1030 = (Double) execution.getVariable("discount1030");
		Double discount30 = (Double) execution.getVariable("discount30");
		
		ConnectionManager.putNewBusAgreement(company_name, discount10, discount1030,discount30);
				
	}
}
