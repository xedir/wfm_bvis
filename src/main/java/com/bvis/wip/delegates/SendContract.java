package com.bvis.wip.delegates;

import java.util.Date;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.PolicySending;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class SendContract implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingSelect");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		// Get contract object from previous process (create contract)
		Contract object = (Contract) execution.getVariable("testAPIobject");
		
		// create policySending object based on contract (object)
		PolicySending policySending = new PolicySending(object, execution.getProcessInstanceId());
		
		// serialize policySending object
		// not needed anymore, format of policySending is already conform
		// ObjectValue typedObject = Variables.objectValue(policySending).serializationDataFormat("application/json").create();

		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();

		//Send contract to Capitol. No response required.
		restTemplate.postForLocation("http://192.168.0.123:5555/", policySending);

		
		// LOGGER.info("Policy Send: " + policySending.toString());
		
	}

}
