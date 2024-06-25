# Moteur de Jeu
Grégory Toureille, Romain Prat, Rémi Gaulmin, David Darras, Samuel Lepin

## Compiling

- Open the terminal in an empty folder 
- Clone the project into this folder (https://gricad-gitlab.univ-grenoble-alpes.fr/michael_perin_private_project/2023_info3_ple/g6)

### Compiling with Eclipse 

- Open Eclipse in the git folder 
- In Eclipse :
	- File -> Open Projects from File System or Archive-> select this path : ~/path-to-folder/g6/project.game
	- Check if the required libraries are correctly imported:
		- gson-2.11.jar 
		- info3.game.jar
		- parser.jar
	- Check that the compiler is in the correct version: Window -> Preferences -> Java -> Compiler -> Compiler compilance level: 11 
	- Clean the project 
	- Run project.gam/src/Game.java file to start the game
- If you have compilation issues, check if jdk11 is correctly installed: sudo apt install openjdk-11-jdk 

### Compiling using Makefile

- Open the terminal in the project.game folder
- Run the command `make` to compile the project
- Run the command `make run` to run the project

## Structure du code 

1. Model :
Path : src/Model

- Le package src.Model contient des informations sur l'état du jeu, telles que les entités présentes sur la carte, et des fonctions utiles pour manipuler les vecteurs et les angles dans notre monde métrique.
- Le package src.Model.World génère aléatoirement une carte, et la classe Biome permet d'ajouter des salles vides parmi les couloirs.
- Le package src.Model.Collision s'occupe de toutes les collisions possibles dans le jeu : entre deux AABB (Axis-Aligned Bounding Box) pour les collisions entre entités, entre un arc de cercle et une AABB pour détecter le joueur dans le champ de vision des adversaires, etc.
- Le package src.Model.Entities regroupe toutes les entités utilisées dans le jeu.

2. View :

- Chaque joueur a son viewport. Chaque viewport est associé à un objet EntityTracker du modèle.
Un EntityTracker est un rectangle qui suit une entité, et garde une liste des entités qui sont actuellement dans le rectangle.
Cela permet de n'afficher à l'écran que les entités visibles afin de gagner en performances.
Chaque entité est associée à un Avatar, qui s'occupe de représenter l'entité dans la vue.

3. Controller :
Path : src

- La partie Controller permet de récupérer les touches pressées et la position de la souris fournies par le GameCanvas. Ces informations sont stockées dans des tableaux, mis à jour à chaque tick, et sont appelées par src.AI pour la condition Key.

4. Bots :
Path : src/AI/

- Nous utilisons le parser mit à notre disposition pour parser et créer un arbre AST à partir des fichiers GAL. 
Notre visitor va permettre de créer nos automates à partir de cet arbre au fil du parcours de l'arbre AST.
On s'est inspirés des classes du parser pour faire nos automates.
[Insérer schema/graph object automates]
On définit une interface pour les actions et une pour les conditions. Pour chaque action et condition que nous implémentons, elles implémenteront ses interfaces pour qu'on puisse les utiliser dans le model.
Pour éviter une trop grosse population d'objets, nous allons en créer moins en utilisant l'aliasing pour les objets ayant le même comportement. Par exemple, si on a plusieurs Move(N) nous ne ferons qu'un object Move(N).

5. Divers :

- Le patterne singleton a été utilisé pour les classes qui ne devaient être instanciées qu'une seule fois, comme pour Map, Model, Controller, etc.

- Pour éviter des problèmes d'aliasing, des méthodes clone() ont été ajoutées pour Vector, Angle, AABB, etc.


## Environnement 

### Fichier de config 

Path : project.game/resources/Config-MG.json ou Config-Alien.json

Voici les différents paramètres que l'on peut modifier dans les fichiers de config :
- parameters : 
	- seed : graine pour la génération aléatoire
	- visionFieldRadius : rayon du champ de vision
	- visionFieldApertureAngle : angle d'ouverture du champ de vision
	- player1 : identifiant du joueur 1
	- player2 : identifiant du joueur 2
	- exit : identifiant de la sortie
	- itemToWin : identifiant de l'objet à récupérer pour gagner
	- enableWalls : activer ou non les murs/arbres
	- backgroundMusic : chemin vers le fichier de musique de fond
	- gameOverBGM : chemin vers le fichier de musique de fin de partie
	- volume : volume de la musique de fond
- world :
	- rowsNum : nombre de lignes
	- colsNum : nombre de colonnes
	- tile : 
		- width : largeur d'une case
		- height : hauteur d'une case
		- sprite : chemin vers l'image de la case
		- obstacles : liste des chemins vers les sprites des obstacles (tels que les murs)
	- biome : 
		- width : largeur en cases d'un biome
		- height : hauteur en cases d'un biome
		- space : espace entre les biomes
	- obstructionDensity : densité des obstacles, tels que les murs ou les arbres
- view :
	- paintHitbox : afficher les hitbox
	- paintVisionField : afficher le champ de vision des entités
	- zoom : zoom de la vue
	- screenWidth : largeur de l'écran
	- screenHeight : hauteur de l'écran
	- title : titre de la fenêtre de jeu
	- enableReducedVisionField : activer ou non le champ de vision réduit pour le joueur
- entities :
	- id : identifiant de l'entité
	- type : type de l'entité (classe qui lui est associée)
	- width : largeur de l'entité
	- height : hauteur de l'entité
	- hasCollision : l'entité a-t-elle une collision
	- velocity : vitesse de l'entité
	- fsm : nom de l'automate associé à l'entité (nom de l'automate et non le fichier gal)
	- typeCat : catégorie de l'entité
	- options : options de l'entité
	- hp : points de vie de l'entité
- enemies :
	- id : identifiant de l'ennemi
	- min : nombre minimum d'ennemis
	- max : nombre maximum d'ennemis
- keyItems :
	- id : liste des identifiants des objets à récupérer/activer pour gagner


### Fichier gal 

Path : project.game/resources/fsm.gal 

Voici les différents façon d'utiliser les Action et les Conditions dans les fichiers GAL:

(C = Category, A = Action, D = Direction, N = Number(Integer))
#### Actions
Add():

	- Ne fait rien (à @Override si on souhaite faire quelque chose)

Explode():

	- L'entité est détruite (donc supprimée du modèle).

Get():

	- Ne fait rien (à @Override si on souhaite faire quelque chose)

Egg():

	- D : 
		- Cas Général : Ne fait rien (à @Override si on souhaite faire quelque chose)
		- Cas Spy : Crée le robot espion dans la direction D

Hit():

	- D:
		- Cas Général : Attaque toutes les entités dans la direction D
	- D, N:
		- Cas Général : Attaque toutes les entités dans la direction D avec une force de N
		- Cas Guard : Tire dans la direction D avec une force de N

Jump(): 

	- D,N (par défaut Forward et 1):
		- Cas Général : Saut de distance N mètres. Le déplacement est instantané, et ignore les obstacles se trouvant sur leur chemin. Le jump ne s'effectue pas si l'entité atterrirait sur un obstacle.

Move(): 

	- D,N (par défaut Forward et 20):
		- Cas Général : Se déplace dans la direction D pendant N millisecondes

Pick(): 

	- D (par défaut Forward):
		- Cas Général : Si une entité Pickable se situe dans son champ de vision dans la direction D, l'entité met le pickable dans sa main. S'il avait une autre entité en main, cette dernière est transférée dans son inventaire.

Protect(): 

	- D, N (par défaut Forward et 100):
		- Cas Général : L'entité ignorera les appels à getHit() provenant d'une entité dans la direction D, pendant N millisecondes.

Rest(): 

	- N, pow (par défaut 100 et 5):
		- L'entité appelle la méthode addPow(pow), puis ne fait rien pendant N millisecondes.

Store(): 

	- L'entité range l'objet qu'il a en main dans son inventaire.

Throw():

	- L'entité pose l'objet qu'il a en main devant lui.

Turn():

	- D (par défaut Right)
		- Cas général: L'entité tourne vers la direction donnée.

Wait():

	- T (par défaut 100)
		- L'entité attend pendant T millisecondes, et ne fait rien pendant ce temps.

Pop():

	- Dans le cas général : ne fait rien. C'est à chaque type d'entité d'Override doPop() afin de s'en servir comme action personnalisée.

Wizz():

	- Dans le cas général : ne fait rien. C'est à chaque type d'entité d'Override doWizz() afin de s'en servir comme action personnalisée.
	- Dans Guard  : sert à donner l'alerte aux autres gardes.

#### Conditions
True():

	- Retourne toujours vrai.

MyDir():

	- D : Vérifie si l'entité est orientée vers la direction D.

Cell():

	- D,C : Vérifie si dans la direction D il y a une entité de la catégorie C.

Closest():

	-C,D (par défaut adversary, Forward) : Vérifie si l'entité la plus proche de la catégorie est dans la bonne direction.

Got():

	-Dans Guard : est utilisé pour vérifier si le garde est en état d'alerte.

Key(A):

	- Vérifie si la touche A est enfoncée.

## Lien vidéo 

[Lien vers la Vidéo](https://we.tl/t-48Q7g0dthT)

## Pourcentage participation 
- David : 20%
- Grégory : 20%
- Rémi : 20%
- Romain : 20%
- Samuel : 20%
