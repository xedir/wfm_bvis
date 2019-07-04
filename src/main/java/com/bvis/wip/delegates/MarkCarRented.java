package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;

public class MarkCarRented implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Integer carid = (Integer) execution.getVariable("CarToBeRented");
		ConnectionManager.putCarAsRented(carid);
		System.out.println("Car with id "+carid+" was Rented");
	}

}
