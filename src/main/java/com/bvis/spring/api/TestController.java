package com.bvis.spring.api;

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

import com.bvis.wip.objects.Car;

@RestController
@RequestMapping("/{processInstanceId}")
public class TestController {

	// autowire runtime service and necessary repositories
	@Autowired
	private RuntimeService runtimeService;


	/**
	 * Controller for accepting contract. Handles get request.
	 * 
	 * @param processInstanceId, String. Unique identifier.
	 * @param boolean,             accepted. Acceptance status.
	 */
	@GetMapping("/contract-accept")
	public void ContractAccept(@PathVariable("processInstanceId") String processInstanceId, boolean accepted) {

		runtimeService.setVariable(processInstanceId, "response", true);
		
		if (accepted) {
			runtimeService.setVariable(processInstanceId, "contractAccepted", true);
		} else {
			runtimeService.setVariable(processInstanceId, "contractAccepted", false);
		}
	}


	
	
	@GetMapping
	public boolean get(@RequestParam String name) {
		if (name == "true") {
			return true;
		} else
			return false;
	}
	
	@PostMapping
	public boolean post(@RequestBody ContractResponse status) {
		
		return status.isAccepted();
	}
}
