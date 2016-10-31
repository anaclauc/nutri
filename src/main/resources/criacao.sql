--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2016-10-30 22:09:20 BRST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 180 (class 3079 OID 12018)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2248 (class 0 OID 0)
-- Dependencies: 180
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 193 (class 1255 OID 16460)
-- Name: peso_em_branco(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION peso_em_branco() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
    BEGIN
        IF NEW.peso_atual IS NULL THEN
            RAISE EXCEPTION 'O peso nao pode ser nulo';
        END IF;
        
        RETURN NEW;
    END;
$$;


ALTER FUNCTION public.peso_em_branco() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 16404)
-- Name: alimentos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE alimentos (
    id integer NOT NULL,
    descricao character varying(180) NOT NULL
);


ALTER TABLE public.alimentos OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16407)
-- Name: alimentos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE alimentos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alimentos_id_seq OWNER TO postgres;

--
-- TOC entry 2249 (class 0 OID 0)
-- Dependencies: 171
-- Name: alimentos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE alimentos_id_seq OWNED BY alimentos.id;


--
-- TOC entry 172 (class 1259 OID 16409)
-- Name: avaliacao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE avaliacao (
    id integer NOT NULL,
    id_usuario bigint NOT NULL,
    id_dieta bigint,
    data date NOT NULL,
    peso_atual double precision NOT NULL,
    imc double precision NOT NULL,
    taxa_basal double precision NOT NULL
);


ALTER TABLE public.avaliacao OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16412)
-- Name: avaliacao_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE avaliacao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.avaliacao_id_seq OWNER TO postgres;

--
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 173
-- Name: avaliacao_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE avaliacao_id_seq OWNED BY avaliacao.id;


--
-- TOC entry 174 (class 1259 OID 16414)
-- Name: dieta_alimentos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dieta_alimentos (
    id_dieta bigint NOT NULL,
    id_alimento bigint NOT NULL,
    quantidade double precision,
    tipo integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.dieta_alimentos OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16417)
-- Name: dieta_alimentos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dieta_alimentos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dieta_alimentos_id_seq OWNER TO postgres;

--
-- TOC entry 2251 (class 0 OID 0)
-- Dependencies: 175
-- Name: dieta_alimentos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dieta_alimentos_id_seq OWNED BY dieta_alimentos.id;


--
-- TOC entry 176 (class 1259 OID 16419)
-- Name: dietas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE dietas (
    id integer NOT NULL,
    descricao character varying(250) NOT NULL,
    nome character varying(180) NOT NULL
);


ALTER TABLE public.dietas OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 16422)
-- Name: dietas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE dietas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dietas_id_seq OWNER TO postgres;

--
-- TOC entry 2252 (class 0 OID 0)
-- Dependencies: 177
-- Name: dietas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE dietas_id_seq OWNED BY dietas.id;


--
-- TOC entry 178 (class 1259 OID 16424)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
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


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16427)
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 2253 (class 0 OID 0)
-- Dependencies: 179
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuarios_id_seq OWNED BY usuarios.id;


--
-- TOC entry 2114 (class 2604 OID 16429)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alimentos ALTER COLUMN id SET DEFAULT nextval('alimentos_id_seq'::regclass);


--
-- TOC entry 2115 (class 2604 OID 16430)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY avaliacao ALTER COLUMN id SET DEFAULT nextval('avaliacao_id_seq'::regclass);


--
-- TOC entry 2116 (class 2604 OID 16431)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dieta_alimentos ALTER COLUMN id SET DEFAULT nextval('dieta_alimentos_id_seq'::regclass);


--
-- TOC entry 2117 (class 2604 OID 16432)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY dietas ALTER COLUMN id SET DEFAULT nextval('dietas_id_seq'::regclass);


--
-- TOC entry 2118 (class 2604 OID 16433)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios ALTER COLUMN id SET DEFAULT nextval('usuarios_id_seq'::regclass);

--
-- TOC entry 2120 (class 2606 OID 16435)
-- Name: pk_usuarios; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT pk_usuarios PRIMARY KEY (id);


--
-- TOC entry 2122 (class 2606 OID 16437)
-- Name: uk_email; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT uk_email UNIQUE (email);


--
-- TOC entry 2123 (class 2620 OID 16461)
-- Name: peso_em_branco; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER peso_em_branco BEFORE INSERT OR UPDATE ON avaliacao FOR EACH ROW EXECUTE PROCEDURE peso_em_branco();


--
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-10-30 22:09:20 BRST

--
-- PostgreSQL database dump complete
--

