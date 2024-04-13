package com.Busybox.rewards.application.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.TJNMoneyTransDao;
import com.Busybox.rewards.application.dao.Tjn_Voucher_Cupons_DAO;
import com.Busybox.rewards.application.model.Tjn_Voucher_Coupons;
import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;

@Service
public class voucherTransactionImpl {

	@Autowired private Tjn_Voucher_Cupons_DAO vdao;
	@Autowired private  TJNMoneyTransDao tjnmoneytransDao;
		public final String Bikram = "@BIKRAM";
//	
//	{
//	  	"customer_phn":"",
//	  	"transaction_amount":"",
//	  	"transaction_mode":"",
//	  	"customer_wallet_id":"",
//	  	"token":"ipiioghhuguygggbgbho"
//	  }
	// check if the transaction is valid or not
	public ResponseEntity<?> doVoucherTransaction(Map<String, Object> userInput){
		String customer_phn = (String) userInput.get("customer_phn");
        int transaction_amount = Integer.parseInt(userInput.get("transaction_amount").toString());
        String transaction_mode = userInput.get("transaction_mode").toString();
        String customer_wallet_id = userInput.get("customer_wallet_id").toString();
        String storeId = userInput.get("storeId").toString();
  
        
        String customer_id = Integer.toString(tjnmoneytransDao.cusPhnToId(customer_phn));
        try {
        	if(vdao.walletExists(customer_wallet_id) ==1) {
        	// total number of active voucher the customer has which is active and no empty 
        			int totalVoucherCustomerHas = vdao.customerVoucherNumberTotal(customer_id,customer_wallet_id);
        			if(totalVoucherCustomerHas>=transaction_amount) {
        				// another function to make transactions possible 
        				if(doVoucherTransactionInSql1(customer_id,customer_wallet_id,totalVoucherCustomerHas,transaction_amount)) {
//        					int totalVoucherCustomerHasAfterTransaction = vdao.customerVoucherNumberTotal(customer_id,customer_wallet_id).;
        					
        					// VOUCHER LOGS TRANSACTION
        					tjn_money_transaction_model_Logs LogsC = new tjn_money_transaction_model_Logs();
        					LogsC.setCustomerId(Integer.parseInt(customer_id));
        					LogsC.setCustomerMobNo(Long.parseLong(customer_phn));
        					LogsC.setCardClosingBalance(totalVoucherCustomerHasAfterTransaction(customer_id,customer_wallet_id));
        					LogsC.setCardOpeningBalance(totalVoucherCustomerHas);
        					LogsC.setMode("VOUCHER");
        					LogsC.setTransactionAmt(transaction_amount);
        					LogsC.setTransactionMethod("DEBIT");
        					LogsC.setWalletBalance((double)totalVoucherCustomerHasAfterTransaction(customer_id,customer_wallet_id));
        					LogsC.setTransactionWalletWype(Integer.parseInt(customer_wallet_id));
        					tjnmoneytransDao.save(LogsC);
        					//--------------------------- LOGS C
        					return ResponseHandler.generateResponse("Transaction Successfull"+"\n Voucher Before Transaction "+totalVoucherCustomerHas+
        					""+Bikram 
        							, HttpStatus.OK, "Transaction SuccessFul");
        				}   else
        					return ResponseHandler.generateResponse(customer_phn+" transaction Failed Due To Some Reason "+Bikram , HttpStatus.NOT_FOUND, "Failed @BIKRAM");
        				
        			}else {
        				return ResponseHandler.generateResponseNull(customer_phn +" InSufficient Amount " +Bikram , HttpStatus.NOT_FOUND);
        			}
        			
        	}else {
        		return ResponseHandler.generateResponse(customer_phn + " Has No Such Wallets/Vouchers" +Bikram , HttpStatus.NOT_FOUND, customer_wallet_id);
        	}
        		
        }catch(Exception e) {
        	return ResponseHandler.generateResponseNull(e.toString()+"  Voucher Not Found In DataBase " +Bikram , HttpStatus.NOT_FOUND);
        }
		
        
	}
	
    //check if the trans_amt <= total_voucher left 
    // do transaction + store in Logs
	public boolean doVoucherTransactionInSql1(String customer_id,String customer_wallet_id, int totalVoucherCustomerHas,int transaction_amount) {
		
		try {
			while(transaction_amount>0) {
				
				Tjn_Voucher_Coupons tvc = vdao.getFirstRowDetails(customer_id,customer_wallet_id);
				
				long longbalance=  tvc.getBalance();
				int balance = (int) longbalance;
				
				if(balance >= transaction_amount) {
					
					balance = balance - transaction_amount;
					System.out.println("Transaction Done Completely");
					

					long tvcbalance = balance;
					if(tvcbalance>0) {
						System.out.println(tvcbalance);
						tvc.setBalance(tvcbalance);
						tvc.setStatus("ACTIVE");
						tvc.setIsEmpty("NO");
						vdao.save(tvc);
						return true;
					}
					else {
						System.out.println(tvcbalance);
						tvc.setBalance(tvcbalance);
						tvc.setStatus("INACTIVE");
						tvc.setIsEmpty("YES");
						vdao.save(tvc);
						return true;
					}
					
				}
				
				else {
					transaction_amount =transaction_amount - balance;
					tvc.setBalance(0L);
					tvc.setStatus("INACTIVE");
					tvc.setIsEmpty("YES");
					vdao.save(tvc);
					if(transaction_amount ==0) {
						
						return true;
					}
				}
				
				
			}
		}catch(Exception e) {
			
            e.printStackTrace(); 
            return false; 
		}
	return true;	
	}
	
	
	public boolean doVoucherTransactionInSql(String customer_id, String customer_wallet_id, int totalVoucherCustomerHas, int transaction_amount) {

	    while (transaction_amount > 0) {
	        try {
	            // Get the details of the first row
	            Tjn_Voucher_Coupons tvc = vdao.getFirstRowDetails(customer_id, customer_wallet_id);

	            if (tvc == null) {
	                // Handle the case where there are no available vouchers
	                return false;
	            }

	            long longbalance = tvc.getBalance();
	            int balance = (int) longbalance;

	            if (balance >= transaction_amount) {
	                // Sufficient balance in this voucher
	                balance -= transaction_amount;
	                long tvcbalance = balance;
	                tvc.setBalance(tvcbalance);
	                tvc.setStatus("INACTIVE");
	                tvc.setIsEmpty("YES");
	                vdao.save(tvc);

	                System.out.println("Transaction Done Completely");
	                transaction_amount = 0; // Transaction completed
	            } else {
	                // Insufficient balance in this voucher
	                transaction_amount -= balance;
	                tvc.setBalance(0L);
	                tvc.setStatus("INACTIVE");
	                tvc.setIsEmpty("YES");
	                vdao.save(tvc);
	            }
	        } catch (Exception e) {
	            // Handle exceptions here, such as database errors
	            e.printStackTrace(); // Print the error for debugging purposes
	            return false; // Indicate failure
	        }
	    }

	    return true; // Indicate success
	}

	public int totalVoucherCustomerHasAfterTransaction(String cusId, String WalletId) {
		try {
			return vdao.customerVoucherNumberTotal(cusId,WalletId);
			
		}
		catch(Exception e) {
			return 0;
		}
	}
	
}
