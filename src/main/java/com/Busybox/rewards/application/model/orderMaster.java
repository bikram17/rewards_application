package com.Busybox.rewards.application.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.ElementCollection;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name="order_master_table")
public class orderMaster {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	// auto generate
	private String order_id;
	
	private String customer_mobno;
	
	private String customer_name;
	// order status - 
	private String order_status;
	// will come from invoice
	private String invoice_id;
	// upi/cash/card
	private String payment_type;
	//
	private String request_id;
	//shipping address
	@ElementCollection
	private String[] shipping_address;
    
	private double total_amount;
	
	private String order_type;
    
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date order_date;
	
	@ElementCollection
	private List<Items> items;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCustomer_mobno() {
		return customer_mobno;
	}

	public void setCustomer_mobno(String customer_mobno) {
		this.customer_mobno = customer_mobno;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String[] getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String[] shipping_address) {
		this.shipping_address = shipping_address;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	
	
}
