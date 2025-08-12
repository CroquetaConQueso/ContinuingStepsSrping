package com.pepito.jose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pepito.jose.model.Factura;
import com.pepito.jose.model.Producto;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping
public class PrimaryController {
    
    // @Autowired
    // @Qualifier("retUsuario")
    // private Usuario usuario;

    @Autowired
    private Factura factura;

    @GetMapping("/index")
    public String index(Model model) {
        Producto producto = new Producto("Cafe", 1, 20.2);
        model.addAttribute("producto",producto);
        // model.addAttribute("usuario",usuario);
        model.addAttribute("factura",factura);
        return "index";
    }
    
}
