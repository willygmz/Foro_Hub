package com.aluracursos.Foro.Hub.Controller;


import com.aluracursos.Foro.Hub.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
//@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

        @Autowired
        private UsuarioRepository repository;
        @Autowired
        private PasswordEncoder passwordEncoder; // Inyecta desde SecurityConfigurations

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid UsuarioRegistroDTO datos, UriComponentsBuilder uriComponentsBuilder){

        var usuario = new Usuario(datos);
        var passwordPlano = datos.password();
        var passwordHasheado = passwordEncoder.encode(passwordPlano);
        usuario.setPassword(passwordHasheado);
        var usuarioNuevo =   repository.save(usuario);
        var uri = uriComponentsBuilder.path("usuario/{id}").buildAndExpand( usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new UsuarioDetalleDTO(usuarioNuevo));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDetalleDTO>> listar(@PageableDefault(size = 10, sort = {"nombre"}, page = 0) Pageable paginacion){

        var page = repository.findAll(paginacion).map(UsuarioDetalleDTO::new);
        return  ResponseEntity.ok(page);

    }


    @Transactional
    @PutMapping("/{id}")
    public  ResponseEntity actualizar(@PathVariable Long id, @RequestBody UsuarioActualizarDTO datos){

        Optional<Usuario>  usuarioOptional = repository.findById(id) ;
        if (usuarioOptional.isPresent()) {
            Usuario usuario = (Usuario) usuarioOptional.get();

            if (datos.password() != null){
               var contraseniaHasheada = passwordEncoder.encode(datos.password());
                usuario.setPassword(contraseniaHasheada);
            }
            usuario.actualizarUsuario(datos);

            var usuarioUpdate =repository.save(usuario);

            return ResponseEntity.ok(new UsuarioDetalleDTO(usuarioUpdate));
        }
        String mensaje = "⚠️El ID " + id + " no corresponde a ningún usuario registrado.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);

    }

    @Transactional
    @GetMapping("/{id}")
    public  ResponseEntity detallar(@PathVariable Long id ){

        Optional<Usuario>  usuarioOptional = repository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = (Usuario) usuarioOptional.get();
            return ResponseEntity.ok(new UsuarioDetalleDTO(usuario));
        }
        String mensaje = "⚠️El ID " + id + " no corresponde a ningún usuario registrado.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public  ResponseEntity eliminar(@PathVariable Long id){
        Optional<Usuario>  usuarioOptional = repository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.eliminar();
            return  ResponseEntity.noContent().build();
        }
        String mensaje = "⚠️ ⚠️El ID " + id + " no corresponde a ningún usuario registrado.";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);

    }
}
