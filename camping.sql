-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 26 Septembre 2024 à 13:18
-- Version du serveur :  5.6.20-log
-- Version de PHP :  5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `camping`
--

-- --------------------------------------------------------

--
-- Structure de la table `animateurs`
--

CREATE TABLE IF NOT EXISTS `animateurs` (
`id_animateur` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `code_postal` varchar(50) NOT NULL,
  `rue_animateur` varchar(50) NOT NULL,
  `ville_animateur` varchar(50) NOT NULL,
  `num_tel` varchar(50) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `animateurs`
--

INSERT INTO `animateurs` (`id_animateur`, `nom`, `prenom`, `mail`, `code_postal`, `rue_animateur`, `ville_animateur`, `num_tel`) VALUES
(1, 'Jean', 'Dupont', 'jean.dupont@mail.com', '75000', '1 Rue de Paris', 'Paris', '0102030405');

-- --------------------------------------------------------

--
-- Structure de la table `animations`
--

CREATE TABLE IF NOT EXISTS `animations` (
`id_animation` int(11) NOT NULL,
  `libelle_animations` varchar(50) NOT NULL,
  `nom_animation` varchar(50) NOT NULL,
  `date_animations` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `creneaux`
--

CREATE TABLE IF NOT EXISTS `creneaux` (
`id_planning` int(11) NOT NULL,
  `heure_debut` varchar(50) NOT NULL,
  `heure_fin` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `nombre_place` int(11) NOT NULL,
  `id_animation` int(11) NOT NULL,
  `id_lieu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `encadrer`
--

CREATE TABLE IF NOT EXISTS `encadrer` (
  `id_animateur` int(11) NOT NULL,
  `id_planning` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

CREATE TABLE IF NOT EXISTS `lieu` (
`id_lieu` int(11) NOT NULL,
  `libelle_lieu` varchar(50) NOT NULL,
  `coordonnes` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `animateurs`
--
ALTER TABLE `animateurs`
 ADD PRIMARY KEY (`id_animateur`);

--
-- Index pour la table `animations`
--
ALTER TABLE `animations`
 ADD PRIMARY KEY (`id_animation`);

--
-- Index pour la table `creneaux`
--
ALTER TABLE `creneaux`
 ADD PRIMARY KEY (`id_planning`), ADD KEY `Creneaux_Animations_FK` (`id_animation`), ADD KEY `Creneaux_Lieu0_FK` (`id_lieu`);

--
-- Index pour la table `encadrer`
--
ALTER TABLE `encadrer`
 ADD PRIMARY KEY (`id_animateur`,`id_planning`), ADD KEY `encadrer_Creneaux0_FK` (`id_planning`);

--
-- Index pour la table `lieu`
--
ALTER TABLE `lieu`
 ADD PRIMARY KEY (`id_lieu`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `animateurs`
--
ALTER TABLE `animateurs`
MODIFY `id_animateur` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `animations`
--
ALTER TABLE `animations`
MODIFY `id_animation` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `creneaux`
--
ALTER TABLE `creneaux`
MODIFY `id_planning` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `lieu`
--
ALTER TABLE `lieu`
MODIFY `id_lieu` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `creneaux`
--
ALTER TABLE `creneaux`
ADD CONSTRAINT `Creneaux_Animations_FK` FOREIGN KEY (`id_animation`) REFERENCES `animations` (`id_animation`),
ADD CONSTRAINT `Creneaux_Lieu0_FK` FOREIGN KEY (`id_lieu`) REFERENCES `lieu` (`id_lieu`);

--
-- Contraintes pour la table `encadrer`
--
ALTER TABLE `encadrer`
ADD CONSTRAINT `encadrer_Animateurs_FK` FOREIGN KEY (`id_animateur`) REFERENCES `animateurs` (`id_animateur`),
ADD CONSTRAINT `encadrer_Creneaux0_FK` FOREIGN KEY (`id_planning`) REFERENCES `creneaux` (`id_planning`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
