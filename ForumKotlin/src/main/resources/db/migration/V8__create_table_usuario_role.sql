create table usuario_role(
     id BIGINT NOT NULL AUTO_INCREMENT,
     usuario_id BIGINT NOT NULL,
     role_id BIGINT NOT NULL,
     PRIMARY KEY(id),
     FOREIGN KEY(usuario_id) references usuario(id),
     FOREIGN KEY(role_id) references role(id)
);

insert into usuario_role(id,usuario_id, role_id) values(1,1,1)