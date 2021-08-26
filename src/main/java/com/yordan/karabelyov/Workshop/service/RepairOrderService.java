package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.RepairOrder;
import com.yordan.karabelyov.Workshop.model.SparePart;

import java.util.List;

public interface RepairOrderService {

    RepairOrder save(RepairOrder repairOrder);
    List<RepairOrder> findAllOrders();
    RepairOrder findById(Long id);
    void insertSparePart(RepairOrder order, SparePart part);

}
