#FROM java:8
#EXPOSE 8080
#
#VOLUME /tmp
#ADD renren-fast.jar  /app.jar
#RUN bash -c 'touch /app.jar'
#ENTRYPOINT ["java","-jar","/app.jar"]
FROM java:8
EXPOSE 8080
VOLUME /slm
ADD sell.jar boot-docker.jar
RUN sh -c 'touch /boot-docker.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /boot-docker.jar" ]
