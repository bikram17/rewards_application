package com.Busybox.rewards.application.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_listing")
public class ProductListing {

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "product_discount")
    private double productDiscount;

    @Column(name = "availability")
    private boolean availability;

    // ... other getters and setters ...

    @JsonIgnore
    public Long getId() {
        return id;
    }

    // Constructors, getters, and setters
    // ...

    
    
    
    // You can also use Jackson annotations to specify JSON property names
	/*
	 * @JsonProperty("id") public Long getId() { return id; }
	 */
    public ProductListing() {
		super();
	}

	public ProductListing(Long id, String productName, double productPrice, String productDescription,
			String productId, String productCategory, double productDiscount, boolean availability) {
		super();
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productId = productId;
		this.productCategory = productCategory;
		this.productDiscount = productDiscount;
		this.availability = availability;
	}

	
	@JsonProperty("product_name")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("product_price")
    public double getProductPrice() {
        return productPrice;
    }

    @JsonProperty("product_description")
    public String getProductDescription() {
        return productDescription;
    }

    //@JsonIgnore
    @JsonProperty("product_id")
    public String getProductId() {
        return productId;
    }

    
    @JsonProperty("product_category")
    public String getProductCategory() {
        return productCategory;
    }

    @JsonProperty("product_discount")
    public double getProductDiscount() {
        return productDiscount;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public void setProductDiscount(double productDiscount) {
		this.productDiscount = productDiscount;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@JsonProperty("availability")
    public boolean isAvailability() {
        return availability;
    }
}
