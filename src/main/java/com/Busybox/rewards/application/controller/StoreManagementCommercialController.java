package com.Busybox.rewards.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.impl.StoreManagementCommercialService;
import com.Busybox.rewards.application.model.StoreManagementCommercial;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/store-management")
public class StoreManagementCommercialController {
    private final StoreManagementCommercialService storeService;

    public StoreManagementCommercialController(StoreManagementCommercialService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StoreManagementCommercial>> getAllStores() {
        List<StoreManagementCommercial> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StoreManagementCommercial> getStoreById(@PathVariable Long id) {
        Optional<StoreManagementCommercial> store = storeService.getStoreById(id);
        return store.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StoreManagementCommercial> createStore(@RequestBody StoreManagementCommercial store) {
        StoreManagementCommercial createdStore = storeService.createStore(store);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/add-money/{amount}")
    public ResponseEntity<StoreManagementCommercial> addMoneyToWallet(@PathVariable Long id, @PathVariable double amount) {
        StoreManagementCommercial updatedStore = storeService.addMoneyToWallet(id, amount);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    
    // this is to test the store id and the amount to check and take amount from the user   
    @GetMapping("/{id}/add-money1/{amount}")
    public ResponseEntity<StoreManagementCommercial> busyboxCommercialTransaction(@PathVariable Long id, @PathVariable double amount) {
        StoreManagementCommercial updatedStore = storeService.handleTransaction(id, amount,null);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }
    
    
    

}