package com.aluracursos.Foro.Hub.domain.topico;

import com.aluracursos.Foro.Hub.domain.cursos.Curso;
import com.aluracursos.Foro.Hub.domain.cursos.DatosCurso;
import com.aluracursos.Foro.Hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record RegistroTopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull  String usuario,
        @NotNull @Valid DatosCurso curso

) {


}
