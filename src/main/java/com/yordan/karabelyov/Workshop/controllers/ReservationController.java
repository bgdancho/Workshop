package com.yordan.karabelyov.Workshop.controllers;

import com.yordan.karabelyov.Workshop.model.Reservation;
import com.yordan.karabelyov.Workshop.service.EmailService;
import com.yordan.karabelyov.Workshop.service.ReservationService;
import com.yordan.karabelyov.Workshop.util.ReservationConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ReservationService reservationService;
    @Autowired
    EmailService emailService;

    @GetMapping("/new")
    public String reservation(Model model) {

        model.addAttribute("reservation", new Reservation());

        return "reservations/create-reservation";
    }

    @PostMapping("/saved")
    public String saveReservation(@Valid Reservation reservation, Errors error, Model model) {
        if (error.hasErrors()) {
            logger.info("Errors found");
            return "reservations/create-reservation";
        }
        if (!reservationService.isDateAvailable(reservation.getDate())) {
            return "reservations/not-available";
        }
        reservationService.save(reservation);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String getAllReservations(Model model) {

        List<Reservation> allReservations = reservationService.allReservations();
        model.addAttribute("reservations", allReservations);
        model.addAttribute("toUpdate", new Reservation());

        return "reservations/all-reservations";
    }

    @PostMapping("/update-reservation")
    public String showUpdateReservation(Reservation reservation, Model model) {
        logger.info("reservation id => {}", reservation.getId());
        Reservation reservationRes = reservationService.findById(reservation.getId());
        model.addAttribute("reservation", reservationRes);
        return "reservations/update-reservation";
    }
    @PostMapping("/delete-reservation")
    public String deleteReservation(Reservation reservation) {

        logger.info("reservation id => {}", reservation.getId());
        reservationService.deleteById(reservation.getId());

        return "/reservations/all-reservations";
    }

    @PostMapping("/save")
    public String saveReservation(Reservation reservation) {
        reservationService.save(reservation);
        return "redirect:/";
    }



}
