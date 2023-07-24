FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app
COPY . .
#RUN ./gradlew bootJar


#FROM eclipse-temurin:17-jre-alpine as run
#WORKDIR /run
#COPY --from=build /app/build/libs/recipes-0.0.1-SNAPSHOT.jar recipes.jar
#EXPOSE 8080
#CMD java -jar recipes.jar
