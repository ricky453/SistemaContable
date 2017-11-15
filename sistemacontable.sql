-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 15, 2017 at 01:52 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistemacontable`
--

-- --------------------------------------------------------

--
-- Table structure for table `cuenta`
--

CREATE TABLE `cuenta` (
  `IdCuenta` int(11) NOT NULL,
  `IdTipoCuenta` int(11) NOT NULL,
  `IdEstadoFinanciero` int(11) NOT NULL,
  `IdTipoSubcuenta` int(11) NOT NULL,
  `Cuenta` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cuentasanio`
--

CREATE TABLE `cuentasanio` (
  `IdCuenta` int(11) NOT NULL,
  `Anio` int(11) NOT NULL,
  `IdEmpresa` int(11) NOT NULL,
  `Valor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `empresa`
--

CREATE TABLE `empresa` (
  `IdEmpresa` int(4) NOT NULL,
  `Usuario` varchar(50) NOT NULL,
  `Empresa` varchar(300) NOT NULL,
  `Password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `financieros`
--

CREATE TABLE `financieros` (
  `IdEstadoFinanciero` int(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `financieros`
--

INSERT INTO `financieros` (`IdEstadoFinanciero`, `Nombre`) VALUES
(1, 'BALANCE GENERAL'),
(2, 'ESTADO DE RESULTADOS');

-- --------------------------------------------------------

--
-- Table structure for table `subtipocuenta`
--

CREATE TABLE `subtipocuenta` (
  `IdTipoSubCuenta` int(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subtipocuenta`
--

INSERT INTO `subtipocuenta` (`IdTipoSubCuenta`, `Nombre`) VALUES
(1, 'CORRIENTE'),
(2, 'NO CORRIENTE'),
(3, 'CAPITAL');

-- --------------------------------------------------------

--
-- Table structure for table `tipocuenta`
--

CREATE TABLE `tipocuenta` (
  `IdTipoCuenta` int(2) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipocuenta`
--

INSERT INTO `tipocuenta` (`IdTipoCuenta`, `Nombre`) VALUES
(1, 'ACTIVO'),
(2, 'PASIVO'),
(3, 'PATRIMONIO');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`IdCuenta`),
  ADD UNIQUE KEY `IdTipoCuenta` (`IdTipoCuenta`),
  ADD UNIQUE KEY `IdEstadoFinanciero` (`IdEstadoFinanciero`),
  ADD UNIQUE KEY `IdTipoSubcuenta` (`IdTipoSubcuenta`);

--
-- Indexes for table `cuentasanio`
--
ALTER TABLE `cuentasanio`
  ADD PRIMARY KEY (`Anio`),
  ADD UNIQUE KEY `IdCuenta` (`IdCuenta`,`IdEmpresa`),
  ADD KEY `IdEmpresa` (`IdEmpresa`);

--
-- Indexes for table `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`IdEmpresa`);

--
-- Indexes for table `financieros`
--
ALTER TABLE `financieros`
  ADD PRIMARY KEY (`IdEstadoFinanciero`);

--
-- Indexes for table `subtipocuenta`
--
ALTER TABLE `subtipocuenta`
  ADD PRIMARY KEY (`IdTipoSubCuenta`);

--
-- Indexes for table `tipocuenta`
--
ALTER TABLE `tipocuenta`
  ADD PRIMARY KEY (`IdTipoCuenta`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `IdCuenta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `empresa`
--
ALTER TABLE `empresa`
  MODIFY `IdEmpresa` int(4) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `financieros`
--
ALTER TABLE `financieros`
  MODIFY `IdEstadoFinanciero` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`IdTipoSubcuenta`) REFERENCES `subtipocuenta` (`IdTipoSubCuenta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`IdTipoCuenta`) REFERENCES `tipocuenta` (`IdTipoCuenta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuenta_ibfk_3` FOREIGN KEY (`IdEstadoFinanciero`) REFERENCES `financieros` (`IdEstadoFinanciero`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuenta_ibfk_4` FOREIGN KEY (`IdCuenta`) REFERENCES `cuentasanio` (`IdCuenta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cuentasanio`
--
ALTER TABLE `cuentasanio`
  ADD CONSTRAINT `cuentasanio_ibfk_1` FOREIGN KEY (`IdEmpresa`) REFERENCES `empresa` (`IdEmpresa`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
