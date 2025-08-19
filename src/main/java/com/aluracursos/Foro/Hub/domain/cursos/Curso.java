package com.aluracursos.Foro.Hub.domain.cursos;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Curso {

    private String curso;

    @Enumerated(EnumType.STRING)
    private Categoria categoriaCurso;

    public Curso(DatosCurso datos) {
        this.curso = datos.curso();
        this.categoriaCurso =  datos.categoriaCurso();
    }


    public void actualizarCurso(@NotNull Curso curso) {
        if (curso.getCurso() != null) {
            this.curso = curso.getCurso();
        }
        if (curso.getCategoriaCurso() != null) {
            this.categoriaCurso = curso.getCategoriaCurso();
        }
    }
}