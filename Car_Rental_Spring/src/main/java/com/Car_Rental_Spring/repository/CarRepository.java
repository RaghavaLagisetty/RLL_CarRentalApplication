package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // You can add custom query methods here if needed

    List<Car> findAllByModelContainingIgnoreCase(String model);
}
