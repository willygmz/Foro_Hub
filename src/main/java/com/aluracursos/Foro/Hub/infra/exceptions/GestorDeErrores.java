package com.aluracursos.Foro.Hub.infra.exceptions;

import com.aluracursos.Foro.Hub.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity getionarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // no usages
    public ResponseEntity<Object> handleError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
    }

    public record DatosErrorValidacion(String campo, String mensaje
        ) { // usage
        public DatosErrorValidacion(FieldError error)
        { // 1 usage
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion(ValidacionException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
