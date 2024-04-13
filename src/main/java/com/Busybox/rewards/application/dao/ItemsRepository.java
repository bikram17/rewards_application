package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Busybox.rewards.application.model.Items;

public interface ItemsRepository extends JpaRepository<Items, Long> {
    // You can define custom query methods here if needed
}
