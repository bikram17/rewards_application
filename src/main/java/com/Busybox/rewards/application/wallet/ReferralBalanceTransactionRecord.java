package com.Busybox.rewards.application.wallet;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transaction_record_reward_balance_logs")
public class ReferralBalanceTransactionRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String walletName;
	
	private String walletId;
	
	private String parentCustomerId;
	
	private String parentCustomerName;
	
	private String parentCustomerPhonenumber;
	
	private String parentWalletBalanceTransactionId;
	
	private String parentWaleltBalanceTransactionAmount;
	
	private Date parentWalletBalanceTransactionDate;
	
	private String childCustomerId;
	
	private String childCustomerName;
	
	private String childCustomerPhonenumber;
	
	private String childWalletBalanceTransactionId;
	
	private String childWaleltBalanceTransactionAmount;
	
	private Date childWalletBalanceTransactionDate;
	
	@Column(name="busybox_transaction_id")
	private String transactionId;
	
	private double TransactionAmount;
	
	private String walletBalance;
	
	private String openingBalance;
	
	private String closingBalance;
	
	private String storeId;
	
	//Parent Store when implemented
//	private boolean hasParentStoreId;
//	
//	private long parentStoreId;
	
	private String userId;
	
	private String packageOfComission;
	
	private String subSectionOfComission;
	
	@CreationTimestamp
	private Date dateOfTransaction;
	
	@CreatedBy
	private String createdBy;
	
	@CreationTimestamp
	private Date createdAt;
	
	private String updatedBy;
	
	@UpdateTimestamp
	private Date updatedAt;

	private String transactionNote= "OK By Default";
}
