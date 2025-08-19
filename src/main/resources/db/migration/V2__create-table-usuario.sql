create table usuarios(
          id bigint not null auto_increment,
          nombre varchar(100) not null,
          email varchar(100) not null unique,
          Login varchar(100) not null unique,
          password varchar(255) not null,
          perfil varchar(100) not null,
          activo tinyint not null,

          primary key(id)
);

