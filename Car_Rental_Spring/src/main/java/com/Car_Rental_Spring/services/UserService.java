package com.Car_Rental_Spring.services;

import com.Car_Rental_Spring.dto.UpdatePasswordRequestDTO;
import com.Car_Rental_Spring.entity.Car;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.repository.UserRepository;
import com.Car_Rental_Spring.entity.Rental;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // User registration
    public User registerUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser==null){
            user.setRole(User.UserRole.USER);
            user.setRoyaltyPoints(0);
            return userRepository.save(user);
        }
        throw new EntityExistsException("User Already Present With Same Email");
    }

    // User login
    public User userLogin(String email, String password) {
        User storedUser = userRepository.findByEmail(email);
        if(storedUser == null){
            throw  new EntityNotFoundException("User not found.");
        }

        if (storedUser.getPassword().equals(password)) {
            return storedUser;
        } else {
            throw new EntityNotFoundException("Wrong password.");
        }
    }

    public User getUserDetails(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new EntityNotFoundException("User Not Found");
    }

    // Change password
    public User changePassword(UpdatePasswordRequestDTO dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getId());

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getPassword().equals(dto.getCurrentPassword())){
                user.setPassword(dto.getNewPassword());
                return userRepository.save(user);
            }else{
                throw new EntityNotFoundException("Current Password is Wrong.");
            }
        }
        throw new EntityNotFoundException("User Not Found.");
    }
}
