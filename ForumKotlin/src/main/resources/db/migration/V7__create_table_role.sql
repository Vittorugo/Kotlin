create table role(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    PRIMARY KEY(id)
);

insert into role(id,nome) values(1,'LEITURA_ESCRITA')