package com.yordan.karabelyov.Workshop.service;

import com.yordan.karabelyov.Workshop.model.Reservation;
import com.yordan.karabelyov.Workshop.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ReservationServiceImpl implements ReservationService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    EmailService emailService;

    @Override
    public boolean isDateAvailable(Date date) {

        if (date == null || date.before(new Date())) {
            return false;
        }
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            return true;
        }
        int counter = 0;
        for (Reservation reservation : reservations
        ) {
            logger.info("date 1 => {}, date 2 => {}", reservation.getDate(), date);
            if (counter == 1) {
                return false;
            }
            if (reservation.getDate().getYear() == date.getYear() && reservation.getDate().getDay() == date.getDay() && reservation.getDate().getDay() == date.getDay()) {
                counter++;
            }
        }
        return true;
    }

    @Override
    public void save(Reservation reservation) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                emailService.sendEmail(reservation.getEmail(),"Res confirm","See you soon :)");
            }
        });
        thread.start();
        reservationRepository.save(reservation);


    }

    @Override
    public List<Reservation> allReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        return reservation;
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}

