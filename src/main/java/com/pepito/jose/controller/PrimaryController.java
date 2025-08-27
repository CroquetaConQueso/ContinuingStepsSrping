package com.pepito.jose.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.pepito.jose.editor.NombreMayusculaEditor;
import com.pepito.jose.model.Factura;
import com.pepito.jose.model.Pais;
import com.pepito.jose.model.Producto;
import com.pepito.jose.model.Usuario;
import com.pepito.jose.services.PaisService;
import com.pepito.jose.validators.UsuarioValidador;

import jakarta.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
// Si queremos que el atributo entity, el cual no esta mapeado persista(estamos
// hablando del atributo el cual no tiene un @NotEmpty), ni se encuentra en el
// formulario. Estos, son poblados en el usuario y se envian desde este,
// haciendo que todos los atributos que no esten en el formulario sean
// establecidos como null. Añadirlos porque si no es una opción ya que es un
// atributo que el usuario no debe de ver. Para esto usamos SessionAttributes,
// guardandolos en una session http
// dando el nombre del objeto. Teniendo que limpiarlo luego tras haberse usado
// en la base de datos o el formulario en si
@SessionAttributes("usuario")
@RequestMapping("/")
public class PrimaryController {

    @Autowired
    private UsuarioValidador validador;

    // @Autowired
    // @Qualifier("retUsuario")

    // Para automatizar la clase validadora , se debe de establecer un @InitBinder.
    // Esto nos permitira quitar la instanciacion del metodo y permitir hacerlo todo
    // mas limpio ya que se automatizará mediante la anotacion @valid

    @Autowired
    private PaisService paisService;

    // @Autowired
    // private PaisPropertyEditor paisPropertyEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Al establecer un set estamos reemplazondo el validador por defecto con el
        // validador que hemos pasado, por lo que se pierde todas las anteriores
        // anotaciones que se hayan establecido con anterioridad. Para esto vamos a utilizar addValidators
        binder.addValidators(validador);

        
        // Al establecer esto se definira sobre todas las clases de tipo String 

        // Registramos un editor de propiedades para todos los campos de tipo String
        // Esto significa que antes de hacer el data binding (copiar valores del formulario al objeto),
        // cualquier String pasará por NombreMayusculaEditor, el cual lo transformará a MAYÚSCULAS y quitará espacios.
        // Útil para normalizar entradas de texto (ej: nombres propios, códigos, etc.)
    
        binder.registerCustomEditor(String.class,"nameUsuario" ,new NombreMayusculaEditor());
        //binder.registerCustomEditor(Pais.class,"pais", paisPropertyEditor);
    }

    private Usuario usuario;

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
        model.addAttribute("usuario", usuario);
        model.addAttribute("factura", factura);
        return "index";
    }

    @GetMapping("/formulario")
    public String formulario(Model model) {
        // Al mostrar por primera vez el formulario (GET), añadimos un objeto Usuario
        // vacío al modelo.
        // Esto asegura que Thymeleaf pueda enlazar los campos del formulario a las
        // propiedades del objeto,
        // incluso antes de que el usuario envíe datos.
        //
        // HTTP es sin estado, por lo que cada request es independiente.
        // Si tras un POST con errores de validación volvemos a renderizar el
        // formulario,
        // debemos reenviar al modelo el objeto Usuario con los datos introducidos para
        // evitar que
        // los campos aparezcan vacíos. De lo contrario, los valores escritos se
        // perderán al recargar la vista.

        Usuario usuario = new Usuario();
        usuario.setEmailUsuario("carlos@fuengirola1.es");

        // Este valor se va a enviar a el metodo de usuario y pese a que lo tenga
        // prestablecido con un valor, este valor se pierde.
        usuario.setNameUsuario("Gilito");
        usuario.setIdUsuario("1");
        usuario.setFondosUsuario(3000.1);
        model.addAttribute(usuario);

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

    // Para proporcionar un alias a la clase podemos utilizar
    // @ModelAttribute("usuario")
    @PostMapping("/formulario")
    public String procFormulario(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {

        // Ya no es necesario al establecer el InitBinder
        // validador.validate(usuario, result);
        if (result.hasErrors()) {
            // Esto ya se esta haciendo automaticamente con BindingResult por lo que es
            // innecesario especificar esto
            // Map<String, String> errores = new HashMap<>();
            // result.getFieldErrors().forEach(error -> {
            // errores.put(error.getField(),
            // "El campo ".concat(error.getField()).concat("
            // ").concat(error.getDefaultMessage()));
            // });
            // model.addAttribute("error", errores);
            return "formulario";
        }

        model.addAttribute("usuario", usuario);
        // Se establece un html, y cuando estos datos sean tomado, se mostraran en la
        // pagina de resultado

        status.setComplete(); // Se limpia el objeto usuario de la @SessionAttributes
        return "resultado";
    }

    @ModelAttribute("paises")
    public List<String> paises(){
        return List.of("Egipto","España","Francia","Chile","Mexico");
        
    }

    @ModelAttribute("paisesMap")
    public Map<String,Pais> paisesMapa(){
        Map<String,Pais> mapPaises = new HashMap<>();
        mapPaises.put("EG", new Pais(1,"EG","Egipto"));
        mapPaises.put("ES", new Pais(2,"ES","España"));
        mapPaises.put("FR", new Pais(3,"FR","Francia"));
        mapPaises.put("CL", new Pais(4,"CH","Chile"));
        mapPaises.put("ME", new Pais(5,"ME","Mexico"));


        return mapPaises;
    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises(){
        return paisService.Listar();
    }
}
