package com.buildbooster.customer;

import com.buildbooster.customer.model.*;
import com.buildbooster.customer.repository.IBusinessDetailsRepository;
import com.buildbooster.customer.repository.ILocationRepository;
import com.buildbooster.customer.repository.IShippingAddressRepository;
import com.buildbooster.customer.repository.IUserDetailsRepository;
import com.buildbooster.customer.service.IRegistrationService;
import com.buildbooster.customer.serviceimpl.RegistrationServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationJunitTest {

    @InjectMocks
    RegistrationServiceImpl registrationService;

    @Mock
    IBusinessDetailsRepository businessDetailsRepository;

    @Mock
    ILocationRepository locationRepository;

    @Mock
    IShippingAddressRepository shippingAddressRepository;

    @Mock
    IUserDetailsRepository userDetailsRepository;


    @Autowired
    private ResponseBean responseBean1;

    @Test
    public void saveTest()
    {
        Location location1 = new Location( (long)1,"hjj","pune","elexniki3194@rediffmail.com","pune","maharastra"
                ,"424001","India",null);

        List<Location> locationList = new ArrayList<>();
        locationList.add(location1);


        UserDetails userDetails1 = new UserDetails((long)1,"hjk","ykl","xyhhsj","123","213","retailer"
                ,"flase" , "345",null);
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(userDetails1);

        ShippingAddress shippingAddress = new ShippingAddress((long)1,"aaa@gmail.com","bbb@gmail.com","pune","maharastra", "424003"
                ,"Connecticus",null);
        List<ShippingAddress> shippingAddressList = new ArrayList<>();
        shippingAddressList.add(shippingAddress);



        BusinessDetails businessDetails = new BusinessDetails((long)1,"abc","IBM","elexniki@gmail.com","ranu@gmail.com"
                ,"Dhule","Maharastra","42401","India","9975675736", "333","elexniki3194@rediffmail.com","true","44"
                ,"32","Nikita","chaudhari","yyy","4",locationList,userDetailsList,shippingAddressList);

        registrationService.saveUser(businessDetails);

        verify(businessDetailsRepository, times(1)).save(businessDetails);
        assertEquals(true,businessDetails.getLocation().size()>0);
    }


}
