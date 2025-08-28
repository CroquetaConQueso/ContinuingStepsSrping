package com.pepito.jose;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import com.pepito.jose.model.ItemFactura;
import com.pepito.jose.model.Pais;
import com.pepito.jose.model.Producto;
import com.pepito.jose.model.Rol;
import com.pepito.jose.model.Usuario;

@Configuration
public class AppConfig {

    // @RequestScope asegura que este bean se cree de nuevo en cada solicitud HTTP.
    // Antes, al ser un singleton por defecto, el mismo objeto Usuario se compartía
    // entre todas las peticiones,
    // provocando que los cambios (como concatenar el nombre en @PostConstruct) se
    // acumularan.
    // Con este scope, evitamos estado compartido y garantizamos que cada request
    // reciba un Usuario "limpio".

    //La destruccion del bean ocurre por defecto al tener un @RequestScope

    @Bean
    @RequestScope
    public Usuario retUsuario() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha = LocalDate.parse("2025/03/15", formatter);
        List<Rol> roles = new ArrayList<>();
        
        roles.add(new Rol(1,"Administrador"));
        roles.add(new Rol(2,"Usuario"));
        Usuario usuario = new Usuario("Jose","Perez",1,fecha,"1234","wawa@hotmail.com", "1", 200.2,new Pais(2,"ES","España"),roles);
        return usuario;
    }

    @Bean
    public String retDescripcion() {
        String despcripcion = "Gastos De la Compra";
        return despcripcion;
    }

    @Bean
    public List<ItemFactura> retLista() {
        return Stream.of(new ItemFactura(new Producto("Comida Gato", 1, 20.2), 4),
                new ItemFactura(new Producto("Comida Perra", 2, 40.3), 2)).collect(Collectors.toList());
    }

    @Bean
    public List<ItemFactura> retListaOficina() {
        return Stream.of(new ItemFactura(new Producto("Monitor", 3, 201.2), 2),
                new ItemFactura(new Producto("Escritorio", 4, 400.3), 1),
                new ItemFactura(new Producto("Impresora", 5, 80.3), 1)).collect(Collectors.toList());
    }

}
