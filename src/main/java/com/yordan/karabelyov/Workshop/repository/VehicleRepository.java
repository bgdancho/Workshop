package com.yordan.karabelyov.Workshop.repository;

import com.yordan.karabelyov.Workshop.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    List<Vehicle> findByLicensePlate(String licensePlate);
    List<Vehicle> findAll();

}
