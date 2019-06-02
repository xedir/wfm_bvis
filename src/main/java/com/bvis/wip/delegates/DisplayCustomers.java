package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Customer;

public class DisplayCustomers implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		List dl = new LinkedList();
		ResultSet rs;
		rs = ConnectionManager.getAllCustomerData();
		while (rs.next()) {
			String firstname = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			
			Customer customer = new Customer(firstname, lastName);
			dl.add(customer);
		}
		execution.setVariable("data", dl);
	}

}
