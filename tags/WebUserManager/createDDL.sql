CREATE TABLE USUARIO (id_usuario BIGINT NOT NULL, APELLIDO VARCHAR(255), NICK VARCHAR(255), NOMBRE VARCHAR(255), PASS VARCHAR(255), PRIMARY KEY (id_usuario))
CREATE TABLE DESARROLLADOR (id_desarrollador BIGINT NOT NULL, APELLIDO VARCHAR(255), NICK VARCHAR(255), NOMBRE VARCHAR(255), PASS VARCHAR(255), PRIMARY KEY (id_desarrollador))
CREATE TABLE APLICACION (id_aplicacion BIGINT NOT NULL, NOMBRE VARCHAR(255), id_persona BIGINT, PRIMARY KEY (id_aplicacion))
ALTER TABLE APLICACION ADD CONSTRAINT FK_APLICACION_id_persona FOREIGN KEY (id_persona) REFERENCES DESARROLLADOR (id_desarrollador)
ALTER TABLE APLICACION ADD CONSTRAINT FK_APLICACION_id_aplicacion FOREIGN KEY (id_aplicacion) REFERENCES USUARIO (id_usuario)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)