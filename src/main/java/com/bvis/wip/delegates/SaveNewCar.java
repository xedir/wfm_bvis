package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.ConnectionManager;

public class SaveNewCar implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		ConnectionManager.createDefaults();

		String val1 = (String) execution.getVariable("car_name");
		int val2 = (Integer) execution.getVariable("price_per_day");
		
		
		ConnectionManager.putNewCar(val1, val2, "free");
		
		
	}

}
