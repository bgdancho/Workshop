package com.yordan.karabelyov.Workshop.repository;

import com.yordan.karabelyov.Workshop.model.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
    RepairOrder save(RepairOrder repairOrder);

}
