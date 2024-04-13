package com.Busybox.rewards.application.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Busybox.rewards.application.model.parent_class_master;
import com.Busybox.rewards.application.model.referral_model_master_balance;

@EnableJpaRepositories
	public interface referral_model_master_balance_dao extends JpaRepository<referral_model_master_balance, Long>{
	
	
	@Query(value="select * from master_referral_code where referral_code = :code", nativeQuery = true)
	public parent_class_master abc(@Param("code") String code);

	
	@Query(value="select id from master_referral_code where phone_number = :phoneNumber", nativeQuery = true)
	public long getIdUsingPhoneNumber(@Param("phoneNumber")String phoneNumber);
	
	@Query(value="select phone_number from master_referral_code where referral_code = :refCode", nativeQuery = true)
	public String getPhoneNumberUsingReferralCode(@Param("refCode") String refCode);
	
	@Query(value="select count(*) from master_referral_code where referral_code = :code", nativeQuery = true)
	public int countIdUsingReferralCode(@Param("code")String code);
 
	@Query(value="select master_id from master_referral_code where referral_code = :code", nativeQuery = true)
	public long getIdUsingReferralCode(@Param("code") String code);
	
	@Query(value="select name from master_referral_code where referral_code = \":code\"", nativeQuery = true)
	public String getParentName(@Param("code")String code);

	@Query(value="select master_id from master_referral_code where referral_code = :code", nativeQuery = true)
	public long getParentId(@Param("code") String code);
	
	@Query(value="SELECT  COUNT(*) as child_count, parent_id FROM master_test_data where parent_id is not null GROUP BY parent_id;", nativeQuery = true)
	public List<Map<String, Object>> getReferralCount();


	@Query(value="select customer_name from master_test_data where customer_id = :x",nativeQuery=true)
	public String getName(@Param("x") String parentId);
                                 
	@Query(value="select customer_mobno from master_test_data where customer_id = :x",nativeQuery=true)
	public String getPhoneNumber(@Param("x")String parentId);
	
	@Query(value="select customer_mobno from master_test_data where customer_id = :x and store_id = :s",nativeQuery=true)
	public String getPhoneNumberStoreBasis(@Param("x")String parentId, @Param("s") String storeId);

	@Query(value="SELECT referral_code FROM master_referral_code where master_id = :x",nativeQuery=true)
	public String getReferralCode(@Param("x") String parentId);

	
	@Query(value="SELECT referral_code FROM master_referral_code where master_id = :x and store_id = :s",nativeQuery=true)
	public String getReferralCodeStoreBasis(@Param("x") String parentId, @Param("s") String storeId);
	

	@Query(value="SELECT customer_name as name, customer_mobno as phoneNumber, customer_email as email FROM master_test_data where parent_id = :x",nativeQuery=true)                        
	public List<Map<String, Object>> getChildDataOfThisParent(@Param("x") String Id);
	
	
}
