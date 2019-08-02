

package com.buildbooster.customer.repository;


import com.buildbooster.customer.model.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ILoginRepository extends JpaRepository<BusinessDetails,Long> {
	
	@Query("From BusinessDetails r  where r.email=:id")
    BusinessDetails getBidByEmail(@Param("id") String id);
	
	

}
