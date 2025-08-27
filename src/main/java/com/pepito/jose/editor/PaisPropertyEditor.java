// package com.pepito.jose.editor;

// import java.beans.PropertyEditorSupport;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import com.pepito.jose.services.PaisService;
// // Esta clase solo funcionaria se establecemos en el initBinder su uso
// @Component
// public class PaisPropertyEditor extends PropertyEditorSupport{
//     @Autowired
//     private PaisService service;
//     @Override
//     public void setAsText(String id) throws IllegalArgumentException{
//         this.setValue(service.obtenerPorId(Integer.parseInt(id)));
//     }
// }
