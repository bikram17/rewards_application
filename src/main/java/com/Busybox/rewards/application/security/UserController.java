package com.Busybox.rewards.application.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/model")
public class UserController {

	@Autowired UserService userService;
	
	@GetMapping("/getall")
	public List<UserModel> getusers() {
		System.out.println("Getting Users");
		return userService.getAllUsers();
	}
}
