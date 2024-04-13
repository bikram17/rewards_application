package com.Busybox.rewards.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.ParentToChildTransferDao;
import com.Busybox.rewards.application.dao.parent_class_master_dao;
import com.Busybox.rewards.application.model.ParentToChildTransaction;

@Service
public class ParentToChildTransactionImpl {

	@Autowired private ParentToChildTransferDao parentToChildTransferDao;
	
	public void saveReferralTransaction(ParentToChildTransaction parentToChildTransaction) {
		parentToChildTransferDao.save(parentToChildTransaction);
	}
	
	
}
