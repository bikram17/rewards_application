package com.Busybox.rewards.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;

import jakarta.transaction.Transactional;
// wallet balance master and transactaion 
@EnableJpaRepositories
public interface TJNMoneyTransDao extends JpaRepository<tjn_money_transaction_model_Logs,Long>{

	
	
	
	@Query(value="SELECT * FROM busybox_rewards_transaction_101 where customer_id = :x", nativeQuery= true)
	List<tjn_money_transaction_model_Logs> getAllTransactionOfCustomerId(@Param("x") int customer_id);
	
	@Query(value="SELECT customer_id FROM master_test_data where customer_mobno = :x", nativeQuery= true)
	int getIdFromPhoneNumber(@Param("x") String customer_mobno);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE wallet_balance_master SET wallet_balance = wallet_balance - :amt WHERE customer_like %:cid% AND wallet_id like %:wid%", nativeQuery = true)
		public void	getCustomData(@Param("amt") String amt, @Param("cid") String cid, @Param("wid") String wid);

	@Modifying
	@Transactional
	@Query(value = "UPDATE wallet_balance_master SET wallet_balance = wallet_balance - :amt WHERE customer_id LIKE :cid AND wallet_id LIKE :wid", nativeQuery = true)
	void getCustomData2(@Param("amt") String amt, @Param("cid") String cid, @Param("wid") String wid);

		/*
		 * @Query(value="Select * from wallet_balance_master",nativeQuery = true)
		 * List<Object> getAll();
		 */
    
   /* @Query(value="SELECT * FROM busybox_rewards_transaction WHERE customer_id = ?",nativeQuery =  true)
    public void checktehcustyomer_id(long customer_id);
*/


	//public boolean existsById(List<tjn_money_transaction_model> getallTransactions);
	/*
	 * @Query(value="SELECT customer_id  FROM busybox_rewards_transaction"
	 * ,nativeQuery =true) public void customcheck(int customer_id);
	 */
    
    //\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
	/*
	 * @Query(
	 * value="SELECT CASE WHEN COUNT(customer_id) > 0 THEN true ELSE false END as customer_count FROM busybox_rewards_transaction WHERE customer_id = ?"
	 * ,nativeQuery=true) int existsBycustomerid(int customer_id);
	 */
    
    // Cus to Phn No
    @Query(value="select customer_mobno from master_test_data where customer_id= :input", nativeQuery= true)
    String cusIdToPhnNo(@Param("input") String input);
    
    //wallet id exists 
    @Query(value="select wallet_name from the_juice_nation_wallet_master where wallet_id = :wallet", nativeQuery =true)
    String walletifelse(@Param("wallet") String wallet);
    
    //
    @Query(value="select wallet_balance from wallet_balance_master where wallet_id=  :wallet  AND customer_id =:customer_id", nativeQuery=true)
    Double BalCheckwb(@Param("wallet") String wallet,@Param("customer_id") String customer_id);
    
    
    //
   
	/*
	 * @Transactional
	 * 
	 * @Query(
	 * value="update wallet_balance_master set wallet_balance = wallet_balance+ :amount where wallet_id = %:wallet_id% and  customer_id = %:input%"
	 * , nativeQuery=true) void credit(@Param("amount") String
	 * amount,@Param("wallet_id") String wallet_id,@Param("input") String input);
	 * 
	 */    
    @Transactional
    @Modifying
    @Query(value = "UPDATE wallet_balance_master SET wallet_balance = wallet_balance + :amount WHERE wallet_id = :wallet_id AND customer_id =:cid", nativeQuery = true)
	void credit(@Param("amount") String amount, @Param("wallet_id") String wallet_id, @Param("cid") String cid);
    
    	
    @Transactional
    @Modifying
    @Query(value = "UPDATE wallet_balance_master SET wallet_balance = wallet_balance - :amount WHERE wallet_id = :wallet_id AND customer_id =:cid", nativeQuery = true)
	void debit(@Param("amount") String amount, @Param("wallet_id") String wallet_id, @Param("cid") String cid);
    
    
    @Transactional
    @Modifying
    @Query(value="update wallet_balance_master set wallet_balance =wallet_balance+ 100 where wallet_id=1 and customer_id like %CUS1%", nativeQuery= true)
    void add500();
      
    @Query(value="SELECT * FROM wallet_balance_master;",nativeQuery =true)
    List<Object> getWallet_Master_Balance_Table();
    
    
//    @Query(value="", nativeQuery=true)
//    void CreditLogs();
//    @Query(value="", nativeQuery=true)
//    void DebitLogs();
//    
    
    
    
//    @Query(value="SELECT w.wallet_id, w.wallet_balance, t.customer_name, t.customer_mobno, s.wallet_name FROM wallet_balance_master w\r\n"
//    		+ "INNER JOIN master_test_data t ON w.customer_id = t.customer_id\r\n"
//    		+ "INNER JOIN wallet_master s ON w.wallet_id = s.id\r\n"
//    		+ "WHERE t.customer_mobno = :PhoneNo;",nativeQuery=true)
//    List<Object> customeWalletData(@Param("PhoneNo")String PhoneNumber);
    
    
//    @Query(value="SELECT w.wallet_id, w.wallet_balance, t.customer_name, t.customer_mobno, s.wallet_name FROM wallet_balance_master w INNER JOIN master_test_data t ON w.customer_id = t.customer_id INNER JOIN wallet_master s ON w.wallet_id = s.id WHERE t.customer_mobno = :x;", nativeQuery=true)
//    Object custom10(@Param("x") String PhoneNumber);
    
    
    @Query(value="SELECT w.wallet_id, w.wallet_balance, t.customer_name, t.customer_mobno, s.wallet_name FROM wallet_balance_master w INNER JOIN master_test_data t ON w.customer_id = t.customer_id INNER JOIN wallet_master s ON w.wallet_id = s.id WHERE t.customer_mobno = :PhoneNumber", nativeQuery=true)
    List<Object> custom10(@Param("PhoneNumber") String PhoneNumber);

    
    @Query(value="select wallet_id from the_juice_nation_wallet_master where wallet_name = :x", nativeQuery=true)
	int getWalletIdFromVoucherName(@Param("x")String voucherName);

    //---------- for Voucher add 
    
    @Query(value="select * from wallet_balance_master where customer_id = :x and wallet_id = :y", nativeQuery = true)
    Object checkifCustomerHasVoucher(@Param("x") int cid, @Param("y") int wid);
    
    
    @Query(value="update wallet_balance_master set wallet_balance = wallet_balance +  where wallet_id= :wid and customer_id = :cid", nativeQuery= true)
    void addIfExists();

    @Query(value="select customer_id from master_test_data where customer_mobno = :x", nativeQuery=true)
	int cusPhnToId(@Param("x") String customer_phn1);
    
    @Query(value="select count(*) from master_test_data where customer_id = :x", nativeQuery = true)
    int customerExistsReturnsCount(@Param("x") String customer_id);
    
    @Query(value="select count(*) from master_test_data where custoemr_id = cus_id and status = \"ACTIVE\" ", nativeQuery = true)
    int checkCustomerStatusDao(String cus_id);
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO wallet_balance_master (customer_id, wallet_id, wallet_balance, status) VALUES (:customerId, :walletId, :walletBalance, :status)", nativeQuery = true)
    void insertWalletBalance(
        @Param("customerId") int customerId,
        @Param("walletId") int walletId,
        @Param("walletBalance") int walletBalance,
        @Param("status") String status
    );

	// if parent exists or not 
    @Query(value="select count(*) from master_test_data where customer_id = :x  and parent_id is not null", nativeQuery = true)
    int checkParentId(@Param("x")String customer_id);
    
   
    
    
    @Query(value="select parent_id from master_test_data where customer_id  =:x", nativeQuery = true)
    String getParentId(@Param("x") String customer_id);
  
//    @Query(value="select count(*) from master_test_data where customer_id =:x and status = \"ACTIVE\" ")
//	int checkIfCustomerIsActive(String cus_id);
    
    @Transactional
    @Modifying
   @Query(value="INSERT INTO `busybox_rewards_transaction_101` "
   		+ "( `customer_phone_number`, `customer_id`, `wallet_id`, `wallet_balance`, `wallet_mode`, `transaction_method`,"
   		+ " `opening_balance`, `transaction_amount`, `closing_balance`)"
   		+ " VALUES ( :phn, :cid, '2', :walBal, \"REFERRAL\", \"DEBIT\", :open,:transactionAmt , :closingBal);", nativeQuery= true)
   void insertIntoReferralLogs1(@Param("phn") String phn, @Param("cid") String cid, @Param("walBal") double bal
		   ,@Param("open") double openingBalance, @Param("transactionAmt") double tranasactionAmt, @Param("closingBal") double close
   );	
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `busybox_rewards_transaction_101` "
            + "( `customer_phone_number`, `customer_id`, `wallet_id`, `wallet_balance`, `wallet_mode`, `transaction_method`,"
            + " `opening_balance`, `transaction_amount`, `closing_balance`, `date_of_transaction`)"
            + " VALUES ( :phn, :cid, '2', :walBal, 'REFERRAL', 'DEBIT', :open, :transactionAmt, :closingBal, NOW());",
            nativeQuery = true)
    void insertIntoReferralLogs(@Param("phn") String phn, @Param("cid") String cid, @Param("walBal") double bal
            , @Param("open") double openingBalance, @Param("transactionAmt") double transactionAmt,
            @Param("closingBal") double close);

}
