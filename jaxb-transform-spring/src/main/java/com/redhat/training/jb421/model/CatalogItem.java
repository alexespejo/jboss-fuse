package com.redhat.training.jb421.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CatalogItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CatalogItem other = (CatalogItem) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}else if(!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
