package com.Busybox.rewards.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.Tjn_Wallet_Dao;
import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;
import com.Busybox.rewards.application.model.tjn_Wallet_types_model;
import com.Busybox.rewards.application.service.TJN_Wallet_controller_Service;

@Service

public class TJN_Wallet_controller_ServiceImpl implements TJN_Wallet_controller_Service {
	
	@Autowired  tjn_wallet_types_dao voucharDao;
	@Autowired private Tjn_Wallet_Dao tjnDao;

/*ADDING NEW VOUCHAR*/
	@Override
	public tjn_Wallet_types_model addvoucharlist(tjn_Wallet_types_model tjnvoucharmodel) {
		
		return voucharDao.save(tjnvoucharmodel);
	}
	
/* UPDATE A SINGLE VOUCHAR ON THE BASIS OF VOUCHAR ID*/
	@Override
	public tjn_Wallet_types_model updateVouchar(tjn_Wallet_types_model vouchar, String vouchar_id) {
		tjn_Wallet_types_model existingvoucharupdate = voucharDao.findByVoucharId(vouchar_id) ;
		if(existingvoucharupdate != null) {
			existingvoucharupdate.setVouchar_id(vouchar_id);
			existingvoucharupdate.setStatus(vouchar.getStatus());
			existingvoucharupdate.setValidity(vouchar.getValidity());
			existingvoucharupdate.setVouchar_price(vouchar.getVouchar_price());
			existingvoucharupdate.setWallet_name(vouchar.getWallet_name());
				
					return voucharDao.save(existingvoucharupdate);
				
			
		}
		return null;
	}
	
/* DELETE A SINGLE VOUCHAR ON THE BASIS OF VOUCHAR ID*/
	@Override
	public Object deletevouchar(String vouchar_id) {

		return voucharDao.deleteById(vouchar_id);
	}

	@Override
	public Object getAlldata() {
		
		return voucharDao.findAll();
	}

	/*@Override
	public List<Object[]> getDataByCustomerMobno(String customer_mobno) {
		
		return tjnDao.getCustomerData(customer_mobno);
	}*/

	
}