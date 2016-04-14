# Glaisner_Amal_PorjetArchiLogiciel
Projet d'architecture logiciel, Groupe:Glaisner Nicolas, Amal Chafac.


#Principe SOLID et Design Pattern
Nous avons souhaité mettre en oeuvre le design pattern factory qui nous semblait etre la meilleur facon de créer les différents types de tétriminos.
Pour illustrer un des principes de la methode SOLID, nous avons utilisé une class interface Piece. C'est un parfait exemple de la seconde lettre des 
principes solides à savoir O (ouvert/fermé) en effet de cette facon notre classe est ouverte à l'extension mais cependant fermé à la modification.
Nous avons aussi essayé d'appliquer pour l'ensemble de nos classe le Single Responsability Principle comme c'est le cas pour chaque classe 
correspondants aux pièces.

#Maven,Test unitaires et TDD
Nous avons utiliser maven sous eclipse pour développer ce projet. Nous n'avons par contre pas développer en méthodes TDD.
Pas de tests unitaires avec Junit et mockito.

#Fonctionnalitées
-utilisation de touche clavier (keylistener).
-interface 2d graphique (swing).
-incrémentation de score mais pas encore enregistrement.

#Comment jouer
Pour lancer le jeu télécharger le fichier Tetriss.jar et éxecuter commande java -jar Tetriss.jar
La base de jeu est fonctionnel,il vous suffit d'appuyer sur VK_LEFT déplacer la pièce courante à gauche, VK_RIGHT déplace la pièce à droite,
VK_UP et VK_DOWN pour faire tourner la pièce. Vous pouvez utiliser la touche espace pour auglenter la vitesse de chute de la pièce.
