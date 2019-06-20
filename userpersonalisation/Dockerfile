FROM java:8-jdk-alpine

COPY ./target/userpersonalisation-0.0.1-SNAPSHOT.jar /usr/app/

COPY ./src/main/resources/clicks.csv /user/app/src/main/resources/

COPY ./src/main/resources/selections.csv /user/app/src/main/resources/

WORKDIR /usr/app

RUN sh -c 'touch userpersonalisation-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","userpersonalisation-0.0.1-SNAPSHOT.jar"]