package com.buildbooster.customer.api;

import com.buildbooster.customer.model.BusinessDetails;
import com.buildbooster.customer.model.Location;
import com.buildbooster.customer.model.ResponseBean;
import com.buildbooster.customer.repository.ILoginRepository;
import com.buildbooster.customer.request.LoginRequest;
import com.buildbooster.customer.service.ILoginService;
import com.buildbooster.customer.service.IRegistrationService;
import com.buildbooster.customer.service.IUpdateUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;


@RestController
@RequestMapping("/customer")
@Api(value = "HelloWorld Resource", description = "shows business detail registration and login end points")
public class CustomerController {
	
	
	@Autowired
	public IRegistrationService registrationService;
	
	@Autowired
	ILoginService loginService;

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Returns Sucess in Successful login")
	public ResponseEntity<ResponseBean> login(@Valid @RequestBody LoginRequest loginDetails) {
		return loginService.validate(loginDetails);
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Returns Suc cess in Successful registration")
	public ResponseEntity<ResponseBean> createuser(@Valid @RequestBody BusinessDetails user) {
		
		return	registrationService.saveUser(user);
		
	}
	@GetMapping(path = "/activateUser")
	@ResponseBody
	@ApiOperation(value = "Returns Sucess in Successful activation of user")
	public ResponseEntity<ResponseBean> activateuUser(@RequestParam("email") String email, @RequestParam("password") String password) {
		
//		System.out.println(email +" " +password);
//		BusinessDetails	businessDetails = new BusinessDetails();
//		businessDetails.setEmail(email);
//		updateUserService.updateUser(businessDetails);
		
		return null;
		
	}

	@PostMapping(path = "createBusinessDetail", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Returns Success in Successful registration")
	public ResponseEntity<ResponseBean> saveBusinessDetail(@Valid @RequestBody BusinessDetails businessDetails) {

		return	registrationService.saveBusiness(businessDetails);

	}

	@PutMapping(path = "/updatelocation", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Returns Suc cess in Successful registration")
	public ResponseEntity<ResponseBean> saveLocation(@Valid @RequestBody Location location,@RequestParam("businessDetailId") Long businessDetailId) {

		return	registrationService.saveLocation(location,businessDetailId);

	}

}




