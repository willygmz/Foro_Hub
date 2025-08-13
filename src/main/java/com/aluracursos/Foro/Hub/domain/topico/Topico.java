package com.aluracursos.Foro.Hub.domain.topico;

import com.aluracursos.Foro.Hub.domain.cursos.Curso;
import com.aluracursos.Foro.Hub.domain.respuesta.Respuesta;
import com.aluracursos.Foro.Hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titulo;

        private String mensaje;

        @Column(name = "fecha_creacion")
        private LocalDateTime fechaCreacion = LocalDateTime.now();

        @Enumerated(EnumType.STRING)
        private TopicStatus status = TopicStatus.ABIERTO;

        @ManyToOne
        private Usuario usuario;

        @Embedded
        private Curso curso;


        public Topico(RegistroTopicoDTO datos) {
                this.id = null;
                this.titulo =  datos.titulo();
                this.mensaje = datos.mensaje();
                this.usuario = new Usuario (datos.usuario());
                this.curso =   new Curso( datos.curso());
        }

}

