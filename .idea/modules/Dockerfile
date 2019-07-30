FROM tomcat:8.5-jre8
WORKDIR ./
RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY ./out/artifacts/Ara_Server_jar/Ara-Server.jar /usr/local/tomcat/webapps/ROOT.war
