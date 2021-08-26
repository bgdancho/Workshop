package com.yordan.karabelyov.Workshop.service;


import com.yordan.karabelyov.Workshop.model.Reservation;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
}
