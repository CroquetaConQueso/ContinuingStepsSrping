package com.pepito.jose.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidador implements ConstraintValidator<PasswordValida,String> {

    @Override
    public void initialize(PasswordValida constPasswordValida){

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null || value.isBlank()){
            return false;
        }
        return value.matches("[0-9]{9}[-][A-Z]");
    }
    

}
