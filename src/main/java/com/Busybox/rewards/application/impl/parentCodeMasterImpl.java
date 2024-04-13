package com.Busybox.rewards.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.config.OTPGenerator;
import com.Busybox.rewards.application.dao.parent_class_master_dao;
import com.Busybox.rewards.application.model.parent_class_master;

@Service
public class parentCodeMasterImpl {

	// autowires start
	
	@Autowired private parent_class_master_dao codedao;
//	@Autowired private parent_class_master_dao oneDao;
	
	//autowires end
	
	// generate a random code for the customer 
	
	public void assignCodeToParent(int customer_id, String phoneNo, String name,String StoreId) {
		//to-do-lo
		// customer id will come while creating the master data or adding a new customer and then adding them in a new table
		
		try {
			parent_class_master pcm = new parent_class_master();
			pcm.setParentReferralCode(OTPGenerator.generate6DigitCode());
			pcm.setMaster_id(customer_id);
			pcm.setMasterName(name);
			pcm.setMasterPhoneNumber(phoneNo);
			pcm.setStoreId(StoreId);
			codedao.save(pcm);
			System.out.println(OTPGenerator.generate6DigitCode()+"CODE GIVEN");
		}catch(Exception e ) {
			System.out.println(e.toString()+"FAILED CHECK THE PARAENT CODE MASTER IMPL");
		}		
	}	
}
