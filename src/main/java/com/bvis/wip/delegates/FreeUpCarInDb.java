package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.ConnectionManager;

public class FreeUpCarInDb implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		int carId = (int) execution.getVariable("carID");
		
		ConnectionManager.putCarAsFree(carId);
		
	}

}
