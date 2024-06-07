# Contrat du groupe 6
Samuel Lepin, Grégory Toureille, Rémi Gaulmin, David Darras, Romain Prat

## 1.  Objectifs du moteur de jeu 

- Jeux en 2D vue du dessus
- Champ de vision ("Brouillard de Guerre") pour limiter ce qui est visible pour le joueur
- Système d'inventaire
- Écran scindé pour le multijoueur

## 2. Jeux fait grâce au moteur 

### Jeu d'infiltration 

Deux agents secrets, les joueurs, s'infiltrent dans la base militaire
de Polytech Grenoble afin de s'emparer de la Documentation Succinte de
Lustre. On peut choisir un mode affrontement, où le joueur qui s'empare
de la documentation gagne, ou un mode en coopération où les deux joueurs gagnent si l'un d'entre eux s'en empare.

Fonctionnalités :
- Gardes contrôlés par un automate, qui attaquent les joueurs quand ils les repèrent
- Caméras de surveillance qui peuvent alerter les gardes
- Les joueurs peuvent neutraliser les gardes si ils les frappent par derrière
- Plusieurs moyens de se camoufler (en changeant d'automate):
    - se cacher dans une boîte en carton
    - voler le costume d'un garde
- Conduits d'aération
- Génération aléatoire des couloirs dans lesquels s'infiltrer
- Différents gadgets que les joueurs peuvent obtenir
- Les joueurs comme les gardes ont un champ de vision limité
- Mini drone à roulettes pour partir en reconnaissance pour les joueurs (action Egg())

### Jeu de l'alien/métamorphose 

Un vaisseau extraterrestre vient de s'écraser sur Terre.
L'un des joueurs joue l'extraterrestre, qui a la capacité de se
métamorphoser en tous les animaux qu'il voit.
L'autre joueur est un agent de la CIA, chargé de capturer l'extraterrestre.
Le but de l'extraterrestre est de découvrir un maximum d'animaux avant de retourner dans son vaisseau, et le but de l'agent est de repérer l'extraterrestre parmi les animaux, et de le capturer.

Fonctionnalités :
- L'automate de l'extraterrestre change en fonction de l'animal en lequel il s'est transformé
- Animaux sauvages contrôlés par des automates
- Un écran par joueur (2 fenêtres ?)
- Carte générée aléatoirement, avec les animaux dans le bon environnement

### Projet Blair Witch

Un joueur, le survivant, est chassé par un monstre (le deuxième joueur).
Le monstre craint la lumière.
Plusieurs torches sont disposées aléatoirement sur la carte, et des lucioles et autre créatures bioluminescentes se déplacent sur la carte.
Le joueur peut capturer ces lucioles afin d'allumer les lampes.
Le monstre gagne s'il attrappe le joueur, le joueur gagne si il survit jusqu'à l'aube.

Fonctionnalités :
- Système d'éclairage et de champ de vision
- Le joueur a du mal à voir dans l'obscurité, alors que la vision du monstre est sensible au mouvement mais il est aveuglé par la lumière et ne peut pas voir le joueur dedans
- Les lampes s'affaiblissent petit à petit avant de s'éteindre afin d'empêcher le joueur de rester en sécurité trop longtemps
- Le monstre peut faire apparaître un mini monstre qui peut alerter sur la position du joueur