package com.Busybox.rewards.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;

@Service
public class WalletService {

	
	@Autowired
    private tjn_wallet_types_dao dao;  // You can use @Autowired here

    public String getWalletNameById(int walletId) {
        
        return dao.walletNameFromId(walletId);
    }
}
