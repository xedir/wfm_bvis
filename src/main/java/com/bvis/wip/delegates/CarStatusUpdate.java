package com.bvis.wip.delegates;



import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Claim;

public class CarStatusUpdate implements JavaDelegate {
	
	private final static Logger LOGGER = Logger.getLogger("CarStatusUpdate");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info("execution=" + execution);
		
		
		int selectedClaimId = (int) execution.getVariable("claimId");
		LOGGER.info("selectedClaimId=" + selectedClaimId);

		
		Claim claim = Claim.createFromID(selectedClaimId);
		LOGGER.info("claim.location=" + claim.getLocation());
		
		Car car = claim.getCar();
		
		int carId = car.getId();
		LOGGER.info("car.id=" + carId);

		String carStatus = car.getStatus();
		LOGGER.info("car.status=" + carStatus);
			
		ConnectionManager.updateCarStatusToPickedup(carId);
		
		
	}

}