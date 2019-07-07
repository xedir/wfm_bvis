package com.bvis.spring.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class InvoiceController {
	
	@PostMapping("/Invoice")
	public void pickupMessage() {
		//runtimeService.setVariable(pickup.getProcessId(), "pickupDate", pickup.getPickupDate());
		
		
	}
}
