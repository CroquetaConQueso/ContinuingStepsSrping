package com.pepito.jose.services;

import java.util.List;

import com.pepito.jose.model.Rol;

public interface RolService {
    
    public List<Rol> listaRolesClasesUsuario();
    public Rol obtenerRol(Integer ID);
}
