package com.buildbooster.customer.service;

import com.buildbooster.customer.model.BusinessDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface IUpdateUserService {

	public BusinessDetails updateUser(BusinessDetails businessDetails) ;

}