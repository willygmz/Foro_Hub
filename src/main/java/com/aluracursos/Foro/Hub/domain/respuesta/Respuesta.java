package com.aluracursos.Foro.Hub.domain.respuesta;


import com.aluracursos.Foro.Hub.domain.usuario.Usuario;
import com.aluracursos.Foro.Hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    // Constructors
    public Respuesta() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Respuesta(String message, Usuario author) {
        this.mensaje = message;
        this.autor = author;
        this.fechaCreacion = LocalDateTime.now();
    }
}