package com.Busybox.rewards.application.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import com.Busybox.rewards.application.dao.CustomerRepository;
import com.Busybox.rewards.application.dao.referral_model_master_balance_dao;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
//@Audited
@Table(name = "master_referral_code")
public class parent_class_master {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID",nullable=true, unique=true)
	private long id;
	
	@Column(name="Master_ID")
	private long master_id;
	
	@Column(name="Name")
    private String masterName;
    
    @Column(name="Phone_Number")
    private String masterPhoneNumber;
    
    @Column(name="Referral_Code")
    private String parentReferralCode;
    
    @JsonIgnore
    @Column(name="store_id")
    private String storeId;
    
    @CreationTimestamp
    @Column(name="Created_At")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


	public String getStoreId() {
		
		return storeId;
	}

	public void setStoreId(String storeId) {
		
		this.storeId = storeId;
	}

    
    
}

