package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.Busybox.rewards.application.model.tjn_Wallet_types_model;

@EnableJpaRepositories
public interface tjn_wallet_types_dao extends JpaRepository<tjn_Wallet_types_model,Integer> {

	@Query(value="select wallet_name from the_juice_nation_wallet_master where wallet_id = :x", nativeQuery= true)
	public String walletNameFromId(@Param("x") int id);
	
	@Query(value="SELECT * FROM the_juice_nation_wallet_master WHERE vouchar_id = :vouchar_id", nativeQuery= true)
	public tjn_Wallet_types_model findByVoucharId(@Param("vouchar_id")String vouchar_id);

	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM the_juice_nation_wallet_master WHERE vouchar_id = :vouchar_id",nativeQuery=true)
	public Object deleteById(@Param("vouchar_id")String vouchar_id);
	
	
	@Query(value="SELECT wallet_id FROM the_juice_nation_wallet_master WHERE vouchar_id= :vouchar_id",nativeQuery=true)
	public int getwalletIdByVoucharId(@Param("vouchar_id") String vouchar_id);
	
	@Query(value="SELECT validity FROM the_juice_nation_wallet_master WHERE vouchar_id = :vouchar_id",nativeQuery=true)
	public long getValidityByVoucharId(@Param("vouchar_id")String vouchar_id );

//	@Query(value="select * from the_juice_nation_wallet_master ",nativeQuery=true)
//	public tjn_Wallet_types_model selectWalletBalance(@Param("x") String walletId);
}
