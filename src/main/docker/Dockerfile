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




FROM openjdk:8-jre-alpine
ADD confession-b-1.0.0.jar confession-app.jar
EXPOSE 8809 8809
ENV JAVA_OPTS="-Dapp.id=confession -DsystemCode=confession -Dfile.encoding=UTF8 -Duser.timezone=GMT+08"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar confession-app.jar --spring.profiles.active=prod" ]




FROM registry.alauda.cn/library/java:8-jdk-alpine
MAINTAINER author:tangxiaocheng email:1273586908@qq.com
COPY confession-b-1.0.0.jar confession-app.jar
EXPOSE 8809 8809
CMD ["java","-Dapp.id=confession","-DsystemCode=confession","-Dfile.encoding=UTF8","-Duser.timezone=GMT+08", "-jar" ,"confession-app.jar"]