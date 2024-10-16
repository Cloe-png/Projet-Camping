-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 10 oct. 2024 à 21:44
-- Version du serveur : 8.3.0
-- Version de PHP : 8.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `camping`
--

-- --------------------------------------------------------

--
-- Structure de la table `animateurs`
--

CREATE TABLE `animateurs` (
  `id_animateur` int NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `code_postal` varchar(50) NOT NULL,
  `rue_animateur` varchar(50) NOT NULL,
  `ville_animateur` varchar(50) NOT NULL,
  `num_tel` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `animateurs`
--

INSERT INTO `animateurs` (`id_animateur`, `nom`, `prenom`, `mail`, `code_postal`, `rue_animateur`, `ville_animateur`, `num_tel`) VALUES
(1, 'Jean', 'Dupont', 'jean.dupont@mail.com', '75000', '1 Rue de Paris', 'Paris', '0102030405');

-- --------------------------------------------------------

--
-- Structure de la table `animations`
--

CREATE TABLE `animations` (
  `id_animation` int NOT NULL,
  `libelle_animations` varchar(50) NOT NULL,
  `nom_animation` varchar(50) NOT NULL,
  `date_animations` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `creneaux`
--

CREATE TABLE `creneaux` (
  `id_planning` int NOT NULL,
  `heure_debut` varchar(50) NOT NULL,
  `heure_fin` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `nombre_place` int NOT NULL,
  `id_animation` int NOT NULL,
  `id_lieu` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `encadrer`
--

CREATE TABLE `encadrer` (
  `id_animateur` int NOT NULL,
  `id_planning` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

CREATE TABLE `lieu` (
  `id_lieu` int NOT NULL,
  `libelle_lieu` varchar(50) NOT NULL,
  `coordonnes` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `planning`
--

CREATE TABLE `planning` (
  `idPlanning` int NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id_utilisateur` int NOT NULL,
  `nom` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `mot_de_passe` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Index pour les tables déchargées
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
  ADD PRIMARY KEY (`id_planning`),
  ADD KEY `Creneaux_Animations_FK` (`id_animation`),
  ADD KEY `Creneaux_Lieu0_FK` (`id_lieu`);

--
-- Index pour la table `encadrer`
--
ALTER TABLE `encadrer`
  ADD PRIMARY KEY (`id_animateur`,`id_planning`),
  ADD KEY `encadrer_Creneaux0_FK` (`id_planning`);

--
-- Index pour la table `lieu`
--
ALTER TABLE `lieu`
  ADD PRIMARY KEY (`id_lieu`);

--
-- Index pour la table `planning`
--
ALTER TABLE `planning`
  ADD PRIMARY KEY (`idPlanning`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id_utilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `animateurs`
--
ALTER TABLE `animateurs`
  MODIFY `id_animateur` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `animations`
--
ALTER TABLE `animations`
  MODIFY `id_animation` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `creneaux`
--
ALTER TABLE `creneaux`
  MODIFY `id_planning` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `lieu`
--
ALTER TABLE `lieu`
  MODIFY `id_lieu` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `planning`
--
ALTER TABLE `planning`
  MODIFY `idPlanning` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id_utilisateur` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
