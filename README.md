# Scored Backend

This is the Spring Boot backend for the [Scored Discord Bot](https://github.com/KevinVanthuyne/discord-competition-bot)
and Scored UI. It uses a PostgreSQL database to store all data.

## Setup

### Manual Setup

- Install Java SDK 17
- Rename `application-confidential.properties.example` to `application-confidential.properties` and fill in the necessary data
- Install PostgreSQL 14 on port 5432
    - Create a new `scored-db` database
    - Create a new user and fill in the credentials in `application-confidential.properties`
    - Grant all privileges of `scored-db` to the user (initially user to create all schemes)
- Run `ScoredBackendApplication.java`
- After the server has started, revoke all privileges of the `scored-db` user and grant `select, insert, delete, update`

### Docker Setup

Build the app with Maven (with skip tests):
```
maven clean package
```
Build the Docker image:
```
docker build -t kevinvt/scored-backend .
```
Use Docker Compose to run [scored-docker-compose](https://github.com/KevinVanthuyne/scored-docker-compose) and run the entire application

## Docker Hub

Push the image to Docker Hub:
```
docker push kevinvt/scored-backend:latest
```
