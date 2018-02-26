package com.redhat.training.jb421.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.redhat.training.jb421.model.Address;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private Boolean admin;
	private Address BillingAddres;
	private Address shippingAddress;
	private Set<Order> orders = new HashSet<Order>();

	public Customer() {
		super();
	}

	public Customer(Integer id, String firstname, String lastname, String username, String password, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.admin = false;
	}
	
	public Customer(Integer id, String firstname, String lastname, String username, String password, String email, Boolean admin) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.admin = admin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public Address getBillingAddres() {
		return BillingAddres;
	}

	public void setBillingAddres(Address billingAddres) {
		BillingAddres = billingAddres;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if(id == null){
			if(other.username != null)
				return false;
		}else if(!username.equals(other.username))
			return false;
		return true;
	}
}
