package com.Busybox.rewards.application.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletDto {
	
	@Autowired tjn_wallet_types_dao dao;
	
	setMaster_balance sm = new setMaster_balance();
	 int wallet_id= sm.getId();
	
	
	  @JsonProperty("Wallet_name")
	  private String walletName=null;
	 
	
	@JsonProperty("wallet_balance")
   private String walletBalance = null;       

   @JsonProperty("status")
   private String cardStatus = null;



	
	  public String getWalletName() { 
	  //dao.walletNameFromId(Integer.parseInt(this.wallet_id)); 
	  return walletName;
	  }
	  
	  public void setWalletName(String walletName) {
	  
	  this.walletName = walletName; 
	  }
	 

	public String getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(String walletBalance) {
		this.walletBalance = walletBalance;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	
   
   
	/*
	 * public String setWalletname(int x) { return dao.walletNameFromId(x); }
	 */

   // Other fields, getters, and setters
   // ...
   
   
   
}

