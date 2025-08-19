package com.aluracursos.Foro.Hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioActualizarDTO(
       @NotBlank String nombre,
       @NotBlank String password,
       @NotBlank String perfil
) {
}
