package com.pepito.jose.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pepito.jose.model.Usuario;

//Para crear una clase que vayamso a usar para validar datos debemos de añadir la interfaz Validator. Debemos de acordarnos de que tenemos que utilizar el Validator 
// de la interfaz de springframework. Para tambien lo podamos utilizar y que este pueda validar automaticamente nos debemos de acordar de establecerlo como un @Component
@Component
public class UsuarioValidador implements Validator {

    // Con support damos soporte a una clase (duh), en este caso a la clase Usuario
    @Override
    public boolean supports(Class<?> clazz) {

        return Usuario.class.isAssignableFrom(clazz);
    }


    // Ahora para que los valores se validen debemos de realizar
    @Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario)target;

        validarPass(usuario, errors);
    }

    private void validarPass(Usuario u, Errors e){
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "passwordUsuario", "usuario.passwordUsuario");

        if(u.getPasswordUsuario().isEmpty()){
            e.rejectValue("passwordUsuario", "usuario.passwordUsuario");
        }else if(u.getPasswordUsuario().matches("[0-9]{9}")== false){
            e.rejectValue("passwordUsuario", "pattern.usuario.passwordUsuario");
        }else if(u.getPasswordUsuario().length()<5 || u.getPasswordUsuario().length()>12){
            e.rejectValue("passwordUsuario", "pattern.usuario.passwordUsuario");
        }
    }

}
