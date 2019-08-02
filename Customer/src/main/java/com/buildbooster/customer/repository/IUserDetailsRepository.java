package com.buildbooster.customer.repository;

import com.buildbooster.customer.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDetailsRepository extends JpaRepository<UserDetails, Long> {

}
