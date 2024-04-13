package com.Busybox.rewards.application.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "invoice_id")
    private String invoiceId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "tax")
    private double tax;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @CreationTimestamp
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

   

    
	public Invoice() {
		super();
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	@JsonIgnore
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/*
	 * public boolean isIfSuccessful() { return ifSuccessful; }
	 * 
	 * public void setIfSuccessful(boolean ifSuccessful) { this.ifSuccessful =
	 * ifSuccessful; }
	 */

	public Invoice(Long id, String invoiceId, double amount, double tax, String itemId, String itemName,
			Date creationDate/* , boolean ifSuccessful */) {
		super();
		this.id = id;
		this.invoiceId = invoiceId;
		this.amount = amount;
		this.tax = tax;
		this.itemId = itemId;
		this.itemName = itemName;
		this.creationDate = creationDate;
		
	}

    // Constructors, getters, and setters
    // ...

    // Add your own methods, constructors, getters, and setters here

    // You can also customize this class further based on your application's requirements
    
    
	
    
}
