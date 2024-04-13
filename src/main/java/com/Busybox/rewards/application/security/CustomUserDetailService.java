package com.Busybox.rewards.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			UserModel userModel= userRepository.findByEmail(username).orElseThrow(()-> new RuntimeException("USER NOT FOUND"));
		// TODO Auto-generated method stub
		return userModel;
	}

}
