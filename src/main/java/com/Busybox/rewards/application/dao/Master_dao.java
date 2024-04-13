package com.Busybox.rewards.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Busybox.rewards.application.dto.setMaster_balance;

@EnableJpaRepositories
public interface Master_dao extends JpaRepository<setMaster_balance, Integer> {

	@Query(value="select * from wallet_balance_master where customer_id= :customer_id", nativeQuery= true)
	public List<setMaster_balance> setModelData(@Param("customer_id") int customer_id);
	
	
	
}
