package com.redhat.training.jb421.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", crlf = "WINDOWS")
public class CatalogItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@DataField(pos = 1)
	private Integer id;
	@DataField(pos = 2)
	private String author;
	@DataField(pos = 3)
	private String category;
	@DataField(pos = 4)
	private String description;
	@DataField(pos = 5)
	private String imagePath;
	@DataField(pos = 6)
	private Boolean newItem;
	@DataField(pos = 7,decimalSeparator=".",precision=2)
	private BigDecimal price;
	@DataField(pos = 8)
	private String sku;
	@DataField(pos = 9)
	private String title;

	public CatalogItem() {
		super();
	}

	public CatalogItem(String author, String category, String description, String imagePath, Boolean newItem,
			BigDecimal price, String sku, String title) {
		super();
		this.author = author;
		this.category = category;
		this.description = description;
		this.imagePath = imagePath;
		this.newItem = newItem;
		this.price = price;
		this.sku = sku;
		this.title = title;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
