# Moteur de Jeux 
Grégory Toureille, Romain Prat, Rémi Gaulmin, David Darras, Samuel Lepin

## Compilation

- Open the terminal to an empty folder 
- Clone the project in this folder ( https://gricad-gitlab.univ-grenoble-alpes.fr/michael_perin_private_project/2023_info3_ple/g6 )
- Open eclipse in this folder 
- In Eclipse :
	- File -> Opne Projects from File System or Archive-> select this path : ~/path-to-folder/g6/project.game
	- Check if the libraries in this project are imported correctly :
		- gson-2.11.jar 
		- info3.game.jar
		- parser.jar
	- Check that the compiler is at the correct version ; Window -> Preferences -> Java -> Compiler -> Compiler compilance level: 11 
	- Clean the project 
	- Launch the project.gam/src/Game.java file to start the game
- If you have compilation problems, check if you have to install : sudo apt install openjdk-11-jdk 

## Structure du code 

1. Model :

[TO DO]

2. View :

Chaque joueur a son viewport. Chaque viewport est associé à un objet EntityTracker du modèle.
Un EntityTracker est un rectangle qui suit une entité, et garde une liste des entités qui sont actuellement dans le rectangle.
Cela permet de n'afficher à l'écran que les entités visibles afin de gagner en performances.
Chaque entité est associée à un Avatar, qui s'occupe de représenter l'entité dans la vue.

3. Controller :
Path : src
[TO DO]

4. Bots :
Path : src/AI/

Nous utilisons le parser mit à notre disposition pour parser et créer un arbre AST à partir des fichiers GAL. 
Notre visitor va permettre de créer nos automates à patir de cet arbre au fur et à mesure du parcours de l'arbre AST.
On s'est inspiré des classes du parser pour faire nos automates.
[Insérer schema/graph object automates]
On définit une intérface pour les actions et une pour les conditions. Pour chaque action et condition que nous implémenterons, elles devront implémenter ses interfaces pour qu'on puisse les utiliser dans le model.
Pour éviter une trop grosse population d'object nous allons créer moins d'object et faire de l'aliasing pour les objects ayant le même comportement ( par exemple si on a plusieurs Move(N) nous ne ferons qu'un object Move(N))


## Environnement 

### Fichier de config 

Path : project.game/resources/Config-MG.json ou Confif-Alien.json

Voici les différents paramètres que l'on peut modifier dans les fichiers de config :
- parameters : 
	- seed : grain pour la génération aléatoire
	- visionFieldRadius : rayon du champ de vision
	- visionFieldApertureAngle : angle d'ouverture du champ de vision
	- player1 : id du joueur 1
	- player2 : id du joueur 2
	- exit : id de la sortie
	- itemToWin : id de l'objet à récupérer pour gagner
	- enableWalls : activer ou non les murs/arbres
	- backgroundMusic : adresse du fichier de musique de fond
	- gameOverBGM : adresse du fichier de musique de fin de partie
	- volume : volume de la musique de fond
- world :
	- rowsNum : nombre de lignes
	- colsNum : nombre de colonnes
	- tile : 
		- width : largeur d'une case
		- height : hauteur d'une case
		- sprite : adresse de l'image de la case
		- obstacles : liste des sprites des obstacles
	- biome : 
		- width : largeur d'un biome
		- height : hauteur d'un biome
		- space : espace entre les biomes
	- obstructionDensity : densité d'obstruction
- view :
	- paintHitbox : afficher les hitbox
	- paintVisionField : afficher le champ de vision
	- zoom : zoom de la vue
	- screenWidth : largeur de l'écran
	- screenHeight : hauteur de l'écran
	- title : titre de la fenêtre
	- enableReducedVisionField : activer ou non le champ de vision réduit
- entities :
	- id : id de l'entité
	- type : type de l'entité (class qui lui est associée)
	- width : largeur de l'entité
	- height : hauteur de l'entité
	- hasCollision : l'entité a-t-elle une collision
	- velocity : vitesse de l'entité
	- fsm : à quel automate est associé l'entité (nom de l'automate et non le fichier gal)
	- typeCat : catégorie de l'entité
	- options : options de l'entité
	- hp : points de vie de l'entité
- enemies :
	- id : id de l'ennemi
	- min : nombre minimum d'ennemis
	- max : nombre maximum d'ennemis
- keyItems :
	- id : liste des id des objets à récupérer/activer pour gagner


### Fichier gal 

Path : project.game/resources/fsm.gal 

Voici les différents façon d'utiliser les Action et les Conditions dans les fichiers GAL:

(C = Category, A = Action, D = Direction, N = Number(Integer))
#### Actions
Add():
Explode():
Get():
	-Faire rien (à @Overide si on souhaite faire quelque chose)
Egg():
	- D : 
		- Cas Général : Faire rien (à @Overide si on souhaite faire quelque chose)
		- Cas Spy : Crée le robot espion dans la direction D
Hit():
	- D:
		- Cas Général : Attaque toutes les entités dans la direction D
	- D, N:
		- Cas Général : Attaque dans la direction D avec une force de N
		- Cas Guard : Attaque dans la direction D avec une force de N à distance 
Jump():
	- D,N :
		- Cas Général : Saute dans la direction D de N  = dash de distnace N
Move():
	- D:
		- Cas Général : Se déplace dans la direction D pendant 20 ms
	- D,N:
		- Cas Général : Se déplace dans la direction D pendant N ms
Pick():
	- D :
		- Cas Général : Ramasse l'objet dans la direction D
Protect():
Rest():
Store():
Throw():
Turn():
Wait():
Pop():
Wizz():

#### Conditions
True():
MyDir():
Cell():
Closest():
Got():
Key():

## Lien vidéo 

[Insérer lien vidéo]

## Pourcentage participation 
- David : 
- Grégory :
- Rémi :
- Romain :
- Samuel :
