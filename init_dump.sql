--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1 (Ubuntu 13.1-1.pgdg20.04+1)
-- Dumped by pg_dump version 13.1 (Ubuntu 13.1-1.pgdg20.04+1)

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

--
-- Name: currency; Type: TABLE; Schema: public; Owner: iteratia
--

CREATE TABLE public.currency (
    id integer NOT NULL,
    rid text NOT NULL,
    num_code smallint NOT NULL,
    char_code text NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.currency OWNER TO iteratia;

--
-- Name: currency_id_seq; Type: SEQUENCE; Schema: public; Owner: iteratia
--

CREATE SEQUENCE public.currency_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.currency_id_seq OWNER TO iteratia;

--
-- Name: currency_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: iteratia
--

ALTER SEQUENCE public.currency_id_seq OWNED BY public.currency.id;


--
-- Name: exchange_history; Type: TABLE; Schema: public; Owner: iteratia
--

CREATE TABLE public.exchange_history (
    id integer NOT NULL,
    date date NOT NULL,
    currency_from integer,
    currency_to integer,
    amomunt money NOT NULL
);


ALTER TABLE public.exchange_history OWNER TO iteratia;

--
-- Name: exchange_history_id_seq; Type: SEQUENCE; Schema: public; Owner: iteratia
--

CREATE SEQUENCE public.exchange_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exchange_history_id_seq OWNER TO iteratia;

--
-- Name: exchange_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: iteratia
--

ALTER SEQUENCE public.exchange_history_id_seq OWNED BY public.exchange_history.id;


--
-- Name: exchange_rate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.exchange_rate (
    id integer NOT NULL,
    currency_id integer NOT NULL,
    nominal integer NOT NULL
);


ALTER TABLE public.exchange_rate OWNER TO postgres;

--
-- Name: exchange_rate_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.exchange_rate_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exchange_rate_id_seq OWNER TO postgres;

--
-- Name: exchange_rate_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.exchange_rate_id_seq OWNED BY public.exchange_rate.id;


--
-- Name: last_update; Type: TABLE; Schema: public; Owner: iteratia
--

CREATE TABLE public.last_update (
    id smallint DEFAULT 1 NOT NULL,
    date date NOT NULL,
    CONSTRAINT last_update_id_check CHECK ((id = 1))
);


ALTER TABLE public.last_update OWNER TO iteratia;

--
-- Name: currency id; Type: DEFAULT; Schema: public; Owner: iteratia
--

ALTER TABLE ONLY public.currency ALTER COLUMN id SET DEFAULT nextval('public.currency_id_seq'::regclass);


--
-- Name: exchange_history id; Type: DEFAULT; Schema: public; Owner: iteratia
--

ALTER TABLE ONLY public.exchange_history ALTER COLUMN id SET DEFAULT nextval('public.exchange_history_id_seq'::regclass);


--
-- Name: exchange_rate id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exchange_rate ALTER COLUMN id SET DEFAULT nextval('public.exchange_rate_id_seq'::regclass);


--
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: iteratia
--



--
-- Data for Name: exchange_history; Type: TABLE DATA; Schema: public; Owner: iteratia
--



--
-- Data for Name: exchange_rate; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: last_update; Type: TABLE DATA; Schema: public; Owner: iteratia
--



--
-- Name: currency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: iteratia
--

SELECT pg_catalog.setval('public.currency_id_seq', 1, false);


--
-- Name: exchange_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: iteratia
--

SELECT pg_catalog.setval('public.exchange_history_id_seq', 1, false);


--
-- Name: exchange_rate_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.exchange_rate_id_seq', 1, false);


--
-- Name: currency currency_pkey; Type: CONSTRAINT; Schema: public; Owner: iteratia
--

ALTER TABLE ONLY public.currency
    ADD CONSTRAINT currency_pkey PRIMARY KEY (id);


--
-- Name: exchange_history exchange_history_pkey; Type: CONSTRAINT; Schema: public; Owner: iteratia
--

ALTER TABLE ONLY public.exchange_history
    ADD CONSTRAINT exchange_history_pkey PRIMARY KEY (id);


--
-- Name: exchange_rate exchange_rate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exchange_rate
    ADD CONSTRAINT exchange_rate_pkey PRIMARY KEY (id);


--
-- Name: last_update last_update_pkey; Type: CONSTRAINT; Schema: public; Owner: iteratia
--

ALTER TABLE ONLY public.last_update
    ADD CONSTRAINT last_update_pkey PRIMARY KEY (id);


--
-- Name: exchange_history exchange_history_currency_from_fkey; Type: FK CONSTRAINT; Schema: public; Owner: iteratia
--

ALTER TABLE ONLY public.exchange_history
    ADD CONSTRAINT exchange_history_currency_from_fkey FOREIGN KEY (currency_from) REFERENCES public.currency(id);


--
-- Name: exchange_history exchange_history_currency_to_fkey; Type: FK CONSTRAINT; Schema: public; Owner: iteratia
--

ALTER TABLE ONLY public.exchange_history
    ADD CONSTRAINT exchange_history_currency_to_fkey FOREIGN KEY (currency_to) REFERENCES public.currency(id);


--
-- Name: exchange_rate exchange_rate_currency_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.exchange_rate
    ADD CONSTRAINT exchange_rate_currency_id_fkey FOREIGN KEY (currency_id) REFERENCES public.currency(id);


--
-- PostgreSQL database dump complete
--

