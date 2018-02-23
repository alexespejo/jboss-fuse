package org.redhat.training.jb421.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal extPrice;
	private CatalogItem item;
	private Integer quantity;

	public OrderItem() {
		super();
	}

	public BigDecimal getExtPrice() {
		return extPrice;
	}

	public void setExtPrice(BigDecimal extPrice) {
		this.extPrice = extPrice;
	}

	public CatalogItem getItem() {
		return item;
	}

	public void setItem(CatalogItem item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
