package com.Busybox.rewards.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.tjn_cards_dao;
import com.Busybox.rewards.application.model.tjn_cards_model;
import com.Busybox.rewards.application.service.tjn_cards_services;

@Service
public class tjn_cards_service_impl implements tjn_cards_services {
	
	String response;
	@Autowired
	private tjn_cards_dao cardsDao;
	tjn_cards_model tj = new tjn_cards_model();
	

	
	public ResponseEntity<?> addIfExists(int customerID, tjn_cards_model tjn) {
	    try {
	        // Check if the customer exists
	        String existsStatus = cardsDao.checkIfCustomerExists(customerID);
	        
	        // Get the customer's status
	        Object customerStatus = cardsDao.customerStatus(customerID);
	        
	        if (((String) customerStatus).equalsIgnoreCase("ACTIVE")) {
	            if (existsStatus.equalsIgnoreCase("YES")) {
	                // Save the card
	                cardsDao.save(tjn);
	                return ResponseHandler.generateResponse(tjn, HttpStatus.OK, "Card added successfully");
	            } else if (existsStatus.equalsIgnoreCase("NO")) {
	                // Customer not found
	                return ResponseHandler.generateResponse("Customer Not Found", HttpStatus.OK, "Exists Status");
	            } else {
	                // Error in existsStatus
	                return ResponseHandler.generateResponse("Error", HttpStatus.OK, "Exists Status");
	            }
	        } else {
	            // Customer is not active
	            return ResponseHandler.generateResponse("Error", HttpStatus.OK, "Customer Status");
	        }
	    } catch (Exception e) {
	        // Handle exceptions
	        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.OK, "Error");
	    }
	}

	public ResponseEntity<?> checker(long customerId, tjn_cards_model tjn){
		
		return null;
	} 
	// takes the customer id and creates the base Wallet Id
	public ResponseEntity<?> addIfExists2(int customerID) {
	    try {
	        Object customerData = cardsDao.CustomerIDFromData(customerID);
	        if (customerData != null) {
//	        	tjn_cards_model custID = (long) customerData;

	            Object customerStatusData = cardsDao.customerStatus(customerID);
	            if (customerStatusData != null) {
	                String customerStatus = (String) customerStatusData;

	                if (customerStatus.equalsIgnoreCase("ACTIVE")) {
	                    String walletOneCheck = cardsDao.Wallet_id_one_check(customerID);
	                    String walletTwoCheck = cardsDao.Wallet_id_two_check(customerID);

	                    if (walletOneCheck == null && walletTwoCheck == null) {
	                           try {
	                            cardsDao.insertWalletOne(customerID);
	                            cardsDao.insertWalletTwo(customerID);
	                            return ResponseHandler.generateResponse("Added Wallets to wallet master one and two successfully", HttpStatus.OK, "Successfully Fetched");
	                        } catch (Exception e) {
	                            return ResponseHandler.generateResponse(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Error while adding wallets");
	                        }
	                    } else {
	                        return ResponseHandler.generateResponse("Error: Wallets already exist", HttpStatus.BAD_REQUEST, "Wallets already exist");
	                    }
	                } else {
	                    return ResponseHandler.generateResponse("Error: Customer is inactive", HttpStatus.FORBIDDEN, "Customer Inactive");
	                }
	            } else {
	                return ResponseHandler.generateResponse("Error: Failed to fetch customer active status", HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch customer active status");
	            }
	        } else {
	            return ResponseHandler.generateResponse("Error: Customer not found", HttpStatus.NOT_FOUND, "Customer Not Found");
	        }
	    } catch (Exception e) {
	        return ResponseHandler.generateResponse(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Error");
	    }
	}

	
	public ResponseEntity<?> addWallets(int custommerID){
		try {
			cardsDao.insertWalletOne(custommerID);
	        cardsDao.insertWalletTwo(custommerID);
	        return ResponseHandler.generateResponse(custommerID, HttpStatus.OK, "Customer is Createad");
		}catch(Exception e) {
			return ResponseHandler.generateResponse("Customer Registration Failed", HttpStatus.BAD_REQUEST,e.toString());
			}
		
	}
	
	

}
