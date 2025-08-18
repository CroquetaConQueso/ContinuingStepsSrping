package com.pepito.jose.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
@Component
public class Usuario {

    @NotEmpty
    //Si queremos establecer de cuanto debe de ser el tamaño del nombre del usuario, podemos establecer un min y un max
    //Para establecer que tipo de prompt vamos a tener como respuesta tras el error podemos usar message = para establecer uno
    @Size(min=3, max=8, message = "El nombre debe de tener entre 3 y 8 caracteres")
    private String nameUsuario;
    @NotEmpty
    private String surnameUsuario;
    @NotEmpty
    private String passwordUsuario;
    @NotEmpty
    @Email(message="correo con formato incorrecto")
    private String emailUsuario;
    //Un agrupacion el cual puede tener dos caracteres, luego tres... El {} establece la cantidad. \\d es lo mismo que de 0-9
    @Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")
    private String idUsuario;
    private double fondosUsuario;
}
