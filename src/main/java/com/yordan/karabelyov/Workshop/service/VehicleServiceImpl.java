package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.Vehicle;
import com.yordan.karabelyov.Workshop.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Locale;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    EntityManager entityManager;

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

//    @Override
//    public List<Vehicle> findByLicensePlate(String licensePlate) {
//        TypedQuery<Vehicle> query = entityManager.createQuery(
//                "SELECT v FROM Vehicle v WHERE v.licensePlate LIKE :licensePlate",Vehicle.class
//        );
//        return query.setParameter("licensePlate", licensePlate).getResultList();
//    }


    @Override
    public List<Vehicle> findByLicensePlateContaining(String licensePlate) {
        return vehicleRepository.findByLicensePlateContaining(licensePlate);
    }

    @Override
    public List<Vehicle> findAll() {
       return vehicleRepository.findAll();
    }
}
