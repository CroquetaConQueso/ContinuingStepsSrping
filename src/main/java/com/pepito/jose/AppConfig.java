package com.pepito.jose;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pepito.jose.model.ItemFactura;
import com.pepito.jose.model.Producto;
import com.pepito.jose.model.Usuario;

@Configuration
public class AppConfig {

    @Bean
    public Usuario retUsuario() {
        Usuario usuario = new Usuario("Jose", 1, 200.2);
        return usuario;
    }

    @Bean
    public List<ItemFactura> retLista() {
        return Stream.of(new ItemFactura(new Producto("Comida Gato", 1, 20.2), 4),
                new ItemFactura(new Producto("Comida Perra", 2, 40.3), 2)).collect(Collectors.toList());
    }

    @Bean
    public String retDescripcion(){
        String despcripcion = "Gastos De la Compra";
        return despcripcion;
    }
}
