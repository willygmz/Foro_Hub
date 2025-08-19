CREATE TABLE topicos(

    id bigint not null auto_increment,
    titulo varchar(188) not null unique,
    mensaje varchar(500) not null unique,
    fecha_creacion varchar(50) not null ,
    estado char(50) not null,
    usuario_id varchar(100) not null,
    curso varchar(150) not null,
    categoria_curso varchar(150) not null,

    primary key(id)

);