package com.yordan.karabelyov.Workshop.model;

import com.yordan.karabelyov.Workshop.util.TypeOfOperation;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class VehicleOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vId;

    @OneToOne
    private Mechanic mechanic;

    @NonNull
    private double hours;

    private String type;

    public VehicleOperation(Mechanic mechanic, double hours) {
        this.mechanic = mechanic;
        this.hours = hours;
    }

    public VehicleOperation(double hours) {
        this.hours = hours;
    }

    public VehicleOperation() {
    }

    public Long getvId() {
        return vId;
    }

    public void setvId(Long vId) {
        this.vId = vId;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TypeOfOperation> getTypes(){
        List<TypeOfOperation> types = Arrays.asList(TypeOfOperation.values());
        return types;
    }

    @Override
    public String toString() {
        return "VehicleOperation{" +
                "id=" + vId +
                ", mechanic=" + mechanic +
                ", hours=" + hours +
                ", type='" + type + '\'' +
                '}';
    }
}
