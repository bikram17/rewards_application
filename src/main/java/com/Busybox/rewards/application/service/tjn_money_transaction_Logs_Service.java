package com.Busybox.rewards.application.service;



import java.util.List;
import java.util.Map;

import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;

public interface tjn_money_transaction_Logs_Service {

	Object getALLLogsPagewise(int pageNumber,int pageSize);

	List<Map<String, Object>> findByPhoneNumber(String phone_number);

	Object getAllLogs();

	//public List<tjn_money_transaction_model_Logs> getTransactionHistoryByPhoneNumber(String phone_number);

	//Object getByMobno(String customer_mobno);
	
	//public tjn_money_transaction_model_Logs getByMobno(String customer_mobno);


}
