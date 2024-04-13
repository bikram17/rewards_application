package com.Busybox.rewards.application.reports;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.Busybox.rewards.application.commercial.Package_Comission_Details;

@EnableJpaRepositories
public interface ReportsRepository extends JpaRepository<Package_Comission_Details, Long>{

	@Query(value="SELECT COALESCE(SUM(transaction_amount), 0) FROM busybox_rewards_transaction_101 WHERE wallet_mode = 'referral' AND customer_id = :x AND date(date_of_transaction) = CURDATE()", nativeQuery = true)
	public double currentDateRewardOfCustomer(@Param("x") int x);

	@Query(value="SELECT COALESCE(SUM(transaction_amount), 0) FROM busybox_rewards_transaction_101 WHERE wallet_mode = 'referral' AND customer_id = :x", nativeQuery = true)
	public double totalRewardBalanceOfOneCustomer(@Param("x") int x);

	@Query(value = "SELECT COALESCE(SUM(transaction_amount), 0) FROM busybox_rewards_transaction_101 WHERE wallet_mode = 'referral' AND customer_id = :x AND YEAR(date_of_transaction) = YEAR(CURDATE()) AND MONTH(date_of_transaction) = MONTH(CURDATE())", nativeQuery = true)
	public double currentMonthRewardOfCustomer(@Param("x") int x);

	@Query(value = "SELECT COALESCE(SUM(transaction_amount), 0) FROM busybox_rewards_transaction_101 WHERE wallet_mode = 'referral' AND customer_id = :x AND YEAR(date_of_transaction) = YEAR(CURDATE())", nativeQuery = true)
	public double currentYearRewardOfCustomer(@Param("x") int x);

	@Query(value="SELECT CASE WHEN COUNT(*) IS NULL THEN 0 ELSE COUNT(*) END as child_count FROM master_test_data WHERE parent_id = :x AND parent_id IS NOT NULL",nativeQuery=true)
	public int countOfChildReferralId(@Param("x") int id);
	
	//per customer earning
	@Query(value = """
            SELECT 
                mtd_child.customer_name AS referred_customer_name,
                SUM(tr.transaction_amount) AS total_referral_earnings
            FROM 
                master_test_data mtd_parent
            JOIN 
                busybox_rewards_transaction_101 tr ON mtd_parent.customer_id = tr.customer_id
            JOIN
                master_test_data mtd_child ON tr.customer_phone_number = mtd_child.customer_mobno
            WHERE 
                tr.wallet_mode = 'referral' 
                AND mtd_parent.customer_id = :x 
            GROUP BY 
                mtd_child.customer_name
            """, nativeQuery = true)
public List<Object[]> perCustomerEarning(@Param("x") int x);

}
