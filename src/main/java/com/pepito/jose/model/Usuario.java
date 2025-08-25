package com.pepito.jose.model;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

import com.pepito.jose.validators.AnotacionFecha;
import com.pepito.jose.validators.ApellidoValido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    // Si queremos establecer de cuanto debe de ser el tamaño del nombre del
    // usuario, podemos establecer un min y un max
    // Para establecer que tipo de prompt vamos a tener como respuesta tras el error
    // podemos usar message = para establecer uno
    @Size(min = 3, max = 8, message = "El nombre debe de tener entre 3 y 8 caracteres")
    private String nameUsuario;
    @ApellidoValido
    private String surnameUsuario;
    // Como el valor sera un objeto, al ser un objeto de la clase Integer(int), se
    // puede validar con notnull ya que es un objeto y con esto establecemos que no
    // debe de estar vacio. Para establecer un max se haria con Max(cantidad), por
    // ejemplo Max(90) o Min(18) para un minimo
    // Al establecer Not null debemos de saber de que SOLO va a dar feedback
    // correcto si estamos utilizando Integer y no int. Para hacer que el feedback
    // sea correcto y no tener un error de consola como prompt para el usuario
    // debemos de establecer en el properties typeMysmatch junto con toda la ruta de
    // la clase o tambien se puede personalizar y establecer para que salte siempre
    // con un atributo en especifico de la clase como puede ser
    // typemismatch.usuario.edadUsuario
    @NotNull(message = "No puede estar vacio")
    @Min(10)
    @Max(500)
    private Integer edadUsuario;
    @AnotacionFecha
    private LocalDate fechaNacimientoUsuario;
    private String passwordUsuario;
    @NotEmpty
    @Email(message = "correo con formato incorrecto")
    private String emailUsuario;
    // Un agrupacion el cual puede tener dos caracteres, luego tres... El {}
    // establece la cantidad. \\d es lo mismo que de 0-9
    @Pattern(regexp = "[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{1}")
    private String idUsuario;
    private double fondosUsuario;
    @Valid
    private Pais paisUsuario;
}
