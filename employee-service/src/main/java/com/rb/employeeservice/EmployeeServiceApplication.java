package com.rb.employeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import com.rb.employeeservice.entities.Employee;
import com.rb.employeeservice.repository.EmployeeRepo;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class EmployeeServiceApplication {

	public static void main(String[] args) {

		/* SpringApplication.run(EmployeeServiceApplication.class, args); */

		ApplicationContext applicationContext = (ApplicationContext) SpringApplication
				.run(EmployeeServiceApplication.class, args);

		Employee emp = new Employee("Ranjit Bhowmick", "ranjitbhowmick@gmail.com", "O+");
		EmployeeRepo empRepo = applicationContext.getBean(EmployeeRepo.class);
		empRepo.save(emp);

	}

}
