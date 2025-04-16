# Projet Devops : Librairie pandas en Java

## Auteurs

Anna Clavelier - Maxim Frolov - Matthieu Rua

## Aperçu du projet

![CI - Test](https://github.com/annaclavelier/projet_devops/actions/workflows/ci.yml/badge.svg)

[![codecov](https://codecov.io/github/annaclavelier/projet_devops/graph/badge.svg?token=SIMDFJQSIH)](https://codecov.io/github/annaclavelier/projet_devops)


![code style - camelCase](https://img.shields.io/badge/code_style-camel_Case-blue)

## Fonctionnalités de la bibliothèque

Notre projet consiste à développer une bibliothèque Java inspirée de `pandas`, permettant la manipulation de tableaux de données (dataframes) de manière flexible et typée.

###  Conception des classes

La bibliothèque repose sur deux classes principales :

- **`Column<T>`**  
  Représente une colonne dans un tableau de données. Elle est générique (typage `T`), et stocke :
  - un **nom** de colonne (`String`)
  - une **liste de valeurs** du type `T`
  - le **type Java** de la colonne (ex : `Integer.class`, `Double.class`, etc.)

- **`Dataframe`**  
  Représente une table de données structurée hétérogènes (comme dans `pandas`). Elle contient une **liste de colonnes** (`List<Column<?>>`) et fournit :
  - des constructeurs pour créer le tableau (par code ou depuis un fichier CSV)
  - des méthodes pour afficher, selectionner ou analyser les données

Les colonnes peuvent contenir différents types (String, Integer, Double, Boolean), mais **une seule par colonne** (comme dans `pandas`). La classe `Dataframe` est donc **un tableau à deux dimensions**, avec un typage fort par colonne.

---

###  Fonctionnalités implémentées

- **Création de dataframes**
  - Depuis un ensemble de colonnes (type-safe) avec un constructeur Java
  - Depuis un fichier **CSV**, en détectant dynamiquement le type de chaque colonne à partir des données

- **Affichage**
  - Affichage complet du dataframe
  - Affichage des **n premières** lignes (`showHead`)
  - Affichage des **n dernières** lignes (`showTail`)

- **Statistiques sur colonnes**
  - Calcul de la **moyenne**, du **minimum** et du **maximum** d’une colonne
  - Les statistiques sont disponibles uniquement si les types sont compatibles (nombre ou comparable)

- **Structure interne**
  - Le dataframe repose sur un design proche de `pandas` :
    - Colonne sont indexes, permettant avoir le même nom pour deux colonnes
    - Chaque colonne est représentée par une instance de `Column<T>`
    - Chaque colonne est typée (`Integer`, `Double`, `String`, etc.)

### Fonctionnalités prévues / en cours

- Sélection de sous-ensembles :
  - Par index de lignes
  - Par labels de colonnes
- Sélection conditionnelle avancée
  - Prend une condition booléenne écrite dans le format de **JavaScript** à vérifier sur des colonnes
  - La condition peut-être composée, par exemple: `age > 0 && name.StartsWith('A')`
---


## 4. Développement logiciel et intégration continue

### 4.1 Pratiques de développement

Le développement de la bibliothèque a été réalisé en suivant les bonnes pratiques DevOps :
- **Versionnage avec Git**
- **Build et gestion de dépendances via Maven**
- **Tests unitaires avec JUnit**
- **Évaluation de la couverture de code avec JaCoCo**
- Hébergement sur **GitHub**, accessible à tous les membres du groupe.

---

### 4.2 Mise en place GitHub

- Le dépôt principal est hébergé sur GitHub : [https://github.com/annaclavelier/projet_devops](https://github.com/annaclavelier/projet_devops)
- Tous les membres du groupe ont été ajoutés comme **collaborateurs** avec droits de modification.
- Le travail est organisé à l’aide de branches `feature` suivies de **Pull Requests** avec revue de code.

---

### 4.3 Intégration continue

Nous avons mis en place une **GitHub Action** qui :
- Compile automatiquement le projet à chaque `push` ou `pull request` sur le main
- Lance l’ensemble des **tests unitaires**
- Génère un **rapport de couverture de code** visible sur Codecov.


---

### 4.4 Travail collaboratif

Le groupe suit un **workflow Git** basé sur les bonnes pratiques :
- Chaque fonctionnalité ou bug est développé dans une **feature branch**
- Les modifications passent par des **pull requests** et sont relues avant acceptatio et merge
- L’intégration continue valide automatiquement les tests et génère un rapport de couverture de code avant avant validation

---


## 4.5 Livraison continue (Maven)

Nous avons mis en place une livraison continue de la bibliothèque Java vers GitHub Packages, le dépôt Maven intégré à GitHub.

À chaque push sur main ou Pull Request, notre pipeline GitHub Actions :

- Compile le projet avec Maven

- Exécute tous les tests unitaires

- Génère un rapport de couverture avec JaCoCo (envoyé à Codecov)

- Déploie automatiquement l’artefact dans GitHub Packages si les étapes précédentes réussissent

Le fichier ci.yml configure aussi dynamiquement les credentials Maven (settings.xml) pour publier en toute sécurité avec un token GitHub.


---

## 4.6 Livraison continue avec Docker

Une image Docker démonstrative est générée automatiquement à chaque push sur `main`.  
Elle contient un exécutable qui démontre les principales fonctionnalités de la bibliothèque.

Docker Hub : [https://hub.docker.com/r/annaclavelier/pandas-java-demo](https://hub.docker.com/r/annaclavelier/pandas-java-demo)

```bash
docker pull annaclavelier/pandas-java-demo
docker run --rm annaclavelier/pandas-java-demo
```

## 4.7 Infrastructure-as-Code et Cloud (Terraform + Google Cloud)

Nous avons mis en place une **machine virtuelle dans Google Cloud Platform** à l’aide de Terraform :

- Utilisation des crédits étudiants GCP
- Déploiement automatisé d’une VM (Debian 11)
- Installation automatique d’**Apache2** via un script de démarrage
- Création automatique d’une règle **firewall** autorisant le trafic HTTP (port 80)
- IP publique exposée en output via Terraform

Exemple de résultat visible ici : http://34.78.38.172  


---

## 4.8 Badges

Nous avons intégré plusieurs **badges de qualité** dans le README :
- État du pipeline GitHub Actions 
- Couverture de code via Codecov 
- Style de nommage 

---

## 4.9 Valorisation de la bibliothèque via GitHub Pages ou maven-site

A compléter

## Sections non encore réalisées (optionnelles)

-  **4.6 Livraison continue avec Docker**
-  **4.10 Intégration d'autres services GitHub externes (Marketplace)**

---



## Exécution locale

```bash
# Se placer dans le bon répertoire
cd pandas-java

# Compilation
mvn clean compile

# Lancement des tests
mvn test

# Rapport de couverture
mvn jacoco:report
