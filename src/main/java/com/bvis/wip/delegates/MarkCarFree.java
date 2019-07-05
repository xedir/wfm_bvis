package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.IntegerValue;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;

public class MarkCarFree implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		IntegerValue typedVal = execution.getVariableTyped("carID");
		int carID = (int) typedVal.getValue();
		Car car = Car.createFromID(carID);
		car.setFree();
	}

}