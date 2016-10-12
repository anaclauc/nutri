--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.3
-- Dumped by pg_dump version 9.5.3

-- Started on 2016-10-12 09:57:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2145 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 183 (class 1259 OID 16497)
-- Name: alimentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE alimentos (
    id integer NOT NULL,
    descricao character varying(180) NOT NULL
);


ALTER TABLE alimentos OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16500)
-- Name: alimentos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE alimentos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE alimentos_id_seq OWNER TO postgres;

--
-- TOC entry 2146 (class 0 OID 0)
-- Dependencies: 184
-- Name: alimentos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE alimentos_id_seq OWNED BY alimentos.id;


--
-- TOC entry 190 (class 1259 OID 16524)
-- Name: avaliacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE avaliacao (
    id integer NOT NULL,
    id_usuario bigint NOT NULL,
    id_dieta bigint NOT NULL,
    data date NOT NULL,
    peso_atual double precision NOT NULL,
    imc double precision NOT NULL,
    taxa_basal double precision
);


ALTER TABLE avaliacao OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16522)
-- Name: avaliacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE avaliacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE avaliacao_id_seq OWNER TO postgres;

--
-- TOC entry 2147 (class 0 OID 0)
-- Dependencies: 189
-- Name: avaliacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE avaliacao_id_seq OWNED BY avaliacao.id;


--
-- TOC entry 187 (class 1259 OID 16512)
-- Name: dieta_alimentos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE dieta_alimentos (
    id_dieta bigint NOT NULL,
    id_alimento bigint NOT NULL,
    quantidade double precision,
    tipo integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE dieta_alimentos OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16515)
-- Name: dieta_alimentos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dieta_alimentos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dieta_alimentos_id_seq OWNER TO postgres;

--
-- TOC entry 2148 (class 0 OID 0)
-- Dependencies: 188
-- Name: dieta_alimentos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dieta_alimentos_id_seq OWNED BY dieta_alimentos.id;


--
-- TOC entry 186 (class 1259 OID 16508)
-- Name: dietas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE dietas (
    id integer NOT NULL,
    descricao character varying(250) NOT NULL,
    nome character varying(180) NOT NULL
);


ALTER TABLE dietas OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16506)
-- Name: dietas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dietas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE dietas_id_seq OWNER TO postgres;

--
-- TOC entry 2149 (class 0 OID 0)
-- Dependencies: 185
-- Name: dietas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dietas_id_seq OWNED BY dietas.id;


--
-- TOC entry 181 (class 1259 OID 16451)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuarios (
    nome character varying(180) NOT NULL,
    sexo character(1) NOT NULL,
    idade integer NOT NULL,
    peso double precision NOT NULL,
    altura double precision NOT NULL,
    id bigint NOT NULL,
    email character varying(255) NOT NULL
);


ALTER TABLE usuarios OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16473)
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 182
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuarios_id_seq OWNED BY usuarios.id;


--
-- TOC entry 2006 (class 2604 OID 16502)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alimentos ALTER COLUMN id SET DEFAULT nextval('alimentos_id_seq'::regclass);


--
-- TOC entry 2009 (class 2604 OID 16527)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao ALTER COLUMN id SET DEFAULT nextval('avaliacao_id_seq'::regclass);


--
-- TOC entry 2008 (class 2604 OID 16517)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dieta_alimentos ALTER COLUMN id SET DEFAULT nextval('dieta_alimentos_id_seq'::regclass);


--
-- TOC entry 2007 (class 2604 OID 16511)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dietas ALTER COLUMN id SET DEFAULT nextval('dietas_id_seq'::regclass);


--
-- TOC entry 2005 (class 2604 OID 16475)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios ALTER COLUMN id SET DEFAULT nextval('usuarios_id_seq'::regclass);


--
-- TOC entry 2130 (class 0 OID 16497)
-- Dependencies: 183
-- Data for Name: alimentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY alimentos (id, descricao) FROM stdin;
\.


--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 184
-- Name: alimentos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('alimentos_id_seq', 1, false);


--
-- TOC entry 2137 (class 0 OID 16524)
-- Dependencies: 190
-- Data for Name: avaliacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY avaliacao (id, id_usuario, id_dieta, data, peso_atual, imc, taxa_basal) FROM stdin;
\.


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 189
-- Name: avaliacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('avaliacao_id_seq', 1, false);


--
-- TOC entry 2134 (class 0 OID 16512)
-- Dependencies: 187
-- Data for Name: dieta_alimentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY dieta_alimentos (id_dieta, id_alimento, quantidade, tipo, id) FROM stdin;
\.


--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 188
-- Name: dieta_alimentos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('dieta_alimentos_id_seq', 1, false);


--
-- TOC entry 2133 (class 0 OID 16508)
-- Dependencies: 186
-- Data for Name: dietas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY dietas (id, descricao, nome) FROM stdin;
\.


--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 185
-- Name: dietas_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('dietas_id_seq', 1, false);


--
-- TOC entry 2128 (class 0 OID 16451)
-- Dependencies: 181
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY usuarios (nome, sexo, idade, peso, altura, id, email) FROM stdin;
Ana Claudia	F	26	57	1.6699999999999999	1	anclaudiccpatricio2@gmail.com
\.


--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 182
-- Name: usuarios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuarios_id_seq', 4, true);


--
-- TOC entry 2011 (class 2606 OID 16480)
-- Name: pk_usuarios; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT pk_usuarios PRIMARY KEY (id);


--
-- TOC entry 2013 (class 2606 OID 16496)
-- Name: uk_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT uk_email UNIQUE (email);


--
-- TOC entry 2144 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-10-12 09:57:55

--
-- PostgreSQL database dump complete
--

