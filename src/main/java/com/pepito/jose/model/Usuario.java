package com.pepito.jose.model;

import org.springframework.stereotype.Component;

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
    private String nameUsuario;
    private String surnameUsuario;
    private String passwordUsuario;
    private int idUsuario;
    private double fondosUsuario;
}
