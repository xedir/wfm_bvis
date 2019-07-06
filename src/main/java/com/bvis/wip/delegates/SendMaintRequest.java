package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.PolicySending;
import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class SendMaintRequest implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingSelect");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String process_id = execution.getProcessInstanceId();
		String activity_id = execution.getActivityInstanceId();
		int maintid = (int) execution.getVariable("maintcar");
		execution.setVariable("maintid", maintid);

		ResultSet maintCar = ConnectionManager.getCarInMaint(maintid);
		while (maintCar.next()){
		execution.setVariable("maintcarid", maintCar.getString("ID"));
		execution.setVariable("maintcarname", maintCar.getString("CAR_NAME"));

		String jobType = "MAINTENANCE";
		String problemDescription = "Regular Maintenance";
		
		// Create Car object
		Car MaintCar = new Car();
		MaintCar.setId(maintCar.getInt("ID"));
		MaintCar.setLicensePlate(maintCar.getInt("ID"));
		MaintCar.setLocation(maintCar.getString("LOCATION"));
		System.out.println(process_id+" - "+activity_id+" Send Maintenance Request for maintenance id: "+ maintid+" Car License Plate: "+MaintCar.getLicensePlate() +" - ID: "+MaintCar.getId()+" - Loc: "+MaintCar.getLocation());
			
		}
	}

}
