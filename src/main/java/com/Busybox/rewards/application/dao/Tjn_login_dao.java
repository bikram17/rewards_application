package com.Busybox.rewards.application.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Busybox.rewards.application.model.tjn_Login_model;

import jakarta.transaction.Transactional;

//import busybox.tjn.model.TJN_ReferralCardModel;
//import busybox.tjn.model.tjn_Login_model;
//import busybox.tjn.model.tjn_cards_model;

@Repository
//@EnableRepository

public interface Tjn_login_dao extends JpaRepository<tjn_Login_model,Integer>
{
	@Query(value = "select id from tjn_login where email LIKE %:input% ", nativeQuery = true)
	public int UserIDFromSQL(@Param("input") String input);

	@Query(value = "select password from tjn_login where id = :input ", nativeQuery = true)
	public String passwordFromSQL(@Param("input") int input);
	
	@Query(value = "select case when exists(select 1 from tjn_login where id = :input) then 'yes' else 'no' end;", nativeQuery = true)
	String UserFromSQL(@Param("input") int input);	
	
	@Query(value="select count(*) from master_test_data where customer_mobno = :phoneNumber",nativeQuery=true)
	int DoesAnyOneExists(@Param("phoneNumber")String phoneNumber);
	
	@Query(value="select * from tjn_login where email=:post_email and password=:post_password limit 1", nativeQuery=true)
	Object LoginUser(@Param("post_email") String post_email, @Param("post_password") String Password);
	
	
	@Query(value="select password from tjn_login where email = :email",nativeQuery=true)
	String existingOtp(@Param("email") String phone);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE tjn_login SET password = :otp WHERE email = :phone ",nativeQuery=true)
	void updateTheOtp(@Param("otp") String otp,@Param("phone") String phone);
	
	@Query(value="select count(*) from master_test_data where customer_mobno =:x ",nativeQuery=true)
	int countOfThatNumber(@Param("x") String x);
	
}


