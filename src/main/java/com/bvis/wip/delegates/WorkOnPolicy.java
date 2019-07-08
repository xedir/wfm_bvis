package com.bvis.wip.delegates;

import java.security.Policy;
import java.sql.SQLException;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.client.RestTemplate;

import com.bvis.spring.api.PolicySending;
import com.bvis.wip.db.ConnectionManager;

public class WorkOnPolicy implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		
		PolicySending policy = (PolicySending) execution.getVariable("policeRecieved");

		
		boolean accepted;
		
		System.out.println("policy status is: " + policy.getStatus().equalsIgnoreCase("Offered"));
		
		if(policy.getStatus().equalsIgnoreCase("Offered")) {
			accepted = true;
		} else {
			accepted = false;
		}
		
		
		int contractid = (int) execution.getVariable("contractId");
		System.out.println("THE CONTROLLER PRINTER THIS CONTRACT ID: "+contractid);		
		
		if (accepted) {
			execution.setVariable("contractAccepted", true);
			execution.setVariable( "insuranceNumber", policy.getInsuranceNumber());
			int price = policy.getPrice();
			
			try {
				ConnectionManager.putContractAsOngoing(contractid);
				ConnectionManager.putContractInsurancePriceUpdate(price, contractid);
				ConnectionManager.putContractInsuranceNumberUpdate(policy.getInsuranceNumber(), contractid);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			execution.setVariable("contractAccepted", false);
			try {
				ConnectionManager.putContractAsRejected(contractid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		

		
		
	}

}
