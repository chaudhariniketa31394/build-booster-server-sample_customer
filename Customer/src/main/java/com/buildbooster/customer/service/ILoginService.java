package com.buildbooster.customer.service;


import com.buildbooster.customer.model.ResponseBean;
import com.buildbooster.customer.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface ILoginService {
	
	 ResponseEntity<ResponseBean> validate(LoginRequest loginDto);
			
	}