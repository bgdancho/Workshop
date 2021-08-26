package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.VehicleOperation;
import com.yordan.karabelyov.Workshop.repository.VehicleOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleOperationServiceImpl implements VehicleOperationService{

    @Autowired
    VehicleOperationRepository vehicleOperationRepository;

    @Override
    public void save(VehicleOperation operation) {
        vehicleOperationRepository.save(operation);
    }
}
