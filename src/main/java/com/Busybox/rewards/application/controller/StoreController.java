package com.Busybox.rewards.application.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.impl.StoreService;
import com.Busybox.rewards.application.model.Store;
import com.Busybox.rewards.application.security.JwtHelper;
import com.Busybox.rewards.application.security.UserModel;
import com.Busybox.rewards.application.security.UserRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired private final StoreService storeService;
    @Autowired private UserRepository userRepository;
    JwtHelper jwtHelper = new JwtHelper();

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("getall")
    public ResponseEntity<?> getAllStores(HttpServletRequest request) {
    	String jwtToken = request.getHeader("Authorization");
    	jwtToken = jwtToken.substring(7); 
    	
    	Claims claims = jwtHelper.getAllClaimsFromToken(jwtToken);
    	System.out.println(claims);
        String username = claims.get("sub", String.class);
        System.out.println("User " + username + " accessed the API.");
        UserModel userModel= userRepository.findByEmail(username).orElse(null);
        String storeId = userModel.getStoreId();
        if(storeId == null) {
        	
        	return ResponseHandler.generateResponse(storeService.getAllStores(), HttpStatus.OK, "SUCCESS");
        	
        }
        else {
        	return ResponseHandler.generateResponse(storeService.getAllStoresStoreBased(storeId), HttpStatus.OK, "SUCCESS");
        }
        
        
//        return storeService.getAllStoresWithSubStores();
//    	return storeService.getAllStoresWithParentAndSubStores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Optional<Store> store = storeService.getStoreById(id);
        return store.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("addone")
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        Store createdStore = storeService.createStore(store);
        return new ResponseEntity<>(createdStore, HttpStatus.CREATED);
    }
    






    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody Store store) {
        Store updatedStore = storeService.updateStore(id, store);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}