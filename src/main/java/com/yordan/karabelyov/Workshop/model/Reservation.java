package com.yordan.karabelyov.Workshop.model;

import com.yordan.karabelyov.Workshop.util.ReservationConstraint;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Reservations")

public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @NotBlank
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ReservationConstraint(message = "We are not available on this date")
    private Date date;

    @Column(nullable = false)
    private String email;

    public Reservation() {
    }

    public Reservation(String customerName, String phoneNumber, Date date, String email) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.email = email;
    }

    public Reservation(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
