package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    boolean isDateAvailable(Date date);
    void save(Reservation reservation);
    List<Reservation> allReservations();
    Reservation findById(Long id);
    void deleteById(Long id);
}
