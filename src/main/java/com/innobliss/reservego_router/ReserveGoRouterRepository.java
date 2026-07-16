package com.innobliss.reservego_router;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveGoRouterRepository extends JpaRepository<ReserveGoRouter, Long>  {
    
    ReserveGoRouter findByRgRestaurantId(String restaurantId);

}