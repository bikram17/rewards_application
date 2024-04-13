package com.Busybox.rewards.application.controller;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.dao.DatabaseConnection;
import com.Busybox.rewards.application.dao.TJNMoneyTransDao;
import com.Busybox.rewards.application.dto.MoneyTransactionResponse;
import com.Busybox.rewards.application.impl.TJNMoneyTransServiceImpl;

@RestController
@RequestMapping("api/transaction")
public class tjn_money_transaction_controller {
	//@Autowired private TJNMoneyTransService  tjnmoneytransService;
	@Autowired	private TJNMoneyTransDao  tjnDao;
	@Autowired private TJNMoneyTransServiceImpl impl;
	//@Autowired private DatabaseConnection dbc;
	// checking the first query 
	
	
	@GetMapping("/hey")
	public String hey() {
		return "hey";
	}
	
	@GetMapping("/querycheck/{wallet}/{cusId}")
	public ResponseEntity<Object> cusIdToPhnNo(@PathVariable String wallet,@PathVariable String cusId){
	
		
					String balance=	Double.toString(tjnDao.BalCheckwb(wallet,cusId));
//					String s = "500.00";
//					double d = Double.parseDouble(s);
	    		//return  balance;
	    		return ResponseHandler.generateResponseNull(balance,HttpStatus.OK);
}

	// checking the second query 
	
	@GetMapping("/customData/{PhoneNumber}")
	public ResponseEntity<?> customData10(@PathVariable String PhoneNumber){
//		tjnDao.customeWalletData(PhoneNo);
		return ResponseHandler.generateResponse(tjnDao.custom10(PhoneNumber), HttpStatus.OK, "Successfull");
	}
	
	
	
	
	@GetMapping("/removemoney/{amount}/{cusid}/{wid}")
	public ResponseEntity<Object> abcd(@PathVariable String amount,@PathVariable String cusid,@PathVariable String wid){
	
	    		 System.out.println(amount);
	    		 
	    		 System.out.println(cusid);
	    		 
	    		 System.out.println(wid);
	    		 
					tjnDao.debit(amount, wid, cusid);
					  //s return "Successfully Credited into account";
					   return ResponseHandler.generateResponseNull("Successfully Credited into account",HttpStatus.OK);
					 	}


						
	  @GetMapping("/addmoney/{amt}/{cid}/{wid}")
		  public ResponseEntity<Object> sdzfnbsd(@PathVariable String amt,@PathVariable String cid,@PathVariable String wid){
		  // System.out.print(tjnDao.credit(cusId,wallet_id,input));
		  
		  tjnDao.credit(amt, wid, cid);
			
			  System.out.println("This is amount "+amt);
			  
			  System.out.println("This is wid "+wid);
			  
			  System.out.println("This is cid "+cid); 
			 // return "Successfully Credited into account 2";
			  return ResponseHandler.generateResponseNull("Successfully Credited into account 2",HttpStatus.OK);
			  }
				 

	  @GetMapping("/add500")
	  public ResponseEntity<Object> add500() {
		 // tjnDao.add500();
		  DatabaseConnection.abc();
		  //System.out.println(DatabaseConnection.abc());
		 // return "OK";
		  return ResponseHandler.generateResponseNull("OK Added 500",HttpStatus.OK);
	  }
	  
	  //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	  @PostMapping("/Dotransaction")
	  public ResponseEntity<Object> Dotransaction(@RequestBody Map<String,String> userinput){
		  String customer_id1 = userinput.get("customer_phn");
		  String transaction_amount1 = userinput.get("transaction_amount");
		  double transaction_amount = Double.parseDouble(transaction_amount1);
		  String mode1 =userinput.get("transaction_mode");
		  String wallet_id1= userinput.get("customer_wallet_id");
		  String user_token1= userinput.get("token");
		  String storeId= userinput.get("storeId");
		 // double transaction_amount=  Double.parseDouble(transaction_amount1);
		  
		  /*
		  {
		  	"customer_phn":"",
		  	"transaction_amount":"",
		  	"transaction_mode":"",
		  	"customer_wallet_id":"",
		  	
		  	"storeId":"",
		  	"token":""
		  }		  
		  */		  
		  String Auth_token="123456";
		  if(!Auth_token.equalsIgnoreCase(user_token1)) {
			  //return new ResponseEntity<Object>("TOKEN DOES NOT MATCH!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!",HttpStatus.FORBIDDEN);
			  return ResponseHandler.generateResponse("Token Authentication Failed", HttpStatus.FORBIDDEN,"Failed");
			 
		  }else {
			//  try {
				//  return new ResponseEntity<>(tjnmoneytransService.moneydebit(customer_id1, transaction_amount, mode1, wallet_id1),HttpStatus.OK);}
			 // return ResponseHandler.generateResponse(impl.moneydebitDEMO(customer_id1, transaction_amount, mode1, wallet_id1), HttpStatus.OK, "Information From Server Fetched Successfully");
			  List<MoneyTransactionResponse> responses = new ArrayList<>();
			  //-----------------m CHANGES HERE IS MADE FOR STORE ID DATE 29 
			  responses = impl.DoTransactionStore(customer_id1, transaction_amount, mode1, wallet_id1,storeId);
			  MoneyTransactionResponse response = new MoneyTransactionResponse();
			  response = responses.get(0);
			  //response.getStatus()
			  if(response.getStatus().value() != 200) {
				  return ResponseHandler.generateResponseForMoneyTransaction2("failure"  ,response.getMessage());
			  }else {
				 // response.getClosingBalance();
				 // response.getTransactionAmount();
				 // response.getOpeningBalance();
				  				  
				  Map<String, Object> trueTransaction = new LinkedHashMap<>();
				  trueTransaction.put("Opening Balance", response.getOpeningBalance());
					trueTransaction.put("Transaction Amount", response.getTransactionAmount());
					trueTransaction.put("Closing Balance", response.getClosingBalance());
				  
				  return ResponseHandler.generateResponse(trueTransaction , response.getStatus(), response.getMessage());
		  }
		  }
		  /*catch(Exception e) 
			  {
				  return new ResponseEntity<>("Error BAD",HttpStatus.BAD_REQUEST);
				  }*/
		  }
	
	  
	  @PostMapping("addvouchers")
	  public ResponseEntity<?> addvouchers(@RequestBody Map<String, String> serverinput){
		  if(serverinput != null) {
			  List<MoneyTransactionResponse> responses = new ArrayList<>();
			  String customerPhn = serverinput.get("Customer_PhoneNumber");
			  String voucherName= serverinput.get("Voucher_Name");
			  int voucherCount = Integer.parseInt(serverinput.get("Voucher_count"));
			  if(customerPhn == null || voucherName == null || voucherCount <=0) {
				  ResponseHandler.generateResponse(serverinput, HttpStatus.NOT_FOUND, "No Activity");
			  }
//			  impl.VoucherTransaction(customerPhn,voucherName, voucherCount);
			  responses =impl.VoucherTransaction(customerPhn,voucherName, voucherCount);
			  MoneyTransactionResponse response = new MoneyTransactionResponse();
			  response = responses.get(0);
			 return ResponseHandler.generateResponse(responses, response.getStatus(), response.getMessage());
			  
		  }else {
			  return ResponseHandler.generateResponse(serverinput, HttpStatus.NOT_FOUND, "No Activity");
		  }
	  }
 
	  
	  @GetMapping("/test/api")
	  public String testapi() {
		  String cus = "41";
		 int s= tjnDao.checkParentId(cus);
		 String Pid = tjnDao.getParentId(cus);
		  if(s==1) {return "YES "+ s +" " + Pid;}else return "NO "+ s + Pid;
	  }

}
