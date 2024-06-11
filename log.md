# Journal de Bord du groupe 6 - Projet fin d'année
## Semaine 1

### Jour 1 (04 juin):

- configuration de l'environnement de travail git et eclipse.
- choix du formatage de notre code 
- début discussioon sur l'organisation du groupe

### Jour 2 (05 juin):

- choix du type jeu  (vue du deçus)
- choix des deux jeux qu'on va implémenter 
    - alien qui cherche a rejoindre sa soucoupe en se cachant 
    - prophunt 
- choix definitif de l'organisation du groupe 
- discussion avec Mr Gruber sur la meilleure approche d'organisation et des prochaines tâches à suivre dans la semaine

#### Snake Prototype 

- réflexion sur l'organisation du Model 
- réalisation du début du graphe d'objet 
- création de la classe Model, Grid, Cell, Directory, Entity et de certaines de leurs méthodes

Pour demain :

- comment faire le View ?
- discussion sur ce qui a été fait

### Jour 3 (06 juin):

Réunion de début :

- discussion sur le code fait (src-AI et Model)
- réflexion sur la View
- décision du travail à faire pour la journée et répartition des tâches

Réflexion sur la View :

- graphe d'objet de la View
- gestion du polymorphisme des avatars (solution non trouvée) besoin d'un professeur pour nous aider dans cette conception 
    - possible solution : 
        - factory 
        - getAvatar dans entity mais pas propre car on perd la séparation entre View et Model 
- timer sur la view 

- Appel avec Olivier Gruber pour clarifier la vue et le modèle 
- Retard sur le code (trop de conception pas assez d'implémentation) 
- TODO :
    - Implémenter la vue (avec les avatars et une factory)
    - Queue du Snake
    - Assembler tous les différents morceaux du modèle pour voir si ça fonctionne

### Jour 4 (07 juin):

- Discussion avec Pr. Olivier Gruber sur le lien entre automate et entity et de entity/brain/automate 
    - Discussion du rôle de chaque partie 
- Finition de toute la View par David 
- Rémi s'occupe de faire la Tail du snake .
- Romain s'occupe de faire les mouvenet aléatoire du snake 
- Grégory et Samuel s'occupe de la rédaction et de la réflexion du contrat, des fonctionnalité du moteur, des 2 jeux avec ce moteur,...

### Jour 5 (10 juin):

- Rédaction du contrat 
- Choix de ce qui va être fait le lendemnain 


- Sam merge-request et support Model
- Entité (Romain)
- Model : (David)
    - Coordonnées
    - Déplacement 
    - Collisions
    - Champ de vision 
    - Ticks
- View : (Rémi et Greg)
    - Viewport
    - Ecran scindé 
    - Système métrique 
- Automate 
    - Double lien entre automate et entité 


### Jour 6 (11 juin):

- Réflexion sur la génération aléatoire. Choix retenu: système de "biomes". Chaque "biome" est placé sur une grille de la dimension de la carte, sans se superposer les uns sur les autres.
    - Un "biome" peut représenter :
        - Metal Gear : une des salles de Polytech Grenoble ou une quelconque salle d'intérieur.
        - Prop Night : un biome naturel (étang, prairie, forêt, etc...)
    - Si une case du "biome" est collée à une case d'un autre "biome" déjà placé :
        - Metal Gear : rien à faire
        - Prop Night : "dégradé" entre les deux
    - Si elle est collée à un bord, rien à faire pour les deux jeux
    - Si elle n'est collée à rien :
        - Metal Gear : placer un mur
        - Prop Night : "dégradé" vers le fond par défaut.
    - "dégradé" signifie une texture déjà faite qui permet de faire la liaison entre les deux biomes.
