package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.Vehicle;
import com.yordan.karabelyov.Workshop.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        if (vehicle.getVin() != null){
            vehicle.setLicensePlate(vehicle.getLicensePlate().toUpperCase());
            return vehicleRepository.save(vehicle);
        }
        return vehicleRepository.save(vehicle);
    }
    public List<Vehicle> findByLicensePlate(String licensePlate){
        List<Vehicle> list = vehicleRepository.findByLicensePlate(licensePlate.toUpperCase(Locale.ROOT));
        return list;
    }

    @Override
    public List<Vehicle> findAll() {
       return vehicleRepository.findAll();
    }
}
