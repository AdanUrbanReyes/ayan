# movie-information

Microservice in charge of create and deliver movie information.

## Requirements

1. Java 8 or greater
2. Maven 3.8.1 or greater
3. eureka-server
4. config-server
5. spring cloud config server

## Building

### Using mvn command

mvn command from maven, must have MAVE_HOME environment variable setting up

`mvn clean package -P local`

> We are passing the profile to use; follow are the available:
> local : meant to use when want to execute the project on your own local environment

## Running

### Using java command

java command from java, must have JAVA_HOME environment variable setting up

`java -jar target/movie-information-1.0.0-local.jar`

> We are passing a relative path to the jar file; that is generated by building this project

## REST API's endpoints

1. `curl -X GET -v http://localhost:33001/api/v1.0.0/movies`
2. `curl -X GET -v http://localhost:33001/api/v1.0.0/movies/1`
3. `curl -X POST -v -H "Content-Type: application/json;charset=utf-8" -d '{"id":1,"name":"Jumper"}' http://localhost:33001/api/v1.0.0/movies`