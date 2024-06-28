# Price-Service
Microservice that efficiently manages pricing information for products in an e-commerce platform.

# Technical approach
## Overview:
This application follows the principles of the hexagonal architecture, also known as ports and adapters, with this pattern the application is organized in distinct layers, making it highly modular and testable.

## Architecture:
The application is structure with the hexagonal architecture pattern. 

* *Core/Application Layer:* Contains the domain logic and business rules of the application. This layer is isolated from external concerns.
* *Adapter Layer:* Adapts external interfaces (such as controllers, repositories, or external services) to the ports defined in the core layer.

# Technologies used:
* *Spring Boot:* For rapid application development and dependency injection.
* *Spring Data JPA:* For database access and ORM.
* *H2 Database:* H2 in memory database for development and testing purposes.
* *JUnit 5:* For unit and integration tests.
* *Mockito:* For mocking dependencies in tests.
* *Lombok:* To reduce boilerplate code.
* *MapStruct:* To avoid writing boilerplate mapping code manually, reducing errors and saving development time.
* *Spring boot Actuator:* To monitor the health and performance of the application.
* *OpenApi:* For describing and documenting RESTFul APIs.
* *Spring MockMvc:* To write tests to validate the behavior of the controllers.
* *Docker:* To build, distribute, and run applications in containers

# Design pattern and best practices:
* *Ports and Adapters:* Hexagonal architecture pattern to decouple business logic from external concerns.
* *Dependency injection:* For managing component dependencies.
* *Domain-Driven Design(DDD):* Organizing the cover layer based on DDD principles to encapsulate domain logic.
* *Separation of concerns:* Clear separation between logic, application and infrastructure.
* *JPA syntax:* Use JPA syntax instead of @Query annotation can lead to more maintainable, type-safe, consistent codebase and code quality.

# Setup and Installation:
### Prerequisites 📋

- [Java Development Kit (JDK 17)](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)

### Run application

```
mvn spring-boot:run
```

### Run application with docker

```
mvn clean install
docker build -t price-service .
docker run -p 8080:8080 price-service
```

### Remove container & docker image

```
docker container ls
docker rm -f {CONTAINER_ID}
docker images
docker rmi {IMAGE_ID}
```

# URLs:
* [Swagger](http://localhost:8080/api/swagger-ui/index.html)
* [Actuator](http://localhost:8080/api/actuator/health)
* [H2Database](http://localhost:8080/api/h2-console)

## Endpoint(s)

#### Get prices by parameters:

Request :
```
curl --location --request GET 'localhost:8080/api/prices?brand_id=1&product_id=35455&application_date=2020-06-15T10:00:00Z'
```
Response:

```
{
    "id": 3,
    "priority": 1,
    "price": 30.5,
    "curr": "EUR",
    "brand_id": 1,
    "start_date": "2020-06-15T00:00:00",
    "end_date": "2020-06-15T11:00:00",
    "price_list": 3,
    "product_id": 35455
}
```