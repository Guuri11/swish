# Swish Backend: Spring Boot

Swish is a basketball scoreboard platform that allows users to manage Games, Players, Users, and
Teams. The backend of the platform is built using Spring Boot and provides a REST API with CRUD
operations for each of the resources.

## Features

- CRUD operations for Games, Players, Users, and Teams
- Authentication and Authorization
- Web Socket service to update the data during an in game match
- REST API documentation with OpenAPI/Swagger to update the data for an in-game match

## Getting started

- ```git clone https://github.com/Guuri11/swish.git```
- ```./mvnw spring-boot:run```
- Access the API documentation at http://localhost:8080/swagger-ui.html

## Dependencies

- Spring Boot
- Spring Security
- Spring Data JPA
- H2 Database
- WebSocket
- OpenAPI/Swagger