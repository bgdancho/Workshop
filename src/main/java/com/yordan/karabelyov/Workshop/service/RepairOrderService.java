package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.RepairOrder;
import com.yordan.karabelyov.Workshop.model.SparePart;
import com.yordan.karabelyov.Workshop.model.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public interface RepairOrderService {

    RepairOrder save(RepairOrder repairOrder);
    List<RepairOrder> findAllOrders();
    RepairOrder findById(Long id);
    void insertSparePart(RepairOrder order, SparePart part);
    void setStatus(Long id,String status);
    List<RepairOrder> completed();
    List<RepairOrder> notCompleted();
    List<RepairOrder> orderByStartDate();
    List<RepairOrder> ordersByVehicleLicensePlate(String licensePlate);
}
