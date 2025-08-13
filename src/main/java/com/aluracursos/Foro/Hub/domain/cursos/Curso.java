package com.aluracursos.Foro.Hub.domain.cursos;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter // me genera todos los geter y seters ala hora de la ejecucion del la aplicacion
@NoArgsConstructor//genera un constructor vacio
@AllArgsConstructor // genera un construcctor con todos los atributos de la entidad

@Embeddable// para indicar que esa entidad sera embebida en la Tabla medicos
public class Curso {

    private String curso;
    private String categoriaCurso;

    public Curso(DatosCurso datos) {
        this.curso = datos.curso();
        this.categoriaCurso = datos.categoriaCurso();
    }

    public String getCurso() {
        return curso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.curso = nombreCurso;
    }

    public String getCategoriaCurso() {
        return categoriaCurso;
    }

    public void setCategoriaCurso(String categoriaCurso) {
        this.categoriaCurso = categoriaCurso;
    }
}