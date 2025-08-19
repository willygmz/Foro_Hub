package com.aluracursos.Foro.Hub.Controller;

import com.aluracursos.Foro.Hub.domain.topico.*;
import com.aluracursos.Foro.Hub.domain.usuario.Usuario;
import com.aluracursos.Foro.Hub.domain.usuario.UsuarioRepository;
import com.aluracursos.Foro.Hub.domain.topico.DatosActualizarTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetalleTopico> registrar(@RequestBody @Valid RegistroTopicoDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.findById(datos.authorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor con ID " + datos.authorId() + " no encontrado o NO existe en la BD."));

        var topico = new Topico(datos);
        topico.setUsuario(usuario);
        topicosRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(new DatosDetalleTopico(topico));
    }


    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.ASC, page = 0) Pageable paginacion) {
        var page = topicosRepository.findAllByEstado(paginacion).map(DatosDetalleTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<DatosDetalleTopico>> buscarTopicos(@RequestParam(required = false) String curso,
                 @PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.ASC, page = 0) Pageable paginacion)
    {
             Page<Topico> topico = topicosRepository.findByCurso(curso, paginacion);
                    if (topico.get()!=null && !topico.isEmpty()) {
                        return ResponseEntity.ok(topico.map(DatosDetalleTopico::new));
                    }
                    return ResponseEntity.notFound().build();    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detallar(@PathVariable Long id) {
        Optional<Topico> topico = topicosRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleTopico(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        Optional<Topico> topicoOptional = topicosRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.actualizarTopico(datos);
            return ResponseEntity.ok(new DatosDetalleTopico(topico));
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Optional<Topico> optional = topicosRepository.findById(id);
        if (optional.isPresent()) {
            topicosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}







