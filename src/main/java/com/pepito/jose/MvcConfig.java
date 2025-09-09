package com.pepito.jose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



//Para poder registrar el interceptor se ha de implementar WebMvcConfigurer y se va a tener que utilizar la anotacoin @Configuration 
//La anotacion configuration se suele utilizar para registrar beans y establecer el contexto MVC y establecer un interceptor
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //Si no se especifica a que se va a aplicar, el interceptor se va a terminar aplicando a todas las rutas, a todos los handlers, a todo
    @Autowired
    @Qualifier("tiempoTranscurridoInterceptor")
    private HandlerInterceptor interceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //Para establecer que el interceptor solo se aplique a X se debe de utilizar .addPathPatterns
        registry.addInterceptor(interceptor).addPathPatterns("/formulario/**");
    }
}
