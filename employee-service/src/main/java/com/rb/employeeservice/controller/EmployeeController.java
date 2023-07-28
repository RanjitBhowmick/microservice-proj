package com.rb.employeeservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rb.employeeservice.entities.AddressResponse;
import com.rb.employeeservice.entities.Employee;
import com.rb.employeeservice.entities.EmployeeResponse;
import com.rb.employeeservice.repository.EmployeeRepo;

@RequestMapping(value = "/employeeservice")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Value("${address.service.url}")
	private String addressServiceUrl;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ModelMapper modelmapper;

	@GetMapping(value = "/getemployee/{id}")
	public EmployeeResponse getEmployeeDetails(@PathVariable("id") long id) {
		Employee emp = employeeRepo.findById(id).get();
		EmployeeResponse empResponse = modelmapper.map(emp, EmployeeResponse.class);
		AddressResponse addResponse = restTemplate.getForObject(addressServiceUrl, AddressResponse.class, id);
		empResponse.setAddressResponse(addResponse);
		return empResponse;
	}

}
