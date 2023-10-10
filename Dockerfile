FROM eclipse-temurin:17-jdk

ENV TZ=Europe/Warsaw
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apt update && \
    apt upgrade -y && \
    apt install -y maven git && \
    mkdir /code && \
    cd /code && \
    git clone https://github.com/MaciejakMateusz/Zadanie_rekrutacyjne_CL.git . && \
    mvn package && \
    mkdir /opt/app && \
    mv target/Zadanie_rekrutacyjne_CL-0.0.1-SNAPSHOT.war /opt/app/quiz-webapp.war && \
    cd && \
    rm -r /code && \
    apt remove -y git maven && \
    apt clean && \
    apt autoremove --purge -y

EXPOSE 8080

WORKDIR /opt/app

CMD java -jar /opt/app/quiz-webapp.war