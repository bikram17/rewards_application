package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Busybox.rewards.application.model.AddressModel;

@EnableJpaRepositories
public interface AddressDao extends JpaRepository<AddressModel,Integer> {

	
	
	@Query(value="select * from address_master where customer_id= :x", nativeQuery=true)
	public AddressModel getAllwithId(@Param("x") int customer_id);

	@Query(value="select id from address_master where customer_id= :x",nativeQuery=true)
	public int getidnow(@Param("x") int customer_id);
	
	//@Query(value="",nativeQuery=true)
	
}
