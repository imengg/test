package com.example.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.model.CalculateTriangleAreaRequest;
import com.example.test.service.TestService;

@RestController
public class Controller {
	
	private Logger log = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private TestService service;
	
	@GetMapping("/print")
	public Integer[] print() {
		Integer[] result = service.getNumbers();
		log.info("response is: "+ result);
		return result;
	}
	
	@PostMapping("/area")
	public Double calculateTriangleArea(@RequestBody CalculateTriangleAreaRequest request) {
		Integer base = request.getBase();
		Integer altitude = request.getAltitude();
		Double result = service.calculateTriangleArea(base, altitude);
		log.info("response is: "+ result);
		return result;
	}

}
