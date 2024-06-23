package com.Car_Rental_Spring.services;

import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car getCarById(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if(optionalCar.isPresent()){
            return optionalCar.get();
        }
        throw new EntityNotFoundException("Car Not Found");
    }

    public List<Car> getAllCarsByModel(String model) {
        return carRepository.findAllByModelContainingIgnoreCase(model);
    }


}
