package com.Busybox.rewards.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.controller.ResponseHandler;
import com.Busybox.rewards.application.dao.AddressDao;
import com.Busybox.rewards.application.model.AddressModel;
import com.Busybox.rewards.application.service.AddressService;


@Service
public class AddressImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;

	@Override
	public AddressModel getAddressById(int customer_id) {

		return addressDao.findById(customer_id).orElse(null);
	}

	@Override
	public ResponseEntity<?> addAddress(AddressModel addresModel) {

		return ResponseHandler.generateResponse(addressDao.save(addresModel), HttpStatus.OK	, "Called");
	}

	

	/*
	 * @Override public ResponseEntity<?> editAddress(AddressModel addresModel, int
	 * customer_id) {
	 * 
	 * int id = addressDao.getidnow(customer_id); AddressModel
	 * oldaddresModel=addressDao.getById(id); if(oldaddresModel==addresModel) {
	 * System.out.println("Enter new value"); return
	 * ResponseHandler.generateResponse(addresModel,HttpStatus.BAD_REQUEST,
	 * "Changes Saved Successfully"); } else {
	 * 
	 * addressDao.save(addresModel); return
	 * ResponseHandler.generateResponse(addresModel,HttpStatus.OK,
	 * "Changes Saved Successfully"); }
	 * 
	 * }
	 */

	

}
