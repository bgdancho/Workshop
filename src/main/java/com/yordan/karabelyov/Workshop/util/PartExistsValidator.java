package com.yordan.karabelyov.Workshop.util;

import com.yordan.karabelyov.Workshop.service.SparePartService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PartExistsValidator implements ConstraintValidator<PartExist, Integer> {

    @Autowired
    SparePartService sparePartService;

    @Override
    public boolean isValid(Integer code, ConstraintValidatorContext constraintValidatorContext) {
        return sparePartService.exists(code);
    }
}
