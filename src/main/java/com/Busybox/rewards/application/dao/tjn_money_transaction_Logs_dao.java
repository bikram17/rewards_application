package com.Busybox.rewards.application.dao;



import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;
@Repository
@EnableJpaRepositories
public interface tjn_money_transaction_Logs_dao extends JpaRepository<tjn_money_transaction_model_Logs, Long > {

	//List<tjn_money_transaction_model_Logs> findByPhoneNumber(String phone_number);


	@Query(value="SELECT a.*, b.wallet_name, b.wallet_type FROM busybox_rewards_transaction_101 AS a INNER JOIN The_Juice_Nation_Wallet_Master AS b ON a.wallet_id=b.wallet_id WHERE a.customer_phone_number = :phone_number order by a.transaction_id desc",nativeQuery=true)
	List<Map<String, Object>> findAllByPhoneNumber(@Param("phone_number") String phone_number);
	
    @Query(value="SELECT * FROM busybox_rewards_transaction_101", nativeQuery=true)
    List<Map<String, Object>> findAllLogs(Integer pageNumber, Integer pageSize);

    
    
    //@Query(value="SELECT * FROM busybox_rewards_transaction_101 WHERE customer_phone_number = :phone_number AND customer_phone_number = :phone_number",nativeQuery=true)
    //tjn_money_transaction_model_Logs findByPhoneNumber(@Param("phone_number") String phone_number);
}
