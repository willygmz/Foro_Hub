package com.aluracursos.Foro.Hub.domain.respuesta;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RespuestaDTO {

    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String nombreAutor;

    public RespuestaDTO(Respuesta answer) {  
        this.id = answer.getId();
        this.mensaje = answer.getMensaje();
        this.fechaCreacion = answer.getFechaCreacion();
        this.nombreAutor = answer.getAutor().getNombre();
    }
}

