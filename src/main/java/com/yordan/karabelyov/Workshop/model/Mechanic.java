package com.yordan.karabelyov.Workshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mechanic {
    @Id
    @GeneratedValue
    private long Id;

    @Column(nullable = false)
    private String name;

    public Mechanic() {
    }
//    @ManyToMany(mappedBy = "mechanicsWorked")
//    private List<RepairOrder> repairs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Mechanic(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "Id=" + Id +
                ", mechanicName='" + name + '\'' +
                '}';
    }
}
