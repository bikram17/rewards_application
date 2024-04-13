package com.Busybox.rewards.application.impl;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.config.OTPGenerator;
import com.Busybox.rewards.application.config.sendSMS;
import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.Tjn_login_dao;

//import busybox.tjn.dao.tjn_Login_dao;

@Service
public class Tjn_login_ServiceImpl
{
	@Autowired Tjn_login_dao dao;
	@Autowired sendSMS sendImpl;
	
	public ResponseEntity<Object> LoginADMIN( Map<String,String>me) {
		
	try{
			String amardeoapassword = me.get("password")    ;
			String amardeaoinput = me.get("email");
			
			//User Details from Database
			int id = dao.UserIDFromSQL(amardeaoinput);
			String DatabaseUser = dao.UserFromSQL(id);
	 
			if("no".equalsIgnoreCase(DatabaseUser)) {
				return ResponseHandler.generateResponseNull(" USER NOT FOUND", HttpStatus.NOT_FOUND);
				}
			else {
				//int id = dao.UserIDFromSQL(amardeaoinput);
				String DatabasePassword = dao.passwordFromSQL(id);
				if(DatabasePassword.equalsIgnoreCase(amardeoapassword)) {
					//return new ResponseEntity<>("Login Successful", HttpStatus.OK);
					return ResponseHandler.generateResponseNull("Login Successful", HttpStatus.OK);
				}
				else 
					return ResponseHandler.generateResponseNull("Incorrect Password", HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return ResponseHandler.generateResponseNull("Email Not Found", HttpStatus.NOT_FOUND);
		}
	}

	
	public ResponseEntity<Object> Login( Map<String,String>me) {
		
		try{
			String PhoneNumber = me.get("PhoneNumber");
			String amardeaoinput = me.get("email");
			
			//User Details from Database
			int id = dao.UserIDFromSQL(amardeaoinput);
			String DatabaseUser = dao.UserFromSQL(id);
			
			if("no".equalsIgnoreCase(DatabaseUser)) {
				return ResponseHandler.generateResponseNull(" USER NOT FOUND", HttpStatus.NOT_FOUND);
			}
			else {
				//int id = dao.UserIDFromSQL(amardeaoinput);
				int  countOfTheNumberOfCustomerExisting= dao.DoesAnyOneExists(PhoneNumber);
				if(countOfTheNumberOfCustomerExisting== 1) {
					//return new ResponseEntity<>("Login Successful", HttpStatus.OK);
					String otp =OTPGenerator.generate4DigitCode();
					String message ="OTP for Login	Transactional	TJUICN	Your TJN Club Membership Login OTP is "+ otp +" . For any Queries & Orders Contact 9034034018.";
					sendImpl.sendSmsNow(message, PhoneNumber);
					return ResponseHandler.generateResponseNull("Login Successful", HttpStatus.OK);
				}
				else 
					return ResponseHandler.generateResponseNull("Multiple People Existing with a single Number contact Customer Support @ 9034034018", HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			return ResponseHandler.generateResponseNull("Email Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
}
	

