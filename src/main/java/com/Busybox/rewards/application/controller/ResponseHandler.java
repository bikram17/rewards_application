package com.Busybox.rewards.application.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Busybox.rewards.application.dto.Final_Response_All_User_Details;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(Object responseObj, HttpStatus status,String message ) {
		Map<String, Object> map = new LinkedHashMap<>();
		String statusMessage;
		if(status.value()==200 || status.value()==202 || status.value()==201)
		{
			statusMessage = "Success";
		}else  statusMessage = "Failure";
		map.put("message", message);
		map.put("status_message", statusMessage);
		map.put("status", status.value());
		map.put("data", responseObj);
		
		

		return new ResponseEntity<>(map, status);
	}
	
	public static ResponseEntity<Object> generateResponseOfToken(Object responseObj, Object token,String name, HttpStatus status, String message) {
	    Map<String, Object> data = new LinkedHashMap<>();
	    data.put("PhoneNumber", responseObj);
	    data.put("token", token);
	    data.put("CustomerName", name);

	    String statusMessage = (status.value() == 200 || status.value() == 202 || status.value() == 201) ? "Success" : "Failure";

	    Map<String, Object> map = new LinkedHashMap<>();
	    map.put("message", message);
	    map.put("status_message", statusMessage);
	    map.put("status", status.value());
	    map.put("data", data);

	    return new ResponseEntity<>(map, status);
	}

	
	public static ResponseEntity<Object> generateResponseNull(Object responseObj, HttpStatus status) {
        Map<String, Object> map = new LinkedHashMap<>();
       // map.put("message", message);
        String statusMessage;
		if(status.value()==200 || status.value()==202 || status.value()==201)
		{
			statusMessage = "Success";
		}else  statusMessage = "Failure";
		map.put("status_message", statusMessage);
        map.put("status", status.value());
        map.put("messageæ", responseObj);
        
        return new ResponseEntity<>(map, status);
    }
	public static ResponseEntity<Object> generateResponseList(List<Object> responseObj, HttpStatus status,String message ) {
		Map<String, Object> map = new LinkedHashMap<>();
		String statusMessage;
		if(status.value()==200 || status.value()==202 || status.value()==201)
		{
			statusMessage = "Success";
		}else  statusMessage = "Failure";
		
		
		map.put("message", message);
		map.put("status_message", statusMessage);
		map.put("status", status.value());
		map.put("data", responseObj);
		
		

		return new ResponseEntity<>(map, status);
	}
	// VOucher Specific 
	public static ResponseEntity<Object> generateResponseList2(List<?> responseObj, HttpStatus status,String message) {
		Map<String, Object> map = new LinkedHashMap<>();
		String statusMessage;
		if(status.value()==200 || status.value()==202 || status.value()==201)
		{
			statusMessage = "Success";
		}else  statusMessage = "Failure";
		map.put("message", message);
		map.put("status_message", statusMessage);
		map.put("status", status.value());
		map.put("data", responseObj);
		
		

		return new ResponseEntity<>(map, status);
	}
	public static ResponseEntity<Object> generateResponseForCustomerID(String responseObj, HttpStatus status, String message) {
        Map<String, Object> map = new LinkedHashMap<>();
        String statusMessage;
		if(status.value()==200 || status.value()==202 || status.value()==201)
		{
			statusMessage = "Success";
		}else  statusMessage = "Failure";
       // map.put("message", message);
        map.put("message", message);
        map.put("status_message", statusMessage);
        map.put("status:", status.value());
        map.put("token:", responseObj);


        return new ResponseEntity<>(map, status);
    }
	    // Other methods...

	    public static Map<String, Object> extractMapFromResponseEntity(ResponseEntity<?> responseEntity) {
	        Map<String, Object> responseMap = new LinkedHashMap<>();
	        responseMap.put("status_code", responseEntity.getStatusCode());
	        responseMap.put("status_message", responseEntity.getStatusCode().is2xxSuccessful() ? "Success" : "Failure");
	        responseMap.put("data", responseEntity.getBody());
	        return responseMap;
	    }

	
	public static ResponseEntity<Object> getCustomizedDataOfOne(Final_Response_All_User_Details fullDetails, HttpStatus status, String Message){
		Map<String,Object> map = new LinkedHashMap<>();
		String statusMessage;
		if(status.value()==200 || status.value()==202 || status.value()==201)
		{
			statusMessage = "Success";
		}else  statusMessage = "Failure";
		map.put("status_message", statusMessage);
		map.put("StatusCode : " , status);
		map.put("StatusMessage : ", Message);
		map.put("Data : ", fullDetails);
		;
		return new ResponseEntity<>(map,status);
	}
	// 
	public static ResponseEntity<Object> generateResponseForMoneyTransaction(Map<String, String> userinput,HttpStatus status, String message) {
		Map<String, Object> map = new LinkedHashMap<>();
		String statusMessage;
		if(status.value()==200 || status.value()==202 || status.value()==201)
		{
			statusMessage = "Success";
		}else  statusMessage = "Failure";
		map.put("message", message);
		map.put("status_message", statusMessage);
		map.put("status_code", status.value());
		map.put("input data", userinput);
		

		return new ResponseEntity<>(map, status);
	}
	
	
//	public static ResponseEntity<Object> generateResponseForMoneyTransaction2(String status,String message) {
//	    HttpStatus httpStatus;
//	    if ("success".equalsIgnoreCase(status)) {
//	        httpStatus = HttpStatus.OK;
//	    } else if ("failure".equalsIgnoreCase(status)) {
//	        httpStatus = HttpStatus.NOT_FOUND; // You can change this to the appropriate HTTP status for failure
//	    } else {
//	        // Handle other cases if needed
//	        httpStatus = HttpStatus.BAD_REQUEST; // Default to a bad request status
//	    }
//
//	    Map<String, Object> map = new LinkedHashMap<>();
//	    map.put("message", message);
//	    map.put("status_code", httpStatus.value());
//	    map.put("status_message", status);
//	   
//
//	    return new ResponseEntity<>(map, httpStatus);
//	}
	
	
	
	
	public static ResponseEntity<Object> generateResponseForMoneyTransaction2(String status,String message) {
		   HttpStatus httpStatus;
		   if ("success".equalsIgnoreCase(status)) {
		       httpStatus = HttpStatus.OK;
		   } else if ("failure".equalsIgnoreCase(status)) {
		       httpStatus = HttpStatus.NOT_FOUND; // You can change this to the appropriate HTTP status for failure
		   } else {
		       // Handle other cases if needed
		       httpStatus = HttpStatus.BAD_REQUEST; // Default to a bad request status
		   }

		   Map<String, Object> map = new LinkedHashMap<>();
		   map.put("message", message);
		   map.put("status_code", httpStatus.value());
		   map.put("status_message", status);
		 

		   return new ResponseEntity<>(map, httpStatus);
		}
		public static ResponseEntity<Object> generateResponse2(Integer pageNumber, Integer pageSize, Object allLogsPagewise, HttpStatus status, String message) {
		    Map<String, Object> response = new LinkedHashMap<>();
		    
		    String statusMessage;
		    if(status.value()==200)
		    {
		    statusMessage = "Success";
		    }else  statusMessage = "Failure";
		    response.put("status_message", statusMessage);
		    response.put("pageNumber", pageNumber);
		    response.put("pageSize", pageSize);
		    response.put("data", allLogsPagewise);
		    response.put("message", message);
		    
		    return new ResponseEntity<>(response, status);
		}
		public static ResponseEntity<?> generateResponseForMessageandstatus(String status,String message) {
			 HttpStatus httpStatus;
			   if ("success".equalsIgnoreCase(status)) {
			       httpStatus = HttpStatus.OK;
			   } else if ("failure".equalsIgnoreCase(status)) {
			       httpStatus = HttpStatus.NOT_FOUND; // You can change this to the appropriate HTTP status for failure
			   } else {
			       // Handle other cases if needed
			       httpStatus = HttpStatus.BAD_REQUEST; // Default to a bad request status
			   }

			   Map<String, Object> map = new LinkedHashMap<>();
			   map.put("message", message);
			   map.put("status_code", httpStatus.value());
			   map.put("status_message", status);
			 

			   return new ResponseEntity<>(map, httpStatus);
			}
}  