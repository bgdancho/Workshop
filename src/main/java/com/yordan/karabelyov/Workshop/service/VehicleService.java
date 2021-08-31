package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.controllers.VehicleController;
import com.yordan.karabelyov.Workshop.model.Vehicle;

import javax.persistence.EntityManager;
import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);
    List<Vehicle> findByLicensePlate(String licensePlate);
    List<Vehicle> findByLicensePlateContaining(String licensePlate);

    List<Vehicle> findAll();

}
