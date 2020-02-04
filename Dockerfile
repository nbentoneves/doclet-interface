# Alpine Linux with OpenJDK JRE
FROM azul/zulu-openjdk:8

ARG JAR_FILE=target/*.jar
ARG SOURCE_CONFIG_FILE_PATH
ENV SOURCE_CONFIG_FILE=${SOURCE_CONFIG_FILE_PATH}

# copy WAR into image
COPY ${JAR_FILE} /app.jar

# run application with this command line
CMD ["/usr/bin/java", "-jar", "-Dsource.config.file=${SOURCE_CONFIG_FILE}", "-Dspring.profiles.active=default", "/app.jar"]