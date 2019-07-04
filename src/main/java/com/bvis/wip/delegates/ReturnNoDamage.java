package com.bvis.wip.delegates;

import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Contract;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.bpm.engine.variable.value.StringValue;

public class ReturnNoDamage implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
			
		int contractID = (Integer) execution.getVariable("contractID");
		int carID = (Integer) execution.getVariable("carID");
		String loc = (String) execution.getVariable("rentreturnloc");
		System.out.println("Returned Car ID: "+carID+" - Damage Found: No - Location: "+loc);
		ConnectionManager.putNoDamageReturn(carID, loc);
		
	}
}