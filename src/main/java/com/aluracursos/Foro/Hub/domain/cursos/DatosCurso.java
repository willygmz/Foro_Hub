package com.aluracursos.Foro.Hub.domain.cursos;

import jakarta.validation.constraints.NotBlank;


public record DatosCurso(

        @NotBlank String curso,
        @NotBlank String categoriaCurso


) {
}
