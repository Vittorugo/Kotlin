FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD /target/ForumKotlin-0.0.1-SNAPSHOT.jar forum.jar
ENTRYPOINT ["java","-jar","forum.jar"]