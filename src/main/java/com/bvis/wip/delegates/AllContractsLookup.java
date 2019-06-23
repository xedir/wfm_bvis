package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class AllContractsLookup implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		List<Contract> allContracts = Contract.findAllContracts();
		List<String> allContractsStrings = allContracts.stream().map(c -> "" + c.getId() + "-").collect(Collectors.toList()); 
		
		
		Map<String, String> allContractsMap = new HashMap<String, String>();
		
		for (Contract contract : allContracts) {
			String contractIdString = Integer.toString(contract.getId());
			allContractsMap.put(contractIdString, contractIdString + "-" + contract.getCustomer().getName() + "-" + contract.getCar().getName() );
		}
		
		
		ObjectValue contractsObjectValue = Variables.objectValue(allContractsMap)
                .serializationDataFormat(SerializationDataFormats.JSON)
                .create();
		execution.setVariable("availableContracts", contractsObjectValue);
	}
}
