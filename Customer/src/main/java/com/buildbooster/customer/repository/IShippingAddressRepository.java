package com.buildbooster.customer.repository;

import com.buildbooster.customer.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

}
