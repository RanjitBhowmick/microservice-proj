package com.rb.employeeservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rb.employeeservice.entities.AddressResponse;

@FeignClient(name="addressFeignClient", url = "http://localhost:8081/",path="/addressservice")
/* @RibbonClient(name="addressRibbonClient") */
public interface AddressClient {

	@GetMapping("/getaddress/{id}")
	AddressResponse getAddressByEmployeeId(@PathVariable("id") long id);
	
}
