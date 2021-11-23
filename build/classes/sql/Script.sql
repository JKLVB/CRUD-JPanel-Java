--DROP TABLE cadastro.usuario;

CREATE DATABASE projeto
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE SEQUENCE cadastro.sq_usuario
  INCREMENT 1
  MINVALUE 0
  MAXVALUE 2147483647
  START 1
  CACHE 1;
ALTER TABLE cadastro.sq_usuario
  OWNER TO postgres;
GRANT ALL ON SEQUENCE cadastro.sq_usuario TO postgres;

CREATE TABLE cadastro.usuario
(
  id integer NOT NULL DEFAULT nextval('cadastro.sq_usuario'::regclass),
  nome VARCHAR(50) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  senha VARCHAR(30) NOT NULL,
  telefone VARCHAR(11) NOT NULL,
  cargo VARCHAR(30) NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id),
  CONSTRAINT uk_usuario_cpf UNIQUE (cpf),
  CONSTRAINT uk_usuario_teleone UNIQUE (telefone)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cadastro.usuario
  OWNER TO postgres;
  
GRANT ALL ON TABLE cadastro.usuario TO postgres;
