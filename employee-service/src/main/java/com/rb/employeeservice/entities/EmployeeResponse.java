package com.rb.employeeservice.entities;

public class EmployeeResponse {

	private long id;

	private String name;

	private String email;

	private String bloodGroup;

	private AddressResponse addressResponse;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public AddressResponse getAddressResponse() {
		return addressResponse;
	}

	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse = addressResponse;
	}

	public EmployeeResponse(long id, String name, String email, String bloodGroup, AddressResponse addressResponse) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.bloodGroup = bloodGroup;
		this.addressResponse = addressResponse;
	}

	public EmployeeResponse() {
	}

}
