package com.example.demo.validators;

import com.example.demo.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InventoryValidator implements ConstraintValidator<ValidInventory, Part>{

    @Override
    public void initialize(ValidInventory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        int inv = part.getInv();
        int minInv = part.getMinInv();
        int maxInv = part.getMaxInv();

        if (inv < minInv || inv > maxInv) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Inventory must be between the minimum and maximum").addConstraintViolation();
            return false;
        }

        if (minInv > maxInv) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Minimum must be less than or equal to maximum").addConstraintViolation();
            return false;
        }

        return true;
    }
}
