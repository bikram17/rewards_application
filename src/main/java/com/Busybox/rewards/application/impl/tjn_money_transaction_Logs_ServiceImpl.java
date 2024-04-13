package com.Busybox.rewards.application.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.dao.tjn_money_transaction_Logs_dao;
import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;
import com.Busybox.rewards.application.service.tjn_money_transaction_Logs_Service;

@Service

public class tjn_money_transaction_Logs_ServiceImpl implements tjn_money_transaction_Logs_Service {
	
	@Autowired
	private tjn_money_transaction_Logs_dao logDao;

		@Override
		public Object getALLLogsPagewise(int pageNumber,int pageSize) {
			Sort descendingSort = Sort.by(Sort.Order.desc("date_of_transaction"));		
				
				
		
				PageRequest p=PageRequest.of(pageNumber,pageSize);
				
		
							return logDao.findAll(p);
	}
	/*
	 * @Override public Object getByMobno(String customer_mobno) { // TODO
	 * Auto-generated method stub return logDao.findById(customer_mobno); }
	 */
	/*
	 * @Override public List<tjn_money_transaction_model_Logs>
	 * getTransactionHistoryByPhoneNumber(String phone_number) {
	 * 
	 * List<tjn_money_transaction_model_Logs> transactionHistory =
	 * logDao.findByPhoneNumber(phone_number); return transactionHistory;
	 * 
	 * }
	 */

		@Override
		public List<Map<String, Object>> findByPhoneNumber(String phone_number) {

		 return logDao.findAllByPhoneNumber(phone_number);
		}
	@Override
	public Object getAllLogs() {
	
		return logDao.findAll();
	}
}
