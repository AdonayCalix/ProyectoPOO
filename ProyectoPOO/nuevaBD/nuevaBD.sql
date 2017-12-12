-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-12-2017 a las 04:58:45
-- Versión del servidor: 10.1.19-MariaDB
-- Versión de PHP: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aula`
--

CREATE TABLE `aula` (
  `IdAula` char(4) NOT NULL,
  `edificio` char(1) DEFAULT NULL,
  `capacidad` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `IdTipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `aula`
--

INSERT INTO `aula` (`IdAula`, `edificio`, `capacidad`, `estado`, `IdTipo`) VALUES
('1', '1', 60, 1, 1),
('2202', '2', 30, 1, 1),
('2203', '2', 30, 1, 1),
('2204', '2', 30, 1, 1),
('2205', '2', 30, 1, 1),
('2206', '2', 30, 1, 1),
('2301', '2', 30, 1, 1),
('2302', '2', 30, 1, 1),
('2303', '2', 30, 1, 1),
('2304', '2', 30, 1, 1),
('2305', '2', 30, 1, 1),
('2306', '2', 30, 1, 1),
('2401', '2', 30, 1, 1),
('2402', '2', 30, 1, 1),
('2403', '2', 30, 1, 1),
('2406', '2', 30, 1, 1),
('3203', '3', 60, 1, 4),
('3204', '3', 60, 1, 1),
('3301', '3', 60, 1, 1),
('3302', '3', 60, 1, 1),
('3303', '3', 60, 1, 1),
('3401', '3', 60, 1, 1),
('3402', '3', 60, 1, 1),
('3403', '3', 60, 1, 1),
('3404', '3', 30, 1, 1),
('4302', '4', 20, 1, 12),
('4401', '4', 20, 1, 3),
('4402', '4', 20, 1, 3),
('4403', '4', 20, 1, 3),
('4501', '4', 50, 1, 1),
('4502', '4', 50, 1, 1),
('4503', '4', 40, 1, 1),
('HDH', '2', 10, 1, 5),
('LAB1', '1', 13, 1, 8),
('LAB2', '1', 25, 1, 8),
('LAB3', '1', 25, 1, 8),
('LAB4', '1', 11, 1, 8),
('LABB', '3', 30, 1, 15),
('LABF', '3', 30, 1, 2),
('LABI', '1', 28, 1, 7),
('LBC1', '4', 25, 1, 10),
('LBC2', '4', 25, 1, 10),
('LBN3', '3', 20, 1, 4),
('LBNE', '4', 25, 1, 4),
('LBQM', '3', 30, 1, 13),
('MAE1', '2', 30, 1, 6),
('MAE2', '2', 30, 1, 6),
('MAE3', '2', 30, 1, 6),
('MAE4', '3', 60, 1, 6),
('SCO', '2', 70, 1, 7),
('SJO', '3', 25, 1, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase`
--

CREATE TABLE `clase` (
  `IdClase` char(6) NOT NULL,
  `nomClase` char(50) DEFAULT NULL,
  `creditos` int(11) DEFAULT NULL,
  `IdTipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `periodo`
--

CREATE TABLE `periodo` (
  `IdPeriodo` char(1) NOT NULL,
  `año` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE `seccion` (
  `seccion` char(4) NOT NULL,
  `Nseccionesç` char(1) DEFAULT NULL,
  `nalumnos` int(11) DEFAULT NULL,
  `Lunes` binary(1) DEFAULT NULL,
  `IdAula` char(4) DEFAULT NULL,
  `IdPeriodo` char(1) DEFAULT NULL,
  `IdClase` char(6) DEFAULT NULL,
  `hora` char(2) DEFAULT NULL,
  `Martes` binary(1) DEFAULT NULL,
  `Miercoles` binary(1) DEFAULT NULL,
  `Jueves` binary(1) DEFAULT NULL,
  `Viernes` binary(1) DEFAULT NULL,
  `Sabado` binary(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_aula`
--

CREATE TABLE `tipo_aula` (
  `IdTipo` int(11) NOT NULL,
  `tipo` char(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_aula`
--

INSERT INTO `tipo_aula` (`IdTipo`, `tipo`) VALUES
(1, 'GENERAL'),
(2, 'LABFIS'),
(3, 'DIBUJO'),
(4, 'LABNEUMATICA'),
(5, 'HDH'),
(6, 'MAE'),
(7, 'SCO'),
(8, 'LABCOMP'),
(9, 'LABINGL'),
(10, 'LABCI'),
(11, 'LABIND'),
(12, 'MAQUETAS'),
(13, 'LABQMC'),
(14, 'SJO'),
(15, 'LABBIOLOGIA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aula`
--
ALTER TABLE `aula`
  ADD PRIMARY KEY (`IdAula`),
  ADD KEY `R_3` (`IdTipo`);

--
-- Indices de la tabla `clase`
--
ALTER TABLE `clase`
  ADD PRIMARY KEY (`IdClase`),
  ADD KEY `R_7` (`IdTipo`);

--
-- Indices de la tabla `periodo`
--
ALTER TABLE `periodo`
  ADD PRIMARY KEY (`IdPeriodo`);

--
-- Indices de la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD PRIMARY KEY (`seccion`),
  ADD KEY `R_4` (`IdAula`),
  ADD KEY `R_5` (`IdPeriodo`),
  ADD KEY `R_6` (`IdClase`);

--
-- Indices de la tabla `tipo_aula`
--
ALTER TABLE `tipo_aula`
  ADD PRIMARY KEY (`IdTipo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `aula`
--
ALTER TABLE `aula`
  ADD CONSTRAINT `R_3` FOREIGN KEY (`IdTipo`) REFERENCES `tipo_aula` (`IdTipo`);

--
-- Filtros para la tabla `clase`
--
ALTER TABLE `clase`
  ADD CONSTRAINT `R_7` FOREIGN KEY (`IdTipo`) REFERENCES `tipo_aula` (`IdTipo`);

--
-- Filtros para la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD CONSTRAINT `R_4` FOREIGN KEY (`IdAula`) REFERENCES `aula` (`IdAula`),
  ADD CONSTRAINT `R_5` FOREIGN KEY (`IdPeriodo`) REFERENCES `periodo` (`IdPeriodo`),
  ADD CONSTRAINT `R_6` FOREIGN KEY (`IdClase`) REFERENCES `clase` (`IdClase`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
