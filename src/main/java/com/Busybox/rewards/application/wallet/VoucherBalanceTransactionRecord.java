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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="transaction_record_voucher_transaction_logs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherBalanceTransactionRecord {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private long id;
	
	private String walletName;
	
	private String walletId;
	
	private String voucherId;
	
	private String voucherName;
	
	private double voucherAmount;
	
	private long voucherQuantity;
	
	private double totalAmount;
	
	private boolean isStoreDiscount;
	
	private boolean isFlatDiscount;
	
	private double discountedAmount;
	
	private boolean isDeaultPackageDiscount;
	
	private long defaultDiscountPackageId;
	
	private double finalAmount;
	
	private boolean isStorePurchase;
	
	private boolean isUserPurchase;
	
	private String storeId;
	
//	//Parent Store when implemented
//	private boolean hasParentStoreId;
//	
//	private long parentStoreId;
	
	private String transactionNote= "OK By Default";
	
	private String storeName;
	
	private String storeWalletLeft;
	
	private String userId;
	
	private String transactionId;
	
	private Date dateOfTransaction;
	
	private int voucherLeft;
	
	private int voucherActive;
	
	@CreatedBy
	private String createdBy;
	
	@CreationTimestamp
	private Date createdAt;
	
	private String updatedBy;
	
	@UpdateTimestamp
	private Date updatedAt;
}
