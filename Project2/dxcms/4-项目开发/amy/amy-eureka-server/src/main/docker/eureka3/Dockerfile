FROM java:8
VOLUME /tmp
ADD amy-eureka-server-1.0-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8003
ENTRYPOINT ["java","-Dspring.profiles.active=eureka3","-jar","/app.jar"]