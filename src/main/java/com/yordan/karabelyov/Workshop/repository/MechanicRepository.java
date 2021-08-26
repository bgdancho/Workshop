package com.yordan.karabelyov.Workshop.repository;

import com.yordan.karabelyov.Workshop.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> {
    Mechanic save(Mechanic mechanic);
    Mechanic findByName(String name);
}
