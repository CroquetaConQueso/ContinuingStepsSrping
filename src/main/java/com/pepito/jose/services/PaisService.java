package com.pepito.jose.services;

import java.util.List;

import com.pepito.jose.model.Pais;

public interface PaisService {
    public List<Pais> Listar();
    public Pais obtenerPorId(Integer id);
}
