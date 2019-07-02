package com.bvis.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bvis.wip.objects.Car;

@RestController
@RequestMapping("/car")
public class TestController {

	@GetMapping
	public Car get(@RequestParam String name) {
		Car car = new Car(name);
		return car;
	}
	
	@PostMapping
	public String post(@RequestBody Car car) {
		return car.getName();
	}
	
	
}
