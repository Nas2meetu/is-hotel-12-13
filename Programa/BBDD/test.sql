-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 22-05-2013 a las 22:04:01
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `test`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Books`
--

CREATE TABLE IF NOT EXISTS `Books` (
  `idBooks` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) NOT NULL,
  `checkIn` date NOT NULL,
  `checkOut` date NOT NULL,
  `deposit` float NOT NULL,
  `numPerson` int(11) NOT NULL,
  `confirm` tinyint(1) NOT NULL,
  PRIMARY KEY (`idBooks`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ClientCompany`
--

CREATE TABLE IF NOT EXISTS `ClientCompany` (
  `id` int(11) NOT NULL,
  `company` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cif` varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `creditCard` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cif` (`cif`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ClientCompany`
--

INSERT INTO `ClientCompany` (`id`, `company`, `cif`, `phone`, `creditCard`, `address`) VALUES
(17, 'nombremep', '452968745', '689578512', '4444444444444', 'jdnhss'),
(18, 'ewaf', '587496323', '666666666', '4444444444444', 'didiidididididi'),
(19, 'eyii', '587496322', '658714298', '4444444444444', 'didiidididididi');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ClientIndividual`
--

CREATE TABLE IF NOT EXISTS `ClientIndividual` (
  `id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dni` varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(9) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `creditCard` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dni` (`dni`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ClientIndividual`
--

INSERT INTO `ClientIndividual` (`id`, `name`, `surname`, `dni`, `phone`, `creditCard`, `address`) VALUES
(3, 'nnn', 'nnn', '', '689542677', '4444444444444', 'nnnnnn'),
(7, 'name', 'surnameee', '458796322', '658745987', '4444444444444', 'afsdfsaddsa'),
(15, 'kjsdahk', 'jdsk', '586235874', '652563985', '4444444', 'adrees'),
(16, 'sdf', 'fdsa', '587496324', '658714298', '4444444444444', 'didiidididididi');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Clients`
--

CREATE TABLE IF NOT EXISTS `Clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `apellidos` varchar(20) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Employees`
--

CREATE TABLE IF NOT EXISTS `Employees` (
  `idEmployee` int(11) NOT NULL AUTO_INCREMENT,
  `shiftID` int(11) NOT NULL,
  `pay` float NOT NULL,
  `nameEmployee` varchar(12) CHARACTER SET latin1 NOT NULL,
  `surnameEmployee` varchar(50) CHARACTER SET latin1 NOT NULL,
  `dniEmployee` varchar(9) CHARACTER SET latin1 NOT NULL,
  `tlfEmployee` varchar(9) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE KEY `dniEmployee` (`dniEmployee`),
  KEY `shiftID` (`shiftID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Rooms`
--

CREATE TABLE IF NOT EXISTS `Rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_number` int(5) NOT NULL,
  `price` float NOT NULL,
  `bed_number` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Rooms_books`
--

CREATE TABLE IF NOT EXISTS `Rooms_books` (
  `idRom_book` int(11) NOT NULL AUTO_INCREMENT,
  `idBook` int(11) NOT NULL,
  `idRoom` int(11) NOT NULL,
  PRIMARY KEY (`idRom_book`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Services`
--

CREATE TABLE IF NOT EXISTS `Services` (
  `idServices` int(11) NOT NULL AUTO_INCREMENT,
  `services` varchar(50) NOT NULL,
  PRIMARY KEY (`idServices`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `Services`
--

INSERT INTO `Services` (`idServices`, `services`) VALUES
(2, 'mando'),
(3, 'riete'),
(4, 'aireee');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Services_books`
--

CREATE TABLE IF NOT EXISTS `Services_books` (
  `idServicesBooks` int(11) NOT NULL AUTO_INCREMENT,
  `idBook` int(11) NOT NULL,
  `idService` int(11) NOT NULL,
  PRIMARY KEY (`idServicesBooks`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Shifts`
--

CREATE TABLE IF NOT EXISTS `Shifts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nameShift` varchar(11) NOT NULL,
  `checkIn` time NOT NULL,
  `checkOut` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `Shifts`
--

INSERT INTO `Shifts` (`id`, `nameShift`, `checkIn`, `checkOut`) VALUES
(1, 'noche', '22:23:21', '00:00:00');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Employees`
--
ALTER TABLE `Employees`
  ADD CONSTRAINT `Employees_ibfk_1` FOREIGN KEY (`shiftID`) REFERENCES `Shifts` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
