package com.Busybox.rewards.application.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="voucher_balance_child")
public class Tjn_Voucher_Coupons {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int wallet_id;
	private int customer_id;
	private Long balance;
	private Date expiryDate;
	private String status;
	private String isEmpty;

	@Temporal(TemporalType.TIMESTAMP)
	private Date vouchar_purchase_date;
	
	private Double discount;
	private Double amt_bfrdiscount;
	private Double total_amount; 
	private String isflat;
	private String  vouchar_id;

	
}