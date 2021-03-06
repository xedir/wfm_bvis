package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.BusinessCustomer;
import com.bvis.wip.objects.Customer;

public class SelectCustomer implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("LoggingSelect");

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String val1 = (String) execution.getVariable("first_name");
		String val2 = (String) execution.getVariable("last_name");
		String val3 = (String) execution.getVariable("company_name");
		boolean business = (boolean) execution.getVariable("business");
		String date_of_birth = (String) execution.getVariable("birth_date");
		
		ResultSet rs;
		if(business) {
			rs = ConnectionManager.askForBusinessCustomer(val3);
			rs.next();
			BusinessCustomer test = new BusinessCustomer();	
			test.setCompany_name(rs.getString("COMPANY_NAME"));
			LOGGER.info("company Selected:" + rs.getString("company_name")); //throwing error outside
		} else {
			rs = ConnectionManager.askForCustomer(val1, val2);
			rs.next();
			Customer test = new Customer(rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"));	
			test.setAddress(rs.getString("ADDRESS"));
			test.setBirth("date_of_birth");
			LOGGER.info("Customer Selected:" + rs.getString("FIRST_NAME")); //throwing error outside
		}
	}
}
