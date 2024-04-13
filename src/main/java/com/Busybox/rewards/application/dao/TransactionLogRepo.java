package com.Busybox.rewards.application.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;
@Repository
@EnableJpaRepositories
public interface TransactionLogRepo extends JpaRepository<tjn_money_transaction_model_Logs, Integer> {
	    
	    //@Query(value="SELECT * FROM busybox_rewards_transaction_101", nativeQuery=true)
	    //List<Map<String, Object>> findAllLogs(Integer pageNumber, Integer pageSize);

	
	}

//transaction_id,customer_phone_number,customer_id,wallet_id,wallet_balance,wallet_mode,transaction_amount,closing_balance,date_of_transaction