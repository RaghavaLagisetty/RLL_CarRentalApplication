package com.Car_Rental_Spring.services;

import com.Car_Rental_Spring.entity.CarType;
import com.Car_Rental_Spring.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarTypeService {

    @Autowired
    private CarTypeRepository carTypeRepository;

    public List<CarType> getAllCarTypes() {
        return carTypeRepository.findAll();
    }

    public Optional<CarType> getCarTypeById(Long carTypeId) {
        return carTypeRepository.findById(carTypeId);
    }

    public CarType saveCarType(CarType carType) {
        return carTypeRepository.save(carType);
    }

    public void deleteCarType(Long carTypeId) {
        carTypeRepository.deleteById(carTypeId);
    }
}
