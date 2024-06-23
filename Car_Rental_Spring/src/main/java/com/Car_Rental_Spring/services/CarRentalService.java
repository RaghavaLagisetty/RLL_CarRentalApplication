package com.Car_Rental_Spring.services;

import com.Car_Rental_Spring.dto.RentalDTO;
import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.enums.RentalStatus;
import com.Car_Rental_Spring.repository.CarRepository;
import com.Car_Rental_Spring.repository.RentalRepository;
import com.Car_Rental_Spring.entity.Rental;
import com.Car_Rental_Spring.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CarRentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Transactional
    public Rental rentCar(RentalDTO rentalDTO) {
        Optional<User> optionalUser = userRepository.findById(rentalDTO.getUserId());
        Optional<Car> optionalCar = carRepository.findById(rentalDTO.getCarId());

        if(optionalCar.isPresent() && optionalUser.isPresent()){
            Rental rental = new Rental();

            rental.setCar(optionalCar.get());
            rental.setUser(optionalUser.get());
            rental.setRentalStatus(RentalStatus.CREATED);
            rental.setStartOdometerReading(rentalDTO.getStartOdometerReading());
            rental.setEndOdometerReading(rentalDTO.getEndOdometerReading());
            rental.setStartDate(rentalDTO.getStartDate());
            rental.setEndDate(rentalDTO.getEndDate());

            int totalDistance = rentalDTO.getEndOdometerReading() - rentalDTO.getStartOdometerReading();
            rental.setTotalDistance(totalDistance);

            BigDecimal tex = optionalCar.get().getCartype().getTaxRate();
            BigDecimal totalAmount = optionalCar.get().getCostPerKilometer().multiply(BigDecimal.valueOf(totalDistance));
            BigDecimal taxAmount = (totalAmount.multiply(tex)).divide(BigDecimal.valueOf(100));
            BigDecimal totalExpense = totalAmount.add(taxAmount);

            rental.setRentalCost(totalExpense);

            rental.setRentalCost(applyPointsDiscount(optionalUser.get(), rental.getRentalCost()));
            rental = rentalRepository.save(rental);

            return rental;
        }

        throw new EntityNotFoundException("Car Or User Not Found");
    }

    public BigDecimal applyPointsDiscount(User user, BigDecimal totalAmount){
        if(user.getRoyaltyPoints()>25){
            // Calculate 10% of the amount
            BigDecimal percentage = new BigDecimal("0.10");
            BigDecimal result = totalAmount.multiply(percentage);

            user.setRoyaltyPoints(user.getRoyaltyPoints()-25);
            userRepository.save(user);
            return totalAmount.subtract(result);
        }
        return totalAmount;
    }

    public Rental updateRentalStatus(Long id, String rentalStatus) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if(optionalRental.isPresent()){
            Rental rental = optionalRental.get();
            if(rentalStatus.equals("APPROVED")){
                rental.setRentalStatus(RentalStatus.APPROVED);

                Car car = rental.getCar();
                car.setOdometerReading(rental.getEndOdometerReading());
                carRepository.save(car);

                return rentalRepository.save(rental);
            } else if(rentalStatus.equals("REJECTED")){
                rental.setRentalStatus(RentalStatus.REJECTED);
                return rentalRepository.save(rental);
            } else if(rentalStatus.equals("COMPLETED")){
                rental.setRentalStatus(RentalStatus.COMPLETED);
                rental = rentalRepository.save(rental);

                User user = rental.getUser();
                int loyaltyPoints = rental.getTotalDistance() / 50;
                user.setRoyaltyPoints(user.getRoyaltyPoints() + loyaltyPoints);
                userRepository.save(user);

                return rental;
            } else if(rentalStatus.equals("CANCELED")){
                rental.setRentalStatus(RentalStatus.CANCELED);
                rental = rentalRepository.save(rental);
                return rental;
            }
        }
        throw new EntityNotFoundException("Car Or User Not Found");
    }
    public void returnCar(Rental rental, int endOdometerReading) {
        // Implement logic to handle returning a rented car and update end odometer reading
        rental.setEndOdometerReading(endOdometerReading);
        rental.calculateTotalDistance(); // Fixed method call to calculate total distance
        rental.calculateRentalCost(rental.getCar(), rental.getTotalDistance());
        rentalRepository.save(rental);
    }

    public Rental getRentalById(Long rentalId) {
        return rentalRepository.findById(rentalId).orElse(null);
    }

    public void cancelRental(Long rentalId) {
        rentalRepository.deleteById(rentalId);
    }

    // Other methods as needed

    public List<Rental> getRentalsByUser(Long userId) {
        return rentalRepository.findAllByUserId(userId);
    }
}
