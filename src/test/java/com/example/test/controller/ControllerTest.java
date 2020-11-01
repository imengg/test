package com.example.test.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.test.model.CalculateTriangleAreaRequest;
import com.example.test.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {Controller.class, TestService.class})
@WebMvcTest
public class ControllerTest {

	private Logger log = LoggerFactory.getLogger(ControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TestService service;
	
	@Test
	public void testPrint() throws Exception {
		Integer[] mockReturn = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		when(service.getNumbers()).thenReturn(mockReturn);
		
		MvcResult result = mockMvc.perform(get("/print"))
				.andExpect(status().isOk())
				.andReturn();
		
		verify(service).getNumbers();
		
		log.info(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testCalculateTriangleArea() throws Exception {
		Double mockReturn = 50.0;
		CalculateTriangleAreaRequest request = new CalculateTriangleAreaRequest();
		request.setBase(10);
		request.setAltitude(10);
		String jsonBody = new ObjectMapper().writeValueAsString(request);
		log.info("request: "+ jsonBody);
		when(service.calculateTriangleArea(10, 10)).thenReturn(mockReturn);
		
		MvcResult result = mockMvc.perform(post("/area")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonBody))
				.andExpect(status().isOk())
				.andReturn();
		
		verify(service).calculateTriangleArea(10, 10);
		
		log.info(result.getResponse().getContentAsString());
	}
}
