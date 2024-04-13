package com.Busybox.rewards.application.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

	@JsonProperty("transaction_id")
    private Long transactionId = null;

    @JsonProperty("customer_mobile")
    private Long customerMobileNumber = null;

    @JsonProperty("transaction_amount")
    private Double transactionAmount = null;

    @JsonProperty("transaction_wallet_type")
    private Integer transactionWalletType = null;

    @JsonProperty("opening_balance")
    private Double openingBalance = null;

    @JsonProperty("transaction_method")
    private String transactionMethod = "UPI/CASH/NEFT?";

    @JsonProperty("wallet_balance")
    private Double walletBalance = null;

    @JsonProperty("mode")
    private String mode = null;

    @JsonProperty("closing_balance")
    private Double closingBalance = null;

    @JsonProperty("credited_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creditedOn = null;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(Long customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Integer getTransactionWalletType() {
		return transactionWalletType;
	}

	public void setTransactionWalletType(Integer transactionWalletType) {
		this.transactionWalletType = transactionWalletType;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public String getTransactionMethod() {
		return transactionMethod;
	}

	public void setTransactionMethod(String transactionMethod) {
		this.transactionMethod = transactionMethod;
	}

	public Double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(Double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public Date getCreditedOn() {
		return creditedOn;
	}

	public void setCreditedOn(Date creditedOn) {
		this.creditedOn = creditedOn;
	}

    // Getters and setters
    // ...
	
	
    
}
