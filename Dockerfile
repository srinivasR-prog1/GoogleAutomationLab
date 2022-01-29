FROM openjdk:8-jdk-alpine
RUN apk add curl  jq
WORKDIR  /Users/tsrin/workspace/GoogleAutomationLab
COPY target/selenium-docker.jar selenium-docker.jar
COPY target/selenium-docker-tests.jar selenium-docker-tests.jar
COPY target/libs libs
COPY testng.xml testng.xml
#ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -Dbrowser=$browser -DHUB_HOST=$HUB_HOST org.testng.TestNG  $MODULE
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh
ENTRYPOINT sh healthcheck.sh