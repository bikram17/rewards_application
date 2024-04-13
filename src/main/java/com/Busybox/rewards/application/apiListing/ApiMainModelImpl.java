package com.Busybox.rewards.application.apiListing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiMainModelImpl {

	@Autowired private ApiMainModelDao apiMainModelDao;
	
	public List<ApiMainModel> getall() {
		return apiMainModelDao.findAll();
	}
	
	public ApiMainModel addApiModelListOne(ApiMainModel apiModelList) {
		return apiMainModelDao.save(apiModelList);
	}
}
