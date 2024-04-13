package com.Busybox.rewards.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Busybox.rewards.application.dto.setMaster_balance;
import com.Busybox.rewards.application.model.CustomerModel;




@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {
	
	@Modifying
	@Transactional
	@Query(value="INSERT INTO `tjn_login` (`email`, `password`) VALUES (:email, '11111');", nativeQuery=true)	
	void addInLogin(@Param("email") String email);

	//@Query(value="", nativeQuery= true)
	//String 

	@Query(value="select * from master_test_data where customer_mobno = :phoneNumber", nativeQuery= true)
	public CustomerModel findbyPhoneNumber(@Param("phoneNumber") String phoneNumber);
	
	@Query(value="select customer_id from master_test_data where customer_mobno = :x", nativeQuery= true)
	public int getcustomerID(@Param("x") String mobineNumber);
	
	@Query(value="SELECT customer_id FROM master_test_data WHERE customer_mobno = :customer_mobno",nativeQuery=true)
	public int getcustomerIdByPhoneno(@Param("customer_mobno")String customer_mobno);

	@Query(value="select * from master_test_data where store_id = :x",nativeQuery=true)
	List<CustomerModel> findAllByStoreId(@Param("x")String s);
	
	@Query(value = "SELECT * FROM master_test_data WHERE store_id IN :storeIds", nativeQuery = true)
	List<CustomerModel> findByStoreIds(@Param("storeIds") List<Long> storeIds);

	
}
