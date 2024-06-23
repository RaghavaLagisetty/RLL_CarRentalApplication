package com.Car_Rental_Spring.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(name = "odometer_reading", nullable = false)
    private int odometerReading;

    @OneToOne
    private CarType cartype;

    @Column(name = "cost_per_kilometer", nullable = false)
    private BigDecimal costPerKilometer;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    // Constructors, getters and setters

    public Car() {
    }

    public Car(String make, String model, int year, int odometerReading, CarType cartype, BigDecimal costPerKilometer) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.odometerReading = odometerReading;
        this.cartype =cartype;
        this.costPerKilometer = costPerKilometer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(int odometerReading) {
        this.odometerReading = odometerReading;
    }

    public CarType getType() {
        return cartype;
    }

    public void setType(CarType type) {
        this.cartype = type;
    }

    public BigDecimal getCostPerKilometer() {
        return costPerKilometer;
    }

    public void setCostPerKilometer(BigDecimal costPerKilometer) {
        this.costPerKilometer = costPerKilometer;
    }

    public CarType getCartype() {
        return cartype;
    }

    public void setCartype(CarType cartype) {
        this.cartype = cartype;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
