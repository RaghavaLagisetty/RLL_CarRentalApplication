package com.Car_Rental_Spring.entity;


import java.math.BigDecimal;
import java.time.LocalDate;

import com.Car_Rental_Spring.enums.RentalStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RentalStatus rentalStatus;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_odometer_reading", nullable = false)
    private int startOdometerReading;

    @Column(name = "end_odometer_reading")
    private int endOdometerReading;

    @Column(name = "total_distance")
    private int totalDistance;

    @Column(name = "rental_cost")
    private BigDecimal rentalCost;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getStartOdometerReading() {
        return startOdometerReading;
    }

    public void setStartOdometerReading(int startOdometerReading) {
        this.startOdometerReading = startOdometerReading;
    }

    public int getEndOdometerReading() {
        return endOdometerReading;
    }

    public void setEndOdometerReading(int endOdometerReading) {
        this.endOdometerReading = endOdometerReading;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public BigDecimal getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(BigDecimal rentalCost) {
        this.rentalCost = rentalCost;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public void calculateRentalCost(Car car2, int totalDistance2) {
		// TODO Auto-generated method stub
		
	}

	public void calculateTotalDistance() {
		
	}

}

