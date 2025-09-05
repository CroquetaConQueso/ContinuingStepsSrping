package com.pepito.jose.editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pepito.jose.model.Rol;
import com.pepito.jose.services.RolService;

@Component
public class RolConverter implements Converter<String,Rol> {

    @Autowired
    RolService rolService;

    @Override
    public Rol convert(String arg0) {
        if(arg0 == null || arg0.isEmpty()){return null;}

        Rol rol = new Rol();

        rol = rolService.obtenerRol(Integer.parseInt(arg0));

        return rol;
    }


    
}
