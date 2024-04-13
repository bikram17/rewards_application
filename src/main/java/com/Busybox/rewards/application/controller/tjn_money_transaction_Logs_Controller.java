package com.Busybox.rewards.application.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Busybox.rewards.application.dao.TransactionLogRepo;
import com.Busybox.rewards.application.dao.tjn_money_transaction_Logs_dao;
import com.Busybox.rewards.application.model.tjn_money_transaction_model_Logs;
import com.Busybox.rewards.application.service.tjn_money_transaction_Logs_Service;

@CrossOrigin("*")
@RestController
@RequestMapping("api/page")
 // POSTMAN URL FOR SEARCHING ::  localhost:8080/api/page/alllogs?pageNumber=2&pageSize=5
public class tjn_money_transaction_Logs_Controller {
	
	@Autowired
	private tjn_money_transaction_Logs_Service logService;
//	@Autowired TransactionLogRepo LogDao;
	@Autowired
	private tjn_money_transaction_Logs_dao logDao;
	@GetMapping("/hello")
	private String hello() {
		return "hello  this is pagination api" ;
	}
	@GetMapping("/Alllogs")
	public ResponseEntity<Object>getAllLogs(){
		
		return ResponseHandler.generateResponse(logService.getAllLogs(),HttpStatus.OK,"Details Fetched Successfully");
	}
	
	
	
	
	
	
	@GetMapping("/alllogs")
	public  ResponseEntity<Object>getAllLogs(
			
	        @RequestParam(value = "pageNumber",defaultValue="1",required=false) Integer pageNumber,
	        @RequestParam(value = "pageSize",defaultValue="5",required=false) Integer pageSize){
	
		
	   List<Map<String, Object>> logs = logDao.findAllLogs(pageNumber, pageSize);
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("PageNo", pageNumber);
	    response.put("PageSize", pageSize);
	    response.put("message", "Fetched Successfully");
	    response.put("status", HttpStatus.OK.value());
	    response.put("data", logs);

	    //return ResponseEntity.status(HttpStatus.OK).body(response);
		 //Pageable pageable = PageRequest.of(pageNumber / pageSize, pageSize);
		 //Page<tjn_money_transaction_model_Logs> page = LogDao.findAll(pageable);
		
		
	    return ResponseHandler.generateResponse2(pageNumber, pageSize,logService.getALLLogsPagewise(pageNumber, pageSize),HttpStatus.OK, "Data Fetched Successfully");
	    
	}
	
	/*
	 * @GetMapping("/alllogs/{customer_mobno}") public
	 * ResponseEntity<?>getByMobno(@PathVariable String customer_mobno){
	 * 
	 * return
	 * ResponseHandler.generateResponse(logService.getByMobno(customer_mobno),
	 * HttpStatus.OK,"Fetched Successfully"); }
	 */
	
	@GetMapping("/alllogs/{phone_number}")
    public ResponseEntity<Object> findByPhoneNumber(@PathVariable String phone_number) {
        return ResponseHandler.generateResponse(logService.findByPhoneNumber(phone_number),HttpStatus.OK,"Fetched Succcessfuly");
			
	
       
	}
}

