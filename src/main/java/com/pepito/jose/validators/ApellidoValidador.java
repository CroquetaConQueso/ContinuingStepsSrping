package com.pepito.jose.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ApellidoValidador implements ConstraintValidator<ApellidoValido,String>{

    @Override
    public void initialize(ApellidoValido wawa){}
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isBlank() || value.isEmpty()){
            return false;
        }
        
        return value.matches("[A-Z][a-z]+[ ]?[A-Z]?[a-z]+");
    }
    
}
