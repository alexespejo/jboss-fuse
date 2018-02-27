package com.redhat.training.jb421.model;

import java.io.Serializable;

public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String streetsAddress1;
	private String streetsAddress2;
	private String streetsAddress3;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStreetsAddress1() {
		return streetsAddress1;
	}
	public void setStreetsAddress1(String streetsAddress1) {
		this.streetsAddress1 = streetsAddress1;
	}
	public String getStreetsAddress2() {
		return streetsAddress2;
	}
	public void setStreetsAddress2(String streetsAddress2) {
		this.streetsAddress2 = streetsAddress2;
	}
	public String getStreetsAddress3() {
		return streetsAddress3;
	}
	public void setStreetsAddress3(String streetsAddress3) {
		this.streetsAddress3 = streetsAddress3;
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
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
