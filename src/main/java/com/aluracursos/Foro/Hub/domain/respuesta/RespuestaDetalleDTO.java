package com.aluracursos.Foro.Hub.domain.respuesta;

import com.aluracursos.Foro.Hub.domain.topico.DatosDetalleTopico;   
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaDetalleDTO {

    Long id;
    String mensaje;
    DatosDetalleTopico topico;



    public RespuestaDetalleDTO(Respuesta answer) {
        this.id = answer.getId();
        this.mensaje = answer.getMensaje();
        this.topico =  new DatosDetalleTopico(answer.getTopico());
   
    }
}

