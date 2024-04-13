package com.Busybox.rewards.application.wallet;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="transaction_record_wallet_balance_logs")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WalletBalanceTransactionRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String walletName;
	
	private String walletId;
	
	private String customerId;
	
	private String customerName;
	
	private String customerPhoneNumber;
	
	@Column(name="busybox_transaction_id")
	private String transactionId;
	
	private String walletBalance;
	
	private String openingBalance;
	
	private String closingBalance;
	
	private double TransactionAmount;
	
	private String transactionType;
	
	private String storeId;
	
	//Parent Store when implemented
//	private boolean hasParentStoreId;
//	
//	private long parentStoreId;
	
	private String userId;
	
	private String packageOfCustomer;
	
	private String childrelated;
	
	private String childStoreId;
	
	private boolean childReceivedMoney;
	
	private String referralTableIdChild;
	
	private Date dateOfTransaction;
	
	private String createdBy;
	
	@CreationTimestamp
	private Date createdAt;
	
	private String updatedBy;
	@UpdateTimestamp
	private Date updatedAt;
	
	private String transactionNote= "OK By Default";
}
