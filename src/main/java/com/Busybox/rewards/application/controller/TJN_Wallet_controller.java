package com.Busybox.rewards.application.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

import com.Busybox.rewards.application.dao.Tjn_Wallet_Dao;
import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;
import com.Busybox.rewards.application.model.tjn_Wallet_types_model;
import com.Busybox.rewards.application.security.JwtHelper;
import com.Busybox.rewards.application.security.UserModel;
import com.Busybox.rewards.application.security.UserRepository;
import com.Busybox.rewards.application.service.TJN_Wallet_controller_Service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
//import com.Busybox.rewards.application.dao.Tjn_walletMaster_dao;
//import com.Busybox.rewards.application.model.Tjn_walletMaster_model;
//import com.Busybox.rewards.application.service.Tjn_walletMaster_service;
@CrossOrigin("*")
@RestController
@RequestMapping("api/wallets")
public class TJN_Wallet_controller 
{
@Autowired private Tjn_Wallet_Dao walletdao;
@Autowired private TJN_Wallet_controller_Service walletService;
@Autowired private tjn_wallet_types_dao wallettypedao;
@Autowired UserRepository userRepository;

				JwtHelper jwtHelper = new JwtHelper();
			
				@GetMapping("/vouchar")
		public ResponseEntity<Object> allwallets(HttpServletRequest request)
				{
		 String jwtToken = request.getHeader("Authorization");
		 if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
		        jwtToken = jwtToken.substring(7); // Remove "Bearer " prefix
		        String storeId = null;

		        // Call the non-static method on the instance
		        Claims claims = jwtHelper.getAllClaimsFromToken(jwtToken);

		        System.out.println(claims);
		        String username = claims.get("sub", String.class);

		        System.out.println("User " + username + " accessed the API.");
		        UserModel userModel= userRepository.findByEmail(username).orElse(null);
		        storeId = userModel.getStoreId();
		        if(storeId == null) {
		        return ResponseHandler.generateResponse(walletdao.findAllonlyvouchar(),HttpStatus.OK,"Successfully Fetched From Database");}
		        else {
		        	return ResponseHandler.generateResponse(walletdao.findAllonlyvoucharStoreDependent(storeId),HttpStatus.OK,"Successfully Fetched From Database");
		        }
		    }	else {
		    	return ResponseHandler.generateResponse("ew", HttpStatus.I_AM_A_TEAPOT, "FAILED");
		    }
		 }
	/*@GetMapping("/alldata")
	public ResponseEntity<?>alldata(){
		return ResponseHandler.generateResponse(walletService.getAlldata(),HttpStatus.OK, "Successfully fetched");
	}*/
 
	@GetMapping("/allwallets/{customer_mobno}")
	public ResponseEntity<Object> getCustomerWalletData(@PathVariable String customer_mobno) {
	    
	       List<Object[]> objectList = walletdao.getCustomerData(customer_mobno);
			    
			    
			    
			    if (objectList.isEmpty()) {
			        Map<String, Object> errorResponse = new HashMap<>();
//			       return ("Person does not exist");
			        //errorResponse.print("Person does not exist");
			        errorResponse.put("error", "Person does not exist");
			        //Collections.singletonList(errorResponse)
			        return ResponseHandler.generateResponse(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY,"CUSTOMER NOT FOUND");
			    }
			  
//			  
			    List<Map<String, Object>> jsonList = new ArrayList<>();
			    
			    for (Object[] values : objectList) {
			    	Integer customer_id = (Integer) values[0];
			        String customer_mobile = (String) values[1];
			        String wallet_id = (String) values[2];
			        String wallet_name = (String) values[4];
			        String wallet_balance  = (String) values[3];
			       /// String customer_name = (String)values[5];
			        //String customer_name  = (String) values[2];
			  
			        Map<String, Object> jsonItem = new HashMap<>();
			        jsonItem.put("wallet_id", wallet_id);
			        jsonItem.put("customer_id", customer_id);
			        //jsonItem.put("customer_name", customer_name);
			        jsonItem.put("customer_mobile", customer_mobile);
			        jsonItem.put("wallet_name", wallet_name);
	 	            jsonItem.put("wallet_balance", wallet_balance);
			        //jsonItem.put("customer_name", customer_name);

			        jsonList.add(jsonItem);
			    }

			    return ResponseHandler.generateResponse(jsonList,HttpStatus.OK,"CUSTOMER EXIST");
			}

		

//\\
	@GetMapping("/mywallets/{customer_mobno}/Credit")
	public ResponseEntity<Object> getCreditInfoCredit(@PathVariable String customer_mobno) {
	    
	       List<Object[]> objectList = walletdao.getCustomerData2Credit(customer_mobno,"float");
			    
			    
			    
			    if (objectList.isEmpty()) {
			        Map<String, Object> errorResponse = new HashMap<>();
//			       return ("Person does not exist");
			        //errorResponse.print("Person does not exist");
			        errorResponse.put("error", "Person does not exist");
			        //Collections.singletonList(errorResponse)
			        return ResponseHandler.generateResponse(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY,"CUSTOMER NOT FOUND");
			    }
			  
//			  
			    List<Map<String, Object>> jsonList = new ArrayList<>();
			    

			    for (Object[] values : objectList) {
			        System.out.println("Values Array: " + Arrays.toString(values)); 
			        Integer customer_id = (Integer) values[0];
			        String customer_name = (String) values[1];
			        String customer_mobno1 = (String) values[2];
			        String wallet_id = (String) values[3];
			        String wallet_balance = (String) values[4];
			        String wallet_name = (String) values[5]; 
			        System.out.println("customer_name: " + customer_name); 

			        Map<String, Object> jsonItem = new HashMap<>();
			        jsonItem.put("wallet_id", wallet_id);
			        jsonItem.put("customer_id", customer_id);
			        jsonItem.put("customer_name", customer_name); 
			        jsonItem.put("customer_mobile", customer_mobno1);
			        jsonItem.put("wallet_name", wallet_name);
			        jsonItem.put("wallet_balance", wallet_balance);

			        jsonList.add(jsonItem);
			    }


			    return ResponseHandler.generateResponse(jsonList,HttpStatus.OK,"CUSTOMER EXIST");
			}

		
	
//END\\


@GetMapping("/mywallets/{customer_mobno}")
public ResponseEntity<Object> getCustomerWalletData2(@PathVariable String customer_mobno) {
    
       List<Object[]> objectList = walletdao.getCustomerData2(customer_mobno);
		    
		    
		    
		    if (objectList.isEmpty()) {
		        Map<String, Object> errorResponse = new HashMap<>();
//		       return ("Person does not exist");
		        //errorResponse.print("Person does not exist");
		        errorResponse.put("error", "Person does not exist");
		        //Collections.singletonList(errorResponse)
		        return ResponseHandler.generateResponse(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY,"CUSTOMER NOT FOUND");
		    }
		  
//		  
		    List<Map<String, Object>> jsonList = new ArrayList<>();
		    

		    for (Object[] values : objectList) {
		        System.out.println("Values Array: " + Arrays.toString(values)); 
		        Integer customer_id = (Integer) values[0];
		        String customer_name = (String) values[1];
		        String customer_mobno1 = (String) values[2];
		        String wallet_id = (String) values[3];
		        String wallet_balance = (String) values[4];
		        String wallet_name = (String) values[5]; 
		        System.out.println("customer_name: " + customer_name); 

		        Map<String, Object> jsonItem = new HashMap<>();
		        jsonItem.put("wallet_id", wallet_id);
		        jsonItem.put("customer_id", customer_id);
		        jsonItem.put("customer_name", customer_name); 
		        jsonItem.put("customer_mobile", customer_mobno1);
		        jsonItem.put("wallet_name", wallet_name);
		        jsonItem.put("wallet_balance", wallet_balance);

		        jsonList.add(jsonItem);
		    }


		    return ResponseHandler.generateResponse(jsonList,HttpStatus.OK,"CUSTOMER EXIST");
		}

	

@GetMapping("MyWalletsDetailsVouchar/{customer_mobno}")
public ResponseEntity<Object> getCustomerData3(@PathVariable String customer_mobno) {
    
       List<Object[]> objectList = walletdao.getCustomerData2Credit(customer_mobno,"vouchar");
		    
		    
		    
		    if (objectList.isEmpty()) {
		        Map<String, Object> errorResponse = new HashMap<>();
//		       return ("Person does not exist");
		        //errorResponse.print("Person does not exist");
		        errorResponse.put("error", "Person does not exist");
		        //Collections.singletonList(errorResponse)
		        return ResponseHandler.generateResponse(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY,"CUSTOMER NOT FOUND");
		    }
		    List<Map<String, Object>> jsonList = new ArrayList<>();
		    for (Object[] values : objectList) {
		        System.out.println("Values Array: " + Arrays.toString(values)); 
		        Integer customer_id = (Integer) values[0];
		        String customer_name = (String) values[1];
		        String customer_mobno1 = (String) values[2];
		        String wallet_id = (String) values[3];
		        String wallet_balance = (String) values[4];
		        String wallet_name = (String) values[5]; 
		        System.out.println("customer_name: " + customer_name); 

		        Map<String, Object> jsonItem = new HashMap<>();
		        jsonItem.put("wallet_id", wallet_id);
		        jsonItem.put("customer_id", customer_id);
		        jsonItem.put("customer_name", customer_name); 
		        jsonItem.put("customer_mobile", customer_mobno1);
		        jsonItem.put("wallet_name", wallet_name);
		        jsonItem.put("wallet_balance", wallet_balance);

		        jsonList.add(jsonItem);
		    }


		    return ResponseHandler.generateResponse(jsonList,HttpStatus.OK,"CUSTOMER EXIST");
		}

@GetMapping("vouchar/{vouchar_id}")
public ResponseEntity<?> getVoucherById(@PathVariable String vouchar_id) {
    tjn_Wallet_types_model voucher = wallettypedao.findByVoucharId(vouchar_id);
    
    if (voucher == null) {
//        return new ResponseEntity<>("Voucher not found", HttpStatus.NOT_FOUND);
    	return ResponseHandler.generateResponse("NOT FOUND", HttpStatus.BAD_REQUEST, "FAILED");
    }
    
    return new ResponseEntity<>(voucher, HttpStatus.OK);
}



/*ADD VOUCHARS
//---------------------------------Posting Format------------------------------//
 * localhost:9090/api/wallets/voucharlist
 */
 /*{
    "wallet_name":"",
    "vouchar_price":"",
    "validity":""
    
 }*/
//---------------------------------Posting Format-------------------------------//

  @PostMapping("/voucharlist")
  public ResponseEntity<?>addvoucharlistt(@RequestBody tjn_Wallet_types_model tjnvoucharmodel,HttpServletRequest request){
	  List<String> uniqueIds = UniqueIdGenerator.generateUniqueIds();
	  String jwtToken = request.getHeader("Authorization");
	  
	  jwtToken = jwtToken.substring(7); // Remove "Bearer " prefix
      Claims claims = jwtHelper.getAllClaimsFromToken(jwtToken);
      System.out.println(claims);
      String username = claims.get("sub", String.class);
      System.out.println("User " + username + " accessed the API.");
      UserModel userModel= userRepository.findByEmail(username).orElse(null);
      String storeId = userModel.getStoreId();
      if(storeId == null) {
    	  storeId = "ADMIN";
      }
    	  String voucherId = uniqueIds.get(0); 
          tjnvoucharmodel.setVouchar_id(voucherId);
      //int wallet_id=2;
     // tjnvoucharmodel.setWallet_id(wallet_id);
	  String status ="ACTIVE";
	  tjnvoucharmodel.setStatus(status);
	  String wallet_type ="VOUCHAR";
	  tjnvoucharmodel.setWalletType(wallet_type);  
	  tjnvoucharmodel.setStore_id(storeId);
  tjn_Wallet_types_model addvoucharlist = walletService.addvoucharlist(tjnvoucharmodel);
  return new ResponseEntity<>(addvoucharlist, HttpStatus.CREATED);
  
  }


  @PutMapping("updateVouchar/{vouchar_id}")
  public ResponseEntity<tjn_Wallet_types_model>updateVouchar(@RequestBody tjn_Wallet_types_model vouchar,  @PathVariable String vouchar_id ){
  tjn_Wallet_types_model voucharupdate = walletService.updateVouchar(vouchar,vouchar_id);
  if (voucharupdate != null) {
      return new ResponseEntity<>(voucharupdate, HttpStatus.OK);
  } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
  

  }


 @DeleteMapping("deletevouchar/{vouchar_id}")
 public ResponseEntity<?>deletevouchar(@PathVariable String vouchar_id){
	  
	 return ResponseHandler.generateResponse(walletService.deletevouchar(vouchar_id), HttpStatus.OK, "Successfully Deleted");
	 
 }
}





