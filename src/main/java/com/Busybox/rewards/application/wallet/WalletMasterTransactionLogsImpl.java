package com.Busybox.rewards.application.wallet;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.CustomerRepository;
import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;
import com.Busybox.rewards.application.model.CustomerModel;
import com.Busybox.rewards.application.model.tjn_Wallet_types_model;
import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;

@Service
public class WalletMasterTransactionLogsImpl {

	
	@Autowired private ReferralBalanceDao referralBalanceDao;
	@Autowired private WalletBalanceDao walletBalanceDao;
	@Autowired private VoucherBalanceDao voucherBalanceDao;
	@Autowired private tjn_wallet_types_dao tjn_wallet_types_dao;
	@Autowired private CustomerRepository customerRepository;
	
	
	 public void insertTransactionRecordInWalletBalanceLogs(String  customerId,
			Double transactionAmount, 
			 String transactionType,
			 tjn_money_transaction_model_Logs log,
			 String childId,
			 String walletId) {
	        

	        try {
	            
	        	tjn_Wallet_types_model walletDetails=tjn_wallet_types_dao.findById(Integer.parseInt(walletId)).orElse(null);
	        	//Optional<CustomerModel> mainCustomer = customerRepository.findById(Integer.parseInt(customerId).orElse(null);
	        	
	            // Create a new WalletBalanceTransactionRecord entity
	            WalletBalanceTransactionRecord transactionRecord = new WalletBalanceTransactionRecord();
	            transactionRecord.setCustomerId(customerId);
	            transactionRecord.setTransactionAmount(transactionAmount);
	            transactionRecord.setTransactionType(transactionType);
	            transactionRecord.setDateOfTransaction(log.getCredited_on()); // Assuming your entity expects a String for DateOfTransaction
	            transactionRecord.setCreatedBy("TJN"); // Replace with the actual user information
	            // Wallet
	            transactionRecord.setWalletName(walletDetails.getWallet_name()); // Replace with actual data
	            transactionRecord.setWalletId(walletId); // Replace with actual data
	            // Customer Details
	            transactionRecord.setCustomerName("some_customer_name"); // Replace with actual data
	            transactionRecord.setCustomerPhoneNumber("some_phone_number"); // Replace with actual data
	            // Transaction Details
	            transactionRecord.setTransactionId("some_transaction_id"); // Replace with actual data
	            transactionRecord.setWalletBalance("some_wallet_balance"); // Replace with actual data
	            transactionRecord.setOpeningBalance("some_opening_balance"); // Replace with actual data
	            transactionRecord.setClosingBalance("some_closing_balance"); // Replace with actual data
	            // Store Details
	            transactionRecord.setStoreId("some_store_id"); // Replace with actual data
	            transactionRecord.setUserId("some_user_id"); // Replace with actual data
	            transactionRecord.setPackageOfCustomer("some_package"); // Replace with actual data
	            transactionRecord.setCreatedBy("CUSTOMER CONTROLLER CLAIMS");	            // Child Details
	            if(childId != null) {
	            	transactionRecord.setChildrelated("N/A"); // Replace with actual data
	            	transactionRecord.setChildStoreId("N/A"); // Replace with actual data
	            	transactionRecord.setChildReceivedMoney(false); // Replace with actual data
	            	transactionRecord.setReferralTableIdChild("N/A"); // Replace with actual data
	            }
//	            else {
//	            	transactionRecord.setChildrelated("N/A"); // Replace with actual data
//		            transactionRecord.setChildStoreId("N/A"); // Replace with actual data
//		            transactionRecord.setChildReceivedMoney(true); // Replace with actual data
//		            transactionRecord.setReferralTableIdChild("N/A"); // Replace with actual data	
//	            }
	            // Save the entity to the database
	            walletBalanceDao.save(transactionRecord);
	        } catch (NumberFormatException e) {
	            // Handle invalid number format
	            e.printStackTrace();
	        }
	    }
	}
