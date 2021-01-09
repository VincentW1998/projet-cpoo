Projet CPOO
==============

**NGO FELIX** :

*    numéro étudiant : 71702718
*    alias : @ngof
    
**WEI VINCENT** :

*    numéro étudiant : 71805701
*    alias : @weiv


## Etape 1 : Java

Pour compiler notre projet, il vous faut la version **10.0.2** de java et de javac

Pour connaitre votre version java tapez dans le terminal

*  `java -version `

Pour connaitre votre version javac tapez dans le terminal 

*  `javac -version`

Saisissez dans un terminal les commandes suivantes. Ceci va lister la liste des versions disponibles, et vous n'aurez plus qu'à choisir le numéro de celle que vous souhaitez utiliser par défaut dans notre cas la version 10.0.2.
*  `sudo update-alternatives --config java`
*  `sudo update-alternatives --config javac`

Vous pouvez passer à l'étape 2, si vous avez choisi la version 10.0.2

Si jamais vous n'avez pas la bonne version java d'installer aller sur [https://www.oracle.com/java/technologies/java-archive-javase10-downloads.html](url)
Prenez par exemple la version Linux jdk-10.0.2_linux-x64_bin.tar.gz
*  `tar xzvf jdk-10.0.2_linux-x64_bin.tar.gz`
*  `sudo mv jdk-10.0.2/ /usr/lib/jvm/`
*  `sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-10.0.2/bin/java 1`
*  `sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/jdk-10.0.2/bin/javac 1`
*  `sudo update-alternatives --config java`
*  `sudo update-alternatives --config javac`

Maintenant il faut changer la localisation de $JAVA_HOME
*  `sudo vim /etc/environment`

coller à la fin du fichier 
**$JAVA_HOME="/usr/lib/jvm/jdk-10.0.2"**

Ensuite pour finir avec java :
*  `source /etc/environment`

## Etape 2 : Maven

Verifiez que vous avez bien la version 3.6.3 avec la ligne de commande suivante :
*  `mvn -version`

 sinon faites :
*  `sudo apt install maven`

Si vous ètes sous MacOs lancez maven avec la commande:
*  `source ~/.bash_profile`

## Etape 3 : Instruction de compilation et d'exécution

Aller dans le dossier calculatrice et exécutez le fichier run
*  `./run`

## Implementation 

Notre calculatrice a les implementations suivantes :

* Syntaxe polonaise inversee, fonctionne operandes/operateurs sur chaque ligne par exemple
    * \> 3
    * 3
    * \> 4
    * 4
    * \> +
    * 7
* L'extension 2 : Syntaxe algebrique fonctionne ligne par ligne :
    * \> (2 * 3) + (4 * 5)
    * 26
* l'Extension 3 : rappels de valeurs avec les commandes `hist`, `pile`, `!x` et `?x`
* l'Extension 4 : calculatrice symbolique avec les commandes `$x` et `subst`
