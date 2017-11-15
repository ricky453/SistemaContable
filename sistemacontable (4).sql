-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 15-11-2017 a las 05:23:08
-- Versión del servidor: 10.1.28-MariaDB
-- Versión de PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `IdCuenta` int(3) NOT NULL,
  `Cuenta` varchar(250) NOT NULL,
  `IdEstadoFinanciero` int(2) NOT NULL,
  `IdTipoCuenta` int(2) DEFAULT NULL,
  `IdTipoSubCuenta` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`IdCuenta`, `Cuenta`, `IdEstadoFinanciero`, `IdTipoCuenta`, `IdTipoSubCuenta`) VALUES
(1, 'VENTAS', 2, NULL, NULL),
(2, 'EFECTIVO', 1, 1, 1),
(3, 'GASTOS DE COMPRA', 2, NULL, NULL),
(4, 'COMISIONES EN VENTA', 2, NULL, NULL),
(5, 'SUELDOS DE OFICINA', 2, NULL, NULL),
(6, 'SERVICIOS BASICOS', 2, NULL, NULL),
(7, 'DEVOLUCIONES SOBRE COMPRAS', 2, NULL, NULL),
(8, 'DESCUENTO SOBRE VENTA', 2, NULL, NULL),
(9, 'ALQUILER DE OFICINA', 2, NULL, NULL),
(10, 'OTROS INGRESOS', 2, NULL, NULL),
(11, 'INTERESES', 2, NULL, NULL),
(12, 'EDIFICIOS Y TERRENOS', 1, 1, 2),
(13, 'PATENTES', 1, 1, 2),
(14, 'DOCUMENTOS POR PAGAR', 1, 2, 1),
(15, 'UTILIDAD POR DISTRIBUIR', 2, NULL, NULL),
(16, 'RESERVA LEGAL', 2, NULL, NULL),
(17, 'CAPITAL SOCIAL', 1, 3, 3),
(18, 'MOBILIARIO Y EQUIPO', 1, 1, 2),
(19, 'CUENTAS POR COBRAR', 1, 1, 1),
(20, 'INVENTARIO INICIAL', 2, NULL, NULL),
(21, 'INVENTARIO FINAL', 2, NULL, NULL),
(22, 'INVENTARIO', 1, 1, 1),
(23, 'DEVOLUCIONES SOBRE VENTAS', 2, NULL, NULL),
(24, 'REBAJAS SOBRE COMPRAS', 2, NULL, NULL),
(25, 'ALQUILER SALA DE VENTAS', 2, NULL, NULL),
(26, 'SUELDO DEL GERENTE', 2, NULL, NULL),
(27, 'PUBLICIDAD', 2, NULL, NULL),
(28, 'OTROS GASTOS', 2, NULL, NULL),
(29, 'SUELDO DE VENDEDORES', 2, NULL, NULL),
(30, 'REBAJAS SOBRE VENTAS', 2, NULL, NULL),
(31, 'COMPRAS', 2, NULL, NULL),
(32, 'PRESTAMOS A LARGO PLAZO', 1, 2, 2),
(33, 'CUENTAS POR PAGAR', 1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentasanio`
--

CREATE TABLE `cuentasanio` (
  `Anio` varchar(4) NOT NULL,
  `IdEmpresa` int(4) NOT NULL,
  `IdCuenta` int(3) NOT NULL,
  `Valor` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuentasanio`
--

INSERT INTO `cuentasanio` (`Anio`, `IdEmpresa`, `IdCuenta`, `Valor`) VALUES
('2015', 1, 2, 100000),
('2015', 1, 12, 200000),
('2015', 1, 13, 25000),
('2015', 1, 14, 25000),
('2015', 1, 17, 275000),
('2015', 1, 18, 175000),
('2015', 1, 19, 50000),
('2015', 1, 22, 150000),
('2015', 1, 32, 100000),
('2015', 1, 33, 75000);

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

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`IdEmpresa`, `Usuario`, `Empresa`, `Password`) VALUES
(1, 'RICKY453', 'LA DESPENSA DE DON JUAN', '1234');

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
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`IdCuenta`),
  ADD KEY `IdEstadoFinanciero` (`IdEstadoFinanciero`),
  ADD KEY `IdTipoCuenta` (`IdTipoCuenta`),
  ADD KEY `IdTipoSubCuenta` (`IdTipoSubCuenta`);

--
-- Indices de la tabla `cuentasanio`
--
ALTER TABLE `cuentasanio`
  ADD PRIMARY KEY (`Anio`,`IdEmpresa`,`IdCuenta`),
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
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `IdCuenta` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

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
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`IdTipoCuenta`) REFERENCES `tipocuenta` (`IdTipoCuenta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`IdEstadoFinanciero`) REFERENCES `financieros` (`IdEstadoFinanciero`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuenta_ibfk_3` FOREIGN KEY (`IdTipoSubCuenta`) REFERENCES `subtipocuenta` (`IdTipoSubCuenta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `cuentasanio`
--
ALTER TABLE `cuentasanio`
  ADD CONSTRAINT `cuentasanio_ibfk_2` FOREIGN KEY (`IdCuenta`) REFERENCES `cuenta` (`IdCuenta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuentasanio_ibfk_3` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
