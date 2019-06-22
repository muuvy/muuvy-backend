FROM tomcat:latest

# Update Apt and then install Nano editor (RUN can be removed)
RUN apt-get update && apt-get install -y \
  nano \
  && mkdir -p /usr/local/tomcat/conf

# Copy configurations (Tomcat users, Manager app)
COPY tomcat-users.xml /usr/local/tomcat/conf/
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/

RUN mkdir /usr/local/tomcat/webapps/muuvy-backend

COPY /target/muuvy-backend-1.0.0.war /usr/local/tomcat/webapps/muuvy-backend.war
COPY /target/muuvy-backend-1.0.0/* /usr/local/tomcat/webapps/muuvy-backend/