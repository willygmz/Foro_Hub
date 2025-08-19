package com.aluracursos.Foro.Hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   
    Optional<Usuario> findByEmail(String email);
  
    Optional<Usuario> findById(Long id);

    UserDetails findByLogin(String username);
}
