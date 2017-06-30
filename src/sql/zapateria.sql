-- phpMyAdmin SQL Dump
-- version 2.10.1
-- http://www.phpmyadmin.net
-- 
-- Servidor: localhost
-- Tiempo de generaci�n: 25-06-2017 a las 18:47:11
-- Versi�n del servidor: 5.0.41
-- Versi�n de PHP: 5.2.2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Base de datos: `ventas`
-- 

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `cliente`
-- 

CREATE TABLE `cliente` (
  `ID_CLIENTE` varchar(10) collate latin1_general_ci NOT NULL,
  `CIE_CEDULA` varchar(10) collate latin1_general_ci NOT NULL,
  `CIE_NOMBRE` varchar(15) collate latin1_general_ci NOT NULL,
  `CIE_APELLIDO` varchar(15) collate latin1_general_ci NOT NULL,
  `CIE_DIRECCION` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`ID_CLIENTE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `cliente`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `productos`
-- 

CREATE TABLE `productos` (
  `PRO_ID` varchar(15) collate latin1_general_ci NOT NULL,
  `PRO_MARCA` varchar(15) collate latin1_general_ci NOT NULL,
  `PRO_COLOR` varchar(15) collate latin1_general_ci NOT NULL,
  `PRO_TALLA` varchar(15) collate latin1_general_ci NOT NULL,
  `PRO_MODELO` varchar(15) collate latin1_general_ci NOT NULL,
  `PRO_TIPO` varchar(15) collate latin1_general_ci NOT NULL,
  `Stock` int(11) NOT NULL,
  PRIMARY KEY  (`PRO_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `productos`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `vendedor`
-- 

CREATE TABLE `vendedor` (
  `ID_VENDEDOR` varchar(10) collate latin1_general_ci NOT NULL,
  `VEN_CEDULA` varchar(10) collate latin1_general_ci NOT NULL,
  `VEN_NOMBRE` varchar(15) collate latin1_general_ci NOT NULL,
  `VEN_APELLIDO` varchar(15) collate latin1_general_ci NOT NULL,
  `VEN_DIRECCION` varchar(20) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`ID_VENDEDOR`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `vendedor`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `venta`
-- 

CREATE TABLE `venta` (
  `ID_VENTA` varchar(10) collate latin1_general_ci NOT NULL,
  `VEN_FECHA` date NOT NULL,
  `VEN_VALOR` float NOT NULL,
  `ID_CLIENTE` varchar(10) collate latin1_general_ci NOT NULL,
  `ID_VENDEDOR` varchar(10) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`ID_VENTA`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Volcar la base de datos para la tabla `venta`
-- 

CREATE TABLE detalle_venta(
ID_VENTA varchar(10) NOT NULL,
ID_PROD varchar(15),
CANTIDAD float NOT NULL,
VALOR float NOT NULL
)