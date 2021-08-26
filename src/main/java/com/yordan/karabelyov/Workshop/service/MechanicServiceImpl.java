package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.Mechanic;
import com.yordan.karabelyov.Workshop.repository.MechanicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicServiceImpl implements MechanicService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MechanicRepository mechanicRepository;
//    @Override
//    public Mechanic findByName(String name){
//        Mechanic mechanic = mechanicRepository.findByName(name);
//        logger.info("Mechanic found by Name -> {}",mechanic);
//        return mechanic;
//    }

    public Mechanic save(Mechanic mechanic) {
        if (mechanicRepository.existsById(mechanic.getId())){
            logger.info("Mechanic already in DB, exiting...");
            return null;
        }
        logger.info("Mechanic not found in database, saving...");
            return mechanicRepository.save(mechanic);
    }

    @Override
    public List<Mechanic> findAll() {
        return mechanicRepository.findAll();
    }
}
