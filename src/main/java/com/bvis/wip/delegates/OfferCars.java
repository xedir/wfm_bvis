package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class OfferCars implements JavaDelegate {


	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
	
		List<String> availableCars = new ArrayList();
		ResultSet freeCars = ConnectionManager.getAllFreeCars();
		while (freeCars.next()){
			availableCars.add(freeCars.getString("CAR_NAME"));
		}
		
		ObjectValue cars = Variables.objectValue(availableCars)
	                .serializationDataFormat(SerializationDataFormats.JSON)
	                .create();
		execution.setVariable("Available_Cars", cars);
	}
	
}
