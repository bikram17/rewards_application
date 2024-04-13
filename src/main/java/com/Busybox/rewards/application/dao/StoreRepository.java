package com.Busybox.rewards.application.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.Busybox.rewards.application.model.Store;

@EnableJpaRepositories
public interface StoreRepository extends JpaRepository<Store, Long> {

	
	@Query(value="SELECT * FROM tjn_store_table WHERE parent_id = :x UNION SELECT * FROM tjn_store_table WHERE id = :x",nativeQuery=true)
	List<Store> findAllStoreId(@Param("x") String storeId);

	@Query(value="select id from tjn_store_table where parent_id = ?",nativeQuery=true)
	List<Long> getListOfAllStores(String storeId);


	
}