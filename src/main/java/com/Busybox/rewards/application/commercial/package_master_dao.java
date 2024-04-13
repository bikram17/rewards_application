package com.Busybox.rewards.application.commercial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface package_master_dao extends JpaRepository<Package_Master_Model,Long>{

	@Query(value="SELECT package_id FROM package_master_model where package_code = :x", nativeQuery = true)
	long getIdfromPackageCode(@Param("x") Long x);
	
}
