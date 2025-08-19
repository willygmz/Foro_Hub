package com.aluracursos.Foro.Hub.domain.cursos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosCurso(

        @NotBlank String curso,
        @NotNull Categoria categoriaCurso


) {
}
