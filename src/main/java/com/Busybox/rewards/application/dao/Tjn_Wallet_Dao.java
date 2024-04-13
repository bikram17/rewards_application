package com.Busybox.rewards.application.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Busybox.rewards.application.model.CustomerModel;
import com.Busybox.rewards.application.model.tjn_Wallet_types_model;
//G String abc= "float";

@EnableJpaRepositories
public interface Tjn_Wallet_Dao extends JpaRepository<tjn_Wallet_types_model,Integer>
{

@Query(value = "SELECT a.customer_id,a.customer_mobno,b.wallet_id,b.wallet_balance, c.wallet_name FROM master_test_data AS a INNER JOIN wallet_balance_master AS b ON a.customer_id=b.customer_id LEFT JOIN wallet_master AS c ON b.wallet_id=c.id WHERE a.customer_mobno=:customer_mobno", nativeQuery = true)
	
List<Object[]> getCustomerData(@Param("customer_mobno") String customer_mobno);

@Query(value="SELECT * FROM the_juice_nation_wallet_master WHERE wallet_type = \"VOUCHAR\"",nativeQuery=true)
List<tjn_Wallet_types_model>findAllonlyvouchar();

@Query(value = """
SELECT 
    a.customer_id, 
    a.customer_name, 
    a.customer_mobno, 
    b.wallet_id, 
    b.wallet_balance, 
    c.wallet_name 
FROM 
    master_test_data AS a 
INNER JOIN 
    wallet_balance_master AS b 
ON 
    a.customer_id = b.customer_id 
LEFT JOIN 
    the_juice_nation_wallet_master AS c 
ON 
    b.wallet_id = c.wallet_id 
WHERE 
    a.customer_mobno = :customer_mobno 
""", nativeQuery = true)
List<Object[]> getCustomerData2(@Param("customer_mobno") String customer_mobno);



@Query(value = """
SELECT 
    a.customer_id, 
    a.customer_name, 
    a.customer_mobno, 
    b.wallet_id, 
    b.wallet_balance, 
    c.wallet_name 
FROM 
    master_test_data AS a 
INNER JOIN 
    wallet_balance_master AS b 
ON 
    a.customer_id = b.customer_id 
LEFT JOIN 
    the_juice_nation_wallet_master AS c 
ON 
    b.wallet_id = c.wallet_id 
WHERE 
    a.customer_mobno = :customer_mobno 
    AND c.wallet_type = :float 
""", nativeQuery = true)
List<Object[]> getCustomerData2Credit(@Param("customer_mobno")  String xyz, @Param("float") String abc);


@Query(value = """
SELECT 
    a.customer_id, 
    a.customer_name, 
    a.customer_mobno, 
    b.wallet_id, 
    b.wallet_balance, 
    c.wallet_name 
FROM 
    master_test_data AS a 
INNER JOIN 
    wallet_balance_master AS b 
ON 
    a.customer_id = b.customer_id 
LEFT JOIN 
    the_juice_nation_wallet_master AS c 
ON 
    b.wallet_id = c.wallet_id 
WHERE 
    a.customer_mobno = :customer_mobno 
    AND c.wallet_type = :voucher
""", nativeQuery = true)
List<Object[]> getCustomerData3(@Param("customer_mobno")  String xyz, @Param("voucher") String abc);



@Query(value="SELECT * FROM the_juice_nation_wallet_master WHERE wallet_type = \"VOUCHAR\" and store_id =:x",nativeQuery=true)
public List<tjn_Wallet_types_model> findAllonlyvoucharStoreDependent(@Param("x")String storeId);

/*@Query(value = "SELECT a.*,\r\n"
		+ "       b.*\r\n"
		+ "FROM wallet_balance_master a\r\n"
		+ "INNER JOIN master_test_data c ON a.customer_id = c.customer_id\r\n"
		+ "INNER JOIN voucher_balance_child b ON c.customer_id = b.customer_id\r\n"
		+ "WHERE c.customer_mobno = :customer_mobno", nativeQuery = true)
List<Object[]> getDataByCustomerMobno(@Param("customer_mobno")String customer_mobno);*/




}
