--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3

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

--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

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

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: Homework; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA "Homework";


ALTER SCHEMA "Homework" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: companies; Type: TABLE; Schema: Homework; Owner: postgres
--

CREATE TABLE "Homework".companies (
    id bigint NOT NULL,
    title character varying(255)
);


ALTER TABLE "Homework".companies OWNER TO postgres;

--
-- Name: companies_id_seq; Type: SEQUENCE; Schema: Homework; Owner: postgres
--

CREATE SEQUENCE "Homework".companies_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Homework".companies_id_seq OWNER TO postgres;

--
-- Name: companies_id_seq; Type: SEQUENCE OWNED BY; Schema: Homework; Owner: postgres
--

ALTER SEQUENCE "Homework".companies_id_seq OWNED BY "Homework".companies.id;


--
-- Name: customers; Type: TABLE; Schema: Homework; Owner: postgres
--

CREATE TABLE "Homework".customers (
    customer_id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE "Homework".customers OWNER TO postgres;

--
-- Name: customers_customer_id_seq; Type: SEQUENCE; Schema: Homework; Owner: postgres
--

CREATE SEQUENCE "Homework".customers_customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Homework".customers_customer_id_seq OWNER TO postgres;

--
-- Name: customers_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: Homework; Owner: postgres
--

ALTER SEQUENCE "Homework".customers_customer_id_seq OWNED BY "Homework".customers.customer_id;


--
-- Name: orders; Type: TABLE; Schema: Homework; Owner: postgres
--

CREATE TABLE "Homework".orders (
    order_id bigint NOT NULL,
    date date NOT NULL,
    customer_id bigint NOT NULL
);


ALTER TABLE "Homework".orders OWNER TO postgres;

--
-- Name: orders_details; Type: TABLE; Schema: Homework; Owner: postgres
--

CREATE TABLE "Homework".orders_details (
    product_id bigint NOT NULL,
    quantity integer DEFAULT 0,
    price_on_order_date numeric(19,6),
    order_id bigint NOT NULL
);


ALTER TABLE "Homework".orders_details OWNER TO postgres;

--
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: Homework; Owner: postgres
--

CREATE SEQUENCE "Homework".orders_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Homework".orders_order_id_seq OWNER TO postgres;

--
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: Homework; Owner: postgres
--

ALTER SEQUENCE "Homework".orders_order_id_seq OWNED BY "Homework".orders.order_id;


--
-- Name: products; Type: TABLE; Schema: Homework; Owner: postgres
--

CREATE TABLE "Homework".products (
    product_id bigint NOT NULL,
    title character varying(255) NOT NULL,
    cost numeric(19,6) NOT NULL,
    producer character varying(255)
);


ALTER TABLE "Homework".products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: Homework; Owner: postgres
--

CREATE SEQUENCE "Homework".products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Homework".products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: Homework; Owner: postgres
--

ALTER SEQUENCE "Homework".products_id_seq OWNED BY "Homework".products.product_id;


--
-- Name: companies id; Type: DEFAULT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".companies ALTER COLUMN id SET DEFAULT nextval('"Homework".companies_id_seq'::regclass);


--
-- Name: customers customer_id; Type: DEFAULT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".customers ALTER COLUMN customer_id SET DEFAULT nextval('"Homework".customers_customer_id_seq'::regclass);


--
-- Name: orders order_id; Type: DEFAULT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".orders ALTER COLUMN order_id SET DEFAULT nextval('"Homework".orders_order_id_seq'::regclass);


--
-- Name: products product_id; Type: DEFAULT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".products ALTER COLUMN product_id SET DEFAULT nextval('"Homework".products_id_seq'::regclass);


--
-- Data for Name: companies; Type: TABLE DATA; Schema: Homework; Owner: postgres
--

INSERT INTO "Homework".companies (id, title) VALUES (1, 'Company1');
INSERT INTO "Homework".companies (id, title) VALUES (2, 'Company2');
INSERT INTO "Homework".companies (id, title) VALUES (3, 'Company3');


--
-- Data for Name: customers; Type: TABLE DATA; Schema: Homework; Owner: postgres
--

INSERT INTO "Homework".customers (customer_id, name) VALUES (1, 'Vasya Pupkin');
INSERT INTO "Homework".customers (customer_id, name) VALUES (2, 'Petr Petrovich');
INSERT INTO "Homework".customers (customer_id, name) VALUES (3, 'Billy Boy');
INSERT INTO "Homework".customers (customer_id, name) VALUES (4, 'Test Testovich');
INSERT INTO "Homework".customers (customer_id, name) VALUES (7, 'Tolic Catolic');


--
-- Data for Name: orders; Type: TABLE DATA; Schema: Homework; Owner: postgres
--

INSERT INTO "Homework".orders (order_id, date, customer_id) VALUES (1, '2021-09-05', 1);
INSERT INTO "Homework".orders (order_id, date, customer_id) VALUES (2, '2021-09-03', 2);


--
-- Data for Name: orders_details; Type: TABLE DATA; Schema: Homework; Owner: postgres
--

INSERT INTO "Homework".orders_details (product_id, quantity, price_on_order_date, order_id) VALUES (75, 6, 3.000000, 1);
INSERT INTO "Homework".orders_details (product_id, quantity, price_on_order_date, order_id) VALUES (74, 2, 1.500000, 1);
INSERT INTO "Homework".orders_details (product_id, quantity, price_on_order_date, order_id) VALUES (72, 5, 10.000000, 2);
INSERT INTO "Homework".orders_details (product_id, quantity, price_on_order_date, order_id) VALUES (73, 4, 15.000000, 2);


--
-- Data for Name: products; Type: TABLE DATA; Schema: Homework; Owner: postgres
--

INSERT INTO "Homework".products (product_id, title, cost, producer) VALUES (72, 'Apples', 10.000000, 'Company1');
INSERT INTO "Homework".products (product_id, title, cost, producer) VALUES (73, 'Oranges', 15.000000, 'Company2');
INSERT INTO "Homework".products (product_id, title, cost, producer) VALUES (74, 'Milk', 1.500000, 'Company2');
INSERT INTO "Homework".products (product_id, title, cost, producer) VALUES (75, 'Beer', 3.000000, 'Company2');
INSERT INTO "Homework".products (product_id, title, cost, producer) VALUES (76, 'iPhone', 1000.000000, 'Company3');


--
-- Name: companies_id_seq; Type: SEQUENCE SET; Schema: Homework; Owner: postgres
--

SELECT pg_catalog.setval('"Homework".companies_id_seq', 3, true);


--
-- Name: customers_customer_id_seq; Type: SEQUENCE SET; Schema: Homework; Owner: postgres
--

SELECT pg_catalog.setval('"Homework".customers_customer_id_seq', 9, true);


--
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: Homework; Owner: postgres
--

SELECT pg_catalog.setval('"Homework".orders_order_id_seq', 2, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: Homework; Owner: postgres
--

SELECT pg_catalog.setval('"Homework".products_id_seq', 76, true);


--
-- Name: companies companies_pk; Type: CONSTRAINT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".companies
    ADD CONSTRAINT companies_pk PRIMARY KEY (id);


--
-- Name: customers customers_pk; Type: CONSTRAINT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".customers
    ADD CONSTRAINT customers_pk PRIMARY KEY (customer_id);


--
-- Name: orders_details orders_details_pk; Type: CONSTRAINT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".orders_details
    ADD CONSTRAINT orders_details_pk PRIMARY KEY (product_id, order_id);


--
-- Name: orders orders_pk; Type: CONSTRAINT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (order_id);


--
-- Name: products products_pk; Type: CONSTRAINT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".products
    ADD CONSTRAINT products_pk PRIMARY KEY (product_id);


--
-- Name: companies_title_uindex; Type: INDEX; Schema: Homework; Owner: postgres
--

CREATE UNIQUE INDEX companies_title_uindex ON "Homework".companies USING btree (title);


--
-- Name: orders orders_customers_customer_id_fk; Type: FK CONSTRAINT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".orders
    ADD CONSTRAINT orders_customers_customer_id_fk FOREIGN KEY (customer_id) REFERENCES "Homework".customers(customer_id);


--
-- Name: orders_details orders_details_orders_order_id_fk; Type: FK CONSTRAINT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".orders_details
    ADD CONSTRAINT orders_details_orders_order_id_fk FOREIGN KEY (order_id) REFERENCES "Homework".orders(order_id);


--
-- Name: orders_details orders_details_products_product_id_fk; Type: FK CONSTRAINT; Schema: Homework; Owner: postgres
--

ALTER TABLE ONLY "Homework".orders_details
    ADD CONSTRAINT orders_details_products_product_id_fk FOREIGN KEY (product_id) REFERENCES "Homework".products(product_id);


--
-- PostgreSQL database dump complete
--

