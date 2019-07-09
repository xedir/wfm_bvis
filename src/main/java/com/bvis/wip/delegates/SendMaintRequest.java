package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.Date;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.MaintenanceObject;
import com.bvis.spring.api.PolicySending;
import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class SendMaintRequest implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		int maintid = (int) execution.getVariable("maintid");
		execution.setVariable("maintid", maintid);
		String process_id = execution.getProcessInstanceId();
		String jobType = "MAINTENANCE";
		String problemDescription = "Regular Maintenance";
		
		ResultSet maintCar = ConnectionManager.getCarInMaint(maintid);
		while (maintCar.next()){
		execution.setVariable("maintcarid", maintCar.getString("ID"));
		execution.setVariable("maintcarname", maintCar.getString("CAR_NAME"));

		// Create Car object
		Car MaintCar = new Car();
		MaintCar.setId(maintCar.getInt("ID"));
		MaintCar.setLicensePlate(maintCar.getInt("ID"));
		MaintCar.setLocation(maintCar.getString("LOCATION"));
		System.out.println(process_id+" Send Maintenance Request for maintenance id: "+ maintid+" Car License Plate: "+MaintCar.getLicensePlate() +" - ID: "+MaintCar.getId()+" - Loc: "+MaintCar.getLocation());

		MaintenanceObject maintObject = new MaintenanceObject(MaintCar,jobType, problemDescription, maintid, process_id);
		
		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Capitol IP:
		restTemplate.postForLocation("http://10.67.20.255:8080/requestService", maintObject);
		
		
		
		}
	}
}
