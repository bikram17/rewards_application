package com.Busybox.rewards.application.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleNImpl {

	@Autowired RoleNDao roleNDao;
	
	public List<RoleN> getall(){
		return roleNDao.findAll();
	}
	
	public RoleN addRole(RoleN input) {
		return roleNDao.save(input);
	}
	
	
}
