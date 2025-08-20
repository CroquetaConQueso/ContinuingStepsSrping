package com.pepito.jose.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidador.class) // clase validadora asociada
@Target({ ElementType.METHOD, ElementType.FIELD }) // dónde se puede aplicar
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValida {

    String message() default "{usuario.password.invalida}"; // clave en messages.properties

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
