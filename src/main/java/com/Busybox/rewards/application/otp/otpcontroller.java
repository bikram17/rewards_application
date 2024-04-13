package com.Busybox.rewards.application.otp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

import com.Busybox.rewards.application.config.OTPGenerator;
import com.Busybox.rewards.application.config.sendSMS;
import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.Tjn_login_dao;
import com.Busybox.rewards.application.impl.Tjn_login_ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/sms")
public class otpcontroller {

	@Autowired Tjn_login_dao dao;
	@Autowired Tjn_login_ServiceImpl impl;
	@Autowired sendSMS sendSMSCLASS;
	@Autowired OTPdao otPdao;
	
	private final Map<String, String> otpCache = new HashMap<>();
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@PostMapping("login/send")
	public ResponseEntity<?> loginSend(@RequestBody Map<String,String> input) {
			
		
		String phoneNumber = input.get("PhoneNumber");
		int x = dao.countOfThatNumber(phoneNumber);
		if(x!=1) {
			return ResponseEntity.badRequest().body("NUMBER FAILED TO LOCATE");
		}
		String otp =OTPGenerator.generate6DigitCode();
		String message =URLEncoder.encode("Your TJN Club Membership Login OTP is "+ otp.toString() +". For any Queries & Orders Contact 9034034018- The Juice Nation.");
	    
		
		String otpSent = sendSMSCLASS.sendSmsNow(message, phoneNumber);
		
		String sessionId = UUID.randomUUID().toString();
		
		OTPmodel otpModel = new OTPmodel();
		otpModel.setStauts("GENERATED");
		otpModel.setOtp(otp);
		otpModel.setSession_id(sessionId);
		otpModel.setActivity("LOGINOTP");
		otpModel.setPhoneNumber(phoneNumber);
		otPdao.save(otpModel);
		
	    System.out.println(otpSent);
	    	
		  String jsonString = otpSent;

	        // Create an ObjectMapper instance
	        ObjectMapper objectMapper = new ObjectMapper();
		try {
            // Convert the JSON string to a JSON object (in this case, a Map)
            Map<String, Object> jsonObject = objectMapper.readValue(jsonString, Map.class);

            // Access the "status" field
            String status = (String) jsonObject.get("status");

            // Check if the status is "success" or "failure"
            if ("success".equals(status)) {
            	return ResponseHandler.generateResponse(otpModel,HttpStatus.OK,"SUCCESS");
            } else if ("failure".equals(status)) {
            	return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
            } else {
            	return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }
	
	@PostMapping("login/verify-otp/{inputOTP}")
	public ResponseEntity<?> loginVerify(@RequestBody OTPmodel otPmodel,@PathVariable String inputOTP) {
	     try {
	    	 String inputSessionID=otPmodel.getSession_id();
	    	 String inputActivity =otPmodel.getActivity();
	    	 System.out.println(inputActivity); 
	    	 System.out.println(inputSessionID); 
	    	 System.out.println(inputOTP.toString()); 
	    	 
	    	 //find the data from dao and match with otp
	    	// otpdao.
	    	 int count = otPdao.checkOtp( inputActivity, inputOTP, inputSessionID);
	    	 System.out.println(count);
	    	 if(count ==1) {
	    		 return ResponseHandler.generateResponse("VERIFIED",HttpStatus.OK,"SUCCESS");
	    	 }else {
	    		 return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
	    	 }
	    	 
	    	 
	     }catch(Exception e) {
	    	 return ResponseHandler.generateResponse(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST,e.toString());
	     }
	}

	@PostMapping("/register/send/{PhoneNumber}")
	public ResponseEntity<?> registerSend(@PathVariable String PhoneNumber) throws UnsupportedEncodingException {
		String phoneNumber = PhoneNumber;
		int x = dao.countOfThatNumber(phoneNumber);
		if(x==1) {
			return ResponseEntity.badRequest().body("NUMBER FAILED TO LOCATE");
		}
		String otp =OTPGenerator.generate6DigitCode();
		String message =URLEncoder.encode("Your TJN Club Membership Login OTP is "+ otp.toString() +". For any Queries & Orders Contact 9034034018- The Juice Nation.");
	    
		
		String otpSent = sendSMSCLASS.sendSmsNow(message, phoneNumber);
		
		String sessionId = UUID.randomUUID().toString();
		
		OTPmodel otpModel = new OTPmodel();
		otpModel.setStauts("GENERATED");
		otpModel.setOtp(otp);
		otpModel.setSession_id(sessionId);
		otpModel.setActivity("REGISTEROTP");
		otpModel.setPhoneNumber(phoneNumber);
		otPdao.save(otpModel);
		
	    System.out.println(otpSent);
	    	
		  String jsonString = otpSent;

	        // Create an ObjectMapper instance
	        ObjectMapper objectMapper = new ObjectMapper();
		try {
            // Convert the JSON string to a JSON object (in this case, a Map)
            Map<String, Object> jsonObject = objectMapper.readValue(jsonString, Map.class);

            // Access the "status" field
            String status = (String) jsonObject.get("status");

            // Check if the status is "success" or "failure"
            if ("success".equals(status)) {
            	return ResponseHandler.generateResponse(otpModel,HttpStatus.OK,"SUCCESS");
            } else if ("failure".equals(status)) {
            	return ResponseHandler.generateResponseNull(  "OTP FAILED TO SEND" ,HttpStatus.BAD_REQUEST);
            } else {
            	return ResponseHandler.generateResponseNull(  "ERROR" ,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
	}

	@PostMapping("register/verify-otp/{inputOTP}")
	public ResponseEntity<?> registerVerify(@RequestBody OTPmodel otPmodel, @PathVariable String inputOTP) {
		 try {
	    	 String inputSessionID=otPmodel.getSession_id();
	    	 String inputActivity =otPmodel.getActivity();
	    	 
	    	 //find the data from dao and match with otp
	    	// otpdao.
	    	 int count = otPdao.checkOtp(inputSessionID, inputOTP, inputActivity);
	    	 if(count ==1) {
	    		 return ResponseHandler.generateResponse("VERIFIED",HttpStatus.OK,"SUCCESS");
	    	 }else {
	    		 return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
	    	 }
	    	 
	    	 
	     }catch(Exception e) {
	    	 return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
	     }
	}

	@PostMapping("debit/{customerPhone}/{transaction_amount}/{availBalance}")
	public ResponseEntity<?> moneyDebitOtp(@PathVariable String customerPhone,@PathVariable float transaction_amount,@PathVariable float availBalance){
		
		
		String otp =OTPGenerator.generate6DigitCode();
		@SuppressWarnings("deprecation")
		String message =URLEncoder.encode(
				"Your OTP to Debit "
				+ transaction_amount
				+ " From your TJN Club Card is "
				+ otp
				+ ", Available Balance is "
				+ availBalance
				+ ". For any Queries & Orders Contact "
				+ "9034034018"
				+ "- The Juice Nation."
				);
		
		
		String otpSent = sendSMSCLASS.sendSmsNow(message, customerPhone);
		
String sessionId = UUID.randomUUID().toString();
		
		OTPmodel otpModel = new OTPmodel();
		otpModel.setStauts("GENERATED");
		otpModel.setOtp(otp);
		otpModel.setSession_id(sessionId);
		otpModel.setActivity("MONEYDEBITOTP");
		otpModel.setPhoneNumber(customerPhone);
		otPdao.save(otpModel);
		
	    System.out.println(otpSent);
	    	
		  String jsonString = otpSent;

	        // Create an ObjectMapper instance
	        ObjectMapper objectMapper = new ObjectMapper();
		try {
            // Convert the JSON string to a JSON object (in this case, a Map)
            Map<String, Object> jsonObject = objectMapper.readValue(jsonString, Map.class);

            // Access the "status" field
            String status = (String) jsonObject.get("status");

            // Check if the status is "success" or "failure"
            if ("success".equals(status)) {
            	return ResponseHandler.generateResponse(otpModel,HttpStatus.OK,"SUCCESS");
            } else if ("failure".equals(status)) {
            	return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
            } else {
            	return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
		
	}
	
	@PostMapping("debit/verify-otp/{inputOTP}")
	public ResponseEntity<?> moneyDebitOtpVerify(@RequestBody OTPmodel otPmodel, @PathVariable String inputOTP) {
		 try {
	    	 String inputSessionID=otPmodel.getSession_id();
	    	 String inputActivity =otPmodel.getActivity();
	    	 System.out.println(inputActivity +" "+ inputSessionID+ " "+ inputOTP );
	    	 //find the data from dao and match with otp
	    	// otpdao.
	    	 int count = otPdao.checkOtp(inputActivity, inputOTP, inputSessionID);
	    	 if(count ==1) {
	    		 return ResponseHandler.generateResponse("VERIFIED",HttpStatus.OK,"SUCCESS");
	    	 }else {
	    		 return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
	    	 }
	    	 
	    	 
	     }catch(Exception e) {
	    	 return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
	     }
	}

	@PostMapping("voucherredotp/{customerPhone}/{amountvoucher1}/{amountvoucher2}/{amountvoucher3}/{amountvoucher4}")
	public ResponseEntity<?> otpForRedemptionOfVoucher(@PathVariable String customerPhone,@PathVariable String amountvoucher1,@PathVariable String amountvoucher2,@PathVariable String amountvoucher3,@PathVariable String amountvoucher4){
		
		String otp =OTPGenerator.generate6DigitCode();
		@SuppressWarnings("deprecation")
		String message =URLEncoder.encode(
				"Your OTP to Redeem \r\n"
				//+ "%%|amountvoucher^{\"inputtype\" : \"text\", \"maxlength\" : \"10\"}%%\r\n" 
				+amountvoucher1
				+ "TJN Club Voucher is \r\n"
//				+ "%%|otp^{\"inputtype\" : \"text\", \"maxlength\" : \"6\"}%%\r\n"
				+otp
				+ ", Available Balance for \r\n"
//				+ "%%|amountvoucher^{\"inputtype\" : \"text\", \"maxlength\" : \"10\"}%%\r\n"
				+amountvoucher2
				+ "Voucher is \r\n"
//				+ "%%|vouchercount^{\"inputtype\" : \"text\", \"maxlength\" : \"10\"}%%.\r\n"
				+amountvoucher3
				+ " For Queries & Orders Contact \r\n"
//				+ "%%|storemobile^{\"inputtype\" : \"text\", \"maxlength\" : \"10\"}%%\r\n"
				+amountvoucher4
				+ "- The Juice Nation"
				);
		
		
		String otpSent = sendSMSCLASS.sendSmsNow(message, customerPhone);
		
String sessionId = UUID.randomUUID().toString();
		
		OTPmodel otpModel = new OTPmodel();
		otpModel.setStauts("GENERATED");
		otpModel.setOtp(otp);
		otpModel.setSession_id(sessionId);
		otpModel.setActivity("VOUCHERREDEMPTIONOTP");
		otpModel.setPhoneNumber(customerPhone);
		otPdao.save(otpModel);
		
	    System.out.println(otpSent);
	    	
		  String jsonString = otpSent;

	        // Create an ObjectMapper instance
	        ObjectMapper objectMapper = new ObjectMapper();
		try {
            // Convert the JSON string to a JSON object (in this case, a Map)
            Map<String, Object> jsonObject = objectMapper.readValue(jsonString, Map.class);

            // Access the "status" field
            String status = (String) jsonObject.get("status");

            // Check if the status is "success" or "failure"
            if ("success".equals(status)) {
            	return ResponseHandler.generateResponse(otpModel,HttpStatus.OK,"SUCCESS");
            } else if ("failure".equals(status)) {
            	return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
            } else {
            	return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
		
	}

	@PostMapping("voucherredverify/verify-otp/{inputOTP}")
	public ResponseEntity<?> voucherRedVerifyOtp(@RequestBody OTPmodel otPmodel, @PathVariable String inputOTP) {
		 try {
	    	 String inputSessionID=otPmodel.getSession_id();
	    	 String inputActivity =otPmodel.getActivity();
	    	 
	    	 //find the data from dao and match with otp
	    	// otpdao.
	    	 int count = otPdao.checkOtp(inputSessionID, inputOTP, inputActivity);
	    	 if(count ==1) {
	    		 return ResponseHandler.generateResponse("VERIFIED",HttpStatus.OK,"SUCCESS");
	    	 }else {
	    		 return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
	    	 }
	    	 
	    	 
	     }catch(Exception e) {
	    	 return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
	     }
	}

}
