package com.rb.addressservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rb.addressservice.entities.Address;
import com.rb.addressservice.repository.AddressRepo;

@SpringBootApplication
public class AddressServiceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = (ApplicationContext) SpringApplication
				.run(AddressServiceApplication.class, args);

		Address add = new Address("Snehdeep Apartment flat 212", "Balaji Chowk", "Near goal maidan", "Ulhasnagar",
				"Thane", "421001", 1l);
		AddressRepo addRepo = applicationContext.getBean(AddressRepo.class);
		addRepo.save(add);

	}
}
