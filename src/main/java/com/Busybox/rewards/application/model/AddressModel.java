package com.Busybox.rewards.application.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name="Address_Master")
public class AddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private int customer_id;
	private String address_line1;
	private String state;
	private String city;
	private String pincode;
	private String latitude;
	private String longititude;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	@PrePersist
	private void onCreate() {
		createdOn = new Date(); 
	}
	private String createdBy;
	private String updatedOn;
	private String updateBy;
	private String address_type;
	private String address_status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongititude() {
		return longititude;
	}
	public void setLongititude(String longititude) {
		this.longititude = longititude;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}
	public String getAddress_status() {
		return address_status;
	}
	public void setAddress_status(String address_status) {
		this.address_status = address_status;
	}
	public AddressModel(int id, int customer_id, String address_line1, String state, String city, String pincode,
			String latitude, String longititude, Date createdOn, String createdBy, String updatedOn, String updateBy,
			String address_type, String address_status) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.address_line1 = address_line1;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.latitude = latitude;
		this.longititude = longititude;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updateBy = updateBy;
		this.address_type = address_type;
		this.address_status = address_status;
	}
	public AddressModel() {
		//super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AddressModel [id=" + id + ", customer_id=" + customer_id + ", address_line1=" + address_line1
				+ ", state=" + state + ", city=" + city + ", pincode=" + pincode + ", latitude=" + latitude
				+ ", longititude=" + longititude + ", createdOn=" + createdOn + ", createdBy=" + createdBy
				+ ", updatedOn=" + updatedOn + ", updateBy=" + updateBy + ", address_type=" + address_type
				+ ", address_status=" + address_status + "]";
	}
	
	
	
	
	
	

}
