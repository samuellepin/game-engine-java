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

- Un JViewport qui vient de la librairie Swing va permettre de scinder l'écran en deux parties.
- Chaque JViewport va afficher une partie de l'écran.
- Un JViewport pour chaque joueur.
- Nous utilisons cette solution car nous ne pouvons pas modifier Canvas.
- Un JViewport va prendre en paramètre le Canvas.
- Le Canvas représente le façon dont on dessine les éléments de la carte.
- Chaque JViewport va afficher ce que le joueur voit donc il a besoin de savoir auprès de Canvas ce qu'il doit afficher.

TO DO pour demain :
- 2 JViewport qui vont prendre en paramètre le Canvas et chaque JViewport va afficher une partie de l'écran scindé. (1 JVieport pour chaque joueur)


### Jour 7 (12 juin):

- Réunion de début de journée pour discuter de l'avancement du projet et des tâches à réaliser pour la journée.
- Discussion sur la génération aléatoire et de la vue scindée.

- Modèle : les coordonnées d'une entité correspondent à son barycentre, et pas son coin en haut à gauche

- On a essayé de mettre en place le parser mais on n'a pas réussi à le faire fonctionner. Cela est du a ce qu'il ne compile pas. 
- Mettre le source folder du parser au même niveau que le src du projet.

### Jour 8 (13 juin):

- Définition du planning global du projet pour les semaines à venir pour avoir un objectif clair et précis.
- Définition d'un planning plus precis de la journée d'aujourd'hui et de demain.

- Rémi et Greg s'occupent de faire le View port car c'est une fonctinnalité essentiel et importante de notre projet et cela va nous permettre de pouvoir voir les entités
- Sam : Comprendre le fonctionnemnt du parser et du visitor 
- David mis en place d'une Hitbox pour chaque entité 
- Romain : continuer l'implémentation des biome de façon de façon aléatoire et mise en cohérence avec le code de David 

### Jour 9 (14 juin):
TO DO :
- Remi et greg doivent charger les avatars pour tester si leur Viewport fonctionne bien 
- Sam implementer le visitor et README pour le fichier de config
- David : fusionner les Hitbox pour les objects inanimé pour éviter d'avoir trop d'Hitbox
- Romain : finir la cohérence entre le code de David puis intégration entre les deux maps de biome et couloir et Hitbox  

## Jour 10 (17 juin):

- Réunion de début de journée pour discuter de l'avancement du projet et des tâches à réaliser pour la journée.
- David/Greg/Rémi : merge des différentes parties 
- Romain / Sam : implémentation des automates et du Visitor

## Jour 11 (18 juin):

- Redéfinition des objectifs de la semaine et de la journée.

- David : s'occupe des fichier de config pour les jeux 
- Sam/ Romain : continuation implémentation des automates et du Visitor
- Rémi : aide sur l'implémentation des automates et du Visitor
- Greg : support et aide sur les différentes implémentations

## Jour 12 (19 juin):

- David : parser le fichier de config pour les jeux et l'intégrer dans le projet
- Sam / Rémi : finir les classes des automates et finir le Visitor
- Greg : définit formellement que fait chaque action ,quel paramètres,... et implémentation du Brain qui va permettre de lier l'automate et l'entité
- Romain : matin EC et après-midi support pour les autres membres du groupe + commentateur non sportif

Pour demain :
    - Tester si les automates sont bien générés avec le Visitor
    - Implémenter les actions pour les entités
    - Vérifier si le brain fonctionne
    - Finir le moteur pour de vrai

**Commande parser**

Votre fichier automate : nom_AI.gal

- make nom_AI.txt
- make nom_AI.ast.dot OU make nom_AI.aut.dot
- make nom_AI.ast.jpg OU make nom_AI.aut.jpg

**Planning quotidien :**

- [ ] View => Greg et Rémi
    - [x] Afficher Viewport du modéle avec monde qui bouge ( A tester )
    - [ ] Charger tout les avatars 
    - => Besoin du model 
- [ ] AI (Automaton) => Sam 
    - [ ] Faire un Visitor 
    - [x] comprendre commnent le parser appel les différent méthode : 13/06
    - [ ] Créer les classes qui implémente action (move ,...)
    - [x] Lier le parser et notre projet 
    - [ ] Gestion FSM (traduction ast en FSM)
- [ ] Model => David 
    - [x] Fonctions qui détectes les collisons
    - [x] Géneration des biomes => Romain 
    - [x] Integration des méthodes de collision : 13/06 
        - [x] Hitbox aux entités : 13/06
    - [x] Implémentation champ vision
    - [ ] Gestion des ticks
        - Besoin des automates pour tester
    - [x] biomes : 13/06
    - [x] réunir carte biome et couloir 
- [ ] Controler 
    - [ ] Interpréter les touches pour L'AI


**Planning global :**

Jeudi 13 juin :

- [x] Finir premier jet du moteur (1/2)
    - [x] Finir chaque morceau

Vendredi 14 juin :

- [x] Finir premier jet du moteur (2/2)
    - [x] Assembler les morceaux

Lundi 17 juin :

- [x] Finir premier jet du moteur (3/3)
    - [x] Assembler les morceaux
    - [x] Debug
    - [x] Merge

Mardi 18 juin :

- Continuation du moteur 

Mercredi 19 juin :

- [ ] Implémenter fonctionnalité MG (1/1)
    - [ ] Implémenter les entités
    - [ ] Implémenter les automates
- Soir : Première version du jeu MG

Jeudi 20 juin :

- [ ] Implémenter fonctionnalité PN (1/1)
    - [ ] Implémenter les entités
    - [ ] Implémenter les automates
- Soir : Première version du jeu PN

Vendredi 21 juin :

- [ ] Debug 
- [ ] Ajout visuel et son 

Lundi 24 juin :

- [ ] Finalisation
    - [ ] Vidéo 

Mardi 25 juin :

- [ ] Rendu final
    
