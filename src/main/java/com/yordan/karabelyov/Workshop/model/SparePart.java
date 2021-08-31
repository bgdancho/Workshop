package com.yordan.karabelyov.Workshop.model;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
//@SQLDelete(sql = "UPDATE spare_part SET deleted = true WHERE id=?")
//@Where(clause = "deleted=false")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @NotNull
    @Column(unique = true)
    private int code;

    private int inStock;

    private double price;

//    private boolean deleted = false;

    @ManyToMany(mappedBy = "spareParts")
    private List<RepairOrder> repairOrders = new ArrayList<>();


    public SparePart() {
    }


    public SparePart(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public SparePart(String name, int code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public SparePart(String name, int code, int inStock, double price) {
        this.name = name;
        this.code = code;
        this.inStock = inStock;
        this.price = price;
    }

    public SparePart(int code) {
        this.code = code;
    }

    public SparePart(String name, int code, int inStock) {
        this.name = name;
        this.code = code;
        this.inStock = inStock;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getInStock() {
        return inStock;
    }

    public double getPrice() {
        return price;
    }

    public List<RepairOrder> getRepairOrders() {
        return repairOrders;
    }

    public void setRepairOrders(List<RepairOrder> repairOrders) {
        this.repairOrders = repairOrders;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

//    public boolean isDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        deleted = deleted;
//    }

    public void addStock(int amount) {
        if (amount > 0) {
            this.inStock += amount;
        }
    }

    public void sellStock(int amount) {
        if (amount > 0 && this.inStock >= amount) {
            this.inStock -= amount;
        }
    }

    @Override
    public String toString() {
        return "SparePart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", inStock=" + inStock +
                ", price=" + price +
                ", repairOrders=" + repairOrders +
                '}';
    }
}
