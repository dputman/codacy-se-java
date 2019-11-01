# Codacy SE Challenge

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5535167864f54c8387df05d55df077e3)](https://www.codacy.com/manual/putman.dan/codacy-se-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=dputman/codacy-se-java&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/dputman/codacy-se-java.svg?branch=master)](https://travis-ci.org/dputman/codacy-se-java)

A challenge for SE candidates at Codacy

## Requirements

*  Java 11
*  PostgreSQL (Tested against Postgres 12)

## Setup

Let's assume that Java 11 is taken care of.
If Postgres is not already installed on your machine, I recommend using a Docker image. The following command will pull down the latest image and bind the standard Postgres port to localhost.

```
docker run -p 5432:5432 --name codacy-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres
```

Once the Postgres container is running, you'll need to run the create script in "src/main/resources". These two commands assume you're in the root of the project.

```
docker cp src/main/resources/create.psql codacy-postgres:/create.psql
docker exec codacy-postgres /bin/sh -c 'psql -U postgres </create.psql'
```

## Run

Once Postgres is up and running, you will be able to run tests and launch the api using Gradle.

### Running the tests

```
./gradlew test
```

### Starting the API

```
./gradlew bootRun
```