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
Jump(): [Greg]
	- D,N (par défaut Forward et 1):
		- Cas Général : Saute dans la direction D de N  = dash de distance N mètres. Le déplacement est instantané, et ignore les obstacles se trouvant sur leur chemin. Le jump ne s'effectue pas si l'entité atterrirait sur un obstacle.
Move(): [Greg]
	- D,N (par défaut Forward et 20):
		- Cas Général : Se déplace dans la direction D pendant N ms
Pick(): [Greg]
	- D (par défaut Forward):
		- Cas Général : Si une entité Pickable se situe dans son champ de vision dans la direction D, l'entité met le pickable dans sa main. S'il avait une autre entité en main, cette dernière est transférée dans son inventaire.
Protect(): [Greg]
	- D, N (par défaut Forward et 100):
		- Cas Général : L'entité ignorera les appels à getHit() provenant d'une entité dans la direction D, pendant N millisecondes.
Rest(): [Greg]
	- N, pow (par défaut 100 et 5):
		- L'entité appelle la méthode addPow(pow), puis ne fait rien pendant N millisecondes.
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
