package com.pepito.jose.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemFactura {
    private Producto producto;
    private int cantidad;
}
