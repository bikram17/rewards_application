package com.Busybox.rewards.application.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserModel, String>{

	Optional<UserModel> findByEmail(String email);
	
}
