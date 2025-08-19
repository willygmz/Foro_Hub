package com.aluracursos.Foro.Hub.domain.topico;

import com.aluracursos.Foro.Hub.domain.cursos.Curso;
import com.aluracursos.Foro.Hub.domain.respuesta.Respuesta;
import com.aluracursos.Foro.Hub.domain.usuario.Usuario;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;
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
        @CreationTimestamp
        private LocalDateTime fechaCreacion = LocalDateTime.now();

        @Enumerated(EnumType.STRING)
        private TopicStatus estado = TopicStatus.ABIERTO;

        @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Respuesta> respuestas = new ArrayList<>();

    @Override
    public String toString() {
        return super.toString();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Embedded
    private Curso curso;    

    public Topico(RegistroTopicoDTO datos) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.curso = new Curso(datos.curso());
    }

    public void actualizarTopico(@Valid DatosActualizarTopico datos) {
        if(datos.mensaje()!= null){
            this.mensaje = datos.mensaje();
        }
        if(datos.estado()!= null){
            this.estado = datos.estado();
        }
        if(datos.curso()!= null){
            this.curso.actualizarCurso(datos.curso());
        }

    }

}
