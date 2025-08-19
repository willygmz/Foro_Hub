package com.aluracursos.Foro.Hub.domain.respuesta;


import com.aluracursos.Foro.Hub.domain.usuario.Usuario;
import com.aluracursos.Foro.Hub.domain.topico.Topico;
import com.aluracursos.Foro.Hub.domain.usuario.UsuarioDetalleDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")   
@Entity
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensaje;

    @Column(name = "fecha_creacion")
    @CreationTimestamp
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;
    
    @Column(columnDefinition = "varchar(2) default 'no'")
    private String solucion = "no";

    public Respuesta(@NotBlank String mensaje, Usuario usuario, Topico topico) {
        this.id = null;
        this.mensaje = mensaje;
        this.topico = topico;
        this.autor = usuario;

    }

}