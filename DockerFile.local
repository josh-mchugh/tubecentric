FROM tomcat:9.0.24-jdk8-openjdk

LABEL maintainer="Josh McHugh"
LABEL description="TubeCentric Web App"

# Clear default directory for tomcat installation
RUN bash -c 'rm -drf /usr/local/tomcat/webapps/*'

# Replace the server.xml
RUN bash -c 'rm /usr/local/tomcat/conf/server.xml'
ADD docker/server.xml /usr/local/tomcat/conf/server.xml

# Replace the web.xml
RUN bash -c 'rm /usr/local/tomcat/conf/web.xml'
ADD docker/web.xml /usr/local/tomcat/conf/web.xml

# Add the war file
ADD target/ROOT.war /usr/local/tomcat/webapps/ROOT.war
RUN bash -c 'chmod a+x /usr/local/tomcat/webapps/ROOT.war'

EXPOSE 8080