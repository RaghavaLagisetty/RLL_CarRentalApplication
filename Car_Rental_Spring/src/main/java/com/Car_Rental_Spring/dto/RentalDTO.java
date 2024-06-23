package com.Car_Rental_Spring.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalDTO {

    private Long id;
    private Long userId;
    private Long carId;

    private LocalDate startDate;

    private LocalDate endDate;

    private int startOdometerReading;

    private int endOdometerReading;

    private int totalDistance;

    private BigDecimal rentalCost;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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
}
