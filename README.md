# Scored Backend

This is the Spring Boot backend for the [Scored Discord Bot](https://github.com/KevinVanthuyne/discord-competition-bot)
and Scored UI. It uses a PostgreSQL database to store all data.

## Setup

- Install Java SDK 17
- Rename `application-confidential.properties.example` to `application-confidential.properties`
- Install PostgreSQL 14 on port 5432
    - Create a new `scored-db` database
    - Create a new user and fill in the credentials in `application-confidential.properties`
    - Grant all privileges of `scored-db` to the user (initially user to create all schemes)
- Run `ScoredBackendApplication.java`
- After the server has started, revoke all privileges of the `scored-db` user and grant `select, insert, delete, update`

## Docker Setup

- Run `maven clean package` (with skip tests) to build the app 
- Run `mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)` to extract dependencies to a separate folder
- Run `docker build -t kevinvt/scored-backend .` to build image
