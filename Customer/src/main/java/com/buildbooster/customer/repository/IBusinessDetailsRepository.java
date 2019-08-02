package com.buildbooster.customer.repository;


import com.buildbooster.customer.model.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface IBusinessDetailsRepository extends JpaRepository<BusinessDetails,Long> {


    @Query("From BusinessDetails r  where r.email=:id")
    BusinessDetails getBidByEmail(@Param("id") String id);




}

