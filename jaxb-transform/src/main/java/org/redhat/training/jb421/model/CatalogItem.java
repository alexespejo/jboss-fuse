package org.redhat.training.jb421.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CatalogItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String author;
	private String category;
	private String description;
	private String imagePath;
	private Boolean newItem;
	private BigDecimal price;
	private String sku;
	private String title;

	public CatalogItem() {
		super();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getNewItem() {
		return newItem;
	}

	public void setNewItem(Boolean newItem) {
		this.newItem = newItem;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
