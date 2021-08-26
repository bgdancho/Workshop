package com.yordan.karabelyov.Workshop.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PartExistsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface PartExist {
    public String message() default "Not in in database";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
