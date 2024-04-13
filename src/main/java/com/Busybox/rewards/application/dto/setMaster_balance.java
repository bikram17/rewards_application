package com.Busybox.rewards.application.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="wallet_balance_master")
public class setMaster_balance {
	
	//@Autowired tjn_wallet_types_dao dao;
	
	@Id
	private int id;
    private String customer_id;
    private String wallet_id;
    private  String wallet_balance;//
    private String status;//
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("create_on")
    private Date cardCreatedOn = null;
    private String update_on;
    private String create_by;
    private String update_by;
    
    
	public setMaster_balance(int id, String customer_id, String wallet_id, String wallet_balance, String status,
			Date cardCreatedOn, String update_on, String create_by, String update_by) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.wallet_id = wallet_id;
		this.wallet_balance = wallet_balance;
		this.status = status;
		this.cardCreatedOn = cardCreatedOn;
		this.update_on = update_on;
		this.create_by = create_by;
		this.update_by = update_by;
	}
	public setMaster_balance(String customer_id, String wallet_id, String wallet_balance, String status,
			Date cardCreatedOn, String update_on, String create_by, String update_by) {
		super();
		this.customer_id = customer_id;
		this.wallet_id = wallet_id;
		this.wallet_balance = wallet_balance;
		this.status = status;
		this.cardCreatedOn = cardCreatedOn;
		this.update_on = update_on;
		this.create_by = create_by;
		this.update_by = update_by;
	}
	public setMaster_balance() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getWallet_id() {
		return wallet_id;
	}
	public void setWallet_id(String wallet_id) {
		this.wallet_id = wallet_id;
	}
	public String getWallet_balance() {
		return wallet_balance;
	}
	public void setWallet_balance(String wallet_balance) {
		this.wallet_balance = wallet_balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreate_on() {
		return cardCreatedOn;
	}
	public void setCreate_on(Date cardCreatedOn) {
		this.cardCreatedOn = cardCreatedOn;
	}
	public String getUpdate_on() {
		return update_on;
	}
	public void setUpdate_on(String update_on) {
		this.update_on = update_on;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	
	
    
	 public WalletDto toWalletDto() {
	        WalletDto walletDto = new WalletDto();
	       // tjn_wallet_types_dao dao = new tjn_wallet_types_dao();
	       // String walletName = dao.walletNameFromId(Integer.parseInt(this.wallet_id));
	        	
	      // walletDto.setWalletName(walletDto.setWalletname(Integer.parseInt(this.wallet_id))); 
	        
	       //walletDto.setWalletName(walletDto.getWalletName());
	     //   walletDto.setWalletName(s);
	        walletDto.setWalletBalance(this.wallet_balance);
	        walletDto.setCardStatus(this.status);
	       // walletDto.setCardCreatedOn(this.cardCreatedOn);
	        
	        
	        // Set other fields as needed
	        
	        return walletDto;
	    }
}
