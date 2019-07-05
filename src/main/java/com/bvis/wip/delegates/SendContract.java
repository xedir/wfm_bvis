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

		// Contract object = Contract.createFromID(1);
		Contract object = (Contract) execution.getVariable("testAPIobject");
		
		PolicySending policySending = new PolicySending(object, execution.getProcessInstanceId());

		ObjectValue typedObject = Variables.objectValue(policySending).serializationDataFormat("application/json").create();

		RestTemplate restTemplate = new RestTemplate();

		//Send contract to Capitol. No response required.
		restTemplate.postForLocation("http://192.168.0.234:5555/", typedObject);

		LOGGER.info("Policy Send: " + policySending.toString());
		
	}

}
