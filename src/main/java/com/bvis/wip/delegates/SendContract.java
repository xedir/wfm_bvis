package com.bvis.wip.delegates;

import java.text.SimpleDateFormat;
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
		Contract contract = (Contract) execution.getVariable("contractToSend");
		boolean outOfCountry = (boolean) execution.getVariable("outOfCountryIns");
		
		boolean addDriver = (boolean) execution.getVariable("addDriver");
		
		// create new restTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		// create policySending object based on contract (object)
		if(addDriver) {
			// Additional Driver Information
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern);
			String firstNameAD = (String) execution.getVariable("firstNameAD");
			String lastNameAD = (String) execution.getVariable("lastNameAD");
			String fullNameAD = firstNameAD + " " + lastNameAD;
			Date birthDateAddDriver = (Date) execution.getVariable("birthDateAddDriver");
			String birthDateAddDr = simpleFormatter.format(birthDateAddDriver);
			Customer addDriverObject = new Customer(firstNameAD, lastNameAD);
			addDriverObject.setBirth(birthDateAddDr);
			PolicySending policySending = new PolicySending(contract, execution.getProcessInstanceId(), outOfCountry, addDriverObject);
			restTemplate.postForLocation("http://127.0.0.1:5555/", policySending);
		} else {
			Customer mainCustomer = (Customer) execution.getVariable("mainCustomer");
			PolicySending policySending = new PolicySending(contract, execution.getProcessInstanceId(), outOfCountry);
			restTemplate.postForLocation("http://127.0.0.1:5555/", policySending);
		}
		
		
		// serialize policySending object
		// not needed anymore, format of policySending is already conform
		// ObjectValue typedObject = Variables.objectValue(policySending).serializationDataFormat("application/json").create();



		//Send contract to Capitol. No response required.
		//restTemplate.postForLocation("http://127.0.0.1:5555/", policySending);
		
		// LOGGER.info("Policy Send: " + policySending.toString());
	}

}
