-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2017 a las 00:44:19
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemacontable`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `IdCuenta` int(3) NOT NULL,
  `Cuenta` varchar(250) NOT NULL,
  `IdEstadoFinanciero` int(2) NOT NULL,
  `IdTipoCuenta` int(2) DEFAULT NULL,
  `IdTipoSubCuenta` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentasxempresa`
--

CREATE TABLE `cuentasxempresa` (
  `Fecha` date NOT NULL,
  `IdEmpresa` int(4) NOT NULL,
  `IdCuenta` int(3) NOT NULL,
  `Valor` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `IdEmpresa` int(4) NOT NULL,
  `Usuario` varchar(50) NOT NULL,
  `Empresa` varchar(300) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `financieros`
--

CREATE TABLE `financieros` (
  `IdEstadoFinanciero` int(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Volcado de datos para la tabla `financieros`
--

INSERT INTO `financieros` (`IdEstadoFinanciero`, `Nombre`) VALUES
(1, 'BALANCE GENERAL'),
(2, 'ESTADO DE RESULTADOS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subtipocuenta`
--

CREATE TABLE `subtipocuenta` (
  `IdTipoSubCuenta` int(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `subtipocuenta`
--

INSERT INTO `subtipocuenta` (`IdTipoSubCuenta`, `Nombre`) VALUES
(1, 'CORRIENTE'),
(2, 'NO CORRIENTE'),
(3, 'CAPITAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocuenta`
--

CREATE TABLE `tipocuenta` (
  `IdTipoCuenta` int(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipocuenta`
--

INSERT INTO `tipocuenta` (`IdTipoCuenta`, `Nombre`) VALUES
(1, 'ACTIVO'),
(2, 'PASIVO'),
(3, 'PATRIMONIO');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`IdCuenta`),
  ADD KEY `IdEstadoFinanciero` (`IdEstadoFinanciero`),
  ADD KEY `IdTipoCuenta` (`IdTipoCuenta`),
  ADD KEY `IdTipoSubCuenta` (`IdTipoSubCuenta`);

--
-- Indices de la tabla `cuentasxempresa`
--
ALTER TABLE `cuentasxempresa`
  ADD PRIMARY KEY (`IdEmpresa`,`Fecha`,`IdCuenta`),
  ADD KEY `IdCuenta` (`IdCuenta`),
  ADD KEY `IdEmpresa` (`IdEmpresa`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`IdEmpresa`);

--
-- Indices de la tabla `financieros`
--
ALTER TABLE `financieros`
  ADD PRIMARY KEY (`IdEstadoFinanciero`);

--
-- Indices de la tabla `subtipocuenta`
--
ALTER TABLE `subtipocuenta`
  ADD PRIMARY KEY (`IdTipoSubCuenta`);

--
-- Indices de la tabla `tipocuenta`
--
ALTER TABLE `tipocuenta`
  ADD PRIMARY KEY (`IdTipoCuenta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `IdCuenta` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `IdEmpresa` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `financieros`
--
ALTER TABLE `financieros`
  MODIFY `IdEstadoFinanciero` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`IdTipoCuenta`) REFERENCES `tipocuenta` (`IdTipoCuenta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuentas_ibfk_2` FOREIGN KEY (`IdEstadoFinanciero`) REFERENCES `financieros` (`IdEstadoFinanciero`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuentas_ibfk_3` FOREIGN KEY (`IdTipoSubCuenta`) REFERENCES `subtipocuenta` (`IdTipoSubCuenta`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
