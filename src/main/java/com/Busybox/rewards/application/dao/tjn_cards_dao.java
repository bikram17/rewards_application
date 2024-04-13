package com.Busybox.rewards.application.dao;

import java.lang.annotation.Target;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//import com.Busybox.rewards.application.dto.*;
import org.springframework.transaction.annotation.Transactional;

import com.Busybox.rewards.application.model.tjn_cards_model;

@Repository
@EnableJpaRepositories
public interface tjn_cards_dao extends JpaRepository<tjn_cards_model,Long> {

	//-- trial 2
	@Query(value="select * from tjn_cards_master", nativeQuery=true)
	public List<tjn_cards_model> getAll();

	@Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM tjn_cards_master WHERE customer_id = :getCustomerId) THEN 'yes' ELSE 'no' END", nativeQuery = true)
	String checkIfCustomerExists(@Param("getCustomerId") int getCustomerId);
	
	@Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM tjn_cards_master WHERE customer_id = :getCustomerId) THEN 'yes' ELSE 'no' END", nativeQuery = true)
	String checkWalletExists(@Param("getCustomerId") int getCustomerId);
	
	@Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM tjn_cards_master WHERE customer_id = :getCustomerId and wallet_id =2) THEN 'yes' ELSE 'no' END", nativeQuery = true)
	String checkIfCardExists(@Param("getCustomerId") int getCustomerId);
	
	@Query(value="select * from wallet_balance_master where customer_id = :id and wallet_id= 1 and status = active;", nativeQuery=true)
	String Wallet_id_one_check(@Param("id") int C_id);	
	
	@Query(value="select * from wallet_balance_master where customer_id = :id and wallet_id= 2 and status = active;", nativeQuery=true)
	String Wallet_id_two_check(@Param("id") int C_id);	
	
	@Query(value="select status from master_test_data where customer_id = :id", nativeQuery=true)
	Object customerStatus(@Param("id") int C_id);
	// trial 2
	
	@Query(value="select * from master_test_data where customer_id= :id", nativeQuery= true)
	Object CustomerIDFromData(@Param("id") int id);

//	@Query(value="select customer_id from master_test_data ", nativeQuery=true)
	//public boolean customerStatus(boolean equals);
	
	
	//Insert into wallet master balance the wallet id one for the customer with default data 
	@Modifying
	@Transactional
	@Query(value="INSERT INTO wallet_balance_master (`customer_id`, `wallet_id`, `wallet_balance`, `status`) VALUES (:cid, 1, '0', 'ACTIVE' );", nativeQuery=true)
	void insertWalletOne (@Param("cid") int cid);
	
	
	
	@Modifying
	@Transactional
	@Query(value="INSERT INTO wallet_balance_master (`customer_id`, `wallet_id`, `wallet_balance`, `status`) VALUES (:cid, 2, '0', 'ACTIVE' );", nativeQuery=true)
	void insertWalletTwo (@Param("cid") int cid);
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	/*public List<tjn_cards_model> findBycardBalance(double card_balance);
	
//	public List<tjn_cards_model> findBycardNumber(String card_number);
	
	
	
	 * @Query (value="Select card_id from tjn_cards",nativeQuery=true) List<Object>
	 * GetOnlyCardId();
	 
	 
	
	//checking if the card number exists or not;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
	@Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM tjn_cards WHERE card_id = :card_id) THEN 'yes' ELSE 'no' END", nativeQuery = true)
	String checkCardNumberExists(@Param("card_id") String card_id);
	
	@Query(value = "SELECT card_balance from tjn_cards where card_id= :card_id", nativeQuery = true)
	Double checkBalance(@Param("card_id") String card_id);
	
	
	 * @Query(value="select card_balance from tjn_cardsw where card_id =:id")
	 * Object[] systemChack(@Param("card_id") String card_id);
	 
 	
	@Query(value = "SELECT card_balance FROM tjn_cards WHERE card_id= :x", nativeQuery=true)
	List<Object> checking_cardBalance(@Param("x") int x);
	
	
	@Query(value = "SELECT card_holder_name, card_balance FROM tjn_cards", nativeQuery=true)
	List<Object> getCustomData();
	//SELECT EXISTS (SELECT 1 FROM tjn_cards WHERE card_number =1);

	@Query(value = "SELECT * FROM tjn_cards", nativeQuery = true)
//    @Query(value = "SELECT card_id, card_balance FROM tjn_cards", nativeQuery = true)
//    @Query("SELECT NEW com.Busybox.rewards.aapplication.tjn_card_info_dto(c.cardId, c.cardBalance) FROM Card c")
	 //@Query("SELECT new com.Busybox.rewards.application.dto.tjn_card_info_dto(c.cardId, c.cardBalance) FROM tjn_cards_model c")
	 List<tjn_cards_model> getCardInfo();
	 
	 public default String getCardbalance(Long id){
		//tjn_cards_model.getcardBalance();
		return null;
		 
		
		
		 * List<tjn_cards_model> getAbove5000(){
		 * 
		 * }
		 
	 }
	 
	 @Query(value = "SELECT * FROM tjn_cards WHERE card_balance > :cardBalance", nativeQuery = true)
	 List<tjn_cards_model> getCardWhereBalanceIsMoreThan( Double cardBalance);
	 
	 @Query(value = "SELECT * FROM tjn_cards WHERE card_holder_name LIKE %:card_holder_name%", nativeQuery = true)
	 List<tjn_cards_model> getCardHolderName(@Param("card_holder_name) String card_holder_name);"*/
	 
	 
	 
	 
	 
	 
}

// Customer query here, check how to do it later.
//@Autowired
// tjn_card_module tjncards;
//	 SELECT o.orderid, o.amount, i.description FROM orders o INNER JOIN items i ON o.itemid = i.itemid
//@Query("SELECT c FROM tjn_cards_module c WHERE c.card_balance > 5000")
//List<tjn_cards_module> findCardsWithBalanceAbove5000();X