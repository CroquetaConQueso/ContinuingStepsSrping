package com.pepito.jose.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FechaValidador implements ConstraintValidator<AnotacionFecha, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse("24/02/1993", formatter);
        

        if(value==null){
            return false;
        }else if(value.isBefore(fecha)){
            return false;
        }
        return true;
    }
}
