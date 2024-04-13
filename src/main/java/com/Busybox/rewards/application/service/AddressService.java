package com.Busybox.rewards.application.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Busybox.rewards.application.model.AddressModel;

public interface AddressService {

	AddressModel getAddressById(int customer_id);

	//ResponseEntity<?> addAddress(AddressModel addresModel);

	//Object addAddress(AddressModel addresModel, HttpStatus ok, String string);

	Object addAddress(AddressModel addresModel);

//	Object updateAddress(AddressModel addressModel);

	//AddressModel findByCustomerId(String customerId);

	//Object editAddress(AddressModel addresModel, int customer_id);

}