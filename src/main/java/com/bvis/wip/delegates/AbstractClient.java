package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties
public abstract class AbstractClient implements JavaDelegate{

	@Autowired
	RestTemplate rest;

	String carsCoEndpoint;
	String capitolEndpoint;

	//return "http://" + restProxyPort + "/payment/charges";
	abstract String restEndpoint();
	
	// String request = (String) ctx.getVariable("dummyId")
	//SampleObjectOtherGroup response = rest.postForObject( //
    //	restEndpoint(), //
    //	request, //
    //	SampleObjectOtherGroup.class);
	@Override
	public abstract void execute(DelegateExecution ctx) throws Exception;
}
