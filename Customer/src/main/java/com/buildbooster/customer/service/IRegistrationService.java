package com.buildbooster.customer.service;


import com.buildbooster.customer.model.BusinessDetails;
import com.buildbooster.customer.model.Location;
import com.buildbooster.customer.model.ResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public interface IRegistrationService {
	
	public ResponseEntity<ResponseBean> saveUser(BusinessDetails user) ;

	public ResponseEntity<ResponseBean> saveLocation(Location location,Long businessDetailId);

	public ResponseEntity<ResponseBean> saveBusiness(BusinessDetails businessDetail);
}