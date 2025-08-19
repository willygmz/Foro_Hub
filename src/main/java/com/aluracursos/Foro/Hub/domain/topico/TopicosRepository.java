package com.aluracursos.Foro.Hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicosRepository extends JpaRepository<Topico, Long> {
    @Query("select t from Topico t where t.estado = 'ABIERTO'")
    Page<Topico> findAllByEstado(Pageable paginacion);
    
    @Query("SELECT t FROM Topico t WHERE LOWER(t.curso.curso) LIKE LOWER(concat('%', :curso, '%'))")
    Page<Topico> findByCurso(
        @Param("curso") String curso,
        Pageable pageable
    );
    


}
