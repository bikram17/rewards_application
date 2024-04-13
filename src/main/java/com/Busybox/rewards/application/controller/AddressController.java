package com.Busybox.rewards.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.dao.AddressDao;
import com.Busybox.rewards.application.model.AddressModel;
import com.Busybox.rewards.application.service.AddressService;
//import com.Busybox.rewards.application.service.AddressService;


@CrossOrigin("*")
@RestController
@RequestMapping("api/address")
public class AddressController {
	
	@Autowired	private AddressService addressSrevice;
	@Autowired private AddressDao dao;
	
	
	@GetMapping("/addressdetails/{customer_id}")
    public ResponseEntity<?> getAddressById(@PathVariable int customer_id){
		
		AddressModel address = dao.getAllwithId(customer_id);

	        if (address != null) {
	            // If address is found, return it as a response
	        	return ResponseHandler.generateResponse(address,HttpStatus.OK,"Fetched Successfully");
	        } else {
	            // If address is not found, return a 404 Not Found response
	        	return ResponseHandler.generateResponseNull("Address not Found", HttpStatus.BAD_REQUEST);
	        }
	    }
	@PostMapping("addAddress")
	public ResponseEntity<Object>addAddress(@RequestBody AddressModel addresModel){
		
		
		return ResponseHandler.generateResponse(addressSrevice.addAddress(addresModel),HttpStatus.OK,"Added Successfully");
		
	}
	/*
	 * @PutMapping("updateaddress/{customer_id}") public
	 * ResponseEntity<?>updateaddress(@PathVariable("customer_id")int
	 * customer_id,@RequestBody AddressModel addresModel ){
	 * 
	 * return null;
	 * 
	 * }
	 */
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@DeleteMapping("deletaddress/{customer_id}")
	public ResponseEntity<?>deleteaddress(@PathVariable int customer_id){
		dao.deleteById(customer_id);
		return ResponseHandler.generateResponse(null,HttpStatus.OK,"Deleted Succefully");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	


