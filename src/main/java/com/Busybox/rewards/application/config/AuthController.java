package com.Busybox.rewards.application.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.controller.ResponseHandler;
//import com.Busybox.rewards.application.role.Role;
import com.Busybox.rewards.application.security.CustomUserDetailService;
//import com.Busybox.rewards.application.security.CustomUserDetailService;
import com.Busybox.rewards.application.security.JwtHelper;
//import com.Busybox.rewards.application.security.User;
//import com.Busybox.rewards.application.security.UserRepository;
//import com.Busybox.rewards.application.security.UserServiceImpl;
import com.Busybox.rewards.application.security.UserModel;
import com.Busybox.rewards.application.security.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

	 @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private AuthenticationManager manager;


	    @Autowired CustomUserDetailService customUserDetailService;
	    
	    @Autowired
	    private JwtHelper helper;
	    
	    @Autowired UserService userService;

	    private Logger logger = LoggerFactory.getLogger(AuthController.class);

	    @PostMapping("/login")
	    public ResponseEntity<Object> login(@RequestBody JwtRequest request) {
	        this.doAuthenticate(request.getEmail(), request.getPassword());
	        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        UserModel usermodel = userService.getDetailsUsingEmail(userDetails.getUsername());
	        String storeId = usermodel.getStoreId();
	        Map<String, Object> additionalClaims = new HashMap<>();
	        additionalClaims.put("storeId", storeId);
	        String token = this.helper.generateTokenWithClaims(userDetails.getUsername(), additionalClaims);

//	        String token = this.helper.generateToken(userDetails);
	        JwtResponse response = JwtResponse.builder()
	                .jwtToken(token)
	                .username(userDetails.getUsername()).build();
	        return ResponseHandler.generateResponse(response, HttpStatus.OK, "Success");
	    }

	    private void doAuthenticate(String email, String password) {

	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	        try {
	            manager.authenticate(authentication);


	        } catch (BadCredentialsException e) {
	            throw new BadCredentialsException(" Invalid Username or Password  !!");
	        }
	    }

	    @ExceptionHandler(BadCredentialsException.class)
	    public ResponseEntity<?> exceptionHandler() {
	        return ResponseHandler.generateResponse("Credentials Invalid !!", HttpStatus.BAD_REQUEST, "FAILEED");
	    }
	    
	    @PostMapping("/createUser")
	    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
	    		try {
	    			return ResponseHandler.generateResponse(userService.createUser(user), HttpStatus.OK, "Success");
	    		}catch(Exception e) {
	    			return ResponseHandler.generateResponse(e.toString(), HttpStatus.BAD_REQUEST, "Failed");
	    		}
	    }
	    
//	   @GetMapping("getRoles") 
//	   public ResponseEntity<?> getRoles(){
//		  String allRole= Role.getAllRolesAsString();
//		   return ResponseHandler.generateResponse(allRole, HttpStatus.OK, "ok");
//	   }
	    
	    
	    
	    @GetMapping("/getall")
	    public ResponseEntity<?> getall(){
	    	try {
    			return ResponseHandler.generateResponse(userService.getAllUsers(), HttpStatus.OK, "Success");
    		}catch(Exception e) {
    			return ResponseHandler.generateResponse("FAILEED", HttpStatus.BAD_REQUEST, "Failed");
    		}
	    }
}
