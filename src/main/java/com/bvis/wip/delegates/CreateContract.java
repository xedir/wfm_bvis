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
		
		// Get data from select forms
		StringValue typedVal2 = execution.getVariableTyped("selected_car");
		String car_name = (String) typedVal2.getValue();

		StringValue typedVal3 = execution.getVariableTyped("selected_insurance");
		String insurance = (String) typedVal3.getValue();
		
		Date startForm = (Date) execution.getVariable("CONTRACT_START_DATE");
		Date endForm = (Date) execution.getVariable("CONTRACT_END_DATE");
		
		long duration = TimeUnit.MILLISECONDS.toDays(endForm.getTime() - startForm.getTime());
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern);
		String start = simpleFormatter.format(startForm);
		String end = simpleFormatter.format(endForm);
		
		Customer customer = new Customer(first_name, last_name);
		
		ResultSet rs = ConnectionManager.getFreeCar(car_name);
		rs.next();
		
		Car car = Car.createFromID(rs.getInt("ID"));
		
		 
		
		double price = duration * rs.getInt("PRICE_PER_DAY");
		if(insurance.equalsIgnoreCase("A")) {
			price = price * 1.2;
		} else if (insurance.equalsIgnoreCase("B")) {
			price = price * 1.1;
		}
		
		customer.setAddress(address);
		ConnectionManager.putCarAsRented(car.getId());
		
		Contract contract = new Contract(customer, car, start, end, duration, insurance , price);
		contract.save();
		
	}

}
