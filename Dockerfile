FROM java:8
VOLUME /tmp
ADD bullshit-app-*.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar", "-Dspring.profiles.active=docker"]