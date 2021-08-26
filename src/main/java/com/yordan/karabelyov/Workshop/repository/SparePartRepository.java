package com.yordan.karabelyov.Workshop.repository;

import com.yordan.karabelyov.Workshop.model.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SparePartRepository extends JpaRepository<SparePart, Long> {

    void deleteById(Long id);
    SparePart findByCode(int number);

}
