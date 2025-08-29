package com.pepito.jose.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pepito.jose.model.Rol;

@Service
public class RolServiceImpl implements RolService{
    private List<Rol> listaRoles;

    public RolServiceImpl(){
        this.listaRoles = Arrays.asList(new Rol(1,"Administrador"),new Rol(2,"Usuario"),new Rol(3,"Invitado"));
    }

    @Override
    public List<Rol> listaRolesClasesUsuario() {
        return listaRoles;
    }
    @Override
    public Rol obtenerRol(Integer ID) {
        for (Rol rol : listaRoles) {
            if(ID == rol.getIdRol()){
                return rol;
            }
        }
        return null;
    }
}
