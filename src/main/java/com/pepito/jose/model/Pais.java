package com.pepito.jose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {
    private Integer idPais;
    private String abreviacionPais;
    private String nombrePais;
}
