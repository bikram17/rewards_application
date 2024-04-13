package com.Busybox.rewards.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Busybox.rewards.application.model.ProductListing;

public interface ProductListingRepository extends JpaRepository<ProductListing, Long> {
}
