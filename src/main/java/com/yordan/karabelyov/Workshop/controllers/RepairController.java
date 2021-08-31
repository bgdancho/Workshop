package com.yordan.karabelyov.Workshop.controllers;

import com.yordan.karabelyov.Workshop.model.*;
import com.yordan.karabelyov.Workshop.service.*;
import com.yordan.karabelyov.Workshop.util.OrderStatus;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
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

        RepairOrder orderFromDb = repairOrderService.findById(order.getId());
        VehicleOperation vehicleOperation = new VehicleOperation();

        model.addAttribute("order", orderFromDb);
        return "/orders/details";
    }

    @PostMapping("/newPart")
    public String addNewPart(RepairOrder repairOrder, Model model) {

        model.addAttribute("part", new SparePart());
        model.addAttribute("order", repairOrder);
        return "/parts/add-part";
    }

    @PostMapping("/searchPart")
    public String searchPart(RepairOrder order, SparePart part, Model model) {


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
        if (part.getInStock() == 0) {
            model.addAttribute("error", "Not enough in stock !");
            model.addAttribute("part", part);
            model.addAttribute("order", order);
            return "/parts/found-part";
        }
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

        RepairOrder order = repairOrderService.findById(repairOrder.getId());

        vehicleOperationService.save(operation);
        order.addOperation(operation);

        repairOrderService.save(order);
        model.addAttribute("order", repairOrderService.findById(order.getId()));

        return "/orders/details";
    }

    @PostMapping("/completed")
    public ModelAndView completeOrder(RepairOrder repairOrder) {

        logger.info("RO for completion ID => {}", repairOrder);

        RepairOrder order = repairOrderService.findById(repairOrder.getId());

        repairOrderService.setStatus(order.getId(), OrderStatus.FINISHED.name());

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/management/completedOrders")
    public String completedOrders(Model model) {

        List<RepairOrder> completedOrders = repairOrderService.completed();
        if (completedOrders.isEmpty()) {
            model.addAttribute("found", "false");
            return "orders/management/completed-orders";
        }
        model.addAttribute("found", "true");
        model.addAttribute("completed", completedOrders);
        return "orders/management/completed-orders";
    }

    @GetMapping("/management/notCompletedOrders")
    public String notCompletedOrders(Model model) {

        List<RepairOrder> notCompleted = repairOrderService.notCompleted();
        logger.info("NOT COMPLETED => {}", notCompleted);

        if (notCompleted.isEmpty()) {
            model.addAttribute("found", "false");
            return "orders/management/not-completed-orders";
        }
        model.addAttribute("found", "true");
        model.addAttribute("notCompleted", notCompleted);
        return "orders/management/not-completed-orders";
    }

    @GetMapping("/management/ordersByDate")
    public String ordersByDate(Model model) {

        List<RepairOrder> allByStartDate = repairOrderService.orderByStartDate();
        if (allByStartDate.isEmpty()) {
            model.addAttribute("found", "false");
            return "orders/management/by-start-date-orders";
        }

        model.addAttribute("found", "true");
        model.addAttribute("orders", allByStartDate);
        return "orders/management/by-start-date-orders";
    }

    @GetMapping("/management/ordersByVehicle")
    public String ordersByVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "orders/management/by-vehicle-orders";
    }
    @PostMapping("/management/ordersByVehicleResult")
    public String ordersByVehicleResult(Vehicle vehicle,Model model) {
        List<RepairOrder> repairOrders = repairOrderService.ordersByVehicleLicensePlate(vehicle.getLicensePlate());
        if (repairOrders.isEmpty()){
            model.addAttribute("found","false");
            return "orders/management/byVehicleResultOrders";
        }
        model.addAttribute("orders", repairOrders);
        model.addAttribute("found", "true");
        return "orders/management/byVehicleResultOrders";
    }


}
