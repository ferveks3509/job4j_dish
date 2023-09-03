FROM maven:4.0.0-openjdk-17
RUN mkdir job4j_dish
WORKDIR job4j_dish
COPY . .
RUN mvn package
CMD ["java", "-jar", "target/job4j_dish-0.0.1-SNAPSHOT"]