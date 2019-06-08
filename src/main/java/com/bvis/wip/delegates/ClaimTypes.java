package com.bvis.wip.delegates;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

import com.bvis.wip.db.ConnectionManager;

public class ClaimTypes implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> claimTypes = new ArrayList();
		claimTypes.add("X");
		claimTypes.add("Y");
		claimTypes.add("Z");
	
	
	ObjectValue claims = Variables.objectValue(claimTypes)
                .serializationDataFormat(SerializationDataFormats.JSON)
                .create();
	execution.setVariable("Available_claimTypes", claims);
	}

}
