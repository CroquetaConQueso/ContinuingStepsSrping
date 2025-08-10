package com.pepito.jose.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Producto {
    private String nombreProducto;
    private int idProducto;
    private double precioProducto;
}
