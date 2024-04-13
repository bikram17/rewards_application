package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Busybox.rewards.application.model.Rewards_Application_API_Logs;
@Transactional
public interface ApiLogRepository  extends JpaRepository<Rewards_Application_API_Logs,Long>{

	
	
	
	
}
