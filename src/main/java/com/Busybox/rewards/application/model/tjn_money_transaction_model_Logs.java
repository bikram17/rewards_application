         package com.Busybox.rewards.application.model;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.Busybox.rewards.application.dto.TransactionDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="busybox_rewards_transaction_101")
public class tjn_money_transaction_model_Logs {
	@Id
	@Column(name="Transaction_Id", nullable = true)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transcationId    ;// auto- table
	
	@Column(name="Customer_Id", nullable = true)
	private int customerId;// auto-cus_table
	
	@Column(name="Customer_PhoneNumber", nullable = true)
	private long customerMobNo;// auto-cus_table
	
	@Column(name="Transaction_Amount", nullable = true)
	private double transactionAmt;// auto_tjn_cards
	
	@Column(name="Wallet_Id", nullable = true)
	private int transactionWalletWype;// auto Cashier//url
	
	@Column(name="Opening_Balance", nullable = true)
	private double cardOpeningBalance;// auto
	
	@Column(name="Transaction_Method", nullable = true)
	private String transactionMethod;// payment gateway
	
	@Column(name="Wallet_Balance", nullable = true)	
	private Double walletBalance;
	
	@Column(name="Wallet_Mode", nullable = true)
	private String mode;// debit/creditś
	
	//private String cardStatus;// active/inactive//boolean 1,0.
	
	
	@Column(name="Closing_Balance", nullable = true)
	private double cardClosingBalance;// auto
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Date_Of_Transaction", nullable = false)
	private Date credited_on;// server


	public tjn_money_transaction_model_Logs() {}


	public tjn_money_transaction_model_Logs(long transcationId, int customerId, long customerMobNo, double transactionAmt,
			int transactionWalletWype, double cardOpeningBalance, String transactionMethod, Double walletBalance,
			String mode, double cardClosingBalance, Date credited_on) {
		super();
		this.transcationId = transcationId;
		this.customerId = customerId;
		this.customerMobNo = customerMobNo;
		this.transactionAmt = transactionAmt;
		this.transactionWalletWype = transactionWalletWype;
		this.cardOpeningBalance = cardOpeningBalance;
		this.transactionMethod = transactionMethod;
		this.walletBalance = walletBalance;  
		this.mode = mode;
		this.cardClosingBalance = cardClosingBalance;
		this.credited_on = credited_on;
	}


	public long getTranscationId() {
		return transcationId;
	}


	public void setTranscationId(long transcationId) {
		this.transcationId = transcationId;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public long getCustomerMobNo() {
		return customerMobNo;
	}


	public void setCustomerMobNo(long customerMobNo) {
		this.customerMobNo = customerMobNo;
	}


	public double getTransactionAmt() {
		return transactionAmt;
	}


	public void setTransactionAmt(double transactionAmt) {
		this.transactionAmt = transactionAmt;
	}


	public int getTransactionWalletWype() {
		return transactionWalletWype;
	}


	public void setTransactionWalletWype(int transactionWalletWype) {
		this.transactionWalletWype = transactionWalletWype;
	}


	public double getCardOpeningBalance() {
		return cardOpeningBalance;
	}


	public void setCardOpeningBalance(double cardOpeningBalance) {
		this.cardOpeningBalance = cardOpeningBalance;
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


	public double getCardClosingBalance() {
		return cardClosingBalance;
	}


	public void setCardClosingBalance(double cardClosingBalance) {
		this.cardClosingBalance = cardClosingBalance;
	}


	public Date getCredited_on() {
		return credited_on;
	}


	public void setCredited_on(Date credited_on) {
		this.credited_on = credited_on;
	}


	@Override
	public String toString() {
		return "tjn_money_transaction_model [transcationId=" + transcationId + ", customerId=" + customerId
				+ ", customerMobNo=" + customerMobNo + ", transactionAmt=" + transactionAmt + ", transactionWalletWype="
				+ transactionWalletWype + ", cardOpeningBalance=" + cardOpeningBalance + ", transactionMethod="
				+ transactionMethod + ", walletBalance=" + walletBalance + ", mode=" + mode + ", cardClosingBalance="
				+ cardClosingBalance + ", credited_on=" + credited_on + "]";
	}
	
	
	

	public TransactionDTO toTransactionDTO() {
	    TransactionDTO transactionDTO = new TransactionDTO();
	    
	    transactionDTO.setTransactionId(this.transcationId);
	    transactionDTO.setCustomerMobileNumber(this.customerMobNo);
	    transactionDTO.setTransactionAmount(this.transactionAmt);
	    transactionDTO.setTransactionWalletType(this.transactionWalletWype);
	    transactionDTO.setOpeningBalance(this.cardOpeningBalance);
	    transactionDTO.setTransactionMethod(this.transactionMethod);
	    transactionDTO.setWalletBalance(this.walletBalance);
	    transactionDTO.setMode(this.mode);
	    transactionDTO.setClosingBalance(this.cardClosingBalance);
	    transactionDTO.setCreditedOn(this.credited_on);
	    
	    return transactionDTO;
	
			
			
			
			
			

}}
