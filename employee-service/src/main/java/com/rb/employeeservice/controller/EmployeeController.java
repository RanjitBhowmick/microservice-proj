package com.rb.employeeservice.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.rb.employeeservice.entities.AddressResponse;
import com.rb.employeeservice.entities.Employee;
import com.rb.employeeservice.entities.EmployeeResponse;
import com.rb.employeeservice.feignclient.AddressClient;
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
	private AddressClient addressClient;

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private ModelMapper modelmapper;

	@GetMapping(value = "/getemployee/{id}")
	public EmployeeResponse getEmployeeDetails(@PathVariable("id") long id) {
		Employee emp = employeeRepo.findById(id).get();
		EmployeeResponse empResponse = modelmapper.map(emp, EmployeeResponse.class);

		/*//Below code will get the instance using discovery client.
		 * List<ServiceInstance> instances = discoveryClient.getInstances("ADDRESS_SERVICE");
		 * ServiceInstance serviceInstance = instances.get(0); 
		*/
		
		//Below code will get the instance using loadbalancer client.
		ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS_SERVICE");
		String addressUrl = serviceInstance.getUri().toString();

		/* AddressResponse addResponse = getDataUsingWebClient(id); */
		AddressResponse addResponse = getDataUsingRestTemplate(id, addressUrl);

		empResponse.setAddressResponse(addResponse);
		return empResponse;
	}

	private AddressResponse getDataUsingWebClient(long id) {
		return webClient.get().uri("/getaddress/" + id).retrieve().bodyToMono(AddressResponse.class).block();
	}

	private AddressResponse getDataUsingRestTemplate(long id, String addressServiceUrl1) {
		System.out.println("Calling url : " + addressServiceUrl1);
		return restTemplate.getForObject(addressServiceUrl1 + "/addressservice" + "/getaddress/{id}",
				AddressResponse.class, id);
	}

}
