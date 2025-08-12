package com.pepito.jose.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Factura {
    @Autowired
    @Qualifier("retDescripcion")
    private String facturaDescripcion;
    @Autowired
    @Qualifier("retUsuario")
    private Usuario usuario;
    @Autowired
    @Qualifier("retListaOficina")
    private List<ItemFactura> listaFacturas;
}
