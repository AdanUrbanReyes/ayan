# hello-world

The project basically just expose 2 endpoints which return the string Hello.

One cool feature about this project is that by excluding some dependencies on `pom.xml` file (using production profile)
and extending from `SpringBootServletInitializer class`
and overriding the `configure method`. This project could create a JAR or WAR file.

## Requirements

1. JAVA JDK 11 installed
2. JAVA_HOME environment variable
3. MAVEN 3.8.1 or latter
4. MAVEN_HOME environment variable

## How to build this project?

As this project was developed using maven, execute follow command for build it.

`mvn clean package -P local`

> Notice that we are passing the profile which will
> be use to build it. There are follow profiles
> 1. local; for generate a JAR file
> 2. production; for generate a WAR file

## How to run this project?

### Running using JAR file

As this project was developed using java language, 
execute follow command for run it.

`java -jar target/hello-world-1.0.0-local.jar`

### Running using WAR file

You could deploy this WAR file project over
any Web Server container that has support for
java.

## REST API endpoints

1. `curl -X GET http://localhost:33000/core/say-hello`
2. `curl -X POST -d '{ "name" : "Ayan" }' -H "Content-Type: application/json" http://localhost:33000/core/say-hello`

> As you can notice we are using curl command to consume
> the REST API's