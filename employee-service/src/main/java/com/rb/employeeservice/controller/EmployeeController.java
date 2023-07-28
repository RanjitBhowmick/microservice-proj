package com.rb.employeeservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
	private WebClient webClient;

	@Autowired
	private ModelMapper modelmapper;

	@GetMapping(value = "/getemployee/{id}")
	public EmployeeResponse getEmployeeDetails(@PathVariable("id") long id) {
		Employee emp = employeeRepo.findById(id).get();
		EmployeeResponse empResponse = modelmapper.map(emp, EmployeeResponse.class);
		/* AddressResponse addResponse = getDataUsingRestTemplate(id); */
		AddressResponse addResponse = getDataUsingWebClient(id);
		empResponse.setAddressResponse(addResponse);
		return empResponse;
	}

	private AddressResponse getDataUsingWebClient(long id) {
		return webClient.get().uri("/getaddress/" + id).retrieve().bodyToMono(AddressResponse.class).block();
	}

	private AddressResponse getDataUsingRestTemplate(long id) {
		return restTemplate.getForObject(addressServiceUrl + "/getaddress/{id}", AddressResponse.class, id);
	}

}
