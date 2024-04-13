package com.Busybox.rewards.application.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface RoleNDao extends JpaRepository<RoleN, Long>{

	
}
