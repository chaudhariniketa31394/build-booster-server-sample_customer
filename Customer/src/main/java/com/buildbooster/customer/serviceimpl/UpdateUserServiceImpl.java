package com.buildbooster.customer.serviceimpl;

import com.buildbooster.customer.model.BusinessDetails;
import com.buildbooster.customer.repository.ILoginRepository;
import com.buildbooster.customer.service.IUpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UpdateUserServiceImpl implements IUpdateUserService {

	
	@Autowired
	ILoginRepository loginRepository;
	
	public BusinessDetails updateUser(BusinessDetails businessDetails) {
		
		
		return loginRepository.getBidByEmail(businessDetails.getEmail());	
}
}