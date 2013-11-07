ALTER TABLE APLICACION DROP CONSTRAINT FK_APLICACION_D_ID
ALTER TABLE CANAL DROP CONSTRAINT FK_CANAL_APP_ID
ALTER TABLE PEDIDOMSJ DROP CONSTRAINT FK_PEDIDOMSJ_CANAL_ID
ALTER TABLE PEDIDOUSER DROP CONSTRAINT FK_PEDIDOUSER_USUARIO_ID
ALTER TABLE ROL DROP CONSTRAINT FK_ROL_APLICACION_ID
ALTER TABLE USUARIO DROP CONSTRAINT FK_USUARIO_CANAL_ID
ALTER TABLE USUARIO DROP CONSTRAINT FK_USUARIO_APLICACION_ID
ALTER TABLE APLICACION_PEDIDOJSON DROP CONSTRAINT FK_APLICACION_PEDIDOJSON_Aplicacion_ID
ALTER TABLE APLICACION_PEDIDOJSON DROP CONSTRAINT FK_APLICACION_PEDIDOJSON_pedidosJson_ID
ALTER TABLE APLICACION_PEDIDOMSJ DROP CONSTRAINT FK_APLICACION_PEDIDOMSJ_pedidosMsj_ID
ALTER TABLE APLICACION_PEDIDOMSJ DROP CONSTRAINT FK_APLICACION_PEDIDOMSJ_Aplicacion_ID
ALTER TABLE APLICACION_PEDIDOPUSH DROP CONSTRAINT FK_APLICACION_PEDIDOPUSH_Aplicacion_ID
ALTER TABLE APLICACION_PEDIDOPUSH DROP CONSTRAINT FK_APLICACION_PEDIDOPUSH_pedidosPush_ID
ALTER TABLE APLICACION_PEDIDOUSER DROP CONSTRAINT FK_APLICACION_PEDIDOUSER_Aplicacion_ID
ALTER TABLE APLICACION_PEDIDOUSER DROP CONSTRAINT FK_APLICACION_PEDIDOUSER_pedidosUM_ID
ALTER TABLE APLICACION_PERMISO DROP CONSTRAINT FK_APLICACION_PERMISO_Aplicacion_ID
ALTER TABLE APLICACION_PERMISO DROP CONSTRAINT FK_APLICACION_PERMISO_permisos_ID
ALTER TABLE CANAL_PEDIDOMSJ DROP CONSTRAINT FK_CANAL_PEDIDOMSJ_pedidosMsj_ID
ALTER TABLE CANAL_PEDIDOMSJ DROP CONSTRAINT FK_CANAL_PEDIDOMSJ_Canal_ID
ALTER TABLE REGISTRO_CANAL DROP CONSTRAINT FK_REGISTRO_CANAL_registrados_ID
ALTER TABLE REGISTRO_CANAL DROP CONSTRAINT FK_REGISTRO_CANAL_canales_ID
ALTER TABLE ROL_PERMISO DROP CONSTRAINT FK_ROL_PERMISO_Rol_ID
ALTER TABLE ROL_PERMISO DROP CONSTRAINT FK_ROL_PERMISO_perms_ID
ALTER TABLE USUARIO_ROL DROP CONSTRAINT FK_USUARIO_ROL_roles_ID
ALTER TABLE USUARIO_ROL DROP CONSTRAINT FK_USUARIO_ROL_Usuario_ID
DROP TABLE APLICACION CASCADE
DROP TABLE CANAL CASCADE
DROP TABLE DESARROLLADOR CASCADE
DROP TABLE PEDIDOJSON CASCADE
DROP TABLE PEDIDOMSJ CASCADE
DROP TABLE PEDIDOPUSH CASCADE
DROP TABLE PEDIDOUSER CASCADE
DROP TABLE PERMISO CASCADE
DROP TABLE REGISTRO CASCADE
DROP TABLE ROL CASCADE
DROP TABLE USUARIO CASCADE
DROP TABLE APLICACION_PEDIDOJSON CASCADE
DROP TABLE APLICACION_PEDIDOMSJ CASCADE
DROP TABLE APLICACION_PEDIDOPUSH CASCADE
DROP TABLE APLICACION_PEDIDOUSER CASCADE
DROP TABLE APLICACION_PERMISO CASCADE
DROP TABLE CANAL_PEDIDOMSJ CASCADE
DROP TABLE REGISTRO_CANAL CASCADE
DROP TABLE ROL_PERMISO CASCADE
DROP TABLE USUARIO_ROL CASCADE
DROP SEQUENCE registro_id_seq
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'
DROP SEQUENCE perm_id_seq
DROP SEQUENCE des_id_seq
DROP SEQUENCE app_id_seq
DROP SEQUENCE canal_id_seq
DROP SEQUENCE rol_id_seq
DROP SEQUENCE pedidojson_id_seq
DROP SEQUENCE pedidopush_id_seq
DROP SEQUENCE pedidouser_id_seq
DROP SEQUENCE user_id_seq
DROP SEQUENCE pedidomsj_id_seq
