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

public class MaintenanceReturn implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
			
		int maintid = (int) execution.getVariable("maintcar");
		String loc = (String) execution.getVariable("returnloc");
		
		ConnectionManager.putMaintReturn(maintid, loc);
		System.out.println("Car Return for Maint ID: "+maintid+" to location: "+loc);
		
	}
}