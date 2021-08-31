package com.yordan.karabelyov.Workshop;

import com.yordan.karabelyov.Workshop.model.Mechanic;
import com.yordan.karabelyov.Workshop.model.RepairOrder;
import com.yordan.karabelyov.Workshop.model.SparePart;
import com.yordan.karabelyov.Workshop.model.Vehicle;
import com.yordan.karabelyov.Workshop.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class CarWorkshopApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VehicleService vehicleService;

    @Autowired
    MechanicService mechanicService;

    @Autowired
    ReservationService reservationService;
    @Autowired
    SparePartService sparePartService;

    @Autowired
    RepairOrderService repairOrderService;

    public static void main(String[] args) {
        SpringApplication.run(CarWorkshopApplication.class, args);

    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        Vehicle testVehicle = new Vehicle("123456798", "ca 1111 ca");
        Vehicle testVehicle1 = new Vehicle("12345678912345678", "ca 1112 ca");


        vehicleService.saveVehicle(testVehicle);
        vehicleService.saveVehicle(testVehicle1);

        testVehicle.setLicensePlate("Y 1542 CP");

        logger.info("Find by license plate -> {}", vehicleService.findByLicensePlate("ca 1112 ca"));
        logger.info("Find all -> {}", vehicleService.findAll());

        mechanicService.save(new Mechanic("Ivan"));
        mechanicService.save(new Mechanic("Stoqn"));

        sparePartService.addToWareHouse(new SparePart("Clutch", 55634, 155.22), 12);
        sparePartService.addToWareHouse(new SparePart("Wheel bearing", 52132, 28.44), 22);
        sparePartService.addToWareHouse(new SparePart("Brake pads", 50025, 34.15), 15);

        SparePart spTest = sparePartService.findById(2L);
        spTest.sellStock(10);

        SparePart spTest2 = sparePartService.findById(2L);
        logger.info("sp2Test amount => {}", spTest2.getInStock());

        SparePart sparePart = sparePartService.findById(1L);
        sparePart.addStock(5);

        SparePart sparePart1 = sparePartService.findById(3L);
        sparePart1.sellStock(3);



        SparePart sparePart2 = sparePartService.findById(2L);

        RepairOrder repairOrder1 = new RepairOrder(testVehicle);
        RepairOrder repairOrder2 = new RepairOrder(testVehicle1);

        repairOrder1.addPart(sparePart1);
        repairOrder1.addPart(sparePart2);
        repairOrderService.save(repairOrder2);
        repairOrderService.save(repairOrder1);


        List<Vehicle> vehicles = vehicleService.findByLicensePlateContaining("1");
        logger.info("VEHICLES {}", vehicles);
        repairOrderService.ordersByVehicleLicensePlate("1");
    }
}
