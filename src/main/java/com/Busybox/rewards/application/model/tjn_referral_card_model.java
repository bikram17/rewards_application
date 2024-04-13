package com.Busybox.rewards.application.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tjn_referral_cards")
public class tjn_referral_card_model {
	
	@Id
	private String referral_card_id;
	
	@Column(name="referralCardNumber",nullable=false, unique=true)
	private String referral_card_number;
	
	private String referral_card;
	
	private String primary_card;
	
	@CreatedDate
    private String createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private String updatedAt;

    @LastModifiedBy
    private String updatedBy;
    
    public tjn_referral_card_model() {}

	public tjn_referral_card_model(String referral_card_id, String referral_card_number, String referral_card,
			String primary_card, String createdAt, String createdBy, String updatedAt, String updatedBy) {
		super();
		this.referral_card_id = referral_card_id;
		this.referral_card_number = referral_card_number;
		this.referral_card = referral_card;
		this.primary_card = primary_card;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}
	

	public String getReferral_card_id() {
		return referral_card_id;
	}

	public void setReferral_card_id(String referral_card_id) {
		this.referral_card_id = referral_card_id;
	}

	public String getReferral_card_number() {
		return referral_card_number;
	}

	public void setReferral_card_number(String referral_card_number) {
		this.referral_card_number = referral_card_number;
	}

	public String getReferral_card() {
		return referral_card;
	}

	public void setReferral_card(String referral_card) {
		this.referral_card = referral_card;
	}

	public String getPrimary_card() {
		return primary_card;
	}

	public void setPrimary_card(String primary_card) {
		this.primary_card = primary_card;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
    
    
}

