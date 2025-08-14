package com.pepito.jose.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
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
    private String nameUsuario;
    @NotEmpty
    private String surnameUsuario;
    @NotEmpty
    private String passwordUsuario;
    private int idUsuario;
    private double fondosUsuario;
}
