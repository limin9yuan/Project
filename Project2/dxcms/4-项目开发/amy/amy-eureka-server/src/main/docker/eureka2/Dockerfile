FROM java:8
VOLUME /tmp
ADD amy-eureka-server-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8002
ENTRYPOINT ["java","-Dspring.profiles.active=eureka2","-jar","/app.jar"]