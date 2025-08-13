package com.aluracursos.Foro.Hub.Controller;

import com.aluracursos.Foro.Hub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;


//
//    @GetMapping("/topicos")
//    @Cacheable(value = "topicList")
//    public Page<TopicDTO> list(
//            @RequestParam(required = false) String courseName,
//            @PageableDefault(sort = "creationDate", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {
//
//        Page<Topicos> topics;
//
//        if (courseName == null) {
//            topics = TopicosRepository.findAll(pagination);
//            System.out.println(topics.getTotalElements());
//        } else {
//            topics = topicosRepository.findByCourseName(courseName, pagination);
//            System.out.println(topics.getTotalElements());
//        }
//
//        return TopicDTO.convert(topics);
//    }


    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid RegistroTopicoDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(datos);
        var topico = new Topico(datos);
        topicosRepository.save(topico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(topico);
    }

}



//
//        @PostMapping
//        @Transactional
//        @CacheEvict(value = "topiclist", allEntries = true)
//        public ResponseEntity<TopicDTO> register(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
//            Topic topic = form.convert(courseRepository);
//            topicRepository.save(topico);
//            URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
//            return ResponseEntity.created(uri).body(new TopicDTO(topic));
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<TopicDetailsDTO> detail(@PathVariable Long id) {
//            Optional<Topic> topic = topicRepository.findById(id);
//            if (topic.isPresent()) {
//                return ResponseEntity.ok(new TopicDetailsDTO(topic.get()));
//            }
//            return ResponseEntity.notFound().build();
//        }
//
//        @PutMapping("/{id}")
//        @Transactional
//        public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form) {
//            Optional<Topic> optional = topicRepository.findById(id);
//            if (optional.isPresent()) {
//                Topic topic = form.update(id, topicRepository);
//                return ResponseEntity.ok(new TopicDTO(topic));
//            }
//            return ResponseEntity.notFound().build();
//        }
//
//        @DeleteMapping("/{id}")
//        @Transactional
//        public ResponseEntity<Void> delete(@PathVariable Long id) {
//            Optional<Topic> optional = topicRepository.findById(id);
//            if (optional.isPresent()) {
//                topicRepository.deleteById(id);
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.notFound().build();
//        }
//    }




