package com.aluracursos.Foro.Hub.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Page<Respuesta> findAll(@NonNull Pageable paginacion);
}
