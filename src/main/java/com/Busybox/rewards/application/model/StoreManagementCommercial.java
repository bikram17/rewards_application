package com.Busybox.rewards.application.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

@Entity
@Table(name = "store_wallet_balance_master")
@Data 
@NoArgsConstructor 
@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreManagementCommercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

       

    @Column(name = "store_wallet")
    private double storeWallet;

    @UpdateTimestamp
    @Column(name = "store_last_updated_on")
    private Date storeLastUpdatedOn;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "transaction_hits_till_date")
    private int transactionHitsTillDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "status")
    @JsonProperty("status")
    private String storeStatus; 

    @Column(name = "location")
    private String storeLocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getStoreWallet() {
		return storeWallet;
	}

	public void setStoreWallet(double storeWallet) {
		this.storeWallet = storeWallet;
	}

	public Date getStoreLastUpdatedOn() {
		return storeLastUpdatedOn;
	}

	public void setStoreLastUpdatedOn(Date storeLastUpdatedOn) {
		this.storeLastUpdatedOn = storeLastUpdatedOn;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getTransactionHitsTillDate() {
		return transactionHitsTillDate;
	}

	public void setTransactionHitsTillDate(int transactionHitsTillDate) {
		this.transactionHitsTillDate = transactionHitsTillDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
    
    
}