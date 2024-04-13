package com.Busybox.rewards.application.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="tjn_login")
@JsonFilter("passwordFilter")
//@JsonFilter("idFilter")
public class tjn_Login_model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String Email;
	 @JsonIgnore
	private String Password;
	 
	 
	// //Generating Getters and Setters
	//
	public String getEmail() {
	return Email;
	}
	public int getId() {
	return id;
	}
	public void setId(int id) {
	this.id = id;
	}
	public void setEmail(String email) {
	Email = email;
	}
	public String getPassword() {
	return Password;
	}
	public void setPassword(String password) {
	Password = password;
	}
	// //Super
	public tjn_Login_model() {
	super();
	// TODO Auto-generated constructor stub
	}
	// //Generating Constructor
	public tjn_Login_model(String email, String password) {
	super();
	Email = email;
	Password = password;
	}
	// //ToString
	@Override
	public String toString() {
	return "tjn_Login_model [Email=" + Email + ", Password=" + Password + "]";
	}
	//
	//
}
