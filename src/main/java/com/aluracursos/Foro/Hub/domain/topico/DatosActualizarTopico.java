package com.aluracursos.Foro.Hub.domain.topico;

import com.aluracursos.Foro.Hub.domain.cursos.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        @NotBlank String mensaje,
        @NotNull TopicStatus estado,
        @NotNull Curso curso

        ) {

}
