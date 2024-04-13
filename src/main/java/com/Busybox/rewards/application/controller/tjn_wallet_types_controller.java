package com.Busybox.rewards.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;
import com.Busybox.rewards.application.model.tjn_Wallet_types_model;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/wallettype")
public class tjn_wallet_types_controller {

//	@Autowired tjn_Wallet_types_model wmodel;
	//@Autowired tjn_wallet_types_dao wdao;
	@Autowired tjn_wallet_types_dao dao;
	
	
	@GetMapping("/hello")
	public String abc()
	{
		return "hello from wallet types controller";
	}	
	
	@GetMapping("/getallmodel")
	public List<tjn_Wallet_types_model> abcd()
	{
		return dao.findAll();
	}
	@GetMapping("/allWalletTypes")
	public ResponseEntity<?> getAllWallets(){
//		wdao.findAll();
		//return ResponseHandler.generateResponse(dao.findAll(), HttpStatus.OK, "Data Fetched Successfully");
		try {
			return ResponseHandler.generateResponse(dao.findAll(), HttpStatus.OK, "Data Fetched Successfully");
		}catch(Exception e) {
			return ResponseHandler.generateResponse("Failed To Load Data", HttpStatus.INTERNAL_SERVER_ERROR, "Data Fetched Successfully");
		}
	}
	
	
	
	@PostMapping("/createwallet")
	public ResponseEntity<?> addWallets(@RequestBody  tjn_Wallet_types_model wp){
		try {
//			dao.save(wp);
//			int n= wp.getWalletID();
			dao.save(wp);
			String Walletname =wp.getWallet_name();
			return ResponseHandler.generateResponse(Walletname,HttpStatus.OK,"Wallet Successfully Added");
		}catch(Exception e) {return ResponseHandler.generateResponse(wp, HttpStatus.EXPECTATION_FAILED, "Failed to add this Wallet");}
	}

}
