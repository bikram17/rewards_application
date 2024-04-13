    package com.Busybox.rewards.application.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.dao.CustomerRepository;
import com.Busybox.rewards.application.dao.StoreRepository;
import com.Busybox.rewards.application.dao.referral_model_master_balance_dao;
import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;
import com.Busybox.rewards.application.dto.Final_Response_All_User_Details;
import com.Busybox.rewards.application.impl.CustomerServiceImpl;
import com.Busybox.rewards.application.impl.parentCodeMasterImpl;
import com.Busybox.rewards.application.impl.referral_model_master_balance_impl;
import com.Busybox.rewards.application.impl.tjn_cards_service_impl;
import com.Busybox.rewards.application.model.CustomerModel;
import com.Busybox.rewards.application.security.JwtHelper;
import com.Busybox.rewards.application.security.UserModel;
import com.Busybox.rewards.application.security.UserRepository;
import com.Busybox.rewards.application.service.CustomerService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired private CustomerService customerService;
	@Autowired private CustomerRepository repo;
	@Autowired private tjn_wallet_types_dao tdao;
	@Autowired private referral_model_master_balance_dao rmmbDao;
	@Autowired private UserRepository userRepository;
	@Autowired private tjn_cards_service_impl cardimpl;
	@Autowired private CustomerServiceImpl cusimpl;	
	@Autowired private parentCodeMasterImpl parentcodeimpl;
	@Autowired private referral_model_master_balance_impl REFFimpl;
	@Autowired private StoreRepository storeRepository;
	
	 JwtHelper jwtHelper = new JwtHelper();
		List<Long> ListstoreIds;
	@GetMapping("/ok")
	public String abc(){
		return tdao.walletNameFromId(2);
	}
	
	@GetMapping("/some-api")
	public ResponseEntity<String> someApi(HttpServletRequest request) {
	    String jwtToken = request.getHeader("Authorization");
	    if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
	        jwtToken = jwtToken.substring(7); // Remove "Bearer " prefix
	        Claims claims = jwtHelper.getAllClaimsFromToken(jwtToken);
	        System.out.println(claims);
	        String username = claims.get("sub", String.class);
	        System.out.println("User " + username + " accessed the API.");
	        return ResponseEntity.ok("API Response");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
	    }
	}

	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping()
	public ResponseEntity<?> abaasdasdasdc(){
		return ResponseHandler.generateResponseNull("HELLO WORLD ", HttpStatus.OK); 
	}
	
	@GetMapping("/hello3")
	public ResponseEntity<?> hello3(){
		return ResponseHandler.generateResponseNull("HELLO WORLD 3", HttpStatus.OK); 
	}
	@GetMapping("/hello2")
	
	public ResponseEntity<?> hello2(){
		return ResponseHandler.generateResponseNull("HELLO WORLD 2", HttpStatus.OK); 
	}
	@PutMapping("/customeredit/{customer_phoneNumber}")
	public ResponseEntity<Object> editcustomer101(@RequestBody CustomerModel customerModel,@PathVariable String customer_phoneNumber  ) {
			
			try {Integer customer_id= repo.getcustomerID(customer_phoneNumber);

			return ResponseHandler.generateResponse(customerService.editcustomer(customerModel,customer_id),HttpStatus.OK, "Successfully Customer Data Changed");}
			catch(Exception  e) {
				return ResponseHandler.generateResponseNull("Failed", HttpStatus.BAD_REQUEST);
			}
		}
	
	@GetMapping("/test123/{email}")
	public ResponseEntity<Object> abcdefg(@PathVariable String email) {
	//	rmmbDao.getIdUsingReferralCode(email);
		if(!email.equalsIgnoreCase(null)) {return ResponseHandler.generateResponse(rmmbDao.getIdUsingReferralCode(email), HttpStatus.OK	, email);}
		else {
			return ResponseHandler.generateResponse(rmmbDao.getIdUsingReferralCode(email), HttpStatus.BAD_REQUEST	, "not accepted");
		}
	}
	
	@GetMapping("/allcustomer")
	public ResponseEntity<Object> getAll(HttpServletRequest request){
		String jwtToken = request.getHeader("Authorization");
	    if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
	    	String storeId ;
 	        jwtToken = jwtToken.substring(7); 
	        Claims claims = jwtHelper.getAllClaimsFromToken(jwtToken);
	        System.out.println(claims);
	        String username = claims.get("sub", String.class);
	        UserModel userModel= userRepository.findByEmail(username).orElse(null);
	        if(userModel.getRoleName().equalsIgnoreCase("SUPER_ADMIN") || userModel.getRoleName().equalsIgnoreCase("ADMIN")) {
	        	return ResponseHandler.generateResponse(repo.findAll(), HttpStatus.OK, "SUCCESS");
	        }
	        storeId = userModel.getStoreId();
	        ListstoreIds =storeRepository.getListOfAllStores(storeId);
	        System.out.println("User " + username + " accessed the API.");
	        return ResponseHandler.generateResponse(customerService.getAllCustomerList(ListstoreIds), HttpStatus.OK, "Successfully Fetched From Database");
	    } else {
	        return ResponseHandler.generateResponse("ew", HttpStatus.UNAUTHORIZED,"FAILED");
	    }
	}

	@GetMapping("/getAllDetailsOf/{customer_id}")
	public ResponseEntity<Object> getCustomerById(@PathVariable Integer customer_id) {
//		return customerService.getCustomerById(customer_id);
		return ResponseHandler.generateResponse(customerService.getCustomerById(customer_id), HttpStatus.OK, "Successfully Fetched From Database");
//		CustomerModel cusModel =customerService.getCustomerById(customer_id);
//		String email = cusModel.getCustomer_email();
//		return ResponseHandler.generateResponse(email, HttpStatus.OK, "Successfully Email From Database");
	}

	@GetMapping("getDetails/{phone_number}")
	public ResponseEntity<?> getCustomerByPhoneNumber(@PathVariable String phone_number) {
    	//CustomerModel customer = customerService.getCustomerByPhoneNumber(phone_number);
    	
       try {
    	   CustomerModel customer = customerService.getCustomerByPhoneNumber(phone_number);
			  
			  System.out.println(customer);
			 
            if (customer != null) {
                return ResponseHandler.generateResponse(customer, HttpStatus.OK, "Customer Fetched Successfully");
            } else {
                return ResponseHandler.generateResponseNull("Customer Number Not Found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }
    }
	
	
	@PutMapping("/customer/{customer_id}")
	public ResponseEntity<Object> editcustomer(@RequestBody CustomerModel customerModel,@PathVariable Integer customer_id  ) {
		
		return ResponseHandler.generateResponse(customerService.editcustomer(customerModel,customer_id),HttpStatus.OK, "Successfully Customer Data Changed");
	}
	    
	@PutMapping("/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable int customerId, @RequestBody CustomerModel updatedCustomer) {
        // Retrieve the existing customer from the database
		 CustomerModel existingCustomer = customerService.getCustomerById(customerId);

		    if (existingCustomer == null) {
		        return ResponseEntity.notFound().build();
		    }
       //         CustomerModel existingCustomer = existingCustomerOptional.get();

        // Update only the fields that are provided in the request
        if (updatedCustomer.getCustomer_name() != null) {
            existingCustomer.setCustomer_name(updatedCustomer.getCustomer_name());
        }
        if (updatedCustomer.getCustomer_mobno() != null) {
            existingCustomer.setCustomer_mobno(updatedCustomer.getCustomer_mobno());
        }
        if (updatedCustomer.getCustomer_email() != null) {
            existingCustomer.setCustomer_email(updatedCustomer.getCustomer_email());
        }
        if (updatedCustomer.getCustomer_address() != null) {
            existingCustomer.setCustomer_address(updatedCustomer.getCustomer_address());
        }
        if (updatedCustomer.getCity() != null) {
            existingCustomer.setCity(updatedCustomer.getCity());
        }
        if (updatedCustomer.getState() != null) {
            existingCustomer.setState(updatedCustomer.getState());
        }
        if (updatedCustomer.getPincode() != null) {
            existingCustomer.setPincode(updatedCustomer.getPincode());
        }
        if (updatedCustomer.getOrgId() != null) {
            existingCustomer.setOrgId(updatedCustomer.getOrgId());
        }
        if (updatedCustomer.getStatus() != null) {
            existingCustomer.setStatus(updatedCustomer.getStatus());
        }
        if (updatedCustomer.getUpdatedOn() != null) {
            existingCustomer.setUpdatedOn(updatedCustomer.getUpdatedOn());
        }
        if (updatedCustomer.getLoginStatus() != null) {
            existingCustomer.setLoginStatus(updatedCustomer.getLoginStatus());
        }
        if (updatedCustomer.getCreatedBy() != null) {
            existingCustomer.setCreatedBy(updatedCustomer.getCreatedBy());
        }
        if (updatedCustomer.getUpdatedBy() != null) {
            existingCustomer.setUpdatedBy(updatedCustomer.getUpdatedBy());
        }
        if (updatedCustomer.getStoreId() != null) {
            existingCustomer.setStoreId(updatedCustomer.getStoreId());
        }
        if (updatedCustomer.getCreatedOn() != null) {
            existingCustomer.setCreatedOn(updatedCustomer.getCreatedOn());
        }
        if (updatedCustomer.getParentId() != null) {
            existingCustomer.setParentId(updatedCustomer.getParentId());
        }
        
        if (updatedCustomer.getPackage_Id() != null) {
            existingCustomer.setParentId(updatedCustomer.getParentId());
        }

        // Save the updated customer back to the database
        repo.save(existingCustomer);

        return ResponseEntity.ok(existingCustomer);
    }


	/**
	 * 
	 * old no use 
	 * @return
	 */
	@Deprecated
	@PostMapping("new/addCustomer/old")
	public ResponseEntity<?> saveCustomerModel(@RequestBody CustomerModel customerModel) {
	//	String abc = customerModel.toString();
		
	    try {
	    	String referralCodeOld =customerModel.getParentId();
	    	String[] output =REFFimpl.testID(referralCodeOld);
	    	if(output[0].equalsIgnoreCase("PASSED")) {
	    		customerModel.setParentId(output[1]);
	    	}else if(output[0].equalsIgnoreCase("FAILED")) {
	    		customerModel.setParentId(null);
	    	}
	    	else {
	    		customerModel.setParentId(null);
	    	}
	    	
	    	
	        // Save the customer model
	        CustomerModel newCustomerModel = customerService.saveCustomerModel(customerModel);
	        // parent id nicchi then all calculations, also do 2 things check if they even have an id or not and then check what is the below given 
	        // id 
	       
	        // Extract customer email
	       
	       
	       String email = newCustomerModel.getCustomer_email();
//	        String referralCode =newCustomerModel.getParentId();
	        // Add the email to a repository (assuming 'repo' is your repository)
	        repo.addInLogin(email);

	        // Extract customer ID
	        int customer_id = newCustomerModel.getCustomer_id();
	       
	        // Call cardimpl.addIfExists2() method with customer_id
	        //cardimpl.addIfExists2(customer_id);

	        // Return a success response with the email cardimpl.addWallets(customer_id)
	        cardimpl.addWallets(customer_id);
	        String phone =newCustomerModel.getCustomer_mobno();
	        
	        
	        
//	        rmmbDao.getIdUsingReferralCode(referralCode);
	      ///  System.out.println(rmmbDao.getIdUsingReferralCode(referralCode));
	        // after successfully adding the customer now we create the parent code in a new table
//	        parentcodeimpl.assignCodeToParent(customer_id, phone, newCustomerModel.getCustomer_name());
	        
	      
	        
	        
	        return ResponseHandler.generateResponseForCustomerID(phone, HttpStatus.OK,"Created :Customer. Customer Email : "+email+".  Wallet 1 and 2 created with 0 balance" );
//	}
	        	    } catch (Exception e) {
	        // Handle exceptions and return an error response
	        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,"Customer Registration Failed");
	    }
	}
//	
	@GetMapping("/testingapi/{Customer_id}")
	public ResponseEntity<?> testing(@PathVariable int Customer_id ){
//		cardimpl.addWallets(Customer_id)
		try {
//			cardimpl.addIfExists2(Customer_id);
		return ResponseHandler.generateResponse(cardimpl.addIfExists2(Customer_id), HttpStatus.OK, "Added Successfully");
	}catch(Exception e) {
		return ResponseHandler.generateResponse(cardimpl.addWallets(Customer_id), HttpStatus.I_AM_A_TEAPOT, "Successfully Failed");
	}
		}
	
	//un callable
	@DeleteMapping("/customer2u3rvbyhn9ow485yuir/{customer_id}")
	public void deleteCustomerModel(@PathVariable Integer customer_id) {   
		customerService.deleteCustomerModel(customer_id);
	}
	  
	@PostMapping("/oneforall")
	public ResponseEntity<?> customResponse(@RequestBody Map<String, String> input){
		
			try {String mobileNo = input.get("PhoneNumber");
			Final_Response_All_User_Details fraud = cusimpl.fraudxd(mobileNo);
			//return fraud;
			
			if(fraud != null)
				return ResponseHandler.generateResponse(fraud, HttpStatus.OK, "Fetched From db");
			else  
				return ResponseHandler.generateResponse(fraud, HttpStatus.NOT_FOUND, "Fetched From db");
			}catch(Exception e) {
				return ResponseHandler.generateResponseNull(input, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		 
	} 
	
	@PostMapping("new/addCustomer")
	public ResponseEntity<?> saveCustomerModelNEW(@RequestBody CustomerModel customerModel, HttpServletRequest request) {
	    try {
	    	String jwtToken = request.getHeader("Authorization");
	    	
	    	if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
		    	String storeId = "ADMIN";
	 	        jwtToken = jwtToken.substring(7); 
		        Claims claims = jwtHelper.getAllClaimsFromToken(jwtToken);
		        System.out.println(claims);
		        String username = claims.get("sub", String.class);
		        
		        UserModel userModel= userRepository.findByEmail(username).orElse(null);
		        storeId = userModel.getStoreId();
		        System.out.println("User " + username + " accessed the API.");
		        customerModel.setCreatedBy(username);
		        customerModel.setStoreId(storeId);
		        
	    	}
	    	else {
	    		//give logic for default or keep it null and laster change it default entity
	    		customerModel.setStoreId(null);
	    	}
	    	
	    	
	    	String customer_mobno = customerModel.getCustomer_mobno();
	    	String status = "ACTIVE";
	    	customerModel.setStatus(status);
	    	//-------------
	    	System.out.println(customer_mobno);
	    	if(customer_mobno.length()!=10) {
	    		return ResponseHandler.generateResponse("Mobile Number shuold be in 10 digits",HttpStatus.BAD_REQUEST,"Invalid Input");
	    	}
	    	char firstDigit = customer_mobno.charAt(0);
	    	System.out.println(firstDigit);
			
	    	if (firstDigit >= 6 && firstDigit <=9) {
	    	    return ResponseHandler.generateResponse("First Digit of the mobile no must be in between 6 and 9", HttpStatus.BAD_REQUEST, "Invalid Input");
	    	} else {
	    		
	    		String referralCodeOld =customerModel.getParentId();
		    	String[] output =REFFimpl.testID(referralCodeOld);
		    	if(output[0].equalsIgnoreCase("PASSED")) {
		    		customerModel.setParentId(output[1]);
		    	}else if(output[0].equalsIgnoreCase("FAILED")) {
		    		customerModel.setParentId(null);
		    	}
		    	else {
		    		customerModel.setParentId(null);
		    	}
	    	
		        CustomerModel newCustomerModel = customerService.saveCustomerModel(customerModel);
		        
		        // Extract customer email
		        String email = newCustomerModel.getCustomer_mobno();
		        
		        // Add the email to a repository (assuming 'repo' is your repository)
		        repo.addInLogin(email);

		        // Extract customer ID
		        int customer_id = newCustomerModel.getCustomer_id();
		        
		        // Call cardimpl.addIfExists2() method with customer_id
		        //cardimpl.addIfExists2(customer_id);

		        // Return a success response with the email cardimpl.addWallets(customer_id)
		        cardimpl.addWallets(customer_id);
		        String phone =newCustomerModel.getCustomer_mobno();
		        
		        parentcodeimpl.assignCodeToParent(customer_id, phone,newCustomerModel.getCustomer_name(),customerModel.getStoreId());
		        
		        
		        
		        return ResponseHandler.generateResponseForCustomerID(phone, HttpStatus.OK,"Created :Customer. Customer Email : "+email+".  Wallet 1 and 2 created with 0 balance" );
	    	}
			 
	    	
	    	
	    } catch (Exception e) {
	        // Handle exceptions and return an error response
	        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,"Customer Registration Failed");
	    }
	}
	
}	