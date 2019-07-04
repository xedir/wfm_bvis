package com.bvis.wip.delegates;

import java.util.Date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.SendSomething;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class SendContract implements JavaDelegate {

	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		SendSomething sender = new SendSomething();

		sender.sendSomething();
	}

}
