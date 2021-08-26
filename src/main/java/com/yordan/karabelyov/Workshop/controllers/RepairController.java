package com.yordan.karabelyov.Workshop.controllers;

import com.yordan.karabelyov.Workshop.model.*;
import com.yordan.karabelyov.Workshop.service.*;
import com.yordan.karabelyov.Workshop.util.PartExist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/repairOrder")
public class RepairController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RepairOrderService repairOrderService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    MechanicService mechanicService;
    @Autowired
    SparePartService sparePartService;
    @Autowired
    VehicleOperationService vehicleOperationService;

    @GetMapping("/new")
    public String createRepairOrder(Model model) {


        model.addAttribute("repair", new RepairOrder());
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("mechanic", new Mechanic());
        model.addAttribute("mechanicsList", mechanicService.findAll());
        return "orders/new-order";
    }

    @PostMapping("/save")
    public String saveRepairOrder(Vehicle vehicle, Mechanic mechanic, Model model, RepairOrder repairOrder) {

        logger.info("Received info Date => {}", repairOrder.getDeadline());
        logger.info("Received info vehicle=> {}", vehicle);
        logger.info("Received info mechanic => {}", mechanic);


        mechanicService.save(mechanic);
        vehicleService.saveVehicle(vehicle);
        repairOrderService.save(new RepairOrder(mechanic, vehicle, repairOrder.getDeadline()));

        return "redirect:/";
    }

    @GetMapping("/allOrders")
    public String allOrders(Model model) {
        List<RepairOrder> allOrders = repairOrderService.findAllOrders();
        model.addAttribute("orders", allOrders);
        model.addAttribute("repairOrder", new RepairOrder());
        return "orders/all-orders";
    }

    @PostMapping("/details")
    public String detailsForOrder(RepairOrder order, Model model) {
        logger.info("REPAIR ORDER =====> {}", order);
        RepairOrder orderFromDb = repairOrderService.findById(order.getId());
        VehicleOperation vehicleOperation = new VehicleOperation();

        model.addAttribute("order", orderFromDb);
        return "/orders/details";
    }

    @PostMapping("/newPart")
    public String addNewPart(RepairOrder repairOrder, Model model) {
        logger.info("Repair order in newPart => {}", repairOrder);
        SparePart part = new SparePart();
        model.addAttribute("part", part);
        model.addAttribute("order", repairOrder);
        return "/parts/add-part";
    }

    @PostMapping("/searchPart")
    public String searchPart(RepairOrder order, SparePart part, Model model) {

        logger.info("Order number is search part => {}", order);

        SparePart sparePart = sparePartService.findByCode(part.getCode());

        model.addAttribute("part", sparePart);
        model.addAttribute("order", order);
        if (sparePart == null) {
            return "/parts/does-not-exists";
        }
        return "/parts/found-part";
    }

    @PostMapping("/insertPart")
    public String insertPart(RepairOrder order, SparePart part, Model model) {

        logger.info("Repair order => {}", order);
        logger.info("Spare part => {}", part.toString());


        repairOrderService.insertSparePart(order, part);

        model.addAttribute("order", repairOrderService.findById(order.getId()));


        return "/orders/details";
    }

    @PostMapping("/newOperation")
    public String newOperation(RepairOrder repairOrder, Model model) {
        List<Mechanic> mechanics = mechanicService.findAll();
        model.addAttribute("mechanics", mechanics);
        model.addAttribute("order", repairOrder);
        model.addAttribute("operation", new VehicleOperation());
        return "operations/new-operation";
    }

    @PostMapping("/addOperation")
    public String addOperation(RepairOrder repairOrder, VehicleOperation operation, Model model) {

        logger.info("RO ID => {}",repairOrder);
        logger.info("VO ID => {}", operation);

        RepairOrder order = repairOrderService.findById(repairOrder.getId());

        vehicleOperationService.save(operation);
        order.operations.add(operation);

        repairOrderService.save(order);
        model.addAttribute("order", repairOrderService.findById(order.getId()));

        return "/orders/details";
    }

}
//	55634