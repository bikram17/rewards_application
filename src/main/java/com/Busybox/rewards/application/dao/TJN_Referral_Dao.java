package com.Busybox.rewards.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

//import com.Busybox.rewards.application.impl.Referee;
import com.Busybox.rewards.application.model.TJN_Referral_Model;

import jakarta.transaction.Transactional;
//import com.Busybox.rewards.application.model.Tjn_walletMaster_model;
@EnableJpaRepositories
public interface TJN_Referral_Dao extends JpaRepository<TJN_Referral_Model,Long>{

//	Object getWalletInfo();
	@Query(value="SELECT * FROM tjn_referral_system;",nativeQuery = true)
		List<TJN_Referral_Model> getreferralinfo();
	
		
@Query(value="INSERT INTO `tjn_login` (`email`, `password`) VALUES (:email, '11111');", nativeQuery=true)	

	void addInLogin(@Param("email") String email);
		
	@Transactional
	@Modifying
	@Query(value = "UPDATE wallet_balance_master "
		            + "SET wallet_balance = wallet_balance + comission_amount "
		            + "WHERE customer_id = :customerId", nativeQuery = true)
	void updateWalletBalance(@Param("customerId") Long customerId);
//	@Query(value="")
//	List
//	@Query(value = "UPDATE wallet_balance_master AS w "
//	        + "INNER JOIN tjn_referral AS r ON w.customer_id = r.referral_by "
//	        + "SET w.wallet_balance = w.wallet_balance + :commissionAmount "
//	        + "WHERE r.referral_code = :referralCode", nativeQuery = true)
//	void updateWalletBalance(@Param("referralCode") String referralCode, @Param("commissionAmount") String commissionAmount);
//	@Query(value = "UPDATE wallet_balance_master AS w "
//	        + "INNER JOIN tjn_referral AS r ON w.customer_id = r.referral_by "
//	        + "SET w.wallet_balance = w.wallet_balance + :commissionAmount "
//	        + "WHERE r.referral_code = :referralcode", nativeQuery = true)
//	void updateWalletBalance(@Param("referral_code") String referral_code, @Param("commissionAmount") String commissionAmount);
//	
//	@Query(value = "UPDATE wallet_balance_master  "
//	        + "INNER JOIN tjn_referral  ON wallet_balance_master.customer_id = tjn_referral.referral_by "
//	        + "SET wallet_balance_master.wallet_balance = wallet_balance_master.wallet_balance + :commissionAmount "
//	        + "WHERE tjn_referral.referral_code = :Optional[referral_code]", nativeQuery = true)
//	void updateWalletBalance(@Param("referral_code") String referral_code, @Param("commissionAmount") String commissionAmount);
//	

	@Query(value="SELECT referee_id,referee_name,referee_email FROM tjn_referral WHERE referral_by=:input",nativeQuery = true)
	List<Object[]> findRefereesByReferralBy(@Param("input") String input);
 
//	@Query(value="SELECT referee_id,referee_name,referral_by FROM tjn_referral WHERE referral_code = :ReferralCode",nativeQuery=true)
//	List<Object[]> findByReferralCode(@Param("ReferralCode") String referral_code);
	
	@Query(value="SELECT tjn_referral_system.referee_id, tjn_referral_system.referee_name, tjn_referral_system.referee_email ,tjn_referral_system.referral_by,master_test_data.customer_address,master_test_data.customer_email\n"
			+ "FROM the_juice_nation_master.master_test_data\n"
			+ "INNER JOIN the_juice_nation_master.tjn_referral_system\n"
			+ "ON the_juice_nation_master.master_test_data.customer_id = the_juice_nation_master.tjn_referral_system.referral_by\n"
			+ "WHERE referral_code=:ReferralCode",nativeQuery=true)
	List<Object[]> findReferralDetailsByCode(@Param("ReferralCode") String referral_code);


//	Long findCustomerIdByReferralCode(String referralCode);


//	Long findCustomerIdByReferralCode(String referral_code);


//	Long findCustomerIdByReferralCode(String referral_code);


}





