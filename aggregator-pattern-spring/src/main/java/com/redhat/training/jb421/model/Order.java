package com.redhat.training.jb421.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

@FixedLengthRecord(length=25)
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@DataField(pos = 1,length=6)
	private Integer id;
	@DataField(pos=2,length=8, pattern="MM-dd-yyyy")
	private Date orderDate = new Date();
	@DataField(pos=3, length=5,pattern="##.##")
	private BigDecimal discount;
	@DataField(pos=5,length=6)
	private Integer customerId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}