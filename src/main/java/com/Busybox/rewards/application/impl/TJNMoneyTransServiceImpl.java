package com.Busybox.rewards.application.impl;

 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfiguration;

import com.Busybox.rewards.application.commercial.package_comission_impl;
import com.Busybox.rewards.application.dao.CustomerRepository;
import com.Busybox.rewards.application.dao.StoreManagementCommercialRepository;
import com.Busybox.rewards.application.dao.TJNMoneyTransDao;
import com.Busybox.rewards.application.dto.MoneyTransactionResponse;
import com.Busybox.rewards.application.model.CustomerModel;
import com.Busybox.rewards.application.model.ParentToChildTransaction;
import com.Busybox.rewards.application.model.StoreManagementCommercial;
import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;
import com.Busybox.rewards.application.wallet.WalletMasterTransactionLogsImpl;
//import com.Busybox.rewards.application.service.TJNMoneyTransService;
@Service
public class TJNMoneyTransServiceImpl {
	@Autowired private TJNMoneyTransDao tjnmoneytransDao;
    @Autowired private StoreManagementCommercialRepository storeRepository;
    @Autowired private CustomerRepository customerRepo;
	@Autowired private package_comission_impl comissionImpl;
	@Autowired private StoreManagementCommercialService storeService;
	@Autowired private ParentToChildTransactionImpl parentToChildTransactionImpl;
	@Autowired private WalletMasterTransactionLogsImpl walletMasterTransactionLogsImpl;
// --------------------//-------------------// -------------------- // ----------- // -------------

public List<MoneyTransactionResponse> moneydebitDEMO121(String customer_phn1, Double transaction_amount, String transaction_Mode, String wallet_id) {

	List<MoneyTransactionResponse> responses = new ArrayList<>();

   

    try {

        String customer_id = Integer.toString(tjnmoneytransDao.cusPhnToId(customer_phn1));

        //System.out.println(customer_id);

        String customer_phonenumber=customer_phn1;



        // Check if customer phone number exists

        if (!"null".equals(customer_phonenumber) && !customer_phonenumber.isEmpty()) {

            String dao_wallet_id = tjnmoneytransDao.walletifelse(wallet_id);

           

            // Check if wallet ID exists and is not null

            if (dao_wallet_id != null && !dao_wallet_id.isEmpty()) {

                double dao_balance = tjnmoneytransDao.BalCheckwb(wallet_id, customer_id);

                double bill_amount = transaction_amount;

                double true_amount1 = Math.abs(bill_amount);

                String true_amount = Double.toString(true_amount1);



                if ("debit".equalsIgnoreCase(transaction_Mode)) {

                    if (dao_balance >= transaction_amount) {

                        try {

                            tjnmoneytransDao.debit(true_amount, wallet_id, customer_id);

                            //-------------------------------------------------------------DEBIT-------------------------------------------------------------------------------------------------------

                            // customer ID

                            tjn_money_transaction_model_Logs LogsD = new tjn_money_transaction_model_Logs();

                            int value = Integer.parseInt(customer_id);

                            LogsD.setCustomerId(value);

                           // System.out.print(Logs.getCustomerId());    

                            // Phone Number

                            Long phn = Long.parseLong(customer_phonenumber);

                            LogsD.setCustomerMobNo(phn);

                            // Transaction Amount

                            LogsD.setTransactionAmt(transaction_amount);

                            // Wallet Type

                            int wall = Integer.parseInt(wallet_id);

                            LogsD.setTransactionWalletWype(wall);

                            LogsD.setCardOpeningBalance(dao_balance);

                            LogsD.setTransactionMethod(transaction_Mode);

                            LogsD.setWalletBalance(dao_balance);

                            LogsD.setMode(transaction_Mode);

                            LogsD.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                            tjnmoneytransDao.save(LogsD);

                            //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------

                            MoneyTransactionResponse response = new MoneyTransactionResponse();

                            response.setStatus(HttpStatus.OK);
                            
                            response.setMessage("Transaction OK");

                            response.setTransactionAmount(bill_amount);

                            response.setOpeningBalance(dao_balance);

                            response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                            responses.add(response);

                        } catch (Exception debitException) {

                        String errorMessage = "Debit Transaction Failed: " + debitException.getMessage();

                           MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.NOT_FOUND, errorMessage, null, null, null, null);

                           responses.add(response);                        }

                    } else {

                        MoneyTransactionResponse response = new MoneyTransactionResponse();

                        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);

                        response.setTransactionAmount(transaction_amount);

                        response.setOpeningBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        response.setMessage("Transaction Failed Low Balance");

                        responses.add(response);

                    }

                } else if ("credit".equalsIgnoreCase(transaction_Mode)) {

                    try {

                        tjnmoneytransDao.credit(true_amount, wallet_id, customer_id);

                      //-------------------------------------------------------------CREDIT-------------------------------------------------------------------------------------------------------

                        // customer ID

                        tjn_money_transaction_model_Logs LogsC = new tjn_money_transaction_model_Logs();

                        int value = Integer.parseInt(customer_id);

                        LogsC.setCustomerId(value);

                       // System.out.print(Logs.getCustomerId());    

                        // Phone Number

                        Long phn = Long.parseLong(customer_phonenumber);

                        LogsC.setCustomerMobNo(phn);

                        // Transaction Amount

                        LogsC.setTransactionAmt(transaction_amount);

                        // Wallet Type

                        int wall = Integer.parseInt(wallet_id);

                        LogsC.setTransactionWalletWype(wall);

                        LogsC.setCardOpeningBalance(dao_balance);

                        LogsC.setTransactionMethod(transaction_Mode);

                        LogsC.setWalletBalance(dao_balance);

                        LogsC.setMode(transaction_Mode);

                        LogsC.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        tjnmoneytransDao.save(LogsC);

                        //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------

                        MoneyTransactionResponse response = new MoneyTransactionResponse();

                        response.setStatus(HttpStatus.OK);

                        response.setTransactionAmount(transaction_amount);

                        response.setOpeningBalance(dao_balance);

                        response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        responses.add(response);

                    } catch (Exception creditException) {

                    String errorMessage = "Credit Transaction Failed: " + creditException.getMessage();

                        MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.NOT_FOUND, errorMessage, null, null, null, null);

                        responses.add(response);

                    }

                } else {

                    MoneyTransactionResponse response = new MoneyTransactionResponse();

                    response.setStatus(HttpStatus.NOT_FOUND);

                    response.setMessage("Choose Properly Transaction Mode - Debit? Credit?!!");

                    responses.add(response);

                }

            } else {

                MoneyTransactionResponse response = new MoneyTransactionResponse();

                response.setStatus(HttpStatus.NOT_FOUND);

                response.setMessage("Wrong Wallet ID, Wallet ID does not exist....!!");

                responses.add(response);

            }

        } else {

            MoneyTransactionResponse response = new MoneyTransactionResponse();

            response.setStatus(HttpStatus.NOT_FOUND);

            response.setMessage("Customer Does Not Exist/Wrong Customer ID.....!!");

            responses.add(response);

        }

    } catch (Exception e) {

        MoneyTransactionResponse response = new MoneyTransactionResponse();

        response.setStatus(HttpStatus.NOT_FOUND);

        response.setMessage("Error fetching information from database");

        responses.add(response);

    }

   

    return responses;
     
}
// --------------------//-------------------// -------------------- // ----------- // -------------



public List<MoneyTransactionResponse> moneydebitDEMO(String customer_phn, Double transaction_amount, String transaction_Mode, String wallet_id) {
    List<MoneyTransactionResponse> responses = new ArrayList<>();
    
    try {
    	String customer_id = Integer.toString(tjnmoneytransDao.cusPhnToId(customer_phn));

       // System.out.println(customer_id);
    	
        String customer_phonenumber = tjnmoneytransDao.cusIdToPhnNo(customer_id);
        	
        // Check if customer phone number exists
        if (!"null".equals(customer_phonenumber) && !customer_phonenumber.isEmpty()) {
            String dao_wallet_id = tjnmoneytransDao.walletifelse(wallet_id);
            
            // Check if wallet ID exists and is not null
            if (dao_wallet_id != null && !dao_wallet_id.isEmpty()) {
                double dao_balance = tjnmoneytransDao.BalCheckwb(wallet_id, customer_id);
                double bill_amount = transaction_amount;
                double true_amount1 = Math.abs(bill_amount);
                String true_amount = Double.toString(true_amount1);

                if ("debit".equalsIgnoreCase(transaction_Mode)) {
                    if (dao_balance >= transaction_amount) {
                        try {
                            tjnmoneytransDao.debit(true_amount, wallet_id, customer_id);
                            //-------------------------------------------------------------DEBIT-------------------------------------------------------------------------------------------------------
                            //									customer ID
                            tjn_money_transaction_model_Logs LogsD = new tjn_money_transaction_model_Logs();
                            int value = Integer.parseInt(customer_id);
                            LogsD.setCustomerId(value);
                           // System.out.print(Logs.getCustomerId());     
                            //									Phone Number 
                            Long phn = Long.parseLong(customer_phonenumber);
                            LogsD.setCustomerMobNo(phn);
                            //									Transaction Amount 
                            LogsD.setTransactionAmt(transaction_amount);
                            //									Wallet Type
                            int wall = Integer.parseInt(wallet_id);
                            LogsD.setTransactionWalletWype(wall);
                            LogsD.setCardOpeningBalance(dao_balance);
                            LogsD.setTransactionMethod(transaction_Mode);
                            LogsD.setWalletBalance(dao_balance);
                            LogsD.setMode(transaction_Mode);
                            LogsD.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));
                            tjnmoneytransDao.save(LogsD);
                            //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------
                            MoneyTransactionResponse response = new MoneyTransactionResponse();
                            response.setStatus(HttpStatus.OK);
                            response.setTransactionAmount(bill_amount);
                            response.setOpeningBalance(dao_balance);
                            response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));
                            
                            responses.add(response);
                        } catch (Exception debitException) {
                        	 String errorMessage = "Debit Transaction Failed: " + debitException.getMessage();
                        	    MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.OK, errorMessage, null, null, null, null);
                        	    responses.add(response);                        }
                    } else {
                        MoneyTransactionResponse response = new MoneyTransactionResponse();
                        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
                        response.setTransactionAmount(transaction_amount);
                        response.setOpeningBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));
                        response.setMessage("Transaction Failed");
                        responses.add(response);
                    }
                } else if ("credit".equalsIgnoreCase(transaction_Mode)) {
                    try {
                        tjnmoneytransDao.credit(true_amount, wallet_id, customer_id);
                      //-------------------------------------------------------------CREDIT-------------------------------------------------------------------------------------------------------credit logs, first checcking the 
                        
                        //									customer ID
                        tjn_money_transaction_model_Logs LogsC = new tjn_money_transaction_model_Logs();
                        int value = Integer.parseInt(customer_id);
                        LogsC.setCustomerId(value);
                       // System.out.print(Logs.getCustomerId());     
                        //									Phone Number 
                        Long phn = Long.parseLong(customer_phonenumber);
                        LogsC.setCustomerMobNo(phn);
                        //									Transaction Amount 
                        LogsC.setTransactionAmt(transaction_amount);
                        //									Wallet Type
                        int wall = Integer.parseInt(wallet_id);
                        LogsC.setTransactionWalletWype(wall);
                        LogsC.setCardOpeningBalance(dao_balance);
                        LogsC.setTransactionMethod(transaction_Mode);
                        LogsC.setWalletBalance(dao_balance);
                        LogsC.setMode(transaction_Mode);
                        LogsC.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));
                        tjnmoneytransDao.save(LogsC);
                        //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------
                        MoneyTransactionResponse response = new MoneyTransactionResponse();
                        response.setStatus(HttpStatus.OK);
                        response.setTransactionAmount(transaction_amount);
                        response.setOpeningBalance(dao_balance);
                        response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));
                        responses.add(response);
                    } catch (Exception creditException) {
                    	String errorMessage = "Credit Transaction Failed: " + creditException.getMessage();
                        MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.OK, errorMessage, null, null, null, null);
                        responses.add(response);
                    }
                } else {
                    MoneyTransactionResponse response = new MoneyTransactionResponse();
                    response.setStatus(HttpStatus.NOT_FOUND);
                    response.setMessage("Choose Properly Transaction Mode - Debit? Credit?!!");
                    responses.add(response);
                }
            } else {
                MoneyTransactionResponse response = new MoneyTransactionResponse();
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage("Wrong Wallet ID, Wallet ID does not exist....!!");
                responses.add(response);
            }
        } else {
            MoneyTransactionResponse response = new MoneyTransactionResponse();
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Customer Does Not Exist/Wrong Customer ID.....!!");
            responses.add(response);
        }
    } catch (Exception e) {
        MoneyTransactionResponse response = new MoneyTransactionResponse();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setMessage("Incorrect Data, check Payload");
        responses.add(response);
    }
    
    return responses;
}


public void Logs() {
	
}

public  List<MoneyTransactionResponse> VoucherTransaction(String customerPhn, String voucherName, int voucherCount) {
	// TODO Auto-generated method stub
	 List<MoneyTransactionResponse> responses = new ArrayList<>();
	int customerId= tjnmoneytransDao.getIdFromPhoneNumber(customerPhn);	
	int walletId = tjnmoneytransDao.getWalletIdFromVoucherName(voucherName);
	
	// check if exists in db (returns null if not )
	Object abc = tjnmoneytransDao.checkifCustomerHasVoucher(customerId, walletId);
	if(abc != null) {
		
		String customer_id = String.valueOf(customerId);
        Double transaction_amount = (double) voucherCount;
		String transaction_mode = "CREDIT";
		String wallet_Id = String.valueOf(walletId);
		// String customer_phn, Double transaction_amount, String transaction_Mode, String wallet_id
		responses = moneydebitDEMO121(customerPhn,transaction_amount,transaction_mode,wallet_Id);
		return responses;
	}else  {
		tjnmoneytransDao.insertWalletBalance(customerId, walletId, voucherCount, "ACTIVE");
		MoneyTransactionResponse response = new MoneyTransactionResponse();
		response.setStatus(HttpStatus.OK);
		response.setMessage("Added Cupons to "+ customerPhn);
		
		responses.add(response);
		return responses;
	}
	
	///return responses;	
}


public List<MoneyTransactionResponse> DoTransactionBefore(String customer_phn1, Double transaction_amount, String transaction_Mode, String wallet_id) {

	List<MoneyTransactionResponse> responses = new ArrayList<>();

   
        //   Object object  = new HashMap<String, String>();
           
    try {

        String customer_id = Integer.toString(tjnmoneytransDao.cusPhnToId(customer_phn1));

       // System.out.println(customer_id);

        String customer_phonenumber=customer_phn1;



        // Check if customer phone number exists

        if (!"null".equals(customer_phonenumber) && !customer_phonenumber.isEmpty()) {

            String dao_wallet_id = tjnmoneytransDao.walletifelse(wallet_id);

           

            // Check if wallet ID exists and is not null

            if (dao_wallet_id != null && !dao_wallet_id.isEmpty()) {

                double dao_balance = tjnmoneytransDao.BalCheckwb(wallet_id, customer_id);

                double bill_amount = transaction_amount;

                double true_amount1 = Math.abs(bill_amount);

                String true_amount = Double.toString(true_amount1);



                if ("debit".equalsIgnoreCase(transaction_Mode)) {

                    if (dao_balance >= transaction_amount) {

                        try {

                            tjnmoneytransDao.debit(true_amount, wallet_id, customer_id);                            
                            

                            //-------------------------------------------------------------DEBIT-------------------------------------------------------------------------------------------------------

                            // customer ID

                            tjn_money_transaction_model_Logs LogsD = new tjn_money_transaction_model_Logs();

                            int value = Integer.parseInt(customer_id);

                            LogsD.setCustomerId(value);

                           // System.out.print(Logs.getCustomerId());    

                            // Phone Number

                            Long phn = Long.parseLong(customer_phonenumber);

                            LogsD.setCustomerMobNo(phn);

                            // Transaction Amount

                            LogsD.setTransactionAmt(transaction_amount);

                            // Wallet Type

                            int wall = Integer.parseInt(wallet_id);

                            LogsD.setTransactionWalletWype(wall);

                            LogsD.setCardOpeningBalance(dao_balance);

                            LogsD.setTransactionMethod(transaction_Mode);

                            LogsD.setWalletBalance(dao_balance);

                            LogsD.setMode(transaction_Mode);

                            LogsD.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                            tjnmoneytransDao.save(LogsD);

                            //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------

                            MoneyTransactionResponse response = new MoneyTransactionResponse();

                            response.setStatus(HttpStatus.OK);
                            
                            response.setMessage("Transaction OK");

                            response.setTransactionAmount(bill_amount);

                            response.setOpeningBalance(dao_balance);

                            response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                            responses.add(response);

                        } catch (Exception debitException) {

                        String errorMessage = "Debit Transaction Failed: " + debitException.getMessage();

                           MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.NOT_FOUND, errorMessage, null, null, null, null);

                           responses.add(response);                        }

                    } else {

                        MoneyTransactionResponse response = new MoneyTransactionResponse();

                       
                        
                        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);

                        response.setTransactionAmount(transaction_amount);

                        response.setOpeningBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        response.setMessage("Transaction Failed Low Balance");

                        responses.add(response);

                    }

                } else if ("credit".equalsIgnoreCase(transaction_Mode)) {

                    try {
                       

                        tjnmoneytransDao.credit(true_amount, wallet_id, customer_id);
                        int s = tjnmoneytransDao.checkParentId(customer_id);
                        int w = Integer.parseInt(wallet_id);
                        System.out.println("---------------------------");
                        if(s==1 && w ==1) {
                        	String customerIdOfParent =tjnmoneytransDao.getParentId(customer_id);
                        	System.out.println(customerIdOfParent);
                        	addComissionToParent(customerIdOfParent, transaction_amount,customer_phn1);
                        }
                        

                      //-------------------------------------------------------------CREDIT-------------------------------------------------------------------------------------------------------

                        // customer ID

                        tjn_money_transaction_model_Logs LogsC = new tjn_money_transaction_model_Logs();

                        int value = Integer.parseInt(customer_id);

                        LogsC.setCustomerId(value);

                       // System.out.print(Logs.getCustomerId());    

                        // Phone Number

                        Long phn = Long.parseLong(customer_phonenumber);

                        LogsC.setCustomerMobNo(phn);

                        // Transaction Amount

                        LogsC.setTransactionAmt(transaction_amount);

                        // Wallet Type

                        int wall = Integer.parseInt(wallet_id);

                        LogsC.setTransactionWalletWype(wall);

                        LogsC.setCardOpeningBalance(dao_balance);

                        LogsC.setTransactionMethod(transaction_Mode);

                        LogsC.setWalletBalance(dao_balance);

                        LogsC.setMode(transaction_Mode);

                        LogsC.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        tjnmoneytransDao.save(LogsC);
                       

                        //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------

                        MoneyTransactionResponse response = new MoneyTransactionResponse();

                        response.setStatus(HttpStatus.OK);

                        response.setTransactionAmount(transaction_amount);

                        response.setOpeningBalance(dao_balance);

                        response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));
                        
                        response.setMessage("Transaction Successful");

                        responses.add(response);

                    } catch (Exception creditException) {

                    String errorMessage = "Credit Transaction Failed: " + creditException.getMessage();

                        MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.NOT_FOUND, errorMessage, null, null, null, null);

                        responses.add(response);

                    }

                } else {

                    MoneyTransactionResponse response = new MoneyTransactionResponse();

                    response.setStatus(HttpStatus.NOT_FOUND);

                    response.setMessage("Choose Properly Transaction Mode - Debit? Credit?!!");

                    responses.add(response);

                }

            } else {

                MoneyTransactionResponse response = new MoneyTransactionResponse();

                response.setStatus(HttpStatus.NOT_FOUND);

                response.setMessage("Wrong Wallet ID, Wallet ID does not exist....!!");

                responses.add(response);

            }

        } else {

            MoneyTransactionResponse response = new MoneyTransactionResponse();

            response.setStatus(HttpStatus.NOT_FOUND);

            response.setMessage("Customer Does Not Exist/Wrong Customer ID.....!!");

            responses.add(response);

        }

    } catch (Exception e) {

        MoneyTransactionResponse response = new MoneyTransactionResponse();

        response.setStatus(HttpStatus.NOT_FOUND);

        response.setMessage("Error fetching information from database");

        responses.add(response);

    }

   

    return responses;
     
}


// SUGATA DO TRANSACTION
public List<MoneyTransactionResponse> DoTransaction(String customer_phn1, Double transaction_amount, String transaction_Mode, String wallet_id) {

	List<MoneyTransactionResponse> responses = new ArrayList<>();

   
        //   Object object  = new HashMap<String, String>();
           
    try {

        String customer_id = Integer.toString(tjnmoneytransDao.cusPhnToId(customer_phn1));

       // System.out.println(customer_id);

        String customer_phonenumber=customer_phn1;



        // Check if customer phone number exists

        if (!"null".equals(customer_phonenumber) && !customer_phonenumber.isEmpty()) {

            String dao_wallet_id = tjnmoneytransDao.walletifelse(wallet_id);

           

            // Check if wallet ID exists and is not null

            if (dao_wallet_id != null && !dao_wallet_id.isEmpty()) {

                double dao_balance = tjnmoneytransDao.BalCheckwb(wallet_id, customer_id);

                double bill_amount = transaction_amount;

                double true_amount1 = Math.abs(bill_amount);

                String true_amount = Double.toString(true_amount1);



                if ("debit".equalsIgnoreCase(transaction_Mode)) {

                    if (dao_balance >= transaction_amount) {

                        try {

                            tjnmoneytransDao.debit(true_amount, wallet_id, customer_id);                            
                            

                            //-------------------------------------------------------------DEBIT-------------------------------------------------------------------------------------------------------

                            // customer ID

                            tjn_money_transaction_model_Logs LogsD = new tjn_money_transaction_model_Logs();

                            int value = Integer.parseInt(customer_id);

                            LogsD.setCustomerId(value);

                           // System.out.print(Logs.getCustomerId());    

                            // Phone Number

                            Long phn = Long.parseLong(customer_phonenumber);

                            LogsD.setCustomerMobNo(phn);

                            // Transaction Amount

                            LogsD.setTransactionAmt(transaction_amount);

                            // Wallet Type

                            int wall = Integer.parseInt(wallet_id);

                            LogsD.setTransactionWalletWype(wall);

                            LogsD.setCardOpeningBalance(dao_balance);

                            LogsD.setTransactionMethod(transaction_Mode);

                            LogsD.setWalletBalance(dao_balance);

                            LogsD.setMode("FLOAT");

                            LogsD.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                            tjnmoneytransDao.save(LogsD);

                            //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------

                            MoneyTransactionResponse response = new MoneyTransactionResponse();

                            response.setStatus(HttpStatus.OK);
                            
                            response.setMessage("Transaction OK");

                            response.setTransactionAmount(bill_amount);

                            response.setOpeningBalance(dao_balance);

                            response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                            responses.add(response);

                        } catch (Exception debitException) {

                        String errorMessage = "Debit Transaction Failed: " + debitException.getMessage();

                           MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.NOT_FOUND, errorMessage, null, null, null, null);

                           responses.add(response);                        }

                    } else {

                        MoneyTransactionResponse response = new MoneyTransactionResponse();

                       
                        
                        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);

                        response.setTransactionAmount(transaction_amount);

                        response.setOpeningBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        response.setMessage("Transaction Failed Low Balance");

                        responses.add(response);

                    }

                } else if ("credit".equalsIgnoreCase(transaction_Mode)) {

                    try {
                       

                        tjnmoneytransDao.credit(true_amount, wallet_id, customer_id);
                        int s = tjnmoneytransDao.checkParentId(customer_id);
                        int w = Integer.parseInt(wallet_id);
                        System.out.println("---------------------------");
                        if(s==1 && w ==1) {
                        	String customerIdOfParent =tjnmoneytransDao.getParentId(customer_id);
                        	System.out.println(customerIdOfParent);
                        	addComissionToParent(customerIdOfParent, transaction_amount,customer_phn1);
                        }
                        

                      //-------------------------------------------------------------CREDIT-------------------------------------------------------------------------------------------------------

                        // customer ID

                        tjn_money_transaction_model_Logs LogsC = new tjn_money_transaction_model_Logs();

                        int value = Integer.parseInt(customer_id);

                        LogsC.setCustomerId(value);

                       // System.out.print(Logs.getCustomerId());    

                        // Phone Number

                        Long phn = Long.parseLong(customer_phonenumber);

                        LogsC.setCustomerMobNo(phn);

                        // Transaction Amount

                        LogsC.setTransactionAmt(transaction_amount);

                        // Wallet Type

                        int wall = Integer.parseInt(wallet_id);

                        LogsC.setTransactionWalletWype(wall);

                        LogsC.setCardOpeningBalance(dao_balance);

                        LogsC.setTransactionMethod(transaction_Mode);

                        LogsC.setWalletBalance(dao_balance);

                        LogsC.setMode("FLOAT");

                        LogsC.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        tjnmoneytransDao.save(LogsC);
                       

                        //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------

                        MoneyTransactionResponse response = new MoneyTransactionResponse();

                        response.setStatus(HttpStatus.OK);

                        response.setTransactionAmount(transaction_amount);

                        response.setOpeningBalance(dao_balance);

                        response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));
                        
                        response.setMessage("Transaction Successful");

                        responses.add(response);

                    } catch (Exception creditException) {

                    String errorMessage = "Credit Transaction Failed: " + creditException.getMessage();

                        MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.NOT_FOUND, errorMessage, null, null, null, null);

                        responses.add(response);

                    }

                } else {

                    MoneyTransactionResponse response = new MoneyTransactionResponse();

                    response.setStatus(HttpStatus.NOT_FOUND);

                    response.setMessage("Choose Properly Transaction Mode - Debit? Credit?!!");

                    responses.add(response);

                }

            } else {

                MoneyTransactionResponse response = new MoneyTransactionResponse();

                response.setStatus(HttpStatus.NOT_FOUND);

                response.setMessage("Wrong Wallet ID, Wallet ID does not exist....!!");

                responses.add(response);

            }

        } else {

            MoneyTransactionResponse response = new MoneyTransactionResponse();

            response.setStatus(HttpStatus.NOT_FOUND);

            response.setMessage("Customer Does Not Exist/Wrong Customer ID.....!!");

            responses.add(response);

        }

    } catch (Exception e) {

        MoneyTransactionResponse response = new MoneyTransactionResponse();

        response.setStatus(HttpStatus.NOT_FOUND);

        response.setMessage("Error fetching information from database");

        responses.add(response);

    }

   

    return responses;
     
}
//
public void addComissionToParent(String customerIdOfParent,double transaction_amt, String childcustomer_phn1) {
	double dao_balance = tjnmoneytransDao.BalCheckwb("2", customerIdOfParent);
	//child
	CustomerModel customerModel = customerRepo.findbyPhoneNumber(childcustomer_phn1);
	String Package = customerModel.getPackage_Id().toString(); 
	double amount =comissionImpl.checkComissionAmountMaster(transaction_amt,Package);
	String amountAsString = String.valueOf(amount);
	tjnmoneytransDao.credit(amountAsString, "2", customerIdOfParent);
	double final_Balance = tjnmoneytransDao.BalCheckwb("2", customerIdOfParent);
 // 				LOGS IN 
//	tjn_money_transaction_model_Logs LOG = new tjn_money_transaction_model_Logs();
	            
	tjnmoneytransDao.insertIntoReferralLogs(
			childcustomer_phn1,
			customerIdOfParent, 
			final_Balance, 
			dao_balance,
			amount, 
			final_Balance
			);
	
	//addParentToChildLink(customerModel.getCustomer_id(), amount ,childcustomer_phn1, childcustomer_phn1, customerModel.getCustomer_name() );
	
	//				LOGS OUT
}

	public void addParentToChildLink(String customerIdOfParent,double transaction_amt, String customer_phn1, 
			String childPhoneNumber, String Childname) {
		
		// Parent 
		CustomerModel customerModel = customerRepo.findbyPhoneNumber(customer_phn1);
		// Child
		
		ParentToChildTransaction parentToChildTransaction = new ParentToChildTransaction();
        parentToChildTransaction.setAccountTransaction(transaction_amt);
        parentToChildTransaction.setParentId(customerIdOfParent);
        parentToChildTransaction.setChildName(Childname);
        parentToChildTransaction.setChildPhoneNumber(childPhoneNumber);
        parentToChildTransaction.setParentName(customerModel.getCustomer_name());
        parentToChildTransaction.setParentPhoneNumber(customerModel.getCustomer_mobno());
        
        parentToChildTransactionImpl.saveReferralTransaction(parentToChildTransaction);
	}


public boolean checkIfCustomerExists(String cus_id) {
	return tjnmoneytransDao.customerExistsReturnsCount(cus_id)==1;
	
}

public boolean checkCustomerStatus(String cus_id) {
	return tjnmoneytransDao.checkCustomerStatusDao(cus_id)==1;
}


public List<MoneyTransactionResponse> DoTransactionStore(String customer_phn1, Double transaction_amount, String transaction_Mode, String wallet_id, String storeId) {

	List<MoneyTransactionResponse> responses = new ArrayList<>();

		String childIdFunc = null;
        //   Object object  = new HashMap<String, String>();
           
    try {
    		
    	Long storeintId = Long.parseLong(storeId);
    	
    	// demon
    	Optional<StoreManagementCommercial> storeOptional = storeRepository.findById(storeintId);
    	if(storeOptional.isPresent()) {
    		
    		// STORE Existance
    		if(storeintId<=0) {
//        		MoneyTransactionResponse response = new MoneyTransactionResponse();
        		 MoneyTransactionResponse response = new MoneyTransactionResponse();

                 response.setStatus(HttpStatus.NOT_FOUND);

                 response.setMessage("Store Id invalid!!");

                 responses.add(response);
        	}
        	
        	StoreManagementCommercial store = storeOptional.get();
        	double storeBalance = store.getStoreWallet();
        	String storeStatus = store.getStoreStatus();
        	// ACTIVE INACTIVE
        	
        	if(storeStatus.equalsIgnoreCase("INACTIVE")) {
        		MoneyTransactionResponse response = new MoneyTransactionResponse();

                response.setStatus(HttpStatus.NOT_FOUND);

                response.setMessage("INACTIVE STORE!!");

                responses.add(response);
        	}
        	// STORE BALANCE
        	if(storeBalance < 10) {
        		MoneyTransactionResponse response = new MoneyTransactionResponse();

                response.setStatus(HttpStatus.UNAUTHORIZED);

                response.setMessage("Store HAS LOW BALANCE!!");

                responses.add(response);
                
                return responses;
        	}
        	
    		
    	}else {
    		throw new RuntimeException("Store not found here");
    	}
    	// demon
    	
    	
    	
        String customer_id = Integer.toString(tjnmoneytransDao.cusPhnToId(customer_phn1));

       // System.out.println(customer_id);

        String customer_phonenumber=customer_phn1;



        // Check if customer phone number exists

        if (!"null".equals(customer_phonenumber) && !customer_phonenumber.isEmpty()) {

            String dao_wallet_id = tjnmoneytransDao.walletifelse(wallet_id);

           

            // Check if wallet ID exists and is not null

            if (dao_wallet_id != null && !dao_wallet_id.isEmpty()) {

                double dao_balance = tjnmoneytransDao.BalCheckwb(wallet_id, customer_id);

                double bill_amount = transaction_amount;

                double true_amount1 = Math.abs(bill_amount);

                String true_amount = Double.toString(true_amount1);



                if ("debit".equalsIgnoreCase(transaction_Mode)) {

                    if (dao_balance >= transaction_amount) {

                        try {
                        	
                        	//////////////////////////////// store id based DEBIT, STOPS DEBIT IF THE BALANCE IS TOO LOW 
//                        	if(storeOptional.isPresent()) {
//
//                        		StoreManagementCommercial store = storeOptional.get();
//                        		double storeBalance = store.getStoreWallet();
//                        		// STORE BALANCE
//                        		if(storeBalance <= 0) {
//                        			MoneyTransactionResponse response = new MoneyTransactionResponse();
//
//                        			response.setStatus(HttpStatus.UNAUTHORIZED);
//
//                        			response.setMessage("Store HAS LOW BALANCE!!");
//
//                        			responses.add(response);
//                        		}	
//                        	}

                            tjnmoneytransDao.debit(true_amount, wallet_id, customer_id);             
                            
//                            storeService.handleTransaction(storeintId, transaction_amount, customer_phonenumber);

                            

                            //-------------------------------------------------------------DEBIT-------------------------------------------------------------------------------------------------------

                            // customer ID

                            tjn_money_transaction_model_Logs LogsD = new tjn_money_transaction_model_Logs();

                            int value = Integer.parseInt(customer_id);

                            LogsD.setCustomerId(value);

                           // System.out.print(Logs.getCustomerId());    

                            // Phone Number

                            Long phn = Long.parseLong(customer_phonenumber);

                            LogsD.setCustomerMobNo(phn);

                            // Transaction Amount

                            LogsD.setTransactionAmt(transaction_amount);

                            // Wallet Type

                            int wall = Integer.parseInt(wallet_id);

                            LogsD.setTransactionWalletWype(wall);

                            LogsD.setCardOpeningBalance(dao_balance);

                            LogsD.setTransactionMethod(transaction_Mode);

                            LogsD.setWalletBalance(dao_balance);

                            LogsD.setMode("FLOAT");

                            LogsD.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                            tjn_money_transaction_model_Logs L= tjnmoneytransDao.save(LogsD);
                            
                            walletMasterTransactionLogsImpl.insertTransactionRecordInWalletBalanceLogs(customer_id,
                            		transaction_amount,
                            		"DEBIT",
                            		L,
                            		childIdFunc,
                            		wallet_id
                            		);
                            
                            

                            //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------

                            MoneyTransactionResponse response = new MoneyTransactionResponse();

                            response.setStatus(HttpStatus.OK);
                            
                            response.setMessage("Transaction OK");

                            response.setTransactionAmount(bill_amount);

                            response.setOpeningBalance(dao_balance);

                            response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                            responses.add(response);

                        } catch (Exception debitException) {

                        String errorMessage = "Debit Transaction Failed: " + debitException.getMessage();

                           MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.NOT_FOUND, errorMessage, null, null, null, null);

                           responses.add(response);                        }

                    } else {

                        MoneyTransactionResponse response = new MoneyTransactionResponse();

                       
                        
                        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);

                        response.setTransactionAmount(transaction_amount);

                        response.setOpeningBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        response.setMessage("Transaction Failed Low Balance");

                        responses.add(response);

                    }

                } else if ("credit".equalsIgnoreCase(transaction_Mode)) {

                    try {
                       
                    		////////////////////////////////// STORE ID BASED CREDIT ???????????????????

                    	if(storeOptional.isPresent()) {

                    		StoreManagementCommercial store = storeOptional.get();
                    		double storeBalance = store.getStoreWallet();
                    		// STORE BALANCE
                    		if(storeBalance < 100) {
                    			MoneyTransactionResponse response = new MoneyTransactionResponse();

                    			response.setStatus(HttpStatus.UNAUTHORIZED);

                    			response.setMessage("Store HAS LOW BALANCE!!");

                    			responses.add(response);
                    		}	
                    	}
                    	
                    	
                        tjnmoneytransDao.credit(true_amount, wallet_id, customer_id);
                        int s = tjnmoneytransDao.checkParentId(customer_id);
                        int w = Integer.parseInt(wallet_id);
                        System.out.println("---------------------------");
                        if(s==1 && w ==1) {
                        	String customerIdOfParent =tjnmoneytransDao.getParentId(customer_id);
                        	System.out.println(customerIdOfParent);
                        	// 
                        	addComissionToParent(customerIdOfParent, transaction_amount,customer_phn1);
                        }
                        
                        storeService.handleTransaction(storeintId, transaction_amount,customer_phonenumber);

                      //-------------------------------------------------------------CREDIT-------------------------------------------------------------------------------------------------------

                        // customer ID

                        tjn_money_transaction_model_Logs LogsC = new tjn_money_transaction_model_Logs();

                        int value = Integer.parseInt(customer_id);

                        LogsC.setCustomerId(value);

                       // System.out.print(Logs.getCustomerId());    

                        // Phone Number

                        Long phn = Long.parseLong(customer_phonenumber);

                        LogsC.setCustomerMobNo(phn);

                        // Transaction Amount

                        LogsC.setTransactionAmt(transaction_amount);

                        // Wallet Type

                        int wall = Integer.parseInt(wallet_id);

                        LogsC.setTransactionWalletWype(wall);

                        LogsC.setCardOpeningBalance(dao_balance);

                        LogsC.setTransactionMethod(transaction_Mode);

                        LogsC.setWalletBalance(dao_balance);

                        LogsC.setMode("FLOAT");

                        LogsC.setCardClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));

                        tjnmoneytransDao.save(LogsC);
                        
                        
                        
                        

                        //----------------LOG--------------------------------------------\\-----------------------------------------------------------------------------------------------------

                        MoneyTransactionResponse response = new MoneyTransactionResponse();

                        response.setStatus(HttpStatus.OK);

                        response.setTransactionAmount(transaction_amount);

                        response.setOpeningBalance(dao_balance);

                        response.setClosingBalance(tjnmoneytransDao.BalCheckwb(wallet_id, customer_id));
                        
                        response.setMessage("Transaction Successful");

                        responses.add(response);

                    } catch (Exception creditException) {

                    String errorMessage = "Credit Transaction Failed: " + creditException.getMessage();

                        MoneyTransactionResponse response = new MoneyTransactionResponse(HttpStatus.NOT_FOUND, errorMessage, null, null, null, null);

                        responses.add(response);

                    }

                } else {

                    MoneyTransactionResponse response = new MoneyTransactionResponse();

                    response.setStatus(HttpStatus.NOT_FOUND);

                    response.setMessage("Choose Properly Transaction Mode - Debit? Credit?!!");

                    responses.add(response);

                }

            } else {

                MoneyTransactionResponse response = new MoneyTransactionResponse();

                response.setStatus(HttpStatus.NOT_FOUND);

                response.setMessage("Wrong Wallet ID, Wallet ID does not exist....!!");

                responses.add(response);

            }

        } else {

            MoneyTransactionResponse response = new MoneyTransactionResponse();

            response.setStatus(HttpStatus.NOT_FOUND);

            response.setMessage("Customer Does Not Exist/Wrong Customer ID.....!!");

            responses.add(response);

        }

    } catch (Exception e) {

        MoneyTransactionResponse response = new MoneyTransactionResponse();

        response.setStatus(HttpStatus.NOT_FOUND);

        response.setMessage("Error fetching information from database");

        responses.add(response);

    }

   

    return responses;
     
}


//public boolean checkIfCustomerIsActive(String cus_id) {
//	return tjnmoneytransDao.checkIfCustomerIsActive(cus_id)==1;
//}


}


