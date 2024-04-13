package com.Busybox.rewards.application.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.StoreRepository;
import com.Busybox.rewards.application.model.Store;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Store updateStore(Long id, Store store) {
        if (storeRepository.existsById(id)) {
            store.setId(id);
            return storeRepository.save(store);
        } else {
            throw new RuntimeException("Store not found");
        }
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
    

     
    public String getStackTraceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

	public List<Store> getAllStoresStoreBased(String storeId) {
		return storeRepository.findAllStoreId(storeId);
		
	}



}