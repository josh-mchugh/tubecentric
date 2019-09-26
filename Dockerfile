# https://dzone.com/articles/spring-boot-run-and-build-in-docker

#
# Build stage
#
FROM maven:3.6.2-jdk-11-slim AS build
LABEL maintainer="Josh McHugh"
LABEL description="TubeCentric Maven Build"
COPY src/ /src
COPY frontend/ /frontend
COPY pom.xml /
COPY docker/server.xml docker/server.xml
COPY docker/web.xml docker/web.xml
RUN mvn -B dependency:resolve dependency:resolve-plugins
RUN mvn clean install

#
# Package stage
#
FROM tomcat:9.0.26-jdk11-openjdk-slim as runtime
LABEL maintainer="Josh McHugh"
LABEL description="TubeCentric Tomcat Runtime"
WORKDIR /
# Clear default directory for tomcat installation
RUN bash -c 'rm -drf /usr/local/tomcat/webapps/*'
# Replace the server.xml
RUN bash -c 'rm /usr/local/tomcat/conf/server.xml'
COPY --from=build docker/server.xml /usr/local/tomcat/conf/server.xml
# Replace the web.xml
RUN bash -c 'rm /usr/local/tomcat/conf/web.xml'
COPY --from=build docker/web.xml /usr/local/tomcat/conf/web.xml
# Add the war file
COPY --from=build target/ROOT.war /usr/local/tomcat/webapps/ROOT.war
RUN bash -c 'chmod a+x /usr/local/tomcat/webapps/ROOT.war'
EXPOSE 8080
CMD ["catalina.sh", "run"]