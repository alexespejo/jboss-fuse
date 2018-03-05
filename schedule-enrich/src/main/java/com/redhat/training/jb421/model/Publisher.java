package com.redhat.training.jb421.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
@Entity
@XmlRootElement(name = "publisher")
@XmlAccessorType(XmlAccessType.FIELD)
public class Publisher implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlAttribute
	private Integer id;
	@XmlElement
	private String email;
	@XmlElement
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addr_id")
	@XmlElement
	private Address address;
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
	@XmlTransient
	private Set<CatalogItem> items = new HashSet<CatalogItem>();
	
	
	public Publisher() {
		
	}


	public Publisher(String email, String name, Address address) {
		super();
		this.email = email;
		this.name = name;
		this.address = address;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public Set<CatalogItem> getItems() {
		return items;
	}


	public void setItems(Set<CatalogItem> items) {
		this.items = items;
	}
	
	
}
