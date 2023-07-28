package com.rb.employeeservice.entities;

public class AddressResponse {

	private long id;

	private String apartment;

	private String lane;

	private String area;

	private String city;

	private String state;

	private String pincode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public AddressResponse(long id, String apartment, String lane, String area, String city, String state,
			String pincode) {
		super();
		this.id = id;
		this.apartment = apartment;
		this.lane = lane;
		this.area = area;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public AddressResponse() {
	}

	@Override
	public String toString() {
		return "AddressResponse [id=" + id + ", apartment=" + apartment + ", lane=" + lane + ", area=" + area
				+ ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}

}
