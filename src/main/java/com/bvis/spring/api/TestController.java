package com.bvis.spring.api;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bvis.wip.objects.Car;
import com.bvis.wip.db.ConnectionManager;
import com.bvis.spring.api.Quotation;

@RestController
@RequestMapping("/{processInstanceId}")
public class TestController {

	// autowire runtime service and necessary repositories
	
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private ProcessEngine pengine;
	
	/**
	 * Controller for accepting contract. Handles get request.
	 * 
	 * @param processInstanceId, String. Unique identifier.
	 * @param boolean,             accepted. Acceptance status.
	 */
	
	@PostMapping("/contract-accept")
	public void ContractAccept(@PathVariable("processInstanceId") String processInstanceId, boolean accepted) {

		int contractid = (int) runtimeService.getVariable(processInstanceId, "contractId");
		System.out.println("THE CONTROLLER PRINTER THIS CONTRACT ID: "+contractid);
		
		if (accepted) {
			runtimeService.setVariable(processInstanceId, "contractAccepted", true);
			try {
				ConnectionManager.putContractAsOngoing(contractid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			runtimeService.setVariable(processInstanceId, "contractAccepted", false);
			try {
				ConnectionManager.putContractAsRejected(contractid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		runtimeService.setVariable(processInstanceId, "response", true);
	}
	
	@PostMapping("/contract")
	public void Contract(@RequestBody PolicySending policy) {
		
		String pid = policy.getProcessId();
		
		pengine.getRuntimeService().setVariable(pid, "policeRecieved", policy);
		
	//	runtimeService.setVariable(pid, "response", true);
		pengine.getRuntimeService().createMessageCorrelation("Message_contract").processInstanceId(pid).correlate();
		
	}
	
	
	@PostMapping("/pickupMessage")
	public void pickupMessage(@RequestBody PickupMessage pickup) {
		//runtimeService.setVariable(pickup.getProcessId(), "pickupDate", pickup.getPickupDate());
		System.out.println("pickup Message received for process id "+pickup.getProcessId());
		runtimeService.setVariable(pickup.getProcessId(), "pickedUp", true);
	}
	

	@PostMapping("/quotation")
	public void quotationReciever(@RequestBody Quotation quotation) {	
		String pid = quotation.getProcessId();
		System.out.println(pid);
		pengine.getRuntimeService().setVariable(pid, "quotationSave", quotation);
		pengine.getRuntimeService().setVariable(pid, "recieveQuotation", true);
//		runtimeService.setVariable(quotation.getProcessId(), "quotationSave", quotation);
//		runtimeService.setVariable(quotation.getProcessId(), "recieveQuotation", true);
	}
	
//	@PostMapping("/insuranceCoverage")
//	public void insuranceCoverage(@PathVariable("processInstanceId") String processInstanceId) {
//		runtimeService.setVariable(processInstanceId, "answerCapitol", true);
//	}
	
	@PostMapping("/insuranceCoverage")
	public void insuranceCoverage(@RequestBody InsuranceClaiming insuranceClaim) {
		
		runtimeService.setVariable(insuranceClaim.getProcessId(), "ReturnClaimCapitol", insuranceClaim);
		
		runtimeService.setVariable(insuranceClaim.getProcessId(), "answerCapitol", true);
	}
	
	

//	@PostMapping("/pickupMessage")
//	public void pickupMessage(@PathVariable("processInstanceId") String processInstanceId, int claimId) {
//
//		runtimeService.setVariable(processInstanceId, "pickedup", true);
//		runtimeService.setVariable(processInstanceId, "uselessId", claimId);
//		System.out.println("pickup Message received for process id "+processInstanceId);
//				
//	}
	
//	@PostMapping("/quotation")
//	public void quotation(@PathVariable("processInstanceId") String processInstanceId, int claimId) {
//
//		runtimeService.setVariable(processInstanceId, "pickedup", true);
//		runtimeService.setVariable(processInstanceId, "uselessId", claimId);
//		System.out.println("Quotation received for process id "+processInstanceId);
//				
//	}
//	
	
	@GetMapping
	public boolean get(@RequestParam String name) {
		if (name == "true") {
			return true;
		} else
			return false;
	}
	
//	@PostMapping
//	public boolean post(@RequestBody ContractResponse status) {
//		
//		return status.isAccepted();
//	}
}
