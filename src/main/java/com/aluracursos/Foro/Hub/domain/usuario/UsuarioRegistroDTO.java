package com.aluracursos.Foro.Hub.domain.usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRegistroDTO(

        @NotBlank String nombre,
        @NotBlank String password,
        @NotBlank Long autorId,
        @NotNull @Valid String email

) {


}
