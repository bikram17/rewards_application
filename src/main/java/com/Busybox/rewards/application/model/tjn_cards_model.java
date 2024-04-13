package com.Busybox.rewards.application.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tjn_cards_master")
public class tjn_cards_model {
	
	// Generic counter of the card table. For now one for each customer 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Card_Id_Count", nullable  = true)
	private long cardIdCount;	

    @Column(name = "Customer_Id", nullable = true)
    private long customerId;   
    
    
    @Column(name="Customer_Name", nullable= true)
    private String customerName;    
    
    @Column(name="Card_Number", nullable = true)
    private long cardNumber;
    
    @Column(name="Card_Id_Status", nullable = true)
    private String cardIdStatus;
    
    @Column(name="Card_Expiry")
    private Date cardEndsOn;
    
    @Column(name="Created_By", nullable = false)
    private String createdBy;
    
    
    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="Created_At", nullable = false)
    private Date createdAt;
    
    @Column(name="Test_Field_1", nullable = false)
    private String testField1;

	public long getCardIdCount() {
		return cardIdCount;
	}

	public void setCardIdCount(long cardIdCount) {
		this.cardIdCount = cardIdCount;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardIdStatus() {
		return cardIdStatus;
	}

	public void setCardIdStatus(String cardIdStatus) {
		this.cardIdStatus = cardIdStatus;
	}

	public Date getCardEndsOn() {
		return cardEndsOn;
	}

	public void setCardEndsOn(Date cardEndsOn) {
		this.cardEndsOn = cardEndsOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getTestField1() {
		return testField1;
	}

	public void setTestField1(String testField1) {
		this.testField1 = testField1;
	}

	public tjn_cards_model(long cardIdCount, long customerId, String customerName, long cardNumber,
			String cardIdStatus, Date cardEndsOn, String createdBy, Date createdAt, String testField1) {
		super();
		this.cardIdCount = cardIdCount;
		this.customerId = customerId;
		this.customerName = customerName;
		this.cardNumber = cardNumber;
		this.cardIdStatus = cardIdStatus;
		this.cardEndsOn = cardEndsOn;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.testField1 = testField1;
	}

	public tjn_cards_model(long customerId, String customerName, long cardNumber, String cardIdStatus,
			Date cardEndsOn, String createdBy, Date createdAt, String testField1) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.cardNumber = cardNumber;
		this.cardIdStatus = cardIdStatus;
		this.cardEndsOn = cardEndsOn;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.testField1 = testField1;
	}

	public tjn_cards_model(long customerId, String customerName, long cardNumber, String cardIdStatus,
			Date cardEndsOn, String createdBy, Date createdAt) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.cardNumber = cardNumber;
		this.cardIdStatus = cardIdStatus;
		this.cardEndsOn = cardEndsOn;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
	}
    
	public tjn_cards_model() {}
    
}
