FROM openjdk:10
RUN apt update
RUN apt-get install -y maven
RUN mkdir /home/projet
COPY src/ /home/projet/src
COPY README.md /home/projet/README.md
COPY pom.xml /home/projet/pom.xml
COPY Calculatrice.iml /home/projet/Calculatrice.iml