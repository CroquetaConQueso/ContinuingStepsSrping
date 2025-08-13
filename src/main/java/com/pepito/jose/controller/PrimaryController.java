package com.pepito.jose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pepito.jose.model.Factura;
import com.pepito.jose.model.Producto;
import com.pepito.jose.model.Usuario;

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

    //Se establece que se van a tomar ciertos valores en una pagina 
    //Se puede realizar de muchas maneras la creacion o el traspaso de valores, en nuestro anterior caso hicimos:
    // public String procFormulario(Model model, @RequestParam String username, @RequestParam String usersurname, @RequestParam String userpassword, @RequestParam int userId, @RequestParam double userfondos)
    
    // Junto con:
    //     Usuario usuario = Usuario.builder().nameUsuario(username)
    //             .surnameUsuario(usersurname)
    //             .passwordUsuario(userpassword)
    //             .idUsuario(userId)
    //             .fondosUsuario(userfondos)
    //             .build();

    //Pero esto se puede automatizar mas de tal manera que establecemos Usuario usuario dentro del argumento del metodo de tal manera que esos RequestParams son tomados y añadidos diractemente a la clase
    @PostMapping("/formulario")
    public String procFormulario(Usuario usuario, Model model) {

        model.addAttribute("usuario", usuario);
        //Se establece un html, y cuando estos datos sean tomado, se mostraran en la pagina de resultado
        return "resultado";
    }
    

}
