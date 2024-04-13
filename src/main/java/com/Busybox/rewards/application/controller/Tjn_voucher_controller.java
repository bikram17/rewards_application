package com.Busybox.rewards.application.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.dao.CustomerRepository;
import com.Busybox.rewards.application.dao.TJNMoneyTransDao;
import com.Busybox.rewards.application.dao.Tjn_Voucher_Cupons_DAO;
import com.Busybox.rewards.application.dao.Tjn_Wallet_Dao;
import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;
import com.Busybox.rewards.application.dto.MoneyTransactionResponse;
import com.Busybox.rewards.application.impl.TJNMoneyTransServiceImpl;
import com.Busybox.rewards.application.impl.voucherTransactionImpl;
import com.Busybox.rewards.application.model.CustomerModel;
import com.Busybox.rewards.application.model.Tjn_Voucher_Coupons;
import com.Busybox.rewards.application.model.tjn_Wallet_types_model;
import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;
import com.Busybox.rewards.application.security.JwtHelper;
import com.Busybox.rewards.application.security.UserModel;
import com.Busybox.rewards.application.security.UserRepository;
import com.Busybox.rewards.application.service.CustomerService;
import com.Busybox.rewards.application.service.Tjn_Voucher_Rewards_Service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/vouchar")
public class Tjn_voucher_controller {

	@Autowired private  TJNMoneyTransDao tjnmoneytransDao;
	@Autowired private Tjn_Voucher_Rewards_Service voucharService;
	@Autowired private CustomerService customerService;
	@Autowired private tjn_wallet_types_dao voucharDao;
	@Autowired private CustomerRepository customerDao;
	@Autowired private Tjn_Voucher_Cupons_DAO voucharcouponsDao;
	@Autowired private Tjn_Wallet_Dao walletdao;
	@Autowired private UserRepository userRepository;
	@Autowired private TJNMoneyTransServiceImpl impl;
	@Autowired private voucherTransactionImpl voucherImpl;
	JwtHelper jwtHelper = new JwtHelper();
	
	
	
		/*GET DETAILS OF  VOUCHAR BY  customer_mobno*/
	@GetMapping("getvouchar/{customer_mobno}")
	public ResponseEntity<Object> getCustomerDataCombined(@PathVariable String customer_mobno) {
	    
	       List<Object[]> objectList = voucharcouponsDao.getCustomerDataCombined(customer_mobno);    
			    
			    
			    if (objectList.isEmpty()) {
			        Map<String, Object> errorResponse = new HashMap<>();
			       
			        errorResponse.put("error", "Person does not exist");
			        
			        return ResponseHandler.generateResponse(errorResponse,HttpStatus.NOT_FOUND,"CUSTOMER NOT FOUND");
			    }
			    List<Map<String, Object>> jsonList = new ArrayList<>();
			    

			    for (Object[] values : objectList) {
			        System.out.println("Values Array: " + Arrays.toString(values)); 
			        Integer customer_id = (Integer) values[0];
			        String customer_name = (String) values[1];
			        String customer_mobno1 = String.valueOf(values[2]);
			        Integer wallet_id = (Integer) values[3];
			        Integer balance = (Integer) values[4];
			        String wallet_name = (String) values[5]; 
			        System.out.println("customer_name: " + customer_name); 

			        Map<String, Object> jsonItem = new HashMap<>();
			        jsonItem.put("wallet_id", wallet_id);
			        jsonItem.put("customer_id", customer_id);
			        jsonItem.put("customer_name", customer_name); 
			        jsonItem.put("customer_mobile", customer_mobno1);
			        jsonItem.put("wallet_name", wallet_name);
			        jsonItem.put("balance", balance);

			        jsonList.add(jsonItem);
			    }
			    return ResponseHandler.generateResponse(jsonList,HttpStatus.OK,"CUSTOMER EXIST");
			}
	
	
	/*GET VOUCHAR DETAILS BY customer_mobno*/
	@Deprecated
	@GetMapping("/getdatacombined2/{customer_mobno}")
	public ResponseEntity<Object> getCustomerDetails(@PathVariable String customer_mobno) {
	    List<Object[]> walletObjectList = walletdao.getCustomerData2(customer_mobno);
	    List<Object[]> voucherObjectList = voucharcouponsDao.getCustomerDataCombined(customer_mobno);
	    if (walletObjectList.isEmpty() || voucherObjectList.isEmpty()) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("error", "Customer not found for wallet or voucher");
	        return ResponseHandler.generateResponse(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY, "CUSTOMER NOT FOUND");
	    }
	    List<Map<String, Object>> combinedDetails = new ArrayList<>();
	    for (Object[] values : walletObjectList) {
	        Integer customer_id = (Integer) values[0];
	        String customer_name = (String) values[1];
	        String customer_mobno1 = (String) values[2];
	        String wallet_id = (String) values[3];
	        String wallet_balance = (String) values[4];
	        String wallet_name = (String) values[5];
	        Map<String, Object> jsonItem = new HashMap<>();
	        jsonItem.put("customer_id", customer_id);
	        jsonItem.put("customer_name", customer_name);
	        jsonItem.put("customer_mobile", customer_mobno1);
	        jsonItem.put("wallet_id", wallet_id);
	        jsonItem.put("wallet_balance", wallet_balance);
	        jsonItem.put("wallet_type", "FLOAT");
	        jsonItem.put("wallet_name", wallet_name);
	        combinedDetails.add(jsonItem);
	    }
	    for (Object[] values : voucherObjectList) {
	        Integer customer_id = (Integer) values[0];
	        String customer_name = (String) values[1];
	        String customer_mobno1 = String.valueOf(values[2]);
	        String wallet_id = String.valueOf((Integer) values[3]);
	        String balance = String.valueOf((Long) values[4]);
	        String wallet_name = (String) values[5];
	        String voucherId = (String) values[6];
	        Map<String, Object> jsonItem = new HashMap<>();
	        jsonItem.put("customer_id", customer_id);
	        jsonItem.put("customer_name", customer_name);
	        jsonItem.put("customer_mobile", customer_mobno1);
	        jsonItem.put("wallet_id", wallet_id);
	        jsonItem.put("wallet_balance", balance);
	        jsonItem.put("wallet_type", "VOUCHER");
	        jsonItem.put("wallet_name", wallet_name);
	        jsonItem.put("voucher_code", voucherId);
	        combinedDetails.add(jsonItem);
	    }
	    return ResponseHandler.generateResponse(combinedDetails, HttpStatus.OK, "CUSTOMER DETAILS EXIST");
	}
	
	@Deprecated
	@GetMapping("/getdatacombineddecap2/{customer_mobno}")
	public ResponseEntity<Object> getCustomerDetails2decap2(@PathVariable String customer_mobno) {
		List<Object[]> walletObjectList = walletdao.getCustomerData2(customer_mobno);
		List<Object[]> voucherObjectList = voucharcouponsDao.getCustomerDataCombined(customer_mobno);
		System.out.println(walletObjectList);
		System.out.println(voucherObjectList);
		if (walletObjectList.isEmpty()) {
			Map<String, Object> errorResponse = new HashMap<>();
			errorResponse.put("error", "Customer not found for wallet or voucher");
			return ResponseHandler.generateResponse(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY, "CUSTOMER NOT FOUND");
		}
		List<Map<String, Object>> combinedDetails = new ArrayList<>();
		for (Object[] values : walletObjectList) {
			Integer customer_id = (Integer) values[0];
			String customer_name = (String) values[1];
			String customer_mobno1 = (String) values[2];
			String wallet_id = (String) values[3];
			String wallet_balance = (String) values[4];
			String wallet_name = (String) values[5];
			Map<String, Object> jsonItem = new HashMap<>();
			jsonItem.put("customer_id", customer_id);
			jsonItem.put("customer_name", customer_name);
			jsonItem.put("customer_mobile", customer_mobno1);
			jsonItem.put("wallet_id", wallet_id);
			jsonItem.put("wallet_balance", wallet_balance);
			jsonItem.put("wallet_type", "FLOAT");
			jsonItem.put("wallet_name", wallet_name);
			combinedDetails.add(jsonItem);
		}
		for (Object[] values : voucherObjectList) {
			Integer customer_id = (Integer) values[0];
			String customer_name = (String) values[1];
			String customer_mobno1 = String.valueOf(values[2]);
			String wallet_id = String.valueOf((Integer) values[3]);
			String balance = String.valueOf((Long) values[4]);
			String wallet_name = (String) values[5];
			String voucherId = (String) values[6];
			Map<String, Object> jsonItem = new HashMap<>();
			jsonItem.put("customer_id", customer_id);
			jsonItem.put("customer_name", customer_name);
			jsonItem.put("customer_mobile", customer_mobno1);
			jsonItem.put("wallet_id", wallet_id);
			jsonItem.put("wallet_balance", balance);
			jsonItem.put("wallet_type", "VOUCHER");
			jsonItem.put("wallet_name", wallet_name);
			jsonItem.put("vouchar_id", voucherId);
			combinedDetails.add(jsonItem);
		}
		return ResponseHandler.generateResponse(combinedDetails, HttpStatus.OK, "CUSTOMER DETAILS EXIST");
	}
	
	@GetMapping("/getdatacombined/{customer_mobno}")
	public ResponseEntity<Object> getCustomerDetails(@PathVariable String customer_mobno, HttpServletRequest request) {

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
		        
		        
		        List<Object[]> walletObjectList = walletdao.getCustomerData2(customer_mobno);
			    List<Object[]> voucherObjectList = voucharcouponsDao.getCustomerDataCombinedStoreBasis(customer_mobno,storeId);
			  System.out.println(walletObjectList);
			  System.out.println(voucherObjectList);
			    if (walletObjectList.isEmpty()) {
			        Map<String, Object> errorResponse = new HashMap<>();
			        errorResponse.put("error", "Customer not found for wallet or voucher");
			        return ResponseHandler.generateResponse(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY, "CUSTOMER NOT FOUND");
			    }
			    List<Map<String, Object>> combinedDetails = new ArrayList<>();
			    for (Object[] values : walletObjectList) {
			        Integer customer_id = (Integer) values[0];
			        String customer_name = (String) values[1];
			        String customer_mobno1 = (String) values[2];
			        String wallet_id = (String) values[3];
			        String wallet_balance = (String) values[4];
			        String wallet_name = (String) values[5];
			        Map<String, Object> jsonItem = new HashMap<>();
			        jsonItem.put("customer_id", customer_id);
			        jsonItem.put("customer_name", customer_name);
			        jsonItem.put("customer_mobile", customer_mobno1);
			        jsonItem.put("wallet_id", wallet_id);
			        jsonItem.put("wallet_balance", wallet_balance);
			        jsonItem.put("wallet_type", "FLOAT");
			        jsonItem.put("wallet_name", wallet_name);
			        combinedDetails.add(jsonItem);
			    }
			    for (Object[] values : voucherObjectList) {
			        Integer customer_id = (Integer) values[0];
			        String customer_name = (String) values[1];
			        String customer_mobno1 = String.valueOf(values[2]);
			        String wallet_id = String.valueOf((Integer) values[3]);
			        String balance = String.valueOf((Long) values[4]);
			        String wallet_name = (String) values[5];
			        String voucherId = (String) values[6];
			        Map<String, Object> jsonItem = new HashMap<>();
			        jsonItem.put("customer_id", customer_id);
			        jsonItem.put("customer_name", customer_name);
			        jsonItem.put("customer_mobile", customer_mobno1);
			        jsonItem.put("wallet_id", wallet_id);
			        jsonItem.put("wallet_balance", balance);
			        jsonItem.put("wallet_type", "VOUCHER");
			        jsonItem.put("wallet_name", wallet_name);
			        jsonItem.put("vouchar_id", voucherId);
			        combinedDetails.add(jsonItem);
			    }
			    return ResponseHandler.generateResponse(combinedDetails, HttpStatus.OK, "CUSTOMER DETAILS EXIST");
		        
	    	} else {
	    		List<Object[]> walletObjectList = walletdao.getCustomerData2(customer_mobno);
			    List<Object[]> voucherObjectList = voucharcouponsDao.getCustomerDataCombined(customer_mobno);
			  System.out.println(walletObjectList);
			  System.out.println(voucherObjectList);
			    if (walletObjectList.isEmpty()) {
			        Map<String, Object> errorResponse = new HashMap<>();
			        errorResponse.put("error", "Customer not found for wallet or voucher");
			        return ResponseHandler.generateResponse(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY, "CUSTOMER NOT FOUND");
			    }
			    List<Map<String, Object>> combinedDetails = new ArrayList<>();
			    for (Object[] values : walletObjectList) {
			        Integer customer_id = (Integer) values[0];
			        String customer_name = (String) values[1];
			        String customer_mobno1 = (String) values[2];
			        String wallet_id = (String) values[3];
			        String wallet_balance = (String) values[4];
			        String wallet_name = (String) values[5];
			        Map<String, Object> jsonItem = new HashMap<>();
			        jsonItem.put("customer_id", customer_id);
			        jsonItem.put("customer_name", customer_name);
			        jsonItem.put("customer_mobile", customer_mobno1);
			        jsonItem.put("wallet_id", wallet_id);
			        jsonItem.put("wallet_balance", wallet_balance);
			        jsonItem.put("wallet_type", "FLOAT");
			        jsonItem.put("wallet_name", wallet_name);
			        combinedDetails.add(jsonItem);
			    }
			    for (Object[] values : voucherObjectList) {
			        Integer customer_id = (Integer) values[0];
			        String customer_name = (String) values[1];
			        String customer_mobno1 = String.valueOf(values[2]);
			        String wallet_id = String.valueOf((Integer) values[3]);
			        String balance = String.valueOf((Long) values[4]);
			        String wallet_name = (String) values[5];
			        String voucherId = (String) values[6];
			        Map<String, Object> jsonItem = new HashMap<>();
			        jsonItem.put("customer_id", customer_id);
			        jsonItem.put("customer_name", customer_name);
			        jsonItem.put("customer_mobile", customer_mobno1);
			        jsonItem.put("wallet_id", wallet_id);
			        jsonItem.put("wallet_balance", balance);
			        jsonItem.put("wallet_type", "VOUCHER");
			        jsonItem.put("wallet_name", wallet_name);
			        jsonItem.put("vouchar_id", voucherId);
			        combinedDetails.add(jsonItem);
			    }
			    return ResponseHandler.generateResponse(combinedDetails, HttpStatus.OK, "CUSTOMER DETAILS EXIST");
		    }
	    	
	    }catch(Exception e) {
	    	return ResponseHandler.generateResponse("FAILED", HttpStatus.BAD_REQUEST, "FAILED");
	    }
	}
	/*FOR ADDING  VOUCHAR*/
	/*VOUCHAR ADIING PAYLOAD*/
	/* {
	   
    "vouchar_id":"BAC029",
    "balance":2
    
 }
	 */
	@PostMapping("cusAddVouchars")
	public ResponseEntity<?>cusAddVouchars(@RequestBody Tjn_Voucher_Coupons voucharmodel,@RequestParam("customer_mobno") String customer_mobno){
		try {
			CustomerModel customer = customerService.getCustomerByPhoneNumber(customer_mobno);
	        if (customer == null) {
	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Customer Not Found");
	        }
//	        int customer_id = voucharmodel.getCustomer_id();
	        int customer_id2 = customerDao.getcustomerIdByPhoneno(customer_mobno);
	        voucharmodel.setCustomer_id(customer_id2);
	        String vouchar_id = voucharmodel.getVouchar_id();
	        tjn_Wallet_types_model voucharlist =  voucharDao.findByVoucharId(voucharmodel.getVouchar_id());
	        	if(voucharlist == null) {
	        		return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Vouchar Not Found");
	        	}
	        	long validity = voucharDao.getValidityByVoucharId(vouchar_id);
	        	LocalDate currentDate = LocalDate.now();
	        	LocalDate expiryDate = currentDate.plusDays(validity);
	        	
	        	int wallet_id = voucharDao.getwalletIdByVoucharId(vouchar_id);
	        	voucharmodel.setWallet_id(wallet_id);
	        	voucharmodel.setIsEmpty("NO");
	        	String status ="ACTIVE";
	        	voucharmodel.setStatus(status);
	        	if (currentDate.isAfter(expiryDate)) {
	                voucharmodel.setStatus("INACTIVE");
	            } else {
	                voucharmodel.setStatus("ACTIVE");
	            }
	        	voucharmodel.setExpiryDate(java.sql.Date.valueOf(expiryDate));
	        //		buying voucher here
	        		tjn_money_transaction_model_Logs LogsD = new tjn_money_transaction_model_Logs();
	        		LogsD.setCustomerId(customer_id2);
	        		Long phn = Long.parseLong(customer_mobno);
	        		LogsD.setCustomerMobNo(phn);
	        		LogsD.setTransactionAmt(voucharmodel.getBalance());
	        		Object open =voucharcouponsDao.getCount(customer_mobno,wallet_id);
	        		System.out.println(open);
	        		if(open == null || open.toString().isBlank() || open.toString().isEmpty() ) {
	        		LogsD.setCardOpeningBalance(0);
	        			}else {
	        				LogsD.setCardOpeningBalance(Double.parseDouble(open.toString()));	
	        				}
	        		LogsD.setTransactionWalletWype(wallet_id);
	        		Object ob = voucharService.buyvouchar(voucharmodel);
	        		Object close =voucharcouponsDao.getCount(customer_mobno,wallet_id);
	        		LogsD.setCardClosingBalance(Double.parseDouble(close.toString()));
	        		LogsD.setTransactionMethod("CREDIT");
	        		LogsD.setMode("VOUCHER");
	        		 tjnmoneytransDao.save(LogsD);
			return ResponseHandler.generateResponse(ob, HttpStatus.OK,"Vouchar Added Successfully");
		}catch(Exception e) {
			return ResponseHandler.generateResponse(e.toString(), HttpStatus.BAD_REQUEST, "FAILED");
		}
        	 
	}

	@Deprecated
	@PostMapping("cusAddVoucharsDecap")
	public ResponseEntity<?>buyvouchar(@RequestBody Tjn_Voucher_Coupons voucharmodel,@RequestParam("customer_mobno") String customer_mobno){
		CustomerModel customer = customerService.getCustomerByPhoneNumber(customer_mobno);
        if (customer == null) {
            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Customer Not Found");
        }
        int customer_id = voucharmodel.getCustomer_id();
        int customer_id2 = customerDao.getcustomerIdByPhoneno(customer_mobno);
        voucharmodel.setCustomer_id(customer_id2);
        
        String vouchar_id = voucharmodel.getVouchar_id();
        tjn_Wallet_types_model voucharlist =  voucharDao.findByVoucharId(voucharmodel.getVouchar_id());
        	if(voucharlist == null) {
        		return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Vouchar Not Found");
        	}
        	
        	long validity = voucharDao.getValidityByVoucharId(vouchar_id);
        	LocalDate currentDate = LocalDate.now();
        	LocalDate expiryDate = currentDate.plusDays(validity);
        	
        	
        	int wallet_id = voucharDao.getwalletIdByVoucharId(vouchar_id);
        	voucharmodel.setWallet_id(wallet_id);
        	voucharmodel.setIsEmpty("NO");
        	String status ="ACTIVE";
        	voucharmodel.setStatus(status);
        	if (currentDate.isAfter(expiryDate)) {
                voucharmodel.setStatus("INACTIVE");
            } else {
                voucharmodel.setStatus("ACTIVE");
            }
        	voucharmodel.setExpiryDate(java.sql.Date.valueOf(expiryDate));
        	 
		return ResponseHandler.generateResponse(voucharService.buyvouchar(voucharmodel), HttpStatus.OK,"Vouchar Added Successfully");
        	 
	}
	// Single Transaction
	@PostMapping("/DoTransaction")
	public ResponseEntity<?> doTransaction(@RequestBody Map<String, Object> requestBody) {
	    try {
	        String authToken = "123456";

	        if (!requestBody.containsKey("transaction_amount")) {
	            return ResponseEntity.badRequest().body("Invalid Request");
	        }

	        String customerPhn = (String) requestBody.get("customer_phn");
	        double transactionAmount = Double.parseDouble(requestBody.get("transaction_amount").toString());
	        String transactionMode = requestBody.get("transaction_mode").toString();
	        String token = requestBody.get("token").toString();
	        String customerWalletId = requestBody.getOrDefault("customer_wallet_id", "5").toString();
	        String storeId = requestBody.getOrDefault("storeId", "5").toString();

	        if (!authToken.equals(token)) {
	            return ResponseHandler.generateResponse("Token Authentication Failed", HttpStatus.FORBIDDEN, "Failed");
	        }

	        CustomerModel customer = customerService.getCustomerByPhoneNumber(customerPhn);
	        if (customer == null) {
	            return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Customer Not Found");
	        }

	        if ("Debit".equalsIgnoreCase(transactionMode) && !("2".equalsIgnoreCase(customerWalletId) || "1".equalsIgnoreCase(customerWalletId))) {
	            int vamt = Integer.parseInt(requestBody.get("transaction_amount").toString());
	            int walletId = Integer.parseInt(customerWalletId);
	            return voucherImpl.doVoucherTransaction(requestBody);
	        }

	        List<MoneyTransactionResponse> responses = impl.DoTransactionStore(customerPhn, transactionAmount, transactionMode, customerWalletId, storeId);
	        MoneyTransactionResponse response = responses.get(0);

	        if (response.getStatus().value() != 200) {
	            return ResponseHandler.generateResponseForMoneyTransaction2("failure", response.getMessage());
	        } else {
	            Map<String, Object> trueTransaction = new LinkedHashMap<>();
	            trueTransaction.put("Opening Balance", response.getOpeningBalance());
	            trueTransaction.put("Transaction Amount", response.getTransactionAmount());
	            trueTransaction.put("Closing Balance", response.getClosingBalance());
	            return ResponseHandler.generateResponse(trueTransaction, response.getStatus(), "Transaction Successful");
	        }
	    } catch (NumberFormatException | NullPointerException e) {
	        return ResponseEntity.badRequest().body("Invalid Number Format or Missing Required Fields");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
	    }
	}

	// Multiple Transaction 
	
	@PostMapping("/DoTransactions/array")
	public ResponseEntity<?> doTransactionsArray(@RequestBody List<Map<String, Object>> transactions) {
	    List<Map<String, Object>> transactionResponses = new ArrayList<>();

	    try {
	        String authToken = "123456";

	        for (Map<String, Object> transaction : transactions) {
	            if (!transaction.containsKey("transaction_amount")) {
	                return ResponseEntity.badRequest().body("Invalid Request");
	            }

	            String customerPhn = (String) transaction.get("customer_phn");
	            double transactionAmount = Double.parseDouble(transaction.get("transaction_amount").toString());
	            String transactionMode = transaction.get("transaction_mode").toString();
	            String token = transaction.get("token").toString();
	            String customerWalletId = transaction.getOrDefault("customer_wallet_id", "5").toString();
	            String storeId = transaction.getOrDefault("storeId", "5").toString();

	            if (!authToken.equals(token)) {
	                return ResponseHandler.generateResponse("Token Authentication Failed", HttpStatus.FORBIDDEN, "Failed");
	            }

	            CustomerModel customer = customerService.getCustomerByPhoneNumber(customerPhn);
	            if (customer == null) {
	                transactionResponses.add(ResponseHandler.extractMapFromResponseEntity(ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Customer Not Found")));
	                continue; // Move to the next transaction
	            }

	            if ("Debit".equalsIgnoreCase(transactionMode) && !("2".equalsIgnoreCase(customerWalletId) || "1".equalsIgnoreCase(customerWalletId))) {
	                return voucherImpl.doVoucherTransaction(transaction);
	            }

	            List<MoneyTransactionResponse> responses = impl.DoTransactionStore(customerPhn, transactionAmount, transactionMode, customerWalletId, storeId);
	            MoneyTransactionResponse response = responses.get(0);

	            if (response.getStatus().value() != 200) {
	                transactionResponses.add(ResponseHandler.extractMapFromResponseEntity(ResponseHandler.generateResponseForMoneyTransaction2("failure", response.getMessage())));
	                
	            } else {
	                Map<String, Object> trueTransaction = new LinkedHashMap<>();
	                trueTransaction.put("Opening Balance", response.getOpeningBalance());
	                trueTransaction.put("Transaction Amount", response.getTransactionAmount());
	                trueTransaction.put("Closing Balance", response.getClosingBalance());
	                transactionResponses.add(ResponseHandler.extractMapFromResponseEntity(ResponseHandler.generateResponse(trueTransaction, response.getStatus(), "Transaction Successful")));
	            }
	        }

	        return ResponseHandler.generateResponse(transactionResponses, HttpStatus.OK, "SUCCESS");
	    } catch (NumberFormatException | NullPointerException e) {
	        return ResponseEntity.badRequest().body("Invalid Number Format or Missing Required Fields");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
	    }
	}


	
}
	/*{
	  	"customer_phn":"",
	  	"transaction_amount":"",
	  	"transaction_mode":"",
	  	"customer_wallet_id":"",
	  	"token":"123456"
	  }*/
	
//	@PostMapping("/DoTransaction")
//	public ResponseEntity<?> DoTransaction(@RequestBody Map<String, Object> requestBody) {
//	    try {
//	        String auth_token = "123456";
//
//	        if (requestBody.containsKey("transaction_amount")) {
//	            String customer_phn = (String) requestBody.get("customer_phn");
//	            double transaction_amount = Double.parseDouble(requestBody.get("transaction_amount").toString());
//	            String transaction_mode = requestBody.get("transaction_mode").toString();
//	            String token = requestBody.get("token").toString();
//	            String customer_wallet_id = requestBody.get("customer_wallet_id").toString();
//	            String storeId = requestBody.get("storeId").toString();
//	            if(storeId==null) {
//	            	storeId ="5";
//	            }
//	            if (!auth_token.equals(token)) {
//	                return ResponseHandler.generateResponse("Token Authentication Failed", HttpStatus.FORBIDDEN, "Failed");
//	            }
//
//	            CustomerModel customer = customerService.getCustomerByPhoneNumber(customer_phn);
//	            if (customer == null) {
//	                return ResponseHandler.generateResponse(null, HttpStatus.NOT_FOUND, "Customer Not Found");
//	            }
//	            if ("Debit".equalsIgnoreCase(transaction_mode) && !customer_wallet_id.equalsIgnoreCase("2") && !customer_wallet_id.equalsIgnoreCase("1")) {
//	                
//	                int vamt = Integer.parseInt(requestBody.get("transaction_amount").toString());
//	                int wallet_id = Integer.parseInt(requestBody.get("customer_wallet_id").toString()); 
//	                return voucherImpl.doVoucherTransaction(requestBody);
//	            }
//	            if(customer_wallet_id.equalsIgnoreCase("2") || !customer_wallet_id.equalsIgnoreCase("1")) {
//	            	List<MoneyTransactionResponse> responses = impl.DoTransactionStore(customer_phn, transaction_amount, transaction_mode, customer_wallet_id,storeId);
//	                MoneyTransactionResponse response = responses.get(0);
//
//	                if (response.getStatus().value() != 200) {
//	                    return ResponseHandler.generateResponseForMoneyTransaction2("failure", response.getMessage());
//	                } 
//	                else {
//	                    Map<String, Object> trueTransaction = new LinkedHashMap<>();
//	                    trueTransaction.put("Opening Balance", response.getOpeningBalance());
//	                    trueTransaction.put("Transaction Amount", response.getTransactionAmount());
//	                    trueTransaction.put("Closing Balance", response.getClosingBalance());
//	                    return ResponseHandler.generateResponse(trueTransaction, response.getStatus(), "Transaction Successful");
//	                }
//                }
//	            else {
//	                List<MoneyTransactionResponse> responses = impl.DoTransactionStore(customer_phn, transaction_amount, transaction_mode, customer_wallet_id,storeId);
//	                MoneyTransactionResponse response = responses.get(0);
//	                if (response.getStatus().value() != 200) {
//	                    return ResponseHandler.generateResponseForMoneyTransaction2("failure", response.getMessage());
//	                } 
//	                else {
//	                    Map<String, Object> trueTransaction = new LinkedHashMap<>();
//	                    trueTransaction.put("Opening Balance", response.getOpeningBalance());
//	                    trueTransaction.put("Transaction Amount", response.getTransactionAmount());
//	                    trueTransaction.put("Closing Balance", response.getClosingBalance());
//	                    return ResponseHandler.generateResponse(trueTransaction, response.getStatus(), "Transaction Successful");
//	                }
//	            }
//	        } else {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
//	        }
//	    } catch (Exception e) {
//	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
//	    }
//	}
//}