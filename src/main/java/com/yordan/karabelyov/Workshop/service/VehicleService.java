package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.controllers.VehicleController;
import com.yordan.karabelyov.Workshop.model.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);
    List<Vehicle> findByLicensePlate(String licensePlate);
    List<Vehicle> findAll();

}
