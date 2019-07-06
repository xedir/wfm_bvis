package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bvis.wip.db.ConnectionManager;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class MaintenanceCheck implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	
		List<Integer> needMaintenance = new ArrayList<Integer>();
		int maint_counter = 0;
		ResultSet maintenanceCars = ConnectionManager.getNeedMaintenanceCars();
		while (maintenanceCars.next()){
			int maintid = ConnectionManager.putNewMaintenanceReturnID(maintenanceCars.getInt("ID"), "Waiting Appointment");
			needMaintenance.add(maintid);
			System.out.println("Maintenance ID: "+maintid+" CAR >>> "+maintenanceCars.getString("ID")+" "+maintenanceCars.getString("CAR_NAME")+" next_maintenance: "+maintenanceCars.getString("NEXT_MAINTENANCE"));
//			execution.setVariable("maintcarid", maintenanceCars.getInt("ID"));
//			execution.setVariable("maintcarname", maintenanceCars.getString("CAR_NAME"));
			maint_counter++;
		}
		execution.setVariable("maint_count", maint_counter);
		System.out.println("Number of cars that will need maintenance next week is: " + maint_counter);		
		
		ObjectValue maints = Variables.objectValue(needMaintenance)
	                .serializationDataFormat(SerializationDataFormats.JSON)
	                .create();
		execution.setVariable("maintenance_carsList", maints);
		
	}
}