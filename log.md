# Journal de Bord du groupe 6 - Projet fin d'année
## Semaine 1

### Jour 1 (04 juin):

- configuration de l'environnement de travail git et eclipse.
- choix du formatage de notre code 
- début discussioon sur l'organisation du groupe

### Jour 2 (05 juin):

- choix du type jeu  (vue du deçus)
- choix des deux jeu qu'on va implémenter 
    - alien qui cherche a rejoindre sa socoucoupe en se cachant 
    - prophunt 
- choix definitif de l'organisation du groupe 
- discussion avec Mr Gruber sur la meilleur approche d'orgnisation et des prochaines tâches à suivre dans la semaine

#### Snack-Protype 

- réflexion sur l'organisation du Model 
- réalisation du début du graph d'object 
- creation de la class Model , Grid, Cell , Directory , Entity et de certains de leur méthode

Pour demain :
- comment faire le View ?
- discussion de ce qui a été fait

### Jour 3 (06 juin):

Réunion de début :
- discussion sur le code fait (src-AI et Model)
- réflexion sur la View
- décision du travail à faire pour la journée et répartition des tâches

Reflexion sur la View :
- graph d'objet de la View
- gestion du polymorphisme des avatars (solution non trouvé) besoin d'un professeur pour nous aider dans cette conception 
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