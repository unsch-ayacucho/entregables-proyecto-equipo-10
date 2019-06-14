/* ---------------------------------------------------- */
/*  Created On : 14-Jun.-2019 14:40:47 				*/
/*  DBMS       : MySql 						*/
/* ---------------------------------------------------- */

SET FOREIGN_KEY_CHECKS=0 
;

/* Drop Tables */

DROP TABLE IF EXISTS `Calendario` CASCADE
;

DROP TABLE IF EXISTS `CalendarioDetalle` CASCADE
;

DROP TABLE IF EXISTS `Categoria` CASCADE
;

DROP TABLE IF EXISTS `Comision` CASCADE
;

DROP TABLE IF EXISTS `ComisionMiembro` CASCADE
;

DROP TABLE IF EXISTS `ConsejoFacultad` CASCADE
;

DROP TABLE IF EXISTS `ConsejoUniversitario` CASCADE
;

DROP TABLE IF EXISTS `Docente` CASCADE
;

DROP TABLE IF EXISTS `Informe` CASCADE
;

DROP TABLE IF EXISTS `InformeCF` CASCADE
;

DROP TABLE IF EXISTS `InformeCU` CASCADE
;

DROP TABLE IF EXISTS `InformeDetalle` CASCADE
;

DROP TABLE IF EXISTS `JefeDepartamento` CASCADE
;

DROP TABLE IF EXISTS `Persona` CASCADE
;

DROP TABLE IF EXISTS `Promocion` CASCADE
;

DROP TABLE IF EXISTS `Resolucion` CASCADE
;

DROP TABLE IF EXISTS `TablaEv` CASCADE
;

DROP TABLE IF EXISTS `TablaEvDetalle` CASCADE
;

/* Create Tables */

CREATE TABLE `Calendario`
(
	`idcalendario` BIGINT NOT NULL,
	`proceso` VARCHAR(50) NULL,
	CONSTRAINT `PK_Calendario` PRIMARY KEY (`idcalendario` ASC)
)

;

CREATE TABLE `CalendarioDetalle`
(
	`idcalendariodetalle` BIGINT NOT NULL,
	`idcalendario` BIGINT NULL,
	`actividad` VARCHAR(50) NOT NULL,
	`descripcion` VARCHAR(255) NOT NULL,
	CONSTRAINT `PK_CalendarioDetalle` PRIMARY KEY (`idcalendariodetalle` ASC)
)

;

CREATE TABLE `Categoria`
(
	`idcategoria` BIGINT NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	CONSTRAINT `PK_Table2` PRIMARY KEY (`idcategoria` ASC)
)

;

CREATE TABLE `Comision`
(
	`idcomision` BIGINT NOT NULL,
	`idjefedepartamento` BIGINT NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	`razon` VARCHAR(50) NOT NULL,
	`fecha_creacion` DATETIME NOT NULL,
	CONSTRAINT `PK_Comision` PRIMARY KEY (`idcomision` ASC)
)

;

CREATE TABLE `ComisionMiembro`
(
	`idcomisionmiembro` BIGINT NOT NULL,
	`iddocente` BIGINT NOT NULL,
	`idcomision` BIGINT NOT NULL,
	CONSTRAINT `PK_Table1` PRIMARY KEY (`idcomisionmiembro` ASC)
)

;

CREATE TABLE `ConsejoFacultad`
(
	`idconsejofacultad` BIGINT NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	`fecha_creacion` DATETIME NOT NULL,
	CONSTRAINT `PK_ConsejoFacultad` PRIMARY KEY (`idconsejofacultad` ASC)
)

;

CREATE TABLE `ConsejoUniversitario`
(
	`idconsejouniversitario` BIGINT NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	`fecha_creacion` DATETIME NOT NULL,
	CONSTRAINT `PK_ConsejoUniversitario` PRIMARY KEY (`idconsejouniversitario` ASC)
)

;

CREATE TABLE `Docente`
(
	`iddocente` BIGINT NOT NULL,
	`nombres` VARCHAR(50) NOT NULL,
	`apepaterno` VARCHAR(50) NOT NULL,
	`apematerno` VARCHAR(50) NOT NULL,
	`nrodoc` VARCHAR(8) NOT NULL,
	`cargo` VARCHAR(50) NULL,
	CONSTRAINT `PK_Docente` PRIMARY KEY (`iddocente` ASC)
)

;

CREATE TABLE `Informe`
(
	`idinforme` BIGINT NOT NULL,
	`idpromocion` BIGINT NOT NULL,
	`idcomision` BIGINT NOT NULL,
	`idtablaev` BIGINT NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	`fecha_creacion` DATETIME NOT NULL,
	CONSTRAINT `PK_Table1` PRIMARY KEY (`idinforme` ASC)
)

;

CREATE TABLE `InformeCF`
(
	`idinformecf` BIGINT NOT NULL,
	`idconsejofacultad` BIGINT NOT NULL,
	`idinforme` BIGINT NOT NULL,
	`aprobado` BOOL NOT NULL,
	`detalle` TEXT NOT NULL,
	CONSTRAINT `PK_InformeCF` PRIMARY KEY (`idinformecf` ASC)
)

;

CREATE TABLE `InformeCU`
(
	`idinformecu` BIGINT NOT NULL,
	`idinforme` BIGINT NOT NULL,
	`aprobado` BOOL NOT NULL,
	`idconsejouniversitario` BIGINT NOT NULL,
	`detalle` TEXT NOT NULL,
	CONSTRAINT `PK_Table1` PRIMARY KEY (`idinformecu` ASC)
)

;

CREATE TABLE `InformeDetalle`
(
	`idinformedetalle` BIGINT NOT NULL,
	`idinforme` BIGINT NOT NULL,
	`idtablaevdetalle` BIGINT NOT NULL,
	`puntaje` SMALLINT NOT NULL,
	CONSTRAINT `PK_Table1` PRIMARY KEY (`idinformedetalle` ASC)
)

;

CREATE TABLE `JefeDepartamento`
(
	`idjefedepartamento` BIGINT NOT NULL,
	`nombres` VARCHAR(50) NOT NULL,
	`apepaterno` VARCHAR(50) NOT NULL,
	`apematerno` VARCHAR(50) NOT NULL,
	`nrodoc` VARCHAR(8) NOT NULL,
	`fecha_designo` DATETIME NOT NULL,
	CONSTRAINT `PK_JefeDepartamento` PRIMARY KEY (`idjefedepartamento` ASC)
)

;

CREATE TABLE `Promocion`
(
	`idpromocion` BIGINT NOT NULL,
	`iddocente` BIGINT NOT NULL,
	`idcategoria` BIGINT NOT NULL,
	`solicitud` VARCHAR(50) NOT NULL,
	`expediente` VARCHAR(50) NOT NULL,
	`fecha_solicitud` DATETIME NOT NULL,
	CONSTRAINT `PK_Promocion` PRIMARY KEY (`idpromocion` ASC)
)

;

CREATE TABLE `Resolucion`
(
	`idresolucion` BIGINT NOT NULL,
	`nro` VARCHAR(50) NOT NULL,
	`resumen` VARCHAR(50) NOT NULL,
	`contenido` TEXT NOT NULL,
	`idconsejouniversitario` BIGINT NULL,
	CONSTRAINT `PK_Resolucion` PRIMARY KEY (`idresolucion` ASC)
)

;

CREATE TABLE `TablaEv`
(
	`idtablaev` BIGINT NOT NULL,
	CONSTRAINT `PK_TablaEvaluacion` PRIMARY KEY (`idtablaev` ASC)
)

;

CREATE TABLE `TablaEvDetalle`
(
	`idtablaevdetalle` BIGINT NOT NULL,
	`idtablaev` BIGINT NOT NULL,
	`indicador` VARCHAR(50) NOT NULL,
	`descripcion` VARCHAR(255) NOT NULL,
	`puntaje` SMALLINT NOT NULL,
	CONSTRAINT `PK_Table2` PRIMARY KEY (`idtablaevdetalle` ASC)
)

;

/* Create Primary Keys, Indexes, Uniques, Checks */

ALTER TABLE `CalendarioDetalle` 
 ADD INDEX `IXFK_CalendarioDetalle_Calendario` (`idcalendario` ASC)
;

ALTER TABLE `Comision` 
 ADD INDEX `IXFK_Comision_JefeDepartamento` (`idjefedepartamento` ASC)
;

ALTER TABLE `ComisionMiembro` 
 ADD INDEX `IXFK_ComisionMiembro_Comision` (`idcomision` ASC)
;

ALTER TABLE `ComisionMiembro` 
 ADD INDEX `IXFK_ComisionMiembro_Docente` (`iddocente` ASC)
;

ALTER TABLE `Informe` 
 ADD INDEX `IXFK_Informe_Comision` (`idcomision` ASC)
;

ALTER TABLE `Informe` 
 ADD INDEX `IXFK_Informe_Promocion` (`idpromocion` ASC)
;

ALTER TABLE `Informe` 
 ADD INDEX `IXFK_Informe_TablaEv` (`idtablaev` ASC)
;

ALTER TABLE `InformeCF` 
 ADD INDEX `IXFK_InformeCF_ConsejoFacultad` (`idconsejofacultad` ASC)
;

ALTER TABLE `InformeCF` 
 ADD INDEX `IXFK_InformeCF_Informe` (`idinforme` ASC)
;

ALTER TABLE `InformeCU` 
 ADD INDEX `IXFK_InformeCU_ConsejoUniversitario` (`idconsejouniversitario` ASC)
;

ALTER TABLE `InformeCU` 
 ADD INDEX `IXFK_InformeCU_Informe` (`idinforme` ASC)
;

ALTER TABLE `InformeDetalle` 
 ADD INDEX `IXFK_InformeDetalle_Informe` (`idinforme` ASC)
;

ALTER TABLE `InformeDetalle` 
 ADD INDEX `IXFK_InformeDetalle_TablaEvDetalle` (`idtablaevdetalle` ASC)
;

ALTER TABLE `Promocion` 
 ADD INDEX `IXFK_Promocion_Cargo` (`idcategoria` ASC)
;

ALTER TABLE `Promocion` 
 ADD INDEX `IXFK_Promocion_Docente` (`iddocente` ASC)
;

ALTER TABLE `Resolucion` 
 ADD INDEX `IXFK_Resolucion_ConsejoUniversitario` (`idconsejouniversitario` ASC)
;

ALTER TABLE `TablaEvDetalle` 
 ADD INDEX `IXFK_TablaEvDetalle_TablaEv` (`idtablaev` ASC)
;

/* Create Foreign Key Constraints */

ALTER TABLE `CalendarioDetalle` 
 ADD CONSTRAINT `FK_CalendarioDetalle_Calendario`
	FOREIGN KEY (`idcalendario`) REFERENCES `Calendario` (`idcalendario`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Comision` 
 ADD CONSTRAINT `FK_Comision_JefeDepartamento`
	FOREIGN KEY (`idjefedepartamento`) REFERENCES `JefeDepartamento` (`idjefedepartamento`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `ComisionMiembro` 
 ADD CONSTRAINT `FK_ComisionMiembro_Comision`
	FOREIGN KEY (`idcomision`) REFERENCES `Comision` (`idcomision`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `ComisionMiembro` 
 ADD CONSTRAINT `FK_ComisionMiembro_Docente`
	FOREIGN KEY (`iddocente`) REFERENCES `Docente` (`iddocente`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Informe` 
 ADD CONSTRAINT `FK_Informe_Comision`
	FOREIGN KEY (`idcomision`) REFERENCES `Comision` (`idcomision`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Informe` 
 ADD CONSTRAINT `FK_Informe_Promocion`
	FOREIGN KEY (`idpromocion`) REFERENCES `Promocion` (`idpromocion`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Informe` 
 ADD CONSTRAINT `FK_Informe_TablaEv`
	FOREIGN KEY (`idtablaev`) REFERENCES `TablaEv` (`idtablaev`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `InformeCF` 
 ADD CONSTRAINT `FK_InformeCF_ConsejoFacultad`
	FOREIGN KEY (`idconsejofacultad`) REFERENCES `ConsejoFacultad` (`idconsejofacultad`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `InformeCF` 
 ADD CONSTRAINT `FK_InformeCF_Informe`
	FOREIGN KEY (`idinforme`) REFERENCES `Informe` (`idinforme`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `InformeCU` 
 ADD CONSTRAINT `FK_InformeCU_ConsejoUniversitario`
	FOREIGN KEY (`idconsejouniversitario`) REFERENCES `ConsejoUniversitario` (`idconsejouniversitario`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `InformeCU` 
 ADD CONSTRAINT `FK_InformeCU_Informe`
	FOREIGN KEY (`idinforme`) REFERENCES `Informe` (`idinforme`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `InformeDetalle` 
 ADD CONSTRAINT `FK_InformeDetalle_Informe`
	FOREIGN KEY (`idinforme`) REFERENCES `Informe` (`idinforme`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `InformeDetalle` 
 ADD CONSTRAINT `FK_InformeDetalle_TablaEvDetalle`
	FOREIGN KEY (`idtablaevdetalle`) REFERENCES `TablaEvDetalle` (`idtablaevdetalle`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Promocion` 
 ADD CONSTRAINT `FK_Promocion_Cargo`
	FOREIGN KEY (`idcategoria`) REFERENCES `Categoria` (`idcategoria`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Promocion` 
 ADD CONSTRAINT `FK_Promocion_Docente`
	FOREIGN KEY (`iddocente`) REFERENCES `Docente` (`iddocente`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `Resolucion` 
 ADD CONSTRAINT `FK_Resolucion_ConsejoUniversitario`
	FOREIGN KEY (`idconsejouniversitario`) REFERENCES `ConsejoUniversitario` (`idconsejouniversitario`) ON DELETE Restrict ON UPDATE Restrict
;

ALTER TABLE `TablaEvDetalle` 
 ADD CONSTRAINT `FK_TablaEvDetalle_TablaEv`
	FOREIGN KEY (`idtablaev`) REFERENCES `TablaEv` (`idtablaev`) ON DELETE Restrict ON UPDATE Restrict
;

SET FOREIGN_KEY_CHECKS=1 
;
