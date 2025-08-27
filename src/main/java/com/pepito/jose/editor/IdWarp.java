package com.pepito.jose.editor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pepito.jose.model.Pais;
import com.pepito.jose.services.PaisService;

@Component
public class IdWarp implements Converter<String,Pais>{
    
    @Autowired
    private PaisService paisService;
    @Override
    public Pais convert(String arg0) {
        if (arg0 == null || arg0.isEmpty()){ return null;}
        
        Pais pais = new Pais();

        pais = paisService.obtenerPorId(Integer.parseInt(arg0));
        
        return pais;
    }
    
}
