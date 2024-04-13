package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Busybox.rewards.application.model.orderMaster;

public interface OrderMasterRepository extends JpaRepository<orderMaster, Long> {
}