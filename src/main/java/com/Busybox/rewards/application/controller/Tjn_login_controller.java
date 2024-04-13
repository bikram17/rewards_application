package com.Busybox.rewards.application.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.config.OTPGenerator;
import com.Busybox.rewards.application.config.sendSMS;
import com.Busybox.rewards.application.dao.Tjn_login_dao;
import com.Busybox.rewards.application.impl.Tjn_login_ServiceImpl;
import com.Busybox.rewards.application.model.tjn_Login_model;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

//import busybox.tjn.dao.tjn_Login_dao;
//import busybox.tjn.impl.tjn_Login_Impl;
//import busybox.tjn.model.tjn_Login_model;
@CrossOrigin("*")
@RequestMapping("customer/login")

@RestController

public class Tjn_login_controller 
{
	@Autowired Tjn_login_dao dao;
	@Autowired Tjn_login_ServiceImpl impl;
	@Autowired sendSMS sendSMSCLASS;
	private final Map<String, String> otpCache = new HashMap<>();
@GetMapping("/hello")
   public ResponseEntity<Object> abcd()
   {
	
	   return ResponseHandler.generateResponseNull("Hello", HttpStatus.OK);
   }
	
//	@GetMapping("/all")
//	public List<tjn_Login_model> acbd()  
//  {
//		//List<tjn_Login_model> a = dao.findAll();
//		
//		return this.dao.findAll();
//	}

@GetMapping("/useronly")
@JsonFilter("passwordFilter")
public ResponseEntity<?> abc(){
	
	
	// Create a SimpleFilterProvider
    SimpleFilterProvider filterProvider = new SimpleFilterProvider();
    // Add a filter for the 'customerFilter' (this should match the filter name you specified in @JsonFilter)
    filterProvider.addFilter("passwordFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id", "email"));
    List<tjn_Login_model> loginmodel = dao.findAll();
    // Apply the filter to the response
    MappingJacksonValue mapping = new MappingJacksonValue(loginmodel);
    mapping.setFilters(filterProvider);

    return new ResponseEntity<>(mapping, HttpStatus.OK);
	//return dao.findAll();
}

	
@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Map<String,String> userInfo) {
//	OTP for Login	Transactional	TJUICN	Your TJN Club Membership Login OTP is {#var#}. For any Queries & Orders Contact 9034034018.
		return impl.Login(userInfo);
	}
	
@PostMapping("/sendotp123")
public ResponseEntity<String> sendOtp123(@RequestBody Map<String,String> input) {
	
	//if phone Number doesn't exists
	
	try {
		//if the customer exists
		
		
		String phoneNumber = input.get("PhoneNumber");
		
		int x = dao.countOfThatNumber(phoneNumber);
		
		if(x!=1) {
			return ResponseEntity.badRequest().body("NUMBER FAILED TO LOCATE");
		}
		
		
		
		String otp =OTPGenerator.generate6DigitCode();
		String message =URLEncoder.encode("Your TJN Club Membership Login OTP is "+ otp.toString() +". For any Queries & Orders Contact 9034034018- The Juice Nation.",java.nio.charset.StandardCharsets.UTF_8.toString());
	    String otpSent = sendSMSCLASS.sendSmsNow(message, phoneNumber);
	    dao.updateTheOtp(otp, phoneNumber);
	    System.out.println(otpSent);
	    return ResponseEntity.ok(otpSent);		
	}catch(Exception e) {
		return ResponseEntity.badRequest().body("NUMBER FAILED TO LOCATE");
	}
}

@PostMapping("/sendotp")
public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> input) {
    try {
        String phoneNumber = input.get("PhoneNumber");
        String otp = OTPGenerator.generate4DigitCode();
        
        String messageTemplate = "Your TJN Club Membership Login OTP is %%|otp^{\"inputtype\" : \"text\", \"maxlength\" : \"6\"}%%. For any Queries & Orders Contact 9034034018- The Juice Nation.";
       System.out.println(messageTemplate);
        String encodedMessage = String.format(messageTemplate, otp);

        // Send the encoded message via SMS
        String response = sendSMSCLASS.sendSmsNow(encodedMessage, phoneNumber);
        
        System.out.println("OTP Sent: " + otp);
        dao.updateTheOtp(otp, phoneNumber);
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Failed to send OTP");
    }
}




@PostMapping("/verify-otp/{phoneNumber}/{enteredOtp}")
public ResponseEntity<?> verifyOtp(@PathVariable String phoneNumber, @PathVariable int enteredOtp) {
    // Verify the OTP
    boolean isOtpValid = false;
    
    String daoPass = dao.existingOtp(phoneNumber);
    int otp = Integer.parseInt(daoPass);
    if(otp == enteredOtp) {
    	isOtpValid = true;
    }
    

    if (isOtpValid) {
        // User is authenticated, you can generate a token or perform login actions here.
//        return ResponseEntity.ok("OTP verified successfully");
        return ResponseHandler.generateResponse(phoneNumber, HttpStatus.OK, "OTP verified successfully");
    } else {
//        return ResponseEntity.badRequest().body("OTP verification failed");
        return ResponseHandler.generateResponse(daoPass + "    otp", HttpStatus.BAD_REQUEST, "OTP FAILED TO VERIFY");
        
    }
    
}




@PostMapping("/register/{PhoneNumber}")
public ResponseEntity<?> register(@PathVariable String PhoneNumber) throws UnsupportedEncodingException {
	
    try {
    		int x = dao.countOfThatNumber(PhoneNumber);
		
		if(x==1) {
			return ResponseHandler.generateResponse(PhoneNumber, HttpStatus.BAD_REQUEST, "NUMBER ALREADY EXISTS");
		}
    	String otp = OTPGenerator.generate6DigitCode();
        String message =URLEncoder.encode("Your TJN Club Membership Login OTP is "+ otp.toString() +". For any Queries & Orders Contact 9034034018- The Juice Nation.",java.nio.charset.StandardCharsets.UTF_8.toString());
        String otpSent = sendSMSCLASS.sendSmsNow(message, PhoneNumber);
        otpCache.put(PhoneNumber, otp);

        if(otpSent.contains("Error SMS ")) {
        	return ResponseHandler.generateResponse(otpSent, HttpStatus.BAD_GATEWAY, "FAILED");
        }else {
        	return ResponseHandler.generateResponse(otpSent, HttpStatus.OK, "Success");
        }
    }catch(Exception e) {
    	return ResponseHandler.generateResponse(e.toString(), HttpStatus.BAD_REQUEST, "FAILED");
    }
}



@PostMapping("/register-verify/{phoneNumber}/{submittedOTP}")
public ResponseEntity<?> verify(@PathVariable String phoneNumber, @PathVariable String submittedOTP) {
    try {
        // Retrieve the stored OTP
        String storedOTP = otpCache.get(phoneNumber);
        
        if (storedOTP != null && storedOTP.equals(submittedOTP)) {
            // If the OTP is valid, remove it from cache
            otpCache.remove(phoneNumber);
            return ResponseHandler.generateResponse("Phone number verified", HttpStatus.OK, "Success");
        } else {
            return ResponseHandler.generateResponse("Invalid OTP",HttpStatus.BAD_REQUEST,"FAILED");
        }
    } catch (Exception e) {
        return ResponseHandler.generateResponse("An error occurred during OTP verification",HttpStatus.INTERNAL_SERVER_ERROR,"FAILED");
    }
}































//private String generateRandomOtp() {
//    // Generate a random 6-digit OTP
//    Random random = new Random();
//    int otp = 100000 + random.nextInt(900000);
//    return String.valueOf(otp);
//}
//String abc = generateRandomOtp();
//System.out.println(abc);
}
