package com.bvis.wip.delegates;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.StringValue;
import org.springframework.beans.factory.annotation.Autowired;

import com.bvis.wip.db.ConnectionManager;
import com.bvis.wip.objects.Car;
import com.bvis.wip.objects.Contract;
import com.bvis.wip.objects.Customer;

public class CreateContract implements JavaDelegate {
	
	//autowire runtime service and necessary repositories
	@Autowired
	private RuntimeService runtimeService;

	private final static Logger LOGGER = Logger.getLogger("LoggingContractCreation");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String first_name = (String) execution.getVariable("first_name");
		String last_name = (String) execution.getVariable("last_name");
		String address = (String) execution.getVariable("address");
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
		
		String processInstanceId = execution.getProcessInstanceId();
		System.out.println(processInstanceId);
		
		// new for additional services
		boolean outOfCountry = (boolean) execution.getVariable("outOfCountryIns");
		boolean addDriver = (boolean) execution.getVariable("addDriver");
		String firstNameAD = (String) execution.getVariable("firstNameAD");
		String lastNameAD = (String) execution.getVariable("lastNameAD");
		String fullNameAD = firstNameAD + " " + lastNameAD;
		Date birthDateAddDriver = (Date) execution.getVariable("birthDateAddDriver");
		String birthDateAddDr = simpleFormatter.format(birthDateAddDriver);
		
		String date_of_birth = (String) execution.getVariable("date_of_birth");
		
		Customer customer = new Customer();
		customer.setFirst_name(first_name);
		customer.setLast_name(last_name);
		customer.setAddress(address);
		customer.setBirth(date_of_birth);
		String rentPickUpLoc = (String) execution.getVariable("rentpickuploc");
		
		ResultSet rs = ConnectionManager.getFreeCar(car_name, rentPickUpLoc);
		rs.next();
		
		Car car = Car.createFromID(rs.getInt("ID"));
		execution.setVariable("CarToBeRented", rs.getInt("ID"));
		
		boolean business = (boolean) execution.getVariable("business");
		
		Integer companyId = 0;			
		double price = duration * rs.getInt("PRICE_PER_DAY");

		
		if(business) {
			
			String company = (String) execution.getVariable("company_name");
			ResultSet rs1 = ConnectionManager.askForBusinessCustomer(company);
			rs1.next();
			companyId = rs1.getInt("ID");
				
			ResultSet agreement = ConnectionManager.askForBusinessAgreement(companyId);
			agreement.next();
			Integer count = ConnectionManager.askForActiveContracts(companyId);
			
			System.out.println(company + " has "+count +" active rental contract(s)");
			if (count <= 10) {
				double discount = agreement.getDouble("DISCOUNT_10")/100;
				System.out.println("---------------------");
				System.out.println("Total before Discount:   "+price);
				System.out.println("Discount "+agreement.getDouble("DISCOUNT_10")+" % :         "+price*discount);
				price = price * (1-discount);
				System.out.println("Total after Discount:     "+price);
				System.out.println("---------------------");

			} else if (count <= 30 ) {
				double discount = agreement.getDouble("DISCOUNT_10TO30")/100;
				System.out.println("---------------------");
				System.out.println("Total before Discount:   "+price);
				System.out.println("Discount "+agreement.getDouble("DISCOUNT_10TO30")+" % :         "+price*discount);
				price = price * (1-discount);
				System.out.println("Total after Discount:     "+price);
				System.out.println("---------------------");
				
			} else {
				double discount = agreement.getDouble("DISCOUNT_30UP")/100;
				System.out.println("---------------------");
				System.out.println("Total before Discount:   "+price);
				System.out.println("Discount "+agreement.getDouble("DISCOUNT_30UP")+" % :         "+price*discount);
				price = price * (1-discount);
				System.out.println("Total after Discount:     "+price);
				System.out.println("---------------------");
				price = price * (1-discount);
			}
		}
		
		
		customer.setAddress(address);
		//ConnectionManager.putCarAsRented(car.getId()); //needs to run only when contract is accepted 
		

		
		Contract contract = new Contract(customer, companyId, car, start, end, duration, insurance , price, outOfCountry, addDriver, fullNameAD, birthDateAddDr);
		/* 
		contract.setOutOfC(outOfCountry);
		contract.setAddDriver(addDriver);
		contract.setFirstNameAddDriver(firstNameAddDriver);
		contract.setLastNameAddDriver(lastNameAddDriver);
		*/
		
		Integer createdContractId = contract.save();
		execution.setVariable("contractId", createdContractId);
		execution.setVariable("mainCustomer", customer);
	
		execution.setVariable("contractToSend", contract);
	}

}
