package com.Busybox.rewards.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Busybox.rewards.application.model.Tjn_Voucher_Coupons;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
public interface Tjn_Voucher_Cupons_DAO extends JpaRepository<Tjn_Voucher_Coupons ,Long>
{	
//	@Query(value="",nativeQuery=true)
//	double customerVoucherNumberTotal = 0;


	@Modifying
	@Transactional
	@Query(value="UPDATE voucher_balance_child SET balance = balance -:vamt WHERE status = \"ACTIVE\" AND wallet_id = :wallet_id",nativeQuery=true)
	public void getvouchardata(@Param("vamt")int vamt,@Param("wallet_id")int wallet_id );

	
	
@Query(value = """
SELECT 
a.customer_id, 
a.customer_name, 
a.customer_mobno, 
b.wallet_id, 
CAST(sum(b.balance) AS SIGNED) AS wallet_balance, 
c.wallet_name, 
c.vouchar_id
FROM 
master_test_data AS a 
INNER JOIN 
voucher_balance_child AS b 
ON 
a.customer_id = b.customer_id 
LEFT JOIN 
the_juice_nation_wallet_master AS c 
ON 
b.wallet_id = c.wallet_id 
WHERE 
a.customer_mobno = :customer_mobno 
GROUP BY 
b.wallet_id
""", nativeQuery = true)
		public List<Object[]> getCustomerDataCombined(@Param("customer_mobno") String customer_mobno);
		
		
		@Query(value = """
				SELECT 
				a.customer_id, 
				a.customer_name, 
				a.customer_mobno, 
				b.wallet_id, 
				CAST(sum(b.balance) AS SIGNED) AS wallet_balance, 
				c.wallet_name, 
				c.vouchar_id
				FROM 
				master_test_data AS a 
				INNER JOIN 
				voucher_balance_child AS b 
				ON 
				a.customer_id = b.customer_id 
				LEFT JOIN 
				the_juice_nation_wallet_master AS c 
				ON 
				b.wallet_id = c.wallet_id 
				WHERE 
				a.customer_mobno = :customer_mobno and 
				c.store_id = :s
				GROUP BY 
				b.wallet_id
				""", nativeQuery = true)
						public List<Object[]> getCustomerDataCombinedStoreBasis(@Param("customer_mobno") String customer_mobno, @Param("s") String storeId);

	
	@Query(value="SELECT  CAST(sum(b.balance) AS SIGNED) AS wallet_balance \r\n"
			+ "FROM master_test_data AS a INNER JOIN voucher_balance_child AS b ON a.customer_id = b.customer_id \r\n"
			+ "LEFT JOIN the_juice_nation_wallet_master AS c ON b.wallet_id = c.wallet_id \r\n"
			+ "WHERE a.customer_mobno = :customer_mobno and b.wallet_id = :wids group by b.wallet_id",nativeQuery=true)
	public Object getCount(@Param("customer_mobno")String customer_mobno,@Param("wids") int walletId);
	
	
	@Query(value="SELECT SUM(balance) AS voucher_balance FROM voucher_balance_child WHERE wallet_id = :wallet_id and status='ACTIVE'",nativeQuery=true)
	public int getvoucharBalance(@Param("wallet_id")int wallet_id);


	@Query(value="SELECT count(*) FROM the_juice_nation_wallet_master where wallet_id = :x ",nativeQuery=true)
	public int walletExists(@Param("x") String customer_wallet_id);



	
	@Query(value="select sum(balance) from voucher_balance_child where customer_id = :x and wallet_id=  :y  and status = \"ACTIVE\" and is_empty = \"NO\";",nativeQuery=true)
	public int customerVoucherNumberTotal(@Param("x")String customer_id, @Param("y")String wallet_id);
	
	
	
	@Query(value="select count(*) from voucher_balance_child where customer_id = :x and wallet_id= :y  and status = \"ACTIVE\" and is_empty = \"NO\";",nativeQuery= true)
	public  int getRowsThatHasTheCurrentVouchers(@Param("x")String customer_id, @Param("y")String wallet_id);
	
	@Query(value="select * from voucher_balance_child where customer_id = :x and wallet_id= :y  and status = \"ACTIVE\" and is_empty = \"NO\" LIMIT  1;",nativeQuery= true)
	public Tjn_Voucher_Coupons getFirstRowDetails(@Param("x")String customer_id, @Param("y")String wallet_id);
	
	
	
	
	/*@Query(value="SELECT * FROM voucher_balance_child WHERE vouchar_id = :vouchar_id ")
	public Tjn_Voucher_Coupons getVoucherById(@Param("vouchar_id")String vouchar_id);*/
	
	/*@Query(value="",nativeQuery=true)
	public int  */


	//public void updateVoucher(Tjn_Voucher_Coupons existingVoucher);
	

	
	
	
	
}
