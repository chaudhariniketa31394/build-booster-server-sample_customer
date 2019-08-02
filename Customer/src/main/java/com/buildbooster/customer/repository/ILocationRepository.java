package com.buildbooster.customer.repository;

import com.buildbooster.customer.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ILocationRepository extends JpaRepository<Location, Long> {

}
