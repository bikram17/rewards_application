package com.Busybox.rewards.application.commercial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@EnableJpaRepositories
public interface package_comission_dao extends JpaRepository<Package_Comission_Details, Long> {

	
	@Query(value="SELECT * FROM package_comission_details WHERE :amount between to_amount and from_amount AND package_id = :pid and status = \"ACTIVE\";", nativeQuery=true)
	public Package_Comission_Details getComissionAmount(@Param("amount") Double amount, @Param("pid") String packageId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE wallet_balance_master SET wallet_balance = wallet_balance + :amt WHERE customer_id = :cid and wallet_id =2;", nativeQuery=true)
	void addIntoWallets(@Param("amt")double final_amount_to_be_added,@Param("cid") int customer_id);
	
	@Query(value="select package_id from package_master_model where package_code = :x", nativeQuery = true)
	Long getId(@Param("x") long x);
	
	@Query(value="select * from package_comission_details where package_id = :x", nativeQuery = true)
	List<Package_Comission_Details> getAllComissionDetails(@Param("x") long x);
	
	
}
