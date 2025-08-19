package com.aluracursos.Foro.Hub.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "usuarios")
public class Usuario implements UserDetails    {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String login;
    private String password;
    private String perfil;
    private boolean activo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    public Usuario(UsuarioRegistroDTO datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.password = datos.password();
        this.id = datos.autorId();
        this.email = datos.email();
    }
    public void actualizarUsuario(@Valid UsuarioActualizarDTO datos) {
        if(datos.nombre()!= null){
            this.nombre = datos.nombre();
        }
        if(datos.password()!= null){
            this.password = datos.password();
        }
        if(datos.perfil()!= null){
            this.perfil = datos.perfil();
        }



    }

    public void   eliminar() {
        this.activo = false;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activo;
    }
    
    public boolean getActivo() {
        return activo;
    }


}
