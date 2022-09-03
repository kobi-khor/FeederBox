FROM openjdk:17
ADD target/FeederService.jar FeederService.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","FeederService.jar"]