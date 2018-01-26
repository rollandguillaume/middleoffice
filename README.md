# middleoffice


### Accès client

http://ensibs.westeurope.cloudapp.azure.com:82/


### Pour les devs

    mvn package
    sudo java -jar target/middleoffice-jar-with-dependencies.jar

Acces au serveur dans un navigateur :

GET : <nom_de_la_machine>:80/
POST : curl -X POST -d "param1=truc&param2=truc2" <nom_de_la_machine>:80/

~ eventuellement arreter le service apache2 du port 80

service apache2 stop


[] test
[x] teest
