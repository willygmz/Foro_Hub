package com.aluracursos.Foro.Hub.Controller;

import com.aluracursos.Foro.Hub.domain.respuesta.*;
import com.aluracursos.Foro.Hub.domain.topico.Topico;
import com.aluracursos.Foro.Hub.domain.topico.TopicosRepository;
import com.aluracursos.Foro.Hub.domain.usuario.Usuario;
import com.aluracursos.Foro.Hub.domain.usuario.UsuarioRepository;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {

    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TopicosRepository topicoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity crearRespuesta(@RequestBody @Valid RespuestaRegistroDTO datos, UriComponentsBuilder uriComponentsBuilder){

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(datos.usuarioId());
        if (usuarioBuscado.isEmpty()) {
            String mensaje = "⚠️ El Id de usuario " + datos.usuarioId() + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        Optional<Topico> topicoBuscado = topicoRepository.findById(datos.topicoId());
        if (topicoBuscado.isEmpty()) {
            String mensaje = "⚠️ El ID de tópico " + datos.topicoId() + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }

        Usuario usuario = usuarioBuscado.get();
        Topico topico = topicoBuscado.get();

        var respuesta = new Respuesta(datos.mensaje(), usuario, topico);
        respuestaRepository.save(respuesta);

        var uri = uriComponentsBuilder.path("topico/{id}").buildAndExpand(respuesta.getId()).toUri();
        return  ResponseEntity.created(uri).body(new RespuestaDetalleDTO(respuesta));
    }

    @GetMapping
    public ResponseEntity<Page<ListaRespuestaDTO>> listar(
            @PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.DESC, page = 0) Pageable paginacion) {
        var page = respuestaRepository.findAll(paginacion).map(ListaRespuestaDTO::new);
        return ResponseEntity.ok(page);
    }


}
