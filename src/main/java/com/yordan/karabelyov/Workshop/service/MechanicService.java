package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.Mechanic;

import java.util.List;

public interface MechanicService {
    Mechanic save(Mechanic mechanic);
    List<Mechanic> findAll();
//    Mechanic findByName(String name);
}
