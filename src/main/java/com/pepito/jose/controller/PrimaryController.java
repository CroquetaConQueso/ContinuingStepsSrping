package com.pepito.jose.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pepito.jose.model.Factura;
import com.pepito.jose.model.Producto;
import com.pepito.jose.model.Usuario;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class PrimaryController {

    // @Autowired
    // @Qualifier("retUsuario")
    // private Usuario usuario;

    @Autowired
    private Factura factura;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Producto producto = new Producto("Cafe", 1, 20.2);
        model.addAttribute("producto", producto);
        // model.addAttribute("usuario",usuario);
        model.addAttribute("factura", factura);
        return "index";
    }

    @GetMapping("/formulario")
    public String formulario(Model model) {
        return "formulario";
    }

    // Se establece que se van a tomar ciertos valores en una pagina
    // Se puede realizar de muchas maneras la creacion o el traspaso de valores, en
    // nuestro anterior caso hicimos:
    // public String procFormulario(Model model, @RequestParam String username,
    // @RequestParam String usersurname, @RequestParam String userpassword,
    // @RequestParam int userId, @RequestParam double userfondos)

    // Junto con:
    // Usuario usuario = Usuario.builder().nameUsuario(username)
    // .surnameUsuario(usersurname)
    // .passwordUsuario(userpassword)
    // .idUsuario(userId)
    // .fondosUsuario(userfondos)
    // .build();

    // Pero esto se puede automatizar mas de tal manera que establecemos Usuario
    // usuario dentro del argumento del metodo de tal manera que esos RequestParams
    // son tomados y añadidos diractemente a la clase

    // En SpringBoot existen varias formas de poder validar datos , en este caso en
    // concreto es la utilizacion de la anotacion @Valid la cual se puede utilizar
    // junto con una clase pojo ( o no) para validar los parametros/atributos de
    // esta

    // BindingResult representa la resolucion de la validacion, contiene los
    // errores, todo los fallos de las validaciones y se inyectan de forma
    // automatica tras tener una clase u objeto la anotacion @Valid, pero tiene una
    // restriccion que es muy importante que se respete. El BindingResult debe de
    // estar SIEMPRE tras @Valid
    @PostMapping("/formulario")
    public String procFormulario(@Valid Usuario usuario, BindingResult result, Model model) {

        if (result.hasErrors()) {
            Map<String,String> errores = new HashMap<>();
            result.getFieldErrors().forEach( error ->{
                errores.put(error.getField(), "El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
            });
            model.addAttribute("error", errores);
            return "formulario";
        }

        model.addAttribute("usuario", usuario);
        // Se establece un html, y cuando estos datos sean tomado, se mostraran en la
        // pagina de resultado
        return "resultado";
    }

}
