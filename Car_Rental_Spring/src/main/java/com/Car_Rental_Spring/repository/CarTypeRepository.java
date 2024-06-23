package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    // You can add custom query methods here if needed
}
