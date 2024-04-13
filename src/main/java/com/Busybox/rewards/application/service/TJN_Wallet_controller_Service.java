package com.Busybox.rewards.application.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Busybox.rewards.application.model.tjn_Wallet_types_model;

public interface TJN_Wallet_controller_Service {


	tjn_Wallet_types_model addvoucharlist(tjn_Wallet_types_model tjnvoucharmodel);

	tjn_Wallet_types_model updateVouchar(tjn_Wallet_types_model vouchar, String vouchar_id);
	
	Object deletevouchar(String vouchar_id);

	Object getAlldata();

	//List<Object[]> getDataByCustomerMobno(String customer_mobno);

}
