package com.Busybox.rewards.application.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.controller.ResponseHandler;

@RestController
@RequestMapping("/api/role-management")
public class RoleNController {

	@Autowired RoleNImpl roleNImpl;
	
	@GetMapping("/getall")
	public ResponseEntity<?> getall(){
		try {
			return ResponseHandler.generateResponse(roleNImpl.getall(), HttpStatus.OK, "SUCCESS");
		}catch(Exception e) {
			return ResponseHandler.generateResponse(e.getStackTrace(), HttpStatus.OK, "FAILED");
		}
	}
	
	@PostMapping("add-Role")
	public ResponseEntity<?> addRole(@RequestBody RoleN input){
		try {
			return ResponseHandler.generateResponse(roleNImpl.addRole(input), HttpStatus.OK, "SUCCESS");
		}catch(Exception e) {
			return ResponseHandler.generateResponseNull(e, HttpStatus.BAD_REQUEST);
		}
	}
	
}
