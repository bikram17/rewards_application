package  com.Busybox.rewards.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.CustomerRepository;
import com.Busybox.rewards.application.dao.Master_dao;
import com.Busybox.rewards.application.dao.TJNMoneyTransDao;
import com.Busybox.rewards.application.dao.tjn_wallet_types_dao;
import com.Busybox.rewards.application.dto.Final_Response_All_User_Details;
import com.Busybox.rewards.application.dto.TransactionDTO;
import com.Busybox.rewards.application.dto.WalletDto;
import com.Busybox.rewards.application.dto.setMaster_balance;
import com.Busybox.rewards.application.model.CustomerModel;
import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;
import com.Busybox.rewards.application.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	
	@Autowired private CustomerRepository customerRepo;
	@Autowired Master_dao masterDao;
	@Autowired TJNMoneyTransDao tjnMoneyTransDao;	
	@Autowired tjn_wallet_types_dao daot;
	@Override
	public List<CustomerModel> getAllCustomer(String s) {
		
		if( s == null) {
		return customerRepo.findAll();}
		else {
			return customerRepo.findAllByStoreId(s);
		}
	}
	
	
	@Override
	public CustomerModel getCustomerById(Integer customer_id) {
	
		return customerRepo.findById(customer_id).orElse(null);
	}


	@Override
	public CustomerModel editcustomer(CustomerModel customerModel, Integer customer_id) {
		

		if(customerRepo.existsById(customer_id)) {

			customerModel.setCustomer_id(customer_id);

			return customerRepo.save(customerModel);

		}

		return null;
	}

	@Override
	public void deleteCustomerModel(Integer customer_id) {
			customerRepo.deleteById(customer_id);
		
	}


	@Override
	public CustomerModel saveCustomerModel(CustomerModel customerModel) {
		return customerRepo.save(customerModel);
	}


	@Override
	public CustomerModel getCustomerByPhoneNumber(String phoneNumber) {
		return customerRepo.findbyPhoneNumber(phoneNumber);
		
	}

	
	public Final_Response_All_User_Details fraudxd(String mobilePhone) {
		Final_Response_All_User_Details fraud = new Final_Response_All_User_Details();
		int getCustomerID= customerRepo.getcustomerID(mobilePhone);
		
		CustomerModel cusM =customerRepo.findById(getCustomerID).orElse(null);
		if(cusM!= null) {
			
		fraud.setCustomerName(cusM.getCustomer_name());			fraud.setCustomerMobileNumber(cusM.getCustomer_mobno());	fraud.setCustomerEmail(cusM.getCustomer_email());	
		
		fraud.setCustomerAddress(cusM.getCustomer_address());	fraud.setCustomerCity(cusM.getCity());						fraud.setCustomerState(cusM.getState());
		
		fraud.setCustomerPincode(cusM.getPincode());			fraud.setCustomerStatus(cusM.getStatus());					fraud.setLoginStatus(cusM.getLoginStatus());	
		
		fraud.setCreatedOn(cusM.getCreatedOn());				fraud.setUpdatedOn(cusM.getUpdatedOn());
		
		
		List<setMaster_balance> masterData = masterDao.setModelData(getCustomerID);
		
		List<WalletDto> walletDtos = new ArrayList<>();

		for (setMaster_balance balance : masterData) {
			
		    WalletDto walletDto = balance.toWalletDto(); 
		    
		    String walletName = daot.walletNameFromId(Integer.parseInt(balance.getWallet_id())); 
		    
		    walletDto.setWalletName(walletName);
		    
		    walletDtos.add(walletDto);
		}

		fraud.setCustomerCards(walletDtos);
		
		List<tjn_money_transaction_model_Logs> ListOfLogs = tjnMoneyTransDao.getAllTransactionOfCustomerId(getCustomerID);
		
		List<TransactionDTO> listOfTransactionDTOs = ListOfLogs.stream().map(tjn_money_transaction_model_Logs::toTransactionDTO).collect(Collectors.toList());
		
		for(tjn_money_transaction_model_Logs dto : ListOfLogs) {
			System.out.print(dto);
		}
		
		fraud.setCustomerTransactions(listOfTransactionDTOs);

		}
		return fraud;
	}

	



	

	



}
