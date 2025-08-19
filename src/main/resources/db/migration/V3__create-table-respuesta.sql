
CREATE TABLE respuestas (
    id bigint NOT NULL auto_increment,
       mensaje text NOT NULL,
       topico_id bigint NOT NULL,
       fecha_creacion datetime NOT NULL,
       usuario_id bigint NOT NULL,
       solucion varchar(2),
        primary key(id),

       CONSTRAINT fk_respuesta_topico_id FOREIGN KEY (topico_id) REFERENCES topicos(id),
       CONSTRAINT fk_respuesta_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id)

)