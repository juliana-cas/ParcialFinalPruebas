Instrucciones para ejecutar el proyecto:

Ejecutar las siguientes líneas en SQL shell (psql):


para acceder:
Server [localhost]: localhost
Database [postgres]: postgres
Port [5432]: 5432
Username [postgres]: postgres
Contraseña para usuario postgres: postgres

para crear la base de datos;
CREATE DATABASE inventory;
para acceder a la base de datos creada:
\c inventory

para crear la tabla de producto:
CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255),
    quantity INTEGER CHECK (quantity >= 0),
    price NUMERIC(10,2) CHECK (price >= 0.0)
);

para crear la tabla de venta:
CREATE TABLE sale (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    quantity_sold INTEGER NOT NULL CHECK (quantity_sold > 0),
    sale_date TIMESTAMP NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

para ver las tablas generadas:
\dt

Para probar las pruebas unitarias:
1. ejecutar ParcialFinalPruebasApplication.java
2. ejecutar ProductServiceTest.java

Para probar la prueba de integracion:
ejecutar ProductControllerIntegrationTest 
