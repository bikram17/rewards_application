package com.Busybox.rewards.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.dao.tjn_cards_dao;
//import com.Busybox.rewards.application.impl.tjn_cards_service_impl;
//import com.Busybox.rewards.application.dao.cards_dao_test;
import com.Busybox.rewards.application.model.tjn_cards_model;
//import com.Busybox.rewards.application.service.tjn_cards_services;



@RestController
@RequestMapping("/api/card")
public class tjn_cards_controller {
	
//	@GetMapping("/users")
//	  public List<User> getAllUsers() {
//	    return userRepository.findAll();
//	  }
//	
	
	
	//variables
	String[][] LoginInput = new String[3][3];
	
	
	
//	@Autowired private tjn_cards_services servicetjn;
	@Autowired private tjn_cards_dao cardDaoT;
//	@Autowired private tjn_cards_service_impl impl;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
		
	@GetMapping("/getall")
	private List<tjn_cards_model> getAllData(){
		return cardDaoT.getAll();
		}

//	@PostMapping("/cardEnter")
//	private ResponseEntity<?>  addDataAuto(@RequestBody tjn_cards_model tjn) {
//		long customer_Id= tjn.getCustomerId();
//		long customer_Card= tjn.getCardNumber();
//		return impl.addIfExists(customer_Id,tjn);
		
		
		//return null;
//	}
	
	@PostMapping("/cardEnter/new")
	private ResponseEntity<Object> addNewCardManually(@RequestBody tjn_cards_model tjn){
//		long customer_Id= tjn.getCustomerId();
//		long customer_Card= tjn.getCardNumber();
		
		
		
		return null;
	}   
	@GetMapping("/doom/{id}")
	private ResponseEntity<?> abcd(@PathVariable int id){
		/*
		 * Object abc = cardDaoT.CustomerIDFromData(id); return
		 * ResponseHandler.generateResponse(abc, HttpStatus.OK, "null");
		 */
		try {
			Object abc = cardDaoT.CustomerIDFromData(id);
			if(!abc.equals(null))
			return ResponseHandler.generateResponse(abc, HttpStatus.OK, "Data Found");
			else
				return ResponseHandler.generateResponse(abc, HttpStatus.OK, "No Data Found");
		}catch(Exception e) {
			return ResponseHandler.generateResponse(e.toString(), HttpStatus.OK, "Error here");
		}
	}
	
	
	
}
