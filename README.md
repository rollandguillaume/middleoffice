# middleoffice


### Accès client

http://ensibs.westeurope.cloudapp.azure.com:82/


### Pour les devs
    cd $RACINE_PROJET/
    mvn package
    sudo java -jar target/middleoffice-jar-with-dependencies.jar
    
~ Si une erreur du genre java.net.BindException: Adresse déjà utilisée apparait, il faut arreter le service apache2
    
    service apache2 stop


Acces au serveur dans un navigateur :

GET : <nom_de_la_machine>:80/
POST : curl -X POST -d "param1=truc&param2=truc2" <nom_de_la_machine>:80/
