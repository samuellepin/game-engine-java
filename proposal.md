# Contrat du groupe 6
Samuel Lepin, Grégory Toureille, Rémi Gaulmin, David Darras, Romain Prat

## Jeu 1 et 2 avec leur gameplay

### 1. Jeu d'infiltration 
Deux agents secrets, les joueurs, s'infiltrent dans la base militaire de Polytech Grenoble afin de s'emparer de la Documentation Succinte de Lustre. On peut choisir deux modes :

1. Mode affrontement :
    - Objectif : Celui qui s'empare des documents et ressort en vie de Polytech Grenoble remporte la victoire.
2. Mode coopération :
    - Objectif : L'un des deux joueurs doit s'emparer des documents et ressortir en vie de Polytech Grenoble pour que les deux remportent la victoire. Si le porteur des documents meurt, le deuxième joueur peut les récupérer.

- Les gardes :
    - Les gardes ont un chemin aléatoire d'exploration de la carte.
    - Les gardes ont une arme à longue portée.
    - Ils ont un cône de vision (comme une lampe torche).
    - Lorsqu'ils repèrent un joueur, ils le poursuivent et lui tirent dessus. 
- Les joueurs :
    - Un joueur est repéré/visible lorsqu'il se trouve dans le cône de vision d'un garde.
    - Quand un joueur est repéré, tous les gardes sont alertés de sa position et se dirigent vers lui pour lui tirer dessus.
    - Les gardes arrêtent de le pourchasser s'il réussit à rester caché pendant 10 secondes.
    - S'il tente de se cacher alors qu'il est visible des gardes, les gardes savent où il est caché.
    - Chaque joueur a une barre de vie qui diminue quand il se fait tirer dessus, il est éliminé quand sa barre de vie tombe à zéro.
- Le robot :
    - Un joueur peut déployer un unique robot dont il peut prendre le contrôle.
    - Le robot va plus vite que les gardes.
    - S'il est repéré par un garde, celui-ci le poursuit et lui tire dessus.
    - Si le robot est touché, il est détruit.
    - Entre le robot et le joueur, le garde tire en priorité sur le joueur.
- Les caméras de surveillance :
    - Elles ont un cône de vision et tournent sur elles-mêmes pour balayer leur voisinage.
    - Si un joueur est visible d'une caméra (présent dans le cône de vision), la caméra alerte les gardes.
- Les cartons :
    - Des cartons sont disposés sur la carte.
    - Le joueur peut se cacher dedans et se déplacer avec.
    - Si un garde/une caméra voit le carton se déplacer, l'alerte est donnée.

### 2. Jeu de l'alien 
Un vaisseau extraterrestre vient de s'écraser sur Terre. L'un des joueurs joue l'extraterrestre, qui a la capacité de se métamorphoser en tous les animaux qu'il voit. L'autre joueur est un agent du GIGN, chargé de capturer l'extraterrestre. Le but de l'extraterrestre est de réparer des générateurs pour redémarrer son vaisseau sans se faire repérer par l'agent du GIGN.

- L'alien, lorsqu'il se trouve sur un générateur, doit appuyer continuellement sur un bouton pendant un certain temps pour le réparer
- L'agent vise grâce à la souris et se déplace avec les flèches du clavier.

#### Fonctionnalités réutilisées
- L'agent du GIGN et l'alien réutilisent les mêmes mécanismes que les gardes et les espions :
    - Cône de vision
    - L'arme à longue portée
    - Métamorphose (alien-animaux, espion-carton) : impacte le comportement et le visuel.
    - Le mécanisme de Egg est repris (espion-robot, leurre-agent).
- Les mécanismes pour la génération de la carte à base de bloc est réutilisée.
- Pour l'alien, même condition de victoire : accomplir les objectifs et aller vers la sortie.

## Fonctionnalités du moteur de jeu

- Cône de vision dont on pourra fixer l'angle et le rayon en fonction de l'entité
- Vue du dessus, 2D
- Système de coordonnées métriques
- Système de tir
- Système de collisions
- Génération aléatoire dite "procédurale"
- Métamorphose -> changement d'automates entre entités
- Communication entre entités (__ex :__ les gardes et les caméras communiquent)
- Ecran scindé

## Contraintes

- Facteurs de réglages de difficulté :
    - Nombre de gardes + caméras / animaux + générateurs.
    - Distance entre position initiale du joueur et des objectifs
    - Nombre de points de vie