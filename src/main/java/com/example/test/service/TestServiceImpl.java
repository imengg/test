package com.example.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	
	private Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

	@Override
	public Integer[] getNumbers() {
		log.info("service method getNumbers() is called");
		return new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	}

	@Override
	public Double calculateTriangleArea(Integer base, Integer altitude) {
		Double result = 0.5 * base * altitude;
		log.info("area of triangle is: "+ result);
		return result;
	}

}
