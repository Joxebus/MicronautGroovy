# MicronautGroovy

This example contains an example with Micronaut, Groovy and GORM

Groovy has been created to be companion with Java so most of the Java code is valid for Groovy.

If you are using IntelliJIDEa open the project and select the options:

- Use auto-import
- Create directories for empty content roots automatically
- Create separate module per source set

# Project structure

```
├── Dockerfile
├── README.md
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── micronaut-cli.yml
└── src
    └── main
        ├── groovy
        │   └── com
        │       └── nearsoft
        │           └── micronaut
        │               ├── Application.groovy
        │               ├── api
        │               │   └── PersonApi.groovy
        │               ├── client
        │               │   └── PersonClient.groovy
        │               ├── controller
        │               │   └── PersonController.groovy
        │               ├── domain
        │               │   └── Person.groovy
        │               ├── init
        │               │   └── Bootstrap.groovy
        │               └── service
        │                   └── PersonService.groovy
        └── resources
            ├── application.yml
            └── logback.xml

```


## Requirements

- Java 8
- Gradle 4.x

## Run App

For MacOS and Linux

`` ./gradlew run ``

or for Windows

`` gradlew.bat run``


## Endpoint

`` localhost:8080/people ``

```
GET /people/
GET /people/{id}
POST /people/
PUT /people/
DELETE /people/{id}
```


## JSON Example

```
{
    "name" : "Omar",
    "lastName": "Bautista",
    "age" : 31,
    "phone" : "523-124-8234"
}
```

Take a look to Micronaut project at: http://guides.micronaut.io/creating-your-first-micronaut-app-groovy/guide/index.html
