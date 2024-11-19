# Agon Backend

The Spring Boot backend for the Agon competition application. It provides a REST API to interact with it and is mainly
used to persist all user, score and game data in a PostgreSQL database.

For an overview of all Agon components take a look at
the [Agon Docker Compose repo](https://github.com/KevinVanthuyne/agon-docker-compose).

## Setup

### Manual Setup

- Install Java SDK 17
- Rename `application-confidential.properties.example` to `application-confidential.properties` and fill in the
  necessary data
- Install PostgreSQL 14 on port 5432
    - Create a new `agon-db` database
    - Create a new user and fill in the credentials in `application-confidential.properties`
    - Grant all privileges of `agon-db` to the user (initially user to create all schemes)
- Run `AgonBackendApplication.java`
- After the server has started, revoke all privileges of the `agon-db` user and grant `select, insert, delete, update`

### Docker Setup

#### With Compose

With Compose, all applications will be run at the same time from Docker images.

Build the app with Maven (with skip tests):

```shell
maven clean package
```

Build the Docker image:

```shell
docker build -t kevinvt/agon-backend .
```

Use Docker Compose to run [agon-docker-compose](https://github.com/KevinVanthuyne/agon-docker-compose) and run the
entire application.

#### Without Compose

There's a Dockerfile in the `agon-docker-compose` repo for development that will spin up a PostgreSQL database so
there's no need to install it locally.

### Seeding the database

If you want some initial data in the database, you can include a `data.sql` file in the `src/main/resources` folder and
run the application with the `seed-db` profile. This will recreate all tables from scratch and insert the data from the
SQL file into the database.

## Docker Hub

Push the image to Docker Hub:

```shell
docker push kevinvt/agon-backend:latest
```
