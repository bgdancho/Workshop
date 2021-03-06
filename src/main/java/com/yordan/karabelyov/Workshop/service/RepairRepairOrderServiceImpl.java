package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.RepairOrder;
import com.yordan.karabelyov.Workshop.model.SparePart;
import com.yordan.karabelyov.Workshop.model.Vehicle;
import com.yordan.karabelyov.Workshop.repository.RepairOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepairRepairOrderServiceImpl implements RepairOrderService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RepairOrderRepository repairOrderRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    SparePartService sparePartService;
    @Autowired
    VehicleService vehicleService;

    @Override
    public RepairOrder save(RepairOrder repairOrder) {

        repairOrder.setDeadline(repairOrder.getDeadline());
        entityManager.detach(repairOrder);
        return repairOrderRepository.save(repairOrder);
    }

    @Override
    public List<RepairOrder> findAllOrders() {
        return repairOrderRepository.findAll();
    }

    @Override
    public RepairOrder findById(Long id) {
        return repairOrderRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void insertSparePart(RepairOrder order, SparePart part) {

        RepairOrder repairOrder = repairOrderRepository.findById(order.getId()).get();
        SparePart sparePart = sparePartService.findByCode(part.getCode());

        sparePart.sellStock(1);

        logger.info("remaining parts instock => {}", sparePart.getInStock());

        sparePart.setPrice(part.getPrice());

        repairOrder.addPart(sparePart);

//        sparePartService.save(part);
        repairOrderRepository.save(repairOrder);
    }

    @Override
    @Transactional
    public void setStatus(Long id, String status) {
        RepairOrder repairOrder = repairOrderRepository.findById(id).get();
        repairOrder.setStatus(status);
        repairOrder.calcTotalPrice();
    }

    @Override
    public List<RepairOrder> completed() {
        return repairOrderRepository.completed();
    }

    @Override
    public List<RepairOrder> notCompleted() {
        return repairOrderRepository.notCompleted();
    }

    @Override
    public List<RepairOrder> orderByStartDate() {
        return repairOrderRepository.orderByStartDate();
    }

    @Override
    public List<RepairOrder> ordersByVehicleLicensePlate(String licensePlate) {
        List<Vehicle> vehicles = vehicleService.findByLicensePlateContaining(licensePlate);
        List<RepairOrder> repairOrders = new ArrayList<>();
        for (Vehicle vehicle :
                vehicles) {
          repairOrders.addAll(repairOrderRepository.findByVehicleId(vehicle.getId()));  ;
        }
        logger.info("LIST OF REPAIR ORDERS =? {}", repairOrders);
        return repairOrders;
    }
}

