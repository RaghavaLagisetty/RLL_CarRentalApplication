package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByRole(User.UserRole role);

    User findByEmail(String email);

//	boolean existsById(String username);
}
