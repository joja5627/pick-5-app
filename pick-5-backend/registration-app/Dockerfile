FROM openjdk:10-jre-slim
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EXPOSE 8003