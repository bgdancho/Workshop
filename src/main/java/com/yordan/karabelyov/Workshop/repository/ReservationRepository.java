package com.yordan.karabelyov.Workshop.repository;

import com.yordan.karabelyov.Workshop.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
