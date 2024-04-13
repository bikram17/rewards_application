package com.Busybox.rewards.application.customerApi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.CustomerRepository;
import com.Busybox.rewards.application.dao.Tjn_login_dao;
import com.Busybox.rewards.application.impl.CustomerServiceImpl;
import com.Busybox.rewards.application.impl.Tjn_login_ServiceImpl;
import com.Busybox.rewards.application.impl.parentCodeMasterImpl;
import com.Busybox.rewards.application.impl.referral_model_master_balance_impl;
import com.Busybox.rewards.application.impl.tjn_cards_service_impl;
import com.Busybox.rewards.application.model.CustomerModel;
import com.Busybox.rewards.application.otp.OTPdao;
import com.Busybox.rewards.application.otp.OTPmodel;
import com.Busybox.rewards.application.security.JwtHelper;
import com.Busybox.rewards.application.security.UserModel;
import com.Busybox.rewards.application.security.UserRepository;
import com.Busybox.rewards.application.service.CustomerService;
import com.Busybox.rewards.application.sms.sendSMS;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("customer/open-api")
public class CustomerAccessController {

	@Autowired private Tjn_login_dao dao;
	@Autowired private CustomerRepository Customerrepository;
	@Autowired private CustomerService customerService;
	@Autowired private CustomerRepository repo;
	@Autowired private OTPdao otPdao;
	@Autowired private UserRepository userRepository;
	@Autowired private Tjn_login_ServiceImpl impl;
	@Autowired private sendSMS sendSMSCLASS;
	@Autowired private JwtHelper helper;
	@Autowired private referral_model_master_balance_impl REFFimpl;
	@Autowired private tjn_cards_service_impl cardimpl;
	@Autowired private parentCodeMasterImpl parentcodeimpl;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("login/send")
	public ResponseEntity<?> loginSend(@RequestBody Map<String,String> input) {
			
		String phoneNumber = input.get("PhoneNumber");
		int x = dao.countOfThatNumber(phoneNumber);
		if(x!=1) {
			return ResponseHandler.generateResponse("NUMBER FAILED TO LOCATE", HttpStatus.BAD_REQUEST, "FAILED");
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
	
	@PostMapping("login/verify-otp/{inputOTP}/{phoneNumber}")
	public ResponseEntity<?> loginVerify(@RequestBody OTPmodel otPmodel,@PathVariable String inputOTP, @PathVariable String phoneNumber) {
	     try {
	    	 String inputSessionID=otPmodel.getSession_id();
	    	 String inputActivity =otPmodel.getActivity();
	    	 System.out.println(inputActivity); 
	    	 System.out.println(inputSessionID); 
	    	 System.out.println(inputOTP.toString()); 
	    	 OTPmodel  dbModel =otPdao.findModelfromDB(inputSessionID, inputActivity,inputOTP);
	    	 //find the data from dao and match with otp
	    	// otpdao.
	    	 int count = otPdao.checkOtp( inputActivity, inputOTP, inputSessionID);
	    	 System.out.println("Count "+ count);
	    	 if(count ==1) {
	    		 
	    		 CustomerModel customer = new CustomerModel();
	    		 System.out.println("Customer "+ customer);
	    		 customer = Customerrepository.findbyPhoneNumber(phoneNumber);
	    		 System.out.println("Customer "+ customer);
	    		 System.out.println("PhoneNumber "+ phoneNumber);
	    		 String token = this.helper.generateTokenForCustomer(customer.getCustomer_email());
	    		 System.out.println("token "+ token);
	    		 otPmodel.setStauts("VERIFIED");
	    		 System.out.println("OTP         -"+dbModel.getId());
//	    		 otPdao.changeStatusAfterVerificationDone(dbModel.getId());
	    		 String name = customer.getCustomer_name();
	    		 return ResponseHandler.generateResponseOfToken(phoneNumber,token,name,HttpStatus.OK,"SUCCESS");
	    	 }else {
	    		 
	    		 return ResponseHandler.generateResponseNull(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST);
	    	 }
	    	 
	    	 
	     }catch(Exception e) {
	    	 return ResponseHandler.generateResponse(  "OTP FAILED TO VERIFY" ,HttpStatus.BAD_REQUEST,e.toString());
	     }
	}
	
	@GetMapping("hello")
	public ResponseEntity<?> hello(){
		return ResponseHandler.generateResponse(this.helper.generateTokenForCustomer("demonTesting@gmail.com"), HttpStatus.OK, "SECURE");
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

	@PostMapping("new/addCustomer")
	public ResponseEntity<?> saveCustomerModelNEW(@RequestBody CustomerModel customerModel) {
	    try {
	        if (customerModel.getCustomer_mobno().length() != 10) {
	            return ResponseHandler.generateResponse("Mobile Number should be in 10 digits", HttpStatus.BAD_REQUEST, "Invalid Input");
	        }
	        char firstDigit = customerModel.getCustomer_mobno().charAt(0);
	        if (firstDigit >= '6' && firstDigit <= '9') {
	            return ResponseHandler.generateResponse("First Digit of the mobile number must be in between 6 and 9", HttpStatus.BAD_REQUEST, "Invalid Input");
	        }

	        processReferralCode(customerModel);

	        CustomerModel newCustomerModel = customerService.saveCustomerModel(customerModel);

	        String email = newCustomerModel.getCustomer_mobno();
	        repo.addInLogin(email);

	        int customer_id = newCustomerModel.getCustomer_id();
	        cardimpl.addWallets(customer_id);
	        String phone = newCustomerModel.getCustomer_mobno();

	        parentcodeimpl.assignCodeToParent(customer_id, phone, newCustomerModel.getCustomer_name(), customerModel.getStoreId());

	        
	        String token = this.helper.generateTokenForCustomer(newCustomerModel.getCustomer_email());
	        return ResponseHandler.generateResponseForCustomerID(token, HttpStatus.OK,
	        		"Created: Customer. Customer Email: " 
	        + email + 
	        ". Wallets 1 and 2 created with 0 balance"
	        );
	    } catch (Exception e) {
	        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "Customer Registration Failed");
	    }
	}

	private String processJwtTokenAndGetStoreId(String jwtToken) {
	    String storeId = "ADMIN";
	    if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
	        jwtToken = jwtToken.substring(7);
	        Claims claims = helper.getAllClaimsFromToken(jwtToken);
	        String username = claims.get("sub", String.class);
	        UserModel userModel = userRepository.findByEmail(username).orElse(null);
	        if (userModel != null) {
	            storeId = userModel.getStoreId();
	        }
	        System.out.println("User " + username + " accessed the API.");
	    }
	    return storeId;
	}

	private void processReferralCode(CustomerModel customerModel) {
	    String referralCodeOld = customerModel.getParentId();
	    String[] output = REFFimpl.testID(referralCodeOld);
	    if ("PASSED".equalsIgnoreCase(output[0])) {
	        customerModel.setParentId(output[1]);
	    } else {
	        customerModel.setParentId(null);
	    }
	}

}
