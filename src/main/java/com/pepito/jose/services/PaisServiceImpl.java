package com.pepito.jose.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pepito.jose.model.Pais;

@Service
public class PaisServiceImpl implements PaisService{

    private List<Pais> listaPaises;

    PaisServiceImpl(){
        this.listaPaises = Arrays.asList(new Pais(1,"ES","España"),new Pais(2,"ME","Mexico"),new Pais(3,"CO","Colombia"), new Pais(4,"FR","Francia"),new Pais(5,"ITA","Italia"));
    }
    @Override
    public List<Pais> Listar() {
        return listaPaises;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        for(Pais pais: this.listaPaises){
            if(id == pais.getIdPais()){
                return pais;
            }
        }

        return null;
    }
    
}
