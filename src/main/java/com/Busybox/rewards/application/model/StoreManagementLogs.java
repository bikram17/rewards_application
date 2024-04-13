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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//public class StoreManagementLogs {




@Entity
@Table(name = "store_transaction_logs")
@Data 
@NoArgsConstructor 
@Getter
@Setter
@ToString
@AllArgsConstructor
// customer_mobno, 
public class StoreManagementLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "store_id")
    private Long store_id;
    
    @Column(name = "customer_mobno")
    private String customer_mobno;

    @Column(name = "store_location")
    private String store_location;

    @Column(name = "wallet_balance_before_transaction")
    private double wallet_balance_before_transaction;

    @Column(name = "wallet_mode")
    private String walletMode="DEBIT";
    
    @Column(name = "comission_calculation")
    private String comission="1%";

    @Column(name = "transaction_method")
    private String transactionMethod="COMISSION";

    @Column(name = "opening_balance")
    private double openingBalance;

    @Column(name = "transaction_amount")
    private double transactionAmount;

    @Column(name = "closing_balance")
    private double closingBalance;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_transaction")
    private Date dateOfTransaction;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public String getCustomer_mobno() {
		return customer_mobno;
	}

	public void setCustomer_mobno(String customer_mobno) {
		this.customer_mobno = customer_mobno;
	}

	public String getStore_location() {
		return store_location;
	}

	public void setStore_location(String store_location) {
		this.store_location = store_location;
	}

	public double getWallet_balance_before_transaction() {
		return wallet_balance_before_transaction;
	}

	public void setWallet_balance_before_transaction(double wallet_balance_before_transaction) {
		this.wallet_balance_before_transaction = wallet_balance_before_transaction;
	}

	public String getWalletMode() {
		return walletMode;
	}

	public void setWalletMode(String walletMode) {
		this.walletMode = walletMode;
	}

	public String getComission() {
		return comission;
	}

	public void setComission(String comission) {
		this.comission = comission;
	}

	public String getTransactionMethod() {
		return transactionMethod;
	}

	public void setTransactionMethod(String transactionMethod) {
		this.transactionMethod = transactionMethod;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

    

    // Constructors, getters, setters, and other fields as needed
}