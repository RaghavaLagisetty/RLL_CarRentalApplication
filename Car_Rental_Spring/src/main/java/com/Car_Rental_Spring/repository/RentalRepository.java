package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    // You can add custom query methods here if needed

    List<Rental> findAllByUserId(Long userId);

    void deleteAllByCarId(Long id);
}
