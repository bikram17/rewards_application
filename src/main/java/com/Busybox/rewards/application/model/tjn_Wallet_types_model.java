package com.Busybox.rewards.application.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="The_Juice_Nation_Wallet_Master")//wallet_master
public class tjn_Wallet_types_model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wallet_id;	
	
	private String wallet_name;

	private String walletType;

	private String status;

	private String vouchar_id ;
	
	private Double vouchar_price;

	private String validity;
	
	private String store_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	@PrePersist
	private void onCreate() {
	createdOn = new Date();
	}
	public int getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}
	public String getWallet_name() {
		return wallet_name;
	}
	public void setWallet_name(String wallet_name) {
		this.wallet_name = wallet_name;
	}
	public String getWalletType() {
		return walletType;
	}
	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVouchar_id() {
		return vouchar_id;
	}
	public void setVouchar_id(String vouchar_id) {
		this.vouchar_id = vouchar_id;
	}
	public Double getVouchar_price() {
		return vouchar_price;
	}
	public void setVouchar_price(Double vouchar_price) {
		this.vouchar_price = vouchar_price;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public tjn_Wallet_types_model(int wallet_id, String wallet_name, String walletType, String status,
			String vouchar_id, Double vouchar_price, String validity, String store_id, Date createdOn) {
		super();
		this.wallet_id = wallet_id;
		this.wallet_name = wallet_name;
		this.walletType = walletType;
		this.status = status;
		this.vouchar_id = vouchar_id;
		this.vouchar_price = vouchar_price;
		this.validity = validity;
		this.store_id = store_id;
		this.createdOn = createdOn;
	}
	public tjn_Wallet_types_model() {
		super();
		
	}
	@Override
	public String toString() {
		return "tjn_Wallet_types_model [wallet_id=" + wallet_id + ", wallet_name=" + wallet_name + ", walletType="
				+ walletType + ", status=" + status + ", vouchar_id=" + vouchar_id + ", vouchar_price=" + vouchar_price
				+ ", validity=" + validity + ", store_id=" + store_id + ", createdOn=" + createdOn + "]";
	}
	
	
	
	
	
}