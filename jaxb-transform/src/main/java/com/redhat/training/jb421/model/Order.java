package com.redhat.training.jb421.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {

	private static final long serialVersionUID = 5851038813219503043L;
	@XmlAttribute
	private String id;
	@XmlAttribute
	private String description;
	@XmlAttribute
	private double value;
	@XmlAttribute
	private double tax;
	private Customer customer;
	private Boolean delivered;
	private Date orderDate;

	public Customer getCustomer() {
		return customer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public Boolean getDelivered() {
		return delivered;
	}

	public void setDelivered(Boolean delivered) {
		this.delivered = delivered;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
