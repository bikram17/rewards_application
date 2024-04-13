package com.Busybox.rewards.application.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.StoreManagementCommercialRepository;
import com.Busybox.rewards.application.dao.StoreManagementLogsDao;
import com.Busybox.rewards.application.model.StoreManagementCommercial;
import com.Busybox.rewards.application.model.StoreManagementLogs;

@Service
public class StoreManagementCommercialService {
	
    @Autowired private  StoreManagementCommercialRepository storeRepository;
    @Autowired private  StoreManagementLogsDao LogsDao;
    

    public StoreManagementCommercialService(StoreManagementCommercialRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<StoreManagementCommercial> getAllStores() {
        return storeRepository.findAll();
    }
    
    public Optional<StoreManagementCommercial> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    public StoreManagementCommercial createStore(StoreManagementCommercial store) {
        return storeRepository.save(store);
    }

    public StoreManagementCommercial addMoneyToWallet(Long id, double amount) {
        Optional<StoreManagementCommercial> storeOptional = storeRepository.findById(id);

        if (storeOptional.isPresent()) {
            StoreManagementCommercial store = storeOptional.get();
            double currentWalletAmount = store.getStoreWallet();
            store.setStoreWallet(currentWalletAmount + amount);
            return storeRepository.save(store);
        } else {
            throw new RuntimeException("Store not found");
        }
    }
    public StoreManagementCommercial busyboxCommercialTransaction(Long storeId, double amount) {
    	Optional<StoreManagementCommercial> storeOptional = storeRepository.findById(storeId);
    	if(storeOptional.isPresent()) {
    		StoreManagementCommercial store = storeOptional.get();
    		double currentWalletAmount = store.getStoreWallet();
    		double comissionAmount = currentWalletAmount - (amount*0.01);
    		store.setStoreWallet(comissionAmount);
    		return storeRepository.save(store);
    		
    	}else {
    		throw new RuntimeException("Store not found here");
    	}
    }
    
    public StoreManagementCommercial handleTransaction(Long storeId, double transactionAmount, String mobno) {
    	Optional<StoreManagementCommercial> storeOptional = storeRepository.findById(storeId);
    	if(storeOptional.isPresent()) {
    		StoreManagementCommercial store = storeOptional.get();
    		double currentWalletAmount = store.getStoreWallet();
    		double comissionAmount = currentWalletAmount - (transactionAmount*0.01);
    		store.setStoreWallet(comissionAmount);
    		 storeRepository.save(store);
    		 //-- LOGS
    		 StoreManagementLogs logs = new StoreManagementLogs();
    		logs.setStore_id(storeId);//
    		logs.setStore_location(store.getStoreLocation());
    		logs.setCustomer_mobno(mobno);
    		logs.setWallet_balance_before_transaction(currentWalletAmount);
    		logs.setOpeningBalance(currentWalletAmount);
    		logs.setStore_id(storeId);
    		logs.setTransactionAmount(transactionAmount*0.01);
    		logs.setClosingBalance(comissionAmount);
    		LogsDao.save(logs);
    		 //-- LOGS
    		
    	}else {
    		throw new RuntimeException("Store not found here");
    	}
        Optional<StoreManagementCommercial> storeOptional1 = storeRepository.findById(storeId);

        if (storeOptional1.isPresent()) {
            StoreManagementCommercial store = storeOptional1.get();
            int currentTransactionHits = store.getTransactionHitsTillDate();
            store.setTransactionHitsTillDate(currentTransactionHits + 1);
           return storeRepository.save(store); // Save the updated store entity
        } else {
            throw new RuntimeException("Store not found");
        }
    }

}