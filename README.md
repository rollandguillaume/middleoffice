# middleoffice


### Accès client

http://ensibs.westeurope.cloudapp.azure.com:82/


### Pour les utilisateurs
    cd $RACINE_PROJET/API-Java-Spark
    mvn package
    sudo java -jar target/middleoffice-jar-with-dependencies.jar
  
~ Si une erreur du genre java.net.BindException: Adresse déjà utilisée apparait, il faut arreter le service apache2
    
    service apache2 stop

Acces au serveur dans un navigateur : 
    
    localhost:80/
    
### Pour le prof

Réalisation :
- [x] Page d'accueil (documentation sur comment utiliser l'api)
- [x] Créer une demande via un formulaire html (/demandes/creation)
    - [ ] Accès à cette page grâce à un bouton présent sur l'accueil
    - [x] Vérification des champs de saisi du formulaire de création de demande
    - [x] AutoIncrémentation des id des demandes
    - [x] Après une création de demande, redirection vers la liste de toutes les demandes (/demandes)
- [x] Affichage de toutes les demandes (aucun disign)
- [x] Accéder et Afficher une demande particulière (/demandes/:id)
    - [ ] Accès à cette page grâce à un bouton
    - [x] Message d'erreur dans le cas où la demande n'existe pas
- [x] Voter par oui ou non pour une demande spécifique
    - [ ] Voter via un formulaire
    - [x] Utiliser la commande d'un terminal : curl -X POST -d "voter=oui_non" <nom_de_la_machine>:80/demandes/:id
    - [ ] Etre redirigé automatiquement vers l'url de la réponse donnée à la demande
- [ ] Lecture et écriture dans un fichier json
- [ ] Test POSTMAN
- [x] Réalisation de Backlog
- [x] Automatisation de déploiement sur le serveur du client avec un script pour une bonne gestion des conteneur Docker

    
### Pour les devs
  Commande dans un terminal pour effectuer des requetes 

    GET : <nom_de_la_machine>:80/
    POST : curl -X POST -d "param1=truc&param2=truc2" <nom_de_la_machine>:80/
