package com.Busybox.rewards.application.apiListing;

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
@RequestMapping("/api/api-listing")
public class ApiModelListController {

	@Autowired ApiModelListImpl apiModelListImpl;
	
	@GetMapping ("/hello")
	public String hello() {
		return "HELLO";
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?>  getall(){
		return ResponseHandler.generateResponse(apiModelListImpl.getall(), HttpStatus.OK, "GET DATA");
	}

	@PostMapping("/add/sub-mode-api-list")
	public ResponseEntity<?> addApiList(@RequestBody ApiModelList input ){
		try {
			return ResponseHandler.generateResponse(apiModelListImpl.saveOne(input), HttpStatus.OK	, "SUCCESS");
		}
		catch(Exception e) {
			return ResponseHandler.generateResponseNull("FAILED", HttpStatus.BAD_REQUEST);
		}
	}
	
}
