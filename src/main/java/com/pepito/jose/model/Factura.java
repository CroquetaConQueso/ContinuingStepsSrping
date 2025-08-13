package com.pepito.jose.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// NOTA: Los @Component y @Controller en Spring son singletons por defecto.
// Esto significa que solo existe una instancia en el contenedor para toda la aplicación.
// Por este motivo, deben ser STATELESS (no almacenar estado mutable compartido),
// ya que podría causar problemas de concurrencia, corrupción de datos o fuga de información entre usuarios.
// Cualquier dato específico de una petición debe manejarse en variables locales,
// beans con @RequestScope/@SessionScope o servicios diseñados para manejar estado de forma segura.

// @ApplicationScope indica que este bean tendrá un ciclo de vida ligado
// a toda la aplicación web. Solo se crea una vez y se almacena en el
// ServletContext, siendo compartido por todas las sesiones y peticiones.
// Esto es útil para datos globales como estadísticas, caches o
// configuraciones que deben persistir mientras el servidor esté en ejecución.

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
// @RequestScope Indica que este bean es de ámbito de solicitud, se crea una nueva instancia por cada solicitud HTTP

@SessionScope
// Tambien existe SessionScope el cual esta pensado para mantener el estado del
// usuario a lo largo de varias solicitudes hasta que se cierre el navegador.
// Ideal para carritos de compra o sesiones de usuario. Toda session que se
// establezca debe de implementar serializable porque cuando se transporta o se
// almacena un objeto de java, si lo queremos transportar o guardar de la
// memoria o queremos que persista, necesitamos que implemente esta interfaz.
// Porque convierte el objeto en una secuencia de bytes para que se pueda
// transportar y guardar en estos tipos de almacenamientos. El que mas tarde se terminara restaurando en un objeto identico , siendo clon del original.
public class Factura implements Serializable {

    private static final long serialVersionUID = 123454589123L;

    @Autowired
    @Qualifier("retDescripcion")
    private String facturaDescripcion;

    @Autowired
    @Qualifier("retUsuario")
    private Usuario usuario;

    @Autowired
    @Qualifier("retListaOficina")
    private List<ItemFactura> listaFacturas;

    // @PostConstruct se utiliza para ejecutar lógica de inicialización
    // justo después de que el bean ha sido creado e inyectado por Spring,
    // pero antes de que esté disponible para el resto de la aplicación.
    // Suele emplearse para precargar datos, validar configuraciones o
    // preparar recursos que dependen de otras dependencias inyectadas.
    @PostConstruct
    public void init() {
        usuario.setNameUsuario(usuario.getNameUsuario().concat(" José"));
        facturaDescripcion.concat(" de " + usuario.getNameUsuario());
    }

    // Existen muchos contextos que se pueden establecer para cuando un objeto o
    // bean ha de ser destruido, en este caso al predefinido es que al pararse la
    // aplicación el objeto se destruirá
    @PreDestroy
    public void destructor() {
        System.out.println("Factura destruida ".concat(facturaDescripcion));
    }
}
