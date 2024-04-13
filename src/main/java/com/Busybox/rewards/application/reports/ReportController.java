package com.Busybox.rewards.application.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.CustomerRepository;


@RestController
@RequestMapping("api/report")
public class ReportController {

	
	@Autowired ReportsRepository reportsRepository;
	@Autowired CustomerRepository customerRepository;

	@GetMapping("total/rewards/{phn}")
	public ResponseEntity<?> totalRewards(@PathVariable String phn) {
	    try {
	        
	        // get customer id 
	        int id = customerRepository.getcustomerID(phn);
	        int childCount = reportsRepository.countOfChildReferralId(id);
	        double LifeTimeEarning= reportsRepository.totalRewardBalanceOfOneCustomer(id);
	        double daily = reportsRepository.currentDateRewardOfCustomer(id) ;
	        double monthlyEarming = reportsRepository.currentMonthRewardOfCustomer(id);
	        double YearlyEarning = reportsRepository.currentYearRewardOfCustomer(id);
	        Map<String, Object> responseData = new HashMap<>();
	        responseData.put("TotalRewardsEarning", LifeTimeEarning);
	        responseData.put("DailyReferralEarnings", daily);
	        responseData.put("MonthlyEarning", monthlyEarming);
	        responseData.put("YearlyEarning", YearlyEarning);
	        responseData.put("TotalReferralIdCount", childCount);
	        return ResponseHandler.generateResponse(responseData, HttpStatus.OK, "SUCCESS");
	    } catch (Exception e) {
	        return ResponseHandler.generateResponse(e.toString(), HttpStatus.OK, "FAILED");
	    }
	}

	
		
	@GetMapping("daily/rewards/{phn}")
	public ResponseEntity<?> dailyRewards(@PathVariable String phn){
		try{
			int id = customerRepository.getcustomerID(phn);
			String res ="Amount " + reportsRepository.currentDateRewardOfCustomer(id) ;
			return ResponseHandler.generateResponse(res, HttpStatus.OK, "SUCCESS");
		}catch(Exception e) {
			return ResponseHandler.generateResponse(e, HttpStatus.OK, "FAILED");
		}
	}
	
	@GetMapping("earning/child/{PhoneNumber}")
	public ResponseEntity<?> parentEarningsFromChild(@PathVariable String PhoneNumber) {
	    try {
	        int customerId = customerRepository.getcustomerID(PhoneNumber);
	        List<Object[]> rawResults = reportsRepository.perCustomerEarning(customerId);

	        List<Map<String, Object>> results = new ArrayList<>();
	        for (Object[] row : rawResults) {
	            Map<String, Object> result = new HashMap<>();
	            result.put("referred_customer_name", row[0]);
	            result.put("total_referral_earnings", row[1]);

	            results.add(result);
	        }

	        return ResponseHandler.generateResponse(results, HttpStatus.OK, "SUCCESS");
	    } catch (Exception e) {
	        return ResponseHandler.generateResponse(e.toString(), HttpStatus.BAD_REQUEST, "FAILED");
	    }
	}


}
