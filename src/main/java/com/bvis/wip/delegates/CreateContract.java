package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.StringValue;


import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class CreateContract implements JavaDelegate {

	private final static Logger LOGGER = Logger.getLogger("LoggingContractCreation");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String first_name = (String) execution.getVariable("first_name");
		String last_name = (String) execution.getVariable("last_name");
		String address = (String) execution.getVariable("address");
		
		LOGGER.info("Ich bin hier, vor dem Block");
		
		StringValue typedVal2 = execution.getVariableTyped("selected_car");
		String car_name = (String) typedVal2.getValue();
		
		//String car_name = OfferCars.getMap().get(typedVal2.getValue());
		
		//String car_name = (String) execution.getVariable("selected_car");
		LOGGER.info("Und jetzt hier, nach dem Auto: " + car_name);
		Date startForm = (Date) execution.getVariable("CONTRACT_START_DATE");
		Date endForm = (Date) execution.getVariable("CONTRACT_END_DATE");
		String insurance = (String) execution.getVariable("insurance");
		long duration = TimeUnit.MILLISECONDS.toDays(endForm.getTime() - startForm.getTime());
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern);
		String start = simpleFormatter.format(startForm);
		String end = simpleFormatter.format(endForm);
		
		
		
		Customer customer = new Customer(first_name, last_name);
		Car car = new Car(car_name);
		
		
		ResultSet rs; 
		rs = ConnectionManager.askForPrice(car_name);
		rs.next();
		
		double price = duration * rs.getInt("PRICE_PER_DAY");
		
		customer.setAddress(address);
		
		Contract contract = new Contract(customer, car, start, end, duration, insurance , price);
		contract.save();
		
		contract.saveContract();
		
	}

}
