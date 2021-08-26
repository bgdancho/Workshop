package com.yordan.karabelyov.Workshop.util;

import com.yordan.karabelyov.Workshop.model.Reservation;
import com.yordan.karabelyov.Workshop.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

@Component
public class ReservationConstraintValidator implements ConstraintValidator<ReservationConstraint, Date> {


    Logger logger = LoggerFactory.getLogger(ReservationConstraintValidator.class);

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date.before(new Date())) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(ReservationConstraint constraintAnnotation) {

    }
}
