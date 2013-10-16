CREATE TABLE APLICACION (id_aplicacion BIGINT NOT NULL, DESCRIPCION VARCHAR(255), NOMBRE VARCHAR(255), PRIMARY KEY (id_aplicacion))
CREATE TABLE DESARROLLADOR (id_desarrollador BIGINT NOT NULL, APELLIDO VARCHAR(255), NICK VARCHAR(255), NOMBRE VARCHAR(255), PASS VARCHAR(255), PRIMARY KEY (id_desarrollador))
CREATE TABLE IDENTIFICADOR (NAME VARCHAR(255) NOT NULL, VALUE BIGINT, PRIMARY KEY (NAME))
CREATE TABLE USUARIO (id_usuario BIGINT NOT NULL, APELLIDO VARCHAR(255), NICK VARCHAR(255), NOMBRE VARCHAR(255), PASS VARCHAR(255), PRIMARY KEY (id_usuario))
ALTER TABLE APLICACION ADD CONSTRAINT FK_APLICACION_id_aplicacion FOREIGN KEY (id_aplicacion) REFERENCES DESARROLLADOR (id_desarrollador)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
