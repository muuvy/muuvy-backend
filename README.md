# muuvy rest-api

[![Build Status](https://travis-ci.org/muuvy/muuvy-backend.svg?branch=master)](https://travis-ci.org/muuvy/muuvy-backend)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=muuvy_app-backend&metric=alert_status)](https://sonarcloud.io/dashboard?id=muuvy_app-backend)

## Defintions

* Throntail Version: `2.4.0.Final`
* Config files in `YAML` format

## Project Setup

### Requirements

* Install Maven 3.3+ from [here](https://maven.apache.org/guides/getting-started/)
* Start docker-compose `mongo db` using these [steps](https://github.com/muuvy/database)

To install the dependencies, first run `mvn install`. Run the application with this command:

``` bash
$ mvn thorntai:run
Thorntail is ready
```

Verify if the server is up and running, by visit this [url](http://localhost:8080/api/user)

## Configuration

All thorntail configurations are located in `src/main/resources/project-local.yml`. Each environment has its own config file.

## Components

### Thorntail

* JAX RS
* LOG
* CDI

### JBoss

* Logger

### Misc

* Lombok
* Junit Jupiter
* Mongo Java Driver
* Hibernate OGM Mongo
