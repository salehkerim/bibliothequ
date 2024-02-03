-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 28 mai 2023 à 21:57
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `database_project_final`
--

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE `book` (
  `idBook` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(50) DEFAULT NULL,
  `available` varchar(10) NOT NULL,
  `idSub` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `book`
--

INSERT INTO `book` (`idBook`, `title`, `author`, `available`, `idSub`) VALUES
(1, 'Harry Potter', NULL, 'NON', 1),
(2, 'test2', NULL, 'NON', 2),
(3, 'The 48 Laws Of Power', NULL, 'NON', 3),
(4, 'The Great Gatsby', NULL, 'OUI', NULL),
(5, 'Spiderman', NULL, 'NON', 4),
(7, 'winx', NULL, 'OUI', NULL),
(8, 'Test', NULL, 'OUI', NULL);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `borrow`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `borrow` (
`idBook` int(11)
,`title` varchar(255)
,`subName` varchar(255)
);

-- --------------------------------------------------------

--
-- Structure de la table `subscriber`
--

CREATE TABLE `subscriber` (
  `idsub` int(11) NOT NULL,
  `subName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `subscriber`
--

INSERT INTO `subscriber` (`idsub`, `subName`) VALUES
(1, 'MOHAMED HABIB'),
(2, 'LASSAAD JARRAY'),
(3, 'OUMAYMA BEN FADHEL'),
(4, 'ISLEM BENNOUR'),
(7, 'Snoussi');

-- --------------------------------------------------------

--
-- Structure de la vue `borrow`
--
DROP TABLE IF EXISTS `borrow`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `borrow`  AS SELECT `book`.`idBook` AS `idBook`, `book`.`title` AS `title`, `subscriber`.`subName` AS `subName` FROM (`book` join `subscriber` on(`book`.`idSub` = `subscriber`.`idsub`)) WHERE `book`.`available` = 'NON' ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`idBook`),
  ADD KEY `fk1` (`idSub`);

--
-- Index pour la table `subscriber`
--
ALTER TABLE `subscriber`
  ADD PRIMARY KEY (`idsub`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`idSub`) REFERENCES `subscriber` (`idsub`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
