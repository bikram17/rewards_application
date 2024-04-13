package com.Busybox.rewards.application.model;

import jakarta.annotation.Generated;
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

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="parent_child_balance")
public class ParentToChildTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
		
	private double accountTransaction;
	
	private String parentId;
	
	private String parentName;
	
	private String parentPhoneNumber;
	
	private String childName;
	
	private String childPhoneNumber;
}


// public void addParentToChildLink(String customerIdOfParent,double transaction_amt, String customer_phn1, String childPhoneNumber, String Childname )
