ALTER TABLE APLICACION DROP CONSTRAINT FK_APLICACION_id_aplicacion
DROP TABLE APLICACION CASCADE
DROP TABLE DESARROLLADOR CASCADE
DROP TABLE IDENTIFICADOR CASCADE
DROP TABLE USUARIO CASCADE
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
