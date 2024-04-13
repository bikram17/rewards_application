package com.Busybox.rewards.application.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoneyTransactionResponse {
		
	    @JsonProperty("status")
	    private HttpStatus status;
	    @JsonProperty("message")
	    private String message;
	    @JsonProperty("transactionAmount")
	    private Double transactionAmount;
	    @JsonProperty("openingBalance")
	    private Double openingBalance;
	    @JsonProperty("closingBalance")
	    private Double closingBalance;
	    @JsonProperty("currentBalance")
	    private Double currentBalance;

	    
	    
	    public MoneyTransactionResponse() {
	        // Default constructor
	    }

	    public MoneyTransactionResponse(HttpStatus status, String message, Double transactionAmount, Double openingBalance, Double closingBalance, Double currentBalance) {
	        this.status = status;
	        this.message = message;
	        this.transactionAmount = transactionAmount;
	        this.openingBalance = openingBalance;
	        this.closingBalance = closingBalance;
	        this.currentBalance = currentBalance;
	    }

		public HttpStatus getStatus() {
			return status;
		}

		public void setStatus(HttpStatus status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Double getTransactionAmount() {
			return transactionAmount;
		}

		public void setTransactionAmount(Double transactionAmount) {
			this.transactionAmount = transactionAmount;
		}

		public Double getOpeningBalance() {
			return openingBalance;
		}

		public void setOpeningBalance(Double openingBalance) {
			this.openingBalance = openingBalance;
		}

		public Double getClosingBalance() {
			return closingBalance;
		}

		public void setClosingBalance(Double closingBalance) {
			this.closingBalance = closingBalance;
		}

		public Double getCurrentBalance() {
			return currentBalance;
		}

		public void setCurrentBalance(Double currentBalance) {
			this.currentBalance = currentBalance;
		}

		
	  
    
    
    
}
