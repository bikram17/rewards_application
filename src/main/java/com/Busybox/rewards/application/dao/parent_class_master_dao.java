package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.Busybox.rewards.application.model.parent_class_master;

@EnableJpaRepositories
public interface parent_class_master_dao extends JpaRepository<parent_class_master,Long>{

	
	
	
	
}
