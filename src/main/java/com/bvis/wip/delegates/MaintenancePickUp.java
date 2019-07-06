package com.bvis.wip.delegates;

import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.bvis.wip.db.ConnectionManager;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class MaintenancePickUp implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		int maintid = (int) execution.getVariable("maintcar");
		execution.setVariable("maintid", maintid);

		Date PDate = (Date) execution.getVariable("maintpickupdate");
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern);
		String pickupDate = simpleFormatter.format(PDate);
		ConnectionManager.putMaintPickeUp(maintid, pickupDate);
		System.out.println(pickupDate+"Set date for : "+maintid);
		
	}
}