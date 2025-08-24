package com.pepito.jose.editor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.pepito.jose.model.Pais;

@Component
public class IdWarp implements Converter<String,Pais>{
    @Override
    public Pais convert(String arg0) {
        if (arg0 == null | arg0.isEmpty()){ return null;}
        
        Pais pais = new Pais();

        String[] partes = arg0.split("-");
        pais.setNombrePais(partes[0]);
        pais.setIdPais(Integer.valueOf(partes[1]));

        return pais;
    }
    
}
