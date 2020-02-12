package com.company.employee.co.uk;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.company.employee.co.uk.controller.EmployeeService;
import com.company.employee.co.uk.model.Employee;


@SpringBootTest
class EmployeeApplicationTests {
	@Autowired
	MockMvc mockmvc;

	@MockBean
	EmployeeService service;

	@Test
	public void contextLoads() throws Exception {
		when(service.getAllEmployeeDetails()).thenReturn(Collections.emptyList());
		MvcResult mvcresult = mockmvc
				.perform(MockMvcRequestBuilders.get("/company/employee").accept(MediaType.APPLICATION_CBOR)).andReturn();
		verify(service).getAllEmployeeDetails();
	}

	@Test
	public void getAllCardsTest () {
		EasyRandom generator = new EasyRandom();
	    List<Employee> employee = generator.objects(Employee.class, 3)
	        .collect(Collectors.toList());
		when(service.getAllEmployeeDetails())
			.thenReturn(employee);
		assertEquals(1, service.getAllEmployeeDetails().size());
	}
}
