package com.buildbooster.customer;

import com.buildbooster.customer.model.*;
import com.buildbooster.customer.service.ILoginService;
import com.buildbooster.customer.service.IRegistrationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistrationServiceIntTest {



    @Autowired
    IRegistrationService registrationService;

    @Test
    public void saveUser() {

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


        ResponseEntity<ResponseBean> businessDetailsbean1=registrationService.saveUser(businessDetails);
    }

}
