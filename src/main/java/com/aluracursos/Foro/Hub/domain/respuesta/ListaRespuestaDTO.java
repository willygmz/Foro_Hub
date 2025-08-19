package com.aluracursos.Foro.Hub.domain.respuesta;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record ListaRespuestaDTO(
        Long id,
        String respuestaMensaje,    
        String topicoTitulo,
        String topicoMensaje,
        String topicoCurso,
        String autorNombre,
        String fechaRespuesta

) {

    public ListaRespuestaDTO(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getTopico().getMensaje(),
                respuesta.getTopico().getCurso().getCurso(),
                respuesta.getAutor().getNombre(),
                respuesta.getFechaCreacion().format(
                        DateTimeFormatter.ofPattern( "d 'de' MMMM 'del' yyyy 'a las' h:mm a", new Locale("es", "ES"))
                )
        );
    }

}
