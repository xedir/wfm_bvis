package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Date;
import java.util.logging.Logger;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.db.connection;
import com.bvis.wip.objects.BusinessCustomer;
import com.bvis.wip.objects.Customer;

public class SaveNewBusinessCustomer implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingSaveCustomer");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String company_name = (String) execution.getVariable("company_name");
		String address = (String) execution.getVariable("address");
		Integer Bphone = (Integer) execution.getVariable("Bphone");
		String Bemail = (String) execution.getVariable("Bemail");
		
	
		
		Date birth_date = (Date) execution.getVariable("birth_date");
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern);
		String date_of_birth = simpleFormatter.format(birth_date);
		
		BusinessCustomer customer = new BusinessCustomer();
		customer.setCompany_name(company_name);
		customer.setAddress(address);
		LOGGER.info("Customer Credentials: '" + customer.getCompany_name() + "'...");
		ConnectionManager.putBusinessCustomer(company_name, address, Bphone, Bemail);
		
		
		
		//create the contact if not exists even if business customer
		String fname = (String) execution.getVariable("first_name");
		String lname = (String) execution.getVariable("last_name");
		
		ResultSet rs = ConnectionManager.askForCustomer(fname, lname);
		if (rs.next() == false) {
			LOGGER.info("Contact of the business customer doesn't exist...");
			ConnectionManager.putCustomer(fname, lname, address, Bphone, Bemail, date_of_birth);	
		} else {
			LOGGER.info("Contact of the business customer alreadt exists!");
			execution.setVariable("date_of_birth", rs.getString(7));
			
		}	
	
	}
}
