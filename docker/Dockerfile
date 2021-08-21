FROM openjdk:11
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN apt update
CMD ["java", "-jar" ,"app.jar"]
