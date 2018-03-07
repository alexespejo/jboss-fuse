package com.redhat.training.jb421.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String no;
	@NotNull
	private String expireMonth;
	@NotNull
	private String expireYear;
	@NotNull
	private String holderName;
	@Enumerated(EnumType.ORDINAL)
	private PaymentType paymentType;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getExpireMonth() {
		return expireMonth;
	}

	public void setExpireMonth(String expireMonth) {
		this.expireMonth = expireMonth;
	}

	public String getExpireYear() {
		return expireYear;
	}

	public void setExpireYear(String expireYear) {
		this.expireYear = expireYear;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expireMonth == null) ? 0 : expireMonth.hashCode());
		result = prime * result + ((expireYear == null) ? 0 : expireYear.hashCode());
		result = prime * result + ((holderName == null) ? 0 : holderName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (expireMonth == null) {
			if (other.expireMonth != null)
				return false;
		} else if (!expireMonth.equals(other.expireMonth))
			return false;
		if (expireYear == null) {
			if (other.expireYear != null)
				return false;
		} else if (!expireYear.equals(other.expireYear))
			return false;
		if (holderName == null) {
			if (other.holderName != null)
				return false;
		} else if (!holderName.equals(other.holderName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (no == null) {
			if (other.no != null)
				return false;
		} else if (!no.equals(other.no))
			return false;
		if (paymentType != other.paymentType)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", no=" + no + ", expireMonth=" + expireMonth + ", expireYear="
				+ expireYear + ", holderName=" + holderName + ", paymentType=" + paymentType + "]";
	}
	
	
	

}
