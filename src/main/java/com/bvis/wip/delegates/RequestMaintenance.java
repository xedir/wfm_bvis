package com.bvis.wip.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bvis.wip.db.ConnectionManager;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class RequestMaintenance implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	
		int maintid = (int) execution.getVariable("maintcar");
		execution.setVariable("maintid", maintid);

		ResultSet maintCar = ConnectionManager.getCarInMaint(maintid);
		while (maintCar.next()){
		execution.setVariable("maintcarid", maintCar.getString("ID"));
		execution.setVariable("maintcarname", maintCar.getString("CAR_NAME"));

		System.out.println("Send Maintenance Request for maintenance id: "+ maintid+" Car id: "+maintCar.getString("ID"));
		}
		
	}
}