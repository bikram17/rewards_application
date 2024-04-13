package com.Busybox.rewards.application.StoreManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.security.UserRepository;

@RestController
@RequestMapping("store/fetch")
public class StoreManagerControllers {

	@Autowired UserRepository userRepository;
	
}
