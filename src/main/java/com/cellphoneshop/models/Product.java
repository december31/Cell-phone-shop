package com.cellphoneshop.models;

import javax.persistence.*;

@Entity
public class Product {

	@Id @GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(columnDefinition = "nvarchar(255)")
	private String name;

	@Column(columnDefinition = "nvarchar(255)")
	private String imageUrl;

	private Double price;

	@Column(columnDefinition = "nvarchar(255)")
	private String description;

	private Double ratting;

	public Product() {

	}

	public Product(Long id, String name, String imageUrl, Double price, String description, Double ratting) {
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
		this.ratting = ratting;
	}

	public Product(String name, String imageUrl, Double price, String description, Double ratting) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
		this.ratting = ratting;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public Double getRatting() {
		return ratting;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRatting(Double ratting) {
		this.ratting = ratting;
	}
}
