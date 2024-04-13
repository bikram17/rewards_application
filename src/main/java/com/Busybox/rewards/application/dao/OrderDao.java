//package com.Busybox.rewards.application.dao;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.repository.query.Param;
//
//import com.Busybox.rewards.application.model.OrderModel;
//import com.Busybox.rewards.application.model.OrderModelNew;
//
//import jakarta.persistence.criteria.Order;
//import jakarta.transaction.Transactional;
//
//@EnableJpaRepositories
//public interface OrderDao extends  JpaRepository<OrderModelNew ,Integer> {
//	
//	
//	@Query(value="SELECT * FROM order_master where customer_mobno = :cmo",nativeQuery=true)
//	List<OrderModel> findByPhoneNumber(@Param("cmo") String cmo);
//	
//	@Query(value="SELECT * FROM order_master where order_id = :oid",nativeQuery=true)
//	OrderModel findByOrderid(@Param("oid") String oid);
//	
//	@Modifying
//    @Transactional
//    @Query(value = "UPDATE wallet_balance_master SET wallet_balance = wallet_balance - :amount", nativeQuery = true)
//		public void	orderTrans(@Param("amount")Double amount);
//
//}