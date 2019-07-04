package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Contract;

public class CheckRentalPeriod implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		// Get contractID from form
		int contractID = (Integer) execution.getVariable("contractID");
		// Create contract object from db pull
		Contract contract = Contract.createFromID(contractID);
		Date endDate = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(contract.getEnd()); 
		Date returnDate = (Date) execution.getVariable("CAR_RETURN_DATE");
		System.out.println(returnDate+" --- Contract End Date: "+contract.getEnd()+" --- Current Date: " + endDate );
		long diff = (returnDate.getTime() - endDate.getTime());
	    int daysDiff = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		ResultSet rs = ConnectionManager.getCarPirceByClaimId(contractID);
		rs.next();    
	    int pricePerDay = rs.getInt("PRICE_PER_DAY");
	    if (returnDate.compareTo(endDate) > 0) {
            System.out.println("Car returned after " + daysDiff + " days from contract end date");
            execution.setVariable("ContractExceeded", daysDiff);
            double extraCharge =  (double) (pricePerDay * daysDiff);
            execution.setVariable("ExtraCharge", extraCharge);
        } else {
        	execution.setVariable("ContractExceeded", daysDiff);
        }
	}
}
