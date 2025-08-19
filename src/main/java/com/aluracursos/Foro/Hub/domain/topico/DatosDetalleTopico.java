package com.aluracursos.Foro.Hub.domain.topico;

import com.aluracursos.Foro.Hub.domain.cursos.Curso;
import com.aluracursos.Foro.Hub.domain.usuario.UsuarioDetalleDTO;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String fechaCreacion,
        TopicStatus estado,
        UsuarioDetalleDTO usuario,
        Curso   curso
        
        
) {

      public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion().format(
                        DateTimeFormatter.ofPattern( "d 'de' MMMM 'del' yyyy 'a las' h:mm a", new Locale("es", "ES"))
                ),
                topico.getEstado(),
                new UsuarioDetalleDTO(topico.getUsuario()),
                topico.getCurso()
        
        );
    }
}
