package com.Car_Rental_Spring.services;

import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.entity.CarType;
import com.Car_Rental_Spring.entity.Rental;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.repository.CarRepository;
import com.Car_Rental_Spring.repository.CarTypeRepository;
import com.Car_Rental_Spring.repository.RentalRepository;
import com.Car_Rental_Spring.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarTypeRepository carTypeRepository;

    @Autowired
    private RentalRepository rentalRepository;

    // Admin login
    public boolean adminLogin(String username, String password) {
        // Implement login logic here
        return false;
    }

    // Create admin account
    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.findByRole(User.UserRole.ADMIN);
        if (null == adminAccount) {
            // Create a new admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword("admin"); // Encrypt the password
            admin.setRole(User.UserRole.ADMIN);

            // Save the admin user to the database
            userRepository.save(admin);
        }

        List<CarType> carTypes = carTypeRepository.findAll();
        if(carTypes.size() == 0){
            CarType carType1 = new CarType("Basic", new BigDecimal(10));
            CarType carType2 = new CarType("Mid Range", new BigDecimal(20));
            CarType carType3 = new CarType("Luxury", new BigDecimal(40));

            carTypeRepository.saveAll(List.of(carType1,carType2, carType3));
        }
    }
    // CRUD operations for cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<CarType> getAllCarTypes() {
        return carTypeRepository.findAll();
    }

    public Car addCar(String make, String model, int year, int odometerReading,
                      Long cartype, BigDecimal costPerKilometer, MultipartFile image) throws IOException {
        Optional<CarType> optionalCarType = carTypeRepository.findById(cartype);
        if(optionalCarType.isPresent()){
            Car car = new Car();
            car.setMake(make);
            car.setModel(model);
            car.setYear(year);
            car.setOdometerReading(odometerReading);
            car.setImg(image.getBytes());
            car.setCostPerKilometer(costPerKilometer);
            car.setCartype(optionalCarType.get());

            return carRepository.save(car);
        }
        throw new EntityNotFoundException("Car Type Not Found");
    }

    public Car updateCar(Long id, String make, String model, int year, int odometerReading,
                      Long cartype, BigDecimal costPerKilometer, MultipartFile image) throws IOException {
        Optional<CarType> optionalCarType = carTypeRepository.findById(cartype);
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent() && optionalCarType.isPresent()){
            Car car = optionalCar.get();
            car.setMake(make);
            car.setModel(model);
            car.setYear(year);
            car.setOdometerReading(odometerReading);
            car.setCostPerKilometer(costPerKilometer);
            car.setCartype(optionalCarType.get());
            if(image!= null){
                car.setImg(image.getBytes());
            }
            return carRepository.save(car);
        }
        throw new EntityNotFoundException("Car Type Not Found");
    }

    @Transactional
    public void deleteCar(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if(optionalCar.isPresent()){
            rentalRepository.deleteAllByCarId(carId);
            carRepository.deleteById(carId);
            return;
        }
        throw new EntityNotFoundException("Car Not Found");
    }

    // Manage tax rates for different types of cars
    public void manageTaxRate(String carType, double taxRate) {
        // Implement logic to manage tax rates
    }

    // View rentals of all users
    public List<Rental> viewAllRentals() {
        return rentalRepository.findAll();
    }

    // View rental history for a specific user
    public List<Rental> viewRentalHistory(Long userId) {
        // Implement logic to view rental history for a specific user
        return null;
    }

    // Mark car as rented and update rental date
    public void markCarAsRented(Long carId, LocalDate rentalDate) {
        // Implement logic to mark car as rented and update rental date
    }

    // Calculate rental cost
    public BigDecimal calculateRentalCost(Car car, int distance, int rentalDuration, int loyaltyPoints) {
        // Implement logic to calculate rental cost
        return null;
    }
}
