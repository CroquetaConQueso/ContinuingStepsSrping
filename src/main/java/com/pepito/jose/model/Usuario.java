package com.pepito.jose.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Component
public class Usuario {
    private String nombreUsuario;
    private int idUsuario;
    private double fondosUsuario;
}
