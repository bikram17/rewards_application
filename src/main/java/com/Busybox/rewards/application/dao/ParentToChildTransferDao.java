package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.Busybox.rewards.application.model.ParentToChildTransaction;

@EnableJpaRepositories
public interface ParentToChildTransferDao extends JpaRepository<ParentToChildTransaction, Long>{

	
}
