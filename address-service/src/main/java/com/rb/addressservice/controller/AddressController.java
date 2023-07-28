package com.rb.addressservice.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rb.addressservice.entities.Address;
import com.rb.addressservice.entities.AddressResponse;
import com.rb.addressservice.repository.AddressRepo;

@RequestMapping(value = "/addressservice")
@RestController
public class AddressController {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private ModelMapper modelmapper;

	@GetMapping(value = "/getaddress/{empid}")
	public AddressResponse getEmployeeDetails(@PathVariable("empid") long empid) {
		Address add = addressRepo.findByEmpid(empid);
		AddressResponse addResponse = modelmapper.map(add, AddressResponse.class);
		return addResponse;
	}

}
