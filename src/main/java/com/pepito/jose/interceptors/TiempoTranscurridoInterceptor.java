package com.pepito.jose.interceptors;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Un interceptor puede ser establecido como un component
@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("TiempoTranscurridoInterceptor: preHandle() entrando...");

        // Si se establece logger.info("Interceptando: "+handler) veremos todos los elementos que se estan interceptando 
        
        // System tiene muchos metodos , anteriormente lo hemos visto como System.in
        // pero tambien tenemos System.currentTimeMillis
        long tiempoInicio = System.currentTimeMillis();
        // El valor de lo que vamos a realizar se pasa al request

        // La diferencia entre establecer un setAttribute y establecer un addAttribute
        // del modelo en un contralador es que la establecida por el request solo vivirá
        // en ese request
        request.setAttribute("tiempoInicio", tiempoInicio);
        Random random = new Random();
        Integer demora = random.nextInt(500);
        Thread.sleep(demora);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        long tiempoFinal = System.currentTimeMillis();
        long tiempoInicio = (Long) request.getAttribute("tiempoInicio");

        tiempoFinal = tiempoFinal - tiempoInicio;

        // Es importante establecer que este no sea null, ya que estamos trabajando con
        // dos conceptos distintos. Ya que las hojas de estilo y otros elementos tambien
        // son rutas y tambien se estan interceptando y no tienen el ModelAndView por lo que serian null
        if (modelAndView != null) {
            modelAndView.addObject("tiempoFinal", tiempoFinal);
        }
        logger.info("TiempoTranscurrido :" + tiempoFinal + "milisegundos");
        logger.info("TiempoTranscurridoInterceptor: preHandle() saliendo...");

    }
}
