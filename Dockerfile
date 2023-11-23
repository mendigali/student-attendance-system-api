FROM adoptopenjdk/openjdk11:latest

WORKDIR /app

COPY mvnw .

COPY .mvn .mvn

COPY pom.xml .

COPY src src

RUN ./mvnw install -Pprod -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/student-attendance-system-api.jar"]
