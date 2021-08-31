package com.yordan.karabelyov.Workshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    private String vin;

    private String brand;

    private String model;

    @Column(nullable = true)
    private int engineSize;

    private String licensePlate;

    public Vehicle() {
    }

    public Vehicle(String vin) {
        this.vin = vin;
    }


    public Vehicle(String vin, String licensePlate) {
        this.vin = vin;
        this.licensePlate = licensePlate;
    }

    public Vehicle(String vin, String brand, String licensePlate) {
        this.vin = vin;
        this.brand = brand;
        this.licensePlate = licensePlate;
    }

    public Vehicle(String vin, String brand, String model, int engineSize, String licensePlate) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.engineSize = engineSize;
        this.licensePlate = licensePlate;
    }

    public Long getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", VIN='" + vin + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", engineSize=" + engineSize +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
