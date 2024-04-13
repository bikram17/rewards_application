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
@RequestMapping("api/mainmodel")
public class ApiMainModelController {

	@Autowired ApiMainModelImpl apiMainModelImpl;
	
	@GetMapping("getall")
	public ResponseEntity<?> getall(){
		return ResponseHandler.generateResponse(apiMainModelImpl.getall(), HttpStatus.OK	,"TEST");
	}
	
	@PostMapping("addOneModel")
	public ResponseEntity<?> addOneModelList(@RequestBody ApiMainModel apimodel){
		try {
		return ResponseHandler.generateResponse(apiMainModelImpl.addApiModelListOne(apimodel), HttpStatus.OK, "ADDED");
	}
		catch(Exception e) {}
		return ResponseHandler.generateResponseNull(apimodel, HttpStatus.BAD_REQUEST);
	}
}
