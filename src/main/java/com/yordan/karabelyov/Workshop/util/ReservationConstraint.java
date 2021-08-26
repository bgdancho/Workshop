package com.yordan.karabelyov.Workshop.util;


import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReservationConstraintValidator.class)
public @interface ReservationConstraint {

    String message() default "Invalid date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
