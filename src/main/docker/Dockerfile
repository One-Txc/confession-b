# Dockerfile for spring-boot-study
# Build with:
# docker build -t spring-boot-study .
# Run with:
# docker run -p 8080:8080 -d -v /tmp/logs/spring-boot-study:/opt/spring-boot-maven-docker/logs --network common-net --name spring-boot-study spring-boot-study
# clear container:
# docker container stop spring-boot-study
# docker container rm spring-boot-study

FROM openjdk:8-jre-alpine
ADD spring-boot-study-1.0.0.jar /opt/spring-boot-maven-docker/
EXPOSE 8080
WORKDIR /opt/spring-boot-maven-docker/
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar spring-boot-study-1.0.0.jar --spring.profiles.active=prod" ]
# CMD ["java", "-jar", "spring-boot-study-1.0.0.jar"]