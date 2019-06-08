package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.bvis.wip.objects.Claim;

public class ReceiveConfCC implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// following values will be filled with the sent data via RestAPI. The filled in data are just examplary
		execution.wait();
		// here we receive the data
		int claimID = 1;
		String status = "will be picked up";
		// when the car is picked up we create the claim with the claimID and set the status of the car of this claim to "pickedup"
		if (status == "will be picked up") {
			Claim claim = Claim.createFromID(claimID);
			// I added the method setStatus to class Car
			claim.getCar().setStatus("picked up");
		}
		else {
			execution.isCanceled();
		}
		

	}

}
