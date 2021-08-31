package com.yordan.karabelyov.Workshop.repository;

import com.yordan.karabelyov.Workshop.model.RepairOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {

    RepairOrder save(RepairOrder repairOrder);


    @Query("SELECT r FROM RepairOrder r WHERE r.status='FINISHED'")
    List<RepairOrder> completed();

    @Query("SELECT r FROM RepairOrder r WHERE r.status='STARTED'")
    List<RepairOrder> notCompleted();

    @Query("SELECT r FROM RepairOrder r ORDER BY r.startDate")
    List<RepairOrder> orderByStartDate();

    List<RepairOrder> findByVehicleId(Long id);

}
