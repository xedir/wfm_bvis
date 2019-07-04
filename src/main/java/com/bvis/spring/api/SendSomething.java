package com.bvis.spring.api;

import java.sql.SQLException;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Contract;



public class SendSomething {

	public void sendSomething() throws SQLException {
	
	Contract object = Contract.createFromID(1);
			// (Contract) execution.getVariable("testAPIobject");

	ObjectValue typedObject =  Variables.objectValue(object).serializationDataFormat("application/json").create();
	
	RestTemplate restTemplate = new RestTemplate();		
	
	//Send contract to Capitol. No response required.
	restTemplate.postForLocation("http://192.168.0.234:5555/", typedObject);
	
	}
	
}
