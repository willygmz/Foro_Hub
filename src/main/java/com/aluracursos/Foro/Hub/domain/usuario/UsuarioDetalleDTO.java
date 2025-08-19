package com.aluracursos.Foro.Hub.domain.usuario;


public record UsuarioDetalleDTO(
        Long id,
        String nombre,
        String email,
        String login,
        boolean activo
) {

    public UsuarioDetalleDTO (Usuario user){

        this(
                user.getId(),
                user.getNombre(),
                user.getEmail(),
                user.getLogin(),
                user.isEnabled()

        );
    }

}
