package com.Busybox.rewards.application.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.parent_class_master_dao;
import com.Busybox.rewards.application.dao.referral_model_master_balance_dao;
import com.Busybox.rewards.application.model.parent_class_master;
import com.Busybox.rewards.application.model.referral_model_master_balance;
import com.Busybox.rewards.application.security.JwtHelper;
import com.Busybox.rewards.application.security.UserModel;
import com.Busybox.rewards.application.security.UserRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class referral_model_master_balance_impl {

	@Autowired private referral_model_master_balance_dao rmmbDao;
	@Autowired private parent_class_master_dao codedao;
	@Autowired private UserRepository userRepository;
	JwtHelper jwtHelper = new JwtHelper();
	
	
	public String abcabc(String code, String ChildphoneNumber) {
		long childId = rmmbDao.getIdUsingPhoneNumber(ChildphoneNumber);
		parent_class_master childData = codedao.findById(childId).orElse(null);		
		if(childId == 0) {
			return "Not OK";
		}
			// GOT PARENT ID 
			referral_model_master_balance entry= new referral_model_master_balance();
			entry.setParentCode(code);
			entry.setParentName(rmmbDao.getParentName(code));
			entry.setParentId(rmmbDao.getParentId(code));
			entry.setChildId(childData.getMaster_id());
			entry.setChildName(childData.getMasterName());
			entry.setChildMobno(ChildphoneNumber);
			entry.setStatus("ACTIVE");
			rmmbDao.save(entry);                   
			return "OK DONE";	  
}
	
	public ResponseEntity<?> ifExists(String parentId) {
		try {
			long test = rmmbDao.getIdUsingReferralCode(parentId);
			if(test != 0) {
				
				return ResponseHandler.generateResponse(test, HttpStatus.OK, parentId);
			}
			else {
				return ResponseHandler.generateResponseNull(parentId, null);
			}
		}catch (Exception e ) {
			
				return ResponseHandler.generateResponse(e.toString(), HttpStatus.BAD_REQUEST, parentId);
			
		}
	}
	
	
	public String abc(String code, String ChildphoneNumber) {
	    // First, check if the code is valid or not
	    // parent_class_master child = rmmbDao.abcd(phoneNumber);
	    // parent_class_master parent = rmmbDao.abc(code);
		long childId = rmmbDao.getIdUsingPhoneNumber(ChildphoneNumber);
	    long parentId = rmmbDao.getIdUsingReferralCode(code);
	    

	    // Retrieve child and parent data from the database
	    parent_class_master childData = codedao.findById(childId).orElse(null);
	    parent_class_master ParentData = codedao.findById(parentId).orElse(null);

	    // Check if ParentData is null
	    if (ParentData == null) {
	        // Handle the case where ParentData is null, e.g., log an error or return an appropriate response
	        return "Parent not found"; // Modify this according to your error handling logic
	    }
       
	    // ParentData is not null, proceed with creating and saving the entry
	    referral_model_master_balance entry = new referral_model_master_balance();
	    entry.setParentCode(code);
	    entry.setParentName(ParentData.getMasterName());
	    entry.setParentId(ParentData.getMaster_id());
          
	    // Check if childData is null
	    if (childData != null) {
	        entry.setChildId(childData.getMaster_id());
	        entry.setChildName(childData.getMasterName());
	        entry.setChildMobno(ChildphoneNumber);
	    } else {
	        // Handle the case where childData is null, e.g., log an error or return an appropriate response
	        return "Child not found"; // Modify this according to your error handling logic
	    }
	       
	    entry.setStatus("ACTIVE");
	    rmmbDao.save(entry);
	    return "OK DONE";
	
}
	public String[] testID (@PathVariable String data){
		try {
			long test = rmmbDao.getIdUsingReferralCode(data);
			if(test != 0) {
				
				String[] passed = new String[2];
				passed[0]= "PASSED";
				passed[1]= String.valueOf(test);
				return passed;
			}
			else {
				String[] failed2 = new String[2];
				failed2[0]="FAILED";
				failed2[1]="Failed To Locate Referral Code/ Missing";
				
				return failed2;
			}
		}catch (Exception e ) {
			String[] failed3 = new String[2];
			failed3[0]="FAILED";
			failed3[1]="Failed To Locate Referral Code/ Missing";
				return failed3;
			
		}

	
	}
	public Map<?, ?> getDataOfReferralCountWithAdditionalInfo1(){
		   List<Map<String, Object>> listOfIdAndCount = rmmbDao.getReferralCount();
		   Map<String, Object> resultMap = new LinkedHashMap<>();
		   
		   for (Map<String, Object> row : listOfIdAndCount) {
			    String parentId = (String) row.get("parent_id");
			    Long childCount = (Long) row.get("child_count"); // Use Long for numeric values
			    String name = rmmbDao.getName(parentId);           
			    String phoneNumber = rmmbDao.getPhoneNumber(parentId);
			    String referralCode = rmmbDao.getReferralCode(parentId);
			    
			    //System.out.println(parentId + " " + childCount + " " + name + " " + phoneNumber + " " + referralCode);
			    Map<String, Object> rowDataMap = new LinkedHashMap<>();
	            rowDataMap.put("parent_id", parentId);
	            rowDataMap.put("child_count", childCount);
	            rowDataMap.put("name", name);
	            rowDataMap.put("phoneNumber", phoneNumber);
	            rowDataMap.put("ReferralCode", referralCode);

	            // Add the map to the resultMap
	            resultMap.put(parentId, rowDataMap);
			}
		    return resultMap;
		    }
	
	public List<Map<String, Object>> getDataOfReferralCountWithAdditionalInfo(HttpServletRequest request) {
		
		String jwtToken = request.getHeader("Authorization");
	    	String storeId = "ADMIN";
 	        jwtToken = jwtToken.substring(7); 
	        Claims claims = jwtHelper.getAllClaimsFromToken(jwtToken);
	        System.out.println(claims);
	        String username = claims.get("sub", String.class);
	        UserModel userModel= userRepository.findByEmail(username).orElse(null);
	        storeId = userModel.getStoreId();
	        System.out.println("User " + username + " accessed the API.");
		
		
	    List<Map<String, Object>> listOfIdAndCount;
	    List<Map<String, Object>> resultList = new ArrayList<>();
	    
	    try {
	    	if(storeId == null) {
	        listOfIdAndCount = rmmbDao.getReferralCount();
	        for (Map<String, Object> row : listOfIdAndCount) {
	            String parentId = (String) row.get("parent_id");
	            Long childCount = (Long) row.get("child_count");
	            String name = rmmbDao.getName(parentId);
	            String phoneNumber = rmmbDao.getPhoneNumber(parentId);
	            String referralCode = rmmbDao.getReferralCode(parentId);
	            Map<String, Object> rowDataMap = new LinkedHashMap<>();
	            rowDataMap.put("child_count", childCount);
	            rowDataMap.put("name", name);
	            rowDataMap.put("phoneNumber", phoneNumber);
	            rowDataMap.put("ReferralCode", referralCode);
	            resultList.add(rowDataMap);
	        }
	        }else {
	        	listOfIdAndCount = rmmbDao.getReferralCount();
		        for (Map<String, Object> row : listOfIdAndCount) {
		            String parentId = (String) row.get("parent_id");
		            Long childCount = (Long) row.get("child_count");
		            String name = rmmbDao.getName(parentId);
		            String phoneNumber = rmmbDao.getPhoneNumberStoreBasis(parentId,storeId);
		            String referralCode = rmmbDao.getReferralCodeStoreBasis(parentId,storeId);
		            Map<String, Object> rowDataMap = new LinkedHashMap<>();
		            rowDataMap.put("child_count", childCount);
		            rowDataMap.put("name", name);
		            rowDataMap.put("phoneNumber", phoneNumber);
		            rowDataMap.put("ReferralCode", referralCode);
		            resultList.add(rowDataMap);
	        }
	        }
	    } catch (Exception e) {
	        // Handle exceptions here, e.g., log the error or return an error message
	        e.printStackTrace();
	    }

	    return resultList;
	}
	/**
	 * This method is deprecated and will be removed in future versions.
	 * Use {@link #getDataOfReferralCountWithAdditionalInfo(HttpServletRequest request)} instead.
	 *
	 * @deprecated This method is out dated and should be avoided.
	 */
	@Deprecated
	public List<Map<String, Object>> getDataOfReferralCountWithAdditionalInfodecap(HttpServletRequest request) {
		List<Map<String, Object>> listOfIdAndCount;
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		try {
			listOfIdAndCount = rmmbDao.getReferralCount();
			
			for (Map<String, Object> row : listOfIdAndCount) {
				String parentId = (String) row.get("parent_id");
				Long childCount = (Long) row.get("child_count");
				String name = rmmbDao.getName(parentId);
				String phoneNumber = rmmbDao.getPhoneNumber(parentId);
				String referralCode = rmmbDao.getReferralCode(parentId);
				
				Map<String, Object> rowDataMap = new LinkedHashMap<>();
				rowDataMap.put("child_count", childCount);
				rowDataMap.put("name", name);
				rowDataMap.put("phoneNumber", phoneNumber);
				rowDataMap.put("ReferralCode", referralCode);
				
				resultList.add(rowDataMap);
			}
		} catch (Exception e) {
			// Handle exceptions here, e.g., log the error or return an error message
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	
	
	
	
	// rmmbDao
	public List<Map<String, Object>> getDataOfchildReferral(String code) {
		long x = rmmbDao.getParentId(code);
		String parentId = String.valueOf(x);
		//System.out.println(rmmbDao.getChildDataOfThisParent(parentId));
		return rmmbDao.getChildDataOfThisParent(parentId);
//	    return result;
	}



//

}
