-- Database: nutri

-- DROP DATABASE nutri;

CREATE DATABASE nutri
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;

-- Table: public.usuarios

-- DROP TABLE public.usuarios;

CREATE TABLE public.usuarios
(
  nome character varying(180) NOT NULL,
  sexo character(1) NOT NULL,
  idade integer NOT NULL,
  peso double precision NOT NULL,
  altura double precision NOT NULL,
  id bigint NOT NULL DEFAULT nextval('usuarios_id_seq'::regclass),
  email character varying(255) NOT NULL,
  CONSTRAINT pk_usuarios PRIMARY KEY (id),
  CONSTRAINT uk_email UNIQUE (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuarios
  OWNER TO postgres;

-- Sequence: public.usuarios_id_seq

-- DROP SEQUENCE public.usuarios_id_seq;

CREATE SEQUENCE public.usuarios_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.usuarios_id_seq
  OWNER TO postgres;

-- Table: public.historico_pesos

-- DROP TABLE public.historico_pesos;

CREATE TABLE public.historico_pesos
(
  id_usuario bigint NOT NULL,
  data date NOT NULL,
  peso double precision NOT NULL,
  id bigint NOT NULL DEFAULT nextval('historico_pesos_id_seq'::regclass),
  CONSTRAINT pk_historico_pesos PRIMARY KEY (id),
  CONSTRAINT fk_historico_pesos_usuarios FOREIGN KEY (id_usuario)
      REFERENCES public.usuarios (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.historico_pesos
  OWNER TO postgres;

-- Index: public.fki_historico_pesos_usuarios

-- DROP INDEX public.fki_historico_pesos_usuarios;

CREATE INDEX fki_historico_pesos_usuarios
  ON public.historico_pesos
  USING btree
  (id_usuario);

-- Sequence: public.historico_pesos_id_seq

-- DROP SEQUENCE public.historico_pesos_id_seq;

CREATE SEQUENCE public.historico_pesos_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.historico_pesos_id_seq
  OWNER TO postgres;
