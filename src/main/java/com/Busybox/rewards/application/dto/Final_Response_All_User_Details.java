package com.Busybox.rewards.application.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Final_Response_All_User_Details {
	
    @JsonProperty("name")
    private String customerName = null;

    @JsonProperty("mobile")
    private String customerMobileNumber = null;

    @JsonProperty("email")
    private String customerEmail = null;

    @JsonProperty("address")
    private String customerAddress = null;

    @JsonProperty("city")
    private String customerCity = null;

    @JsonProperty("state")
    private String customerState = null;

    @JsonProperty("pincode")
    private String customerPincode = null;

    @JsonProperty("status")
    private String customerStatus = null;

    @JsonProperty("login_status")
    private String loginStatus = null;

    @JsonProperty("created_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdOn = null;

    @JsonProperty("updated_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedOn = null;

    @JsonProperty("package_id")
    private Integer packageID = null;

    @JsonProperty("wallet")
    private List<WalletDto> customerCards = null;

    @JsonProperty("transactions")
    private List<TransactionDTO> customerTransactions = null;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerPincode() {
		return customerPincode;
	}

	public void setCustomerPincode(String customerPincode) {
		this.customerPincode = customerPincode;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getPackageID() {
		return packageID;
	}

	public void setPackageID(Integer packageID) {
		this.packageID = packageID;
	}

	public List<WalletDto> getCustomerCards() {
		return customerCards;
	}

	public void setCustomerCards(List<WalletDto> customerCards) {
		this.customerCards = customerCards;
	}

	public List<TransactionDTO> getCustomerTransactions() {
		return customerTransactions;
	}

	public void setCustomerTransactions(List<TransactionDTO> customerTransactions) {
		this.customerTransactions = customerTransactions;
	}

    // Getters and setters
    // ...
    
    
    // Inner classes with JSON-friendly annotations
}
    
   
    

