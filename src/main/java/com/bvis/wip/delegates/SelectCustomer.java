package com.bvis.wip.delegates;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.connection;
import com.bvis.wip.objects.Customer;

public class SelectCustomer implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingSelect");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String val1 = (String) execution.getVariable("first_name");
		String val2 = (String) execution.getVariable("last_name");
		
		String name = val1 + " " + val2;
		
		ResultSet rs = connection.askQuery(val1, val2);
		rs.next();

		Customer test = new Customer(rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"));	
		test.setAddress(rs.getString("ADDRESS"));

		LOGGER.info("Customer Selected:" + rs.getString("FIRST_NAME"));
		
	}

}
