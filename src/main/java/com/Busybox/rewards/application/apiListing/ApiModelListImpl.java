package com.Busybox.rewards.application.apiListing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ApiModelListImpl {

	@Autowired ApiModelListDao apiModelListDao;
	
	public List<ApiModelList> getall(){
		
			return apiModelListDao.findAll();
		
		
	}
	
	public ApiModelList saveOne(ApiModelList entity) {
		return apiModelListDao.save(entity);
	}
}
