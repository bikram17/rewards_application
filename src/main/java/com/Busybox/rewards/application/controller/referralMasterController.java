package com.Busybox.rewards.application.controller;

import java.util.Map;

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

//import com.Busybox.rewards.application.dao.parent_class_master_dao;
import com.Busybox.rewards.application.dao.referral_model_master_balance_dao;
import com.Busybox.rewards.application.impl.referral_model_master_balance_impl;

import jakarta.servlet.http.HttpServletRequest;
//import com.Busybox.rewards.application.model.parent_class_master;
@CrossOrigin("*")
@RestController
@RequestMapping("referral")
public class referralMasterController {

	@Autowired private referral_model_master_balance_impl refMasterImpl;
	@Autowired private referral_model_master_balance_dao dao;
	@PostMapping("addParentOrUpdate")
	public ResponseEntity<?> addParentOrUpdate(@RequestBody Map<String, String> userInput) {
		String code = userInput.get("code");
		String phoneNumber = userInput.get("phn");
		return ResponseHandler.generateResponseNull(refMasterImpl.abcabc(code,phoneNumber), HttpStatus.OK);
	}
	
	@PostMapping("all1")
	public ResponseEntity<?> test(@RequestBody Map<String, String> input){
		return ResponseHandler.generateResponseNull(" ", HttpStatus.OK);

}	
	@GetMapping("/getphonenumber/{data}")
	public ResponseEntity<?> getPhoneNumberUsingCode (@PathVariable String data){
			try {
				String test = dao.getPhoneNumberUsingReferralCode(data);
				System.out.println(test);
				if(!test.equalsIgnoreCase(null)) {
					return ResponseHandler.generateResponse(test, HttpStatus.OK, "Success");
				}
				else {
					return ResponseHandler.generateResponseNull("Parent Not Found", null);
				}
			}catch (Exception e ) {
					return ResponseHandler.generateResponse("Referral Code Not Found", HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
			}
	}
	@GetMapping("/getcode/{data}")
	public ResponseEntity<?> getReferralCodeUsingPhoneNumber (@PathVariable String data){
			try {
				String test = dao.getReferralCode(data);
				if(!test.equalsIgnoreCase("null")) {
					return ResponseHandler.generateResponse(test, HttpStatus.OK, "Success");
				}
				else {
					return ResponseHandler.generateResponseNull("Parent Not Found", HttpStatus.OK);
				}
			}catch (Exception e ) {
					return ResponseHandler.generateResponse("Referral Code Not Found", HttpStatus.BAD_REQUEST, "Failed");
			}
	}
	@GetMapping("/test/{data}")
	public ResponseEntity<?> testID (@PathVariable String data){
			try {
				long test = dao.getIdUsingReferralCode(data);
				if(test != 0) {
					String mobNo = dao.getPhoneNumberUsingReferralCode(data);
				
					return ResponseHandler.generateResponse(mobNo, HttpStatus.OK, String.valueOf(test));
				}
				else {
					return ResponseHandler.generateResponseNull("Parent Not Found", null);
				}
			}catch (Exception e ) {
				
					return ResponseHandler.generateResponse("Referral Code Not Found", HttpStatus.BAD_REQUEST, data);
				
			}
	}
	
	@GetMapping("app/link/{data}")
	public ResponseEntity<?> appLink(@PathVariable String data){
		return ResponseHandler.generateResponse("acs.busybox.in/"+ data, HttpStatus.OK, "SUCCESS");
	}
	
	@GetMapping("/getdata")
	public ResponseEntity<?> referralCountData(HttpServletRequest request){
		return ResponseHandler.generateResponse(refMasterImpl.getDataOfReferralCountWithAdditionalInfo(request), HttpStatus.OK, "Success");}
		


	@GetMapping("/childdata/{code}")
		public ResponseEntity<?> childData(@PathVariable String code){
			return ResponseHandler.generateResponse(refMasterImpl.getDataOfchildReferral(code), HttpStatus.OK, "Success");}
	
	}
