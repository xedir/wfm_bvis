package com.bvis.wip.delegates;

import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Contract;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.ObjectValue;

public class ExtraCharge implements JavaDelegate {
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		//add the extra charge in the contract
		int contractID = (Integer) execution.getVariable("contractID");
		double extraCharge = (double) execution.getVariable("ExtraCharge");
		int extradays = (Integer) execution.getVariable("ContractExceeded");  
		Date returnDate = (Date) execution.getVariable("CAR_RETURN_DATE");
		
		//ADD a new column "Extra Charges" in contract table and write the extraCharge there
		ConnectionManager.putExtraCharge(contractID, extraCharge, extradays, returnDate);	
		
	}
}
	
	