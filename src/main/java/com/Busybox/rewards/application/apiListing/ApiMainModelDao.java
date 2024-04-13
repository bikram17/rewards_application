package com.Busybox.rewards.application.apiListing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ApiMainModelDao extends JpaRepository<ApiMainModel	, Long>{

}
