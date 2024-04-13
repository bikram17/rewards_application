package com.Busybox.rewards.application.commercial;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.TJNMoneyTransDao;
import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;


// comission from commercial package
@Service
public class package_comission_impl {

	@Autowired package_comission_dao dao;
	@Autowired package_master_dao mdao;
	@Autowired private  TJNMoneyTransDao tjnmoneytransDao;
	
	public Object checkComissionAmount(Double amount, String pid) {
	    try {
	        Package_Comission_Details pcd = dao.getComissionAmount(amount, pid);

	        if (pcd == null) {
	            return ResponseHandler.generateResponse(pcd, HttpStatus.OK, "Null");
	        }

	        long packageIdInComission = pcd.getPackage_Id();
	        Optional<Package_Master_Model> optionalMasterEntity = mdao.findById(packageIdInComission);

	        if (optionalMasterEntity.isPresent()) {
	            Package_Master_Model master = optionalMasterEntity.get();
	            Long masterId = master.getId();

	            if (masterId.equals(packageIdInComission)) {
	                double comission_amount = pcd.getComissionAmount();
	                String ISFLAT = pcd.getIsFlat();

	                if ("YES".equalsIgnoreCase(ISFLAT)) {
	                    double final_amount_to_be_added = comission_amount;
	                 //   dao.addIntoWallets(final_amount_to_be_added, customer_id);
	                    return ResponseHandler.generateResponse(final_amount_to_be_added, HttpStatus.OK, "Amount Added");
	                }

	                if ("NO".equalsIgnoreCase(ISFLAT)) {
	                    double final_amount_to_be_added = (amount * comission_amount)/100 ;
	                //    dao.addIntoWallets(final_amount_to_be_added, customer_id);
	                    return ResponseHandler.generateResponse(final_amount_to_be_added, HttpStatus.OK, "Amount Added");
	                }

	                return ResponseHandler.generateResponse("Not Working", HttpStatus.NOT_FOUND, "Failed");
	            } else {
	                return ResponseHandler.generateResponse(masterId, HttpStatus.NOT_FOUND, "Not Found Data");
	            }
	        } else {
	            return ResponseHandler.generateResponse(optionalMasterEntity, HttpStatus.NOT_FOUND, "Data Not Found");
	        }
	    } catch (Exception e) {
	        return ResponseHandler.generateResponse(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Get Data");
	    }
	}
	
	
	
	public double checkComissionAmountMaster(Double amount, String pid) {
	    try {
	        Package_Comission_Details pcd = dao.getComissionAmount(amount,pid);

	        if (pcd == null) {
	            return 0;
	        }

	        long packageIdInComission = pcd.getPackage_Id();
	        Optional<Package_Master_Model> optionalMasterEntity = mdao.findById(packageIdInComission);

	        if (optionalMasterEntity.isPresent()) {
	            Package_Master_Model master = optionalMasterEntity.get();
	            Long masterId = master.getId();

	            if (masterId.equals(packageIdInComission)) {
	                double comission_amount = pcd.getComissionAmount();
	                String ISFLAT = pcd.getIsFlat();

	                if ("YES".equalsIgnoreCase(ISFLAT)) {
	                    double final_amount_to_be_added = comission_amount;
	                 //   dao.addIntoWallets(final_amount_to_be_added, customer_id);
	                    return final_amount_to_be_added;
	                }

	                if ("NO".equalsIgnoreCase(ISFLAT)) {
	                    double final_amount_to_be_added = (amount * comission_amount)/100 ;
	                //    dao.addIntoWallets(final_amount_to_be_added, customer_id);
	                    return final_amount_to_be_added;
	                }

	                return 0;
	            } else {
	                return 0;
	            }
	        } else {
	            return 0;
	        }
	    } catch (Exception e) {
	        return 0;
	    }
	}
	
	
	
	public void addComissionToParent2(String customerIdOfParent,double transaction_amt, String pid) {
		double dao_balance = tjnmoneytransDao.BalCheckwb("2", customerIdOfParent);
		double amount =checkComissionAmountMaster(transaction_amt,pid);
		String amountAsString = String.valueOf(amount);
		tjnmoneytransDao.credit(amountAsString, "2", customerIdOfParent);
	 // 				LOGS IN 
		tjn_money_transaction_model_Logs LOG = new tjn_money_transaction_model_Logs();
		int parentID= Integer.parseInt(customerIdOfParent);
		LOG.setCustomerId(parentID);
		LOG.setCustomerMobNo(tjnmoneytransDao.checkParentId(customerIdOfParent));
		LOG.setTransactionAmt(amount);
		LOG.setTransactionWalletWype(2);
		LOG.setCardOpeningBalance(dao_balance);
		LOG.setTransactionMethod("DEBIT");
		LOG.setCardClosingBalance(tjnmoneytransDao.BalCheckwb("2", customerIdOfParent));
		
		tjnmoneytransDao.save(LOG);
		//				LOGS OUT
	}
}
	
	

