package com.Busybox.rewards.application.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.hibernate.id.UUIDGenerationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired UserRepository userRepository;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	
	
	
	
	
	public List<UserModel> getAllUsers(){
		return userRepository.findAll();
	}
	
	public UserModel getDetailsUsingEmail(String email) {
		return userRepository.findByEmail(email).orElse(null);
	}
	
	public UserModel createUser(UserModel userModel) {
		userModel.setId(UUID.randomUUID().toString());
		userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
		return userRepository.save(userModel);
	}

}


