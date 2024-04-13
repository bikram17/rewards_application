package com.Busybox.rewards.application.model;



import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="master_test_data")
public class CustomerModel {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int customer_id;
private String customer_name;
private String customer_mobno;
private String customer_email;
private String customer_address;
private String city;
private String state;
private Integer package_Id;
private String pincode;
private String orgId;
@Column(name ="parentId")
@JsonProperty("ReferralCode")
private String parentId;
private String status;
@UpdateTimestamp
private Date updatedon;
private String loginStatus;
private String createdBy;
private String updatedBy;
private String storeId;
@Temporal(TemporalType.TIMESTAMP)
private Date createdOn;
@PrePersist
private void onCreate() {
createdOn = new Date();
}
public Date getCreatedOn() {
return createdOn;
}
public void setCreatedOn(Date createdOn) {
this.createdOn = createdOn;
}
public Date getUpdatedOn() {
return updatedon;
}
public void setUpdatedOn(Date updatedOn) {
this.updatedon = updatedOn;
}
public int getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}
public String getCustomer_name() {
	return customer_name;
}

public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
}

public String getCustomer_mobno() {
	return customer_mobno;
}

public void setCustomer_mobno(String customer_mobno) {
	this.customer_mobno = customer_mobno;
}

public String getCustomer_email() {
	return customer_email;
}

public void setCustomer_email(String customer_email) {
	this.customer_email = customer_email;
}

public String getCustomer_address() {
	return customer_address;
}

public void setCustomer_address(String customer_address) {
	this.customer_address = customer_address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}

public Integer getPackage_Id() {
	return package_Id;
}

public void setPackage_Id(Integer package_Id) {
	this.package_Id = package_Id;
}

public String getPincode() {
	return pincode;
}

public void setPincode(String pincode) {
	this.pincode = pincode;
}

public String getOrgId() {
	return orgId;
}

public void setOrgId(String orgId) {
	this.orgId = orgId;
}

public String getParentId() {
	return parentId;
}

public void setParentId(String parentId) {
	this.parentId = parentId;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Date getUpdatedon() {
	return updatedon;
}

public void setUpdatedon(Date updatedon) {
	this.updatedon = updatedon;
}

public String getLoginStatus() {
	return loginStatus;
}

public void setLoginStatus(String loginStatus) {
	this.loginStatus = loginStatus;
}

public String getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}

public String getUpdatedBy() {
	return updatedBy;
}

public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}

public String getStoreId() {
	return storeId;
}

public void setStoreId(String storeId) {
	this.storeId = storeId;
}

}