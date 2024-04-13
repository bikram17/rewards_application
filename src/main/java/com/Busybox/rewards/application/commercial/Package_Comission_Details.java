package com.Busybox.rewards.application.commercial;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="package_Comission_Details")
public class Package_Comission_Details {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private long id;
	
	@Column(name="package_Id",nullable=false)
	private long package_Id;
	
	@Column(name="to_amount",nullable=false)
	private long to_amount;
	
	@Column(name="from_amount",nullable=false)
	private long from_amount;
	
	@Column(name="comission_amount",nullable=false)
	private double comissionAmount;
	
	@Column(name="is_Flat",nullable=false)
	private String isFlat;
	
	@Column(name="status", nullable=false)
	private String status;
	
	@CreationTimestamp
	@Column(name="Created_At",nullable=false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name = "Updated_At", nullable = true)
	private LocalDateTime updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPackage_Id() {
		return package_Id;
	}

	public void setPackage_Id(long package_Id) {
		this.package_Id = package_Id;
	}

	public long getTo_amount() {
		return to_amount;
	}

	public void setTo_amount(long to_amount) {
		this.to_amount = to_amount;
	}

	public long getFrom_amount() {
		return from_amount;
	}

	public void setFrom_amount(long from_amount) {
		this.from_amount = from_amount;
	}

	public double getComissionAmount() {
		return comissionAmount;
	}

	public void setComissionAmount(double comissionAmount) {
		this.comissionAmount = comissionAmount;
	}

	public String getIsFlat() {
		return isFlat;
	}

	public void setIsFlat(String isFlat) {
		this.isFlat = isFlat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	
/*	
    "id": 2,
    "package_Id": 1,
    "to_amount": 0,
    "from_amount": 99,
    "comissionAmount": 1,
    "isFlat": "Yes",
    "status": "ACTIVE",
    "createdAt": "2023-09-07T16:30:01.409979",
    "updatedAt": null
*/
}