-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema proyecto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema proyecto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `proyecto` DEFAULT CHARACTER SET latin1 ;
USE `proyecto` ;

-- -----------------------------------------------------
-- Table `proyecto`.`tipo_aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`tipo_aula` (
  `IdTipo` INT(11) NOT NULL,
  `tipo` CHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`IdTipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `proyecto`.`aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`aula` (
  `IdAula` CHAR(4) NOT NULL,
  `edificio` CHAR(1) NULL DEFAULT NULL,
  `capacidad` INT(11) NULL DEFAULT NULL,
  `estado` TINYINT(1) NULL DEFAULT NULL,
  `IdTipo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`IdAula`),
  INDEX `R_3` (`IdTipo` ASC),
  CONSTRAINT `R_3`
    FOREIGN KEY (`IdTipo`)
    REFERENCES `proyecto`.`tipo_aula` (`IdTipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `proyecto`.`clase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`clase` (
  `IdClase` CHAR(6) NOT NULL,
  `nomClase` CHAR(60) NULL DEFAULT NULL,
  `creditos` INT(11) NULL DEFAULT NULL,
  `IdTipo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`IdClase`),
  INDEX `R_7` (`IdTipo` ASC),
  CONSTRAINT `R_7`
    FOREIGN KEY (`IdTipo`)
    REFERENCES `proyecto`.`tipo_aula` (`IdTipo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `proyecto`.`periodo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`periodo` (
  `IdPeriodo` CHAR(1) NOT NULL,
  `año` CHAR(4) NULL DEFAULT NULL,
  PRIMARY KEY (`IdPeriodo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `proyecto`.`seccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `proyecto`.`seccion` (
  `seccion` CHAR(4) NOT NULL,
  `NumeroSeccion` CHAR(1) NULL DEFAULT NULL,
  `nalumnos` INT(11) NULL DEFAULT NULL,
  `Lunes` BINARY(1) NULL DEFAULT NULL,
  `IdAula` CHAR(4) NULL DEFAULT NULL,
  `IdPeriodo` CHAR(1) NULL DEFAULT NULL,
  `IdClase` CHAR(6) NULL DEFAULT NULL,
  `horaInicio` CHAR(2) NULL DEFAULT NULL,
  `Martes` BINARY(1) NULL DEFAULT NULL,
  `Miercoles` BINARY(1) NULL DEFAULT NULL,
  `Jueves` BINARY(1) NULL DEFAULT NULL,
  `Viernes` BINARY(1) NULL DEFAULT NULL,
  `Sabado` BINARY(1) NULL DEFAULT NULL,
  `horaSalida` VARCHAR(4) NULL,
  INDEX `R_4` (`IdAula` ASC),
  INDEX `R_5` (`IdPeriodo` ASC),
  INDEX `R_6` (`IdClase` ASC),
  CONSTRAINT `R_4`
    FOREIGN KEY (`IdAula`)
    REFERENCES `proyecto`.`aula` (`IdAula`),
  CONSTRAINT `R_5`
    FOREIGN KEY (`IdPeriodo`)
    REFERENCES `proyecto`.`periodo` (`IdPeriodo`),
  CONSTRAINT `R_6`
    FOREIGN KEY (`IdClase`)
    REFERENCES `proyecto`.`clase` (`IdClase`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `proyecto`.`tipo_aula`
-- -----------------------------------------------------
START TRANSACTION;
USE `proyecto`;
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (1, 'GENERAL');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (2, 'LABORATORIO DE FÍSICA');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (3, 'LABORATORIO DE BIOLOGÍA');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (4, 'LABORATORIO DE QUÍMICA');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (5, 'DIBUJO');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (6, 'LABORATORIO DE COMPUTACIÓN');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (7, 'MAESTRIA');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (8, 'LABORATORIO DE HÍDRAULICA');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (9, 'MAQUETAS');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (10, 'LABORATORIO DE CIMENTACIONES');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (11, 'SALON DE JUICIOS ORALES');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (12, 'LABORATORIO INDUSTRIAL');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (13, 'SALON DE CONFERENCIAS');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (14, 'LABORATORIO DE NEUMÁTICA');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (15, 'LABORATORIO DE INGLES');
INSERT INTO `proyecto`.`tipo_aula` (`IdTipo`, `tipo`) VALUES (16, 'HARDWARE');

COMMIT;

