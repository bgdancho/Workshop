package com.yordan.karabelyov.Workshop.model;


import com.yordan.karabelyov.Workshop.util.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Entity
public class RepairOrder {

    @Id
    @GeneratedValue
    private long id;

    private double totalPrice;

    private int hourPrice = 50;

    @ManyToMany
    public List<Mechanic> mechanicsWorked = new ArrayList<>();

    @OneToMany
    public List<VehicleOperation> operations = new ArrayList<>();

    @OneToOne
    private Vehicle vehicle = new Vehicle();

    private String status = OrderStatus.STARTED.name();

    @CreationTimestamp
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

   @ManyToMany(cascade = CascadeType.DETACH)
    private List<SparePart> spareParts = new ArrayList<>();

    public RepairOrder() {
    }

    public RepairOrder(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public RepairOrder(Mechanic mechanic, Vehicle vehicle) {
        mechanicsWorked.add(mechanic);
        this.vehicle = vehicle;
    }

    public RepairOrder(Mechanic mechanic, Vehicle vehicle, Date deadline) {
        this.vehicle = vehicle;
        this.deadline = deadline;
        this.mechanicsWorked.add(mechanic);
    }


    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {

        this.totalPrice = totalPrice;
    }

    public List<Mechanic> getMechanicsWorked() {
        return mechanicsWorked;
    }

    public void setMechanicsWorked(List<Mechanic> mechanicsWorked) {
        this.mechanicsWorked = mechanicsWorked;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<SparePart> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(List<SparePart> spareParts) {
        this.spareParts = spareParts;
    }

    public List<VehicleOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<VehicleOperation> operations) {
        this.operations = operations;
    }

    public void addPart(SparePart sparePart) {
        if (sparePart != null) {
            spareParts.add(sparePart);
        }
    }

    public void calcTotalPrice() {
        Double price = 0.0;

        for (SparePart part : spareParts
        ) {
            price += part.getPrice();
        }
        for (VehicleOperation operation : operations
        ) {
            price += (operation.getHours() * hourPrice);
        }

        this.totalPrice = BigDecimal.valueOf(price).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public String toString() {
        return "RepairOrder{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", mechanicsWorked=" + mechanicsWorked +
                ", vehicle=" + vehicle +
                '}';
    }
}
