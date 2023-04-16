--
-- PostgreSQL database dump
--

-- Dumped from database version 13.2 (Debian 13.2-1.pgdg100+1)
-- Dumped by pg_dump version 13.10 (Ubuntu 13.10-1.pgdg20.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;


CREATE TABLE public.article (
                                id SERIAL PRIMARY KEY NOT NULL,
                                body character varying(255),
                                headline character varying(255),
                                image_path character varying(255),
                                created_on DATE
);
ALTER TABLE public.article OWNER TO postgres;
ALTER TABLE public.article_id_seq OWNER TO postgres;
ALTER SEQUENCE public.article_id_seq OWNED BY public.article.id;


CREATE TABLE public.data (
                             id SERIAL NOT NULL,
                             data double precision
);
ALTER TABLE public.data OWNER TO postgres;


CREATE TABLE public.graph (
                              id SERIAL PRIMARY KEY NOT NULL,
                              title character varying(255),
                              title_label character varying(255),
                              type character varying(255)
);
ALTER TABLE public.graph OWNER TO postgres;
ALTER TABLE public.graph_id_seq OWNER TO postgres;
ALTER SEQUENCE public.graph_id_seq OWNED BY public.graph.id;

CREATE TABLE public.heatmap (
                                id SERIAL PRIMARY KEY NOT NULL,
                                latitude double precision NOT NULL,
                                longitude double precision NOT NULL,
                                weight integer NOT NULL
);
ALTER TABLE public.heatmap OWNER TO postgres;
ALTER TABLE public.heatmap_id_seq OWNER TO postgres;
ALTER SEQUENCE public.heatmap_id_seq OWNED BY public.heatmap.id;


CREATE TABLE public.labels (
                               id bigint NOT NULL,
                               labels character varying(255)
);
ALTER TABLE public.labels OWNER TO postgres;

CREATE TABLE public.resource (
                                 id SERIAL PRIMARY KEY NOT NULL,
                                 advice_panel text,
                                 advice_panel_heading character varying(255),
                                 description_heading character varying(255),
                                 description_panel text,
                                 list_items_heading character varying(255),
                                 title character varying(255)
);
ALTER TABLE public.resource OWNER TO postgres;
ALTER TABLE public.resource_id_seq OWNER TO postgres;
ALTER SEQUENCE public.resource_id_seq OWNED BY public.resource.id;


CREATE TABLE public.resource_list_items (
                                            resource_id bigint NOT NULL,
                                            list_items character varying(255),
                                            list_items_key character varying(255) NOT NULL
);
ALTER TABLE public.resource_list_items OWNER TO postgres;


ALTER TABLE ONLY public.article ALTER COLUMN id SET DEFAULT nextval('public.article_id_seq'::regclass);
ALTER TABLE ONLY public.graph ALTER COLUMN id SET DEFAULT nextval('public.graph_id_seq'::regclass);
ALTER TABLE ONLY public.heatmap ALTER COLUMN id SET DEFAULT nextval('public.heatmap_id_seq'::regclass);
ALTER TABLE ONLY public.resource ALTER COLUMN id SET DEFAULT nextval('public.resource_id_seq'::regclass);


SELECT pg_catalog.setval('public.article_id_seq', 4, false);
SELECT pg_catalog.setval('public.graph_id_seq', 4, false);
SELECT pg_catalog.setval('public.heatmap_id_seq', 4, false);
SELECT pg_catalog.setval('public.resource_id_seq', 4, false);

ALTER TABLE ONLY public.resource_list_items
    ADD CONSTRAINT fk9cm6e093pluooqi5nd268t40b FOREIGN KEY (resource_id) REFERENCES public.resource(id);

ALTER TABLE ONLY public.labels
    ADD CONSTRAINT fkfarxe6ujkmt9av5hwvqghukt9 FOREIGN KEY (id) REFERENCES public.graph(id);

ALTER TABLE ONLY public.data
    ADD CONSTRAINT fkiq4rjhn5h0htn2f4xpa8kvlp FOREIGN KEY (id) REFERENCES public.graph(id);
